/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cxf.ws.security.wss4j;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;

import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.helpers.MapNamespaceContext;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.security.wss4j.CryptoCoverageUtil.CoverageScope;
import org.apache.cxf.ws.security.wss4j.CryptoCoverageUtil.CoverageType;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSDataRef;
import org.apache.ws.security.WSSecurityEngineResult;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.apache.ws.security.handler.WSHandlerResult;

/**
 * Utility to enable the checking of WS-Security signature/encryption
 * coverage based on the results of the WSS4J processors.  This interceptor
 * provides an alternative to using WS-Policy based configuration for crypto
 * coverage enforcement.
 * <p/>
 * Note that the processor must properly address the Security Token
 * Reference Dereference transform in the case of a signed security token
 * such as a SAML assertion.  Consequently, a version of WSS4J that properly
 * addresses this transform must be used with this utility if you wish to 
 * check coverage over a message part referenced through the Security Token
 * Reference Dereference transform.
 * See <a href="https://issues.apache.org/jira/browse/WSS-222">WSS-222</a>
 * for more details.
 */
public class CryptoCoverageChecker extends AbstractSoapInterceptor {
    
    /**
     * The XPath expressions for locating elements in SOAP messages
     * that must be covered.  See {@link #prefixMap}
     * for namespace prefixes available.
     */
    protected List<XPathExpression> xPaths = new ArrayList<XPathExpression>();
    
    /**
     * Mapping of namespace prefixes to namespace URIs.
     */
    protected Map<String, String> prefixMap = new HashMap<String, String>();
    
    private boolean checkFaults = true;
    
    /**
     * Creates a new instance.  See {@link #setPrefixes()} and {@link #setXpaths()}
     * for providing configuration options.
     */
    public CryptoCoverageChecker() {
        this(null, null);
    }
    
    /**
     * Creates a new instance that checks for signature coverage over matches to
     * the provided XPath expressions making defensive copies of provided arguments.
     * 
     * @param prefixes
     *            mapping of namespace prefixes to namespace URIs
     * @param xPaths
     *            a list of XPath expressions
     */
    public CryptoCoverageChecker(Map<String, String> prefixes, List<XPathExpression> xPaths)
    {
        super(Phase.PRE_PROTOCOL);
        this.addAfter(WSS4JInInterceptor.class.getName());
        this.setPrefixes(prefixes);
        this.setXPaths(xPaths);
    }

    /**
     * Checks that the WSS4J results refer to the required signed/encrypted
     * elements as defined by the XPath expressions in {@link #xPaths}.
     * 
     * @param message
     *            the SOAP message containing the signature
     * 
     * @throws SoapFault
     *             if there is an error evaluating an XPath or an element is not
     *             covered by the required cryptographic operation
     */
    public void handleMessage(SoapMessage message) throws Fault {
        if (this.xPaths == null || this.xPaths.isEmpty()) {
            // return
        }
        
        Element documentElement = null;
        try {
            SOAPMessage saajDoc = message.getContent(SOAPMessage.class);
            SOAPEnvelope envelope = saajDoc.getSOAPPart().getEnvelope();
            if (!checkFaults && envelope.getBody().hasFault()) {
                return;
            }
            documentElement = envelope;
        } catch (SOAPException e) {
            throw new SoapFault("Error obtaining SOAP document", Fault.FAULT_CODE_CLIENT);
        }
        
        final Collection<WSDataRef> signed = new HashSet<WSDataRef>();
        final Collection<WSDataRef> encrypted = new HashSet<WSDataRef>();
        
        List<WSHandlerResult> results = CastUtils.cast(
                (List<?>) message.get(WSHandlerConstants.RECV_RESULTS));
        
        for (final WSHandlerResult wshr : results) {
            final List<WSSecurityEngineResult> wsSecurityEngineSignResults = 
                WSS4JUtils.fetchAllActionResults(wshr.getResults(), WSConstants.SIGN);
            
            final List<WSSecurityEngineResult> wsSecurityEngineEncResults = 
                WSS4JUtils.fetchAllActionResults(wshr.getResults(), WSConstants.ENCR);
            
            for (WSSecurityEngineResult wser : wsSecurityEngineSignResults) {
            
                List<WSDataRef> sl = CastUtils.cast((List<?>) wser
                        .get(WSSecurityEngineResult.TAG_DATA_REF_URIS));
                if (sl != null) {
                    if (sl.size() == 1
                        && sl.get(0).getName().equals(new QName(WSConstants.SIG_NS, WSConstants.SIG_LN))) {
                        //endorsing the signature so don't include
                        break;
                    }
                    
                    for (WSDataRef r : sl) {
                        signed.add(r);
                    }
                }
            }
            
            for (WSSecurityEngineResult wser : wsSecurityEngineEncResults) {
                List<WSDataRef> el = CastUtils.cast((List<?>) wser
                        .get(WSSecurityEngineResult.TAG_DATA_REF_URIS));

                if (el != null) {
                    for (WSDataRef r : el) {
                        encrypted.add(r);
                    }
                }
            }
        }
        
        CryptoCoverageUtil.reconcileEncryptedSignedRefs(signed, encrypted);

        // XPathFactory and XPath are not thread-safe so we must recreate them
        // each request.
        final XPathFactory factory = XPathFactory.newInstance();
        final XPath xpath = factory.newXPath();

        if (this.prefixMap != null) {
            xpath.setNamespaceContext(new MapNamespaceContext(this.prefixMap));
        }

        for (XPathExpression xPathExpression : this.xPaths) {
            Collection<WSDataRef> refsToCheck = null;

            switch (xPathExpression.getType()) {
            case SIGNED:
                refsToCheck = signed;
                break;
            case ENCRYPTED:
                refsToCheck = encrypted;
                break;
            default:
                throw new IllegalStateException("Unexpected crypto type: " 
                    + xPathExpression.getType());
            }

            try {
                CryptoCoverageUtil.checkCoverage(
                                                 documentElement,
                                                 refsToCheck,
                                                 xpath, 
                                                 Arrays.asList(xPathExpression.getXPath()),
                                                 xPathExpression.getType(),
                                                 xPathExpression.getScope());
            } catch (WSSecurityException e) {
                throw new SoapFault("No " + xPathExpression.getType()
                                    + " element found matching XPath "
                                    + xPathExpression.getXPath(), Fault.FAULT_CODE_CLIENT);
            }
        }
    }

    /**
     * Sets the XPath expressions to check for, clearing all previously
     * set expressions.
     *
     * @param xPaths the XPath expressions to check for
     */
    public final void setXPaths(List<XPathExpression> xpaths) {
        this.xPaths.clear();
        if (xpaths != null) {
            this.xPaths.addAll(xpaths);
        }
    }
    
    /**
     * Adds the XPath expressions to check for, adding to any previously
     * set expressions.
     *
     * @param xPaths the XPath expressions to check for
     */
    public final void addXPaths(List<XPathExpression> xpaths) {
        if (xpaths != null) {
            this.xPaths.addAll(xpaths);
        }
    }

    /**
     * Sets the mapping of namespace prefixes to namespace URIs, clearing all previously
     * set mappings.
     *
     * @param prefixes the mapping of namespace prefixes to namespace URIs
     */
    public final void setPrefixes(Map<String, String> prefixes) {
        this.prefixMap.clear();
        if (prefixes != null) {
            this.prefixMap.putAll(prefixes);
        }
    }
    
    /**
     * Adds the mapping of namespace prefixes to namespace URIs, adding to any previously
     * set mappings.
     *
     * @param prefixes the mapping of namespace prefixes to namespace URIs
     */
    public final void addPrefixes(Map<String, String> prefixes) {
        if (prefixes != null) {
            this.prefixMap.putAll(prefixes);
        }
    }

    public boolean isCheckFaults() {
        return checkFaults;
    }

    public void setCheckFaults(boolean checkFaults) {
        this.checkFaults = checkFaults;
    }

    /**
     * A simple wrapper for an XPath expression and coverage type / scope
     * indicating how the XPath expression should be enforced as a cryptographic
     * coverage requirement.
     */
    public static class XPathExpression {
        
        /**
         * The XPath expression.
         */
        private final String xPath;
        
        /**
         * The type of coverage that is being enforced.
         */
        private final CoverageType type;
        
        /**
         * The scope of the coverage that is being enforced.
         */
        private final CoverageScope scope;

        /**
         * Create a new expression indicating a cryptographic coverage
         * requirement with {@code scope} {@link CoverageScope#ELEMENT}.
         * 
         * @param xPath
         *            the XPath expression
         * @param type
         *            the type of coverage that the expression is meant to
         *            enforce
         * 
         * @throws NullPointerException
         *             if {@code xPath} or {@code type} is {@code null}
         */
        public XPathExpression(String xPath, CoverageType type) {
            this(xPath, type, CoverageScope.ELEMENT);
        }
        
        /**
         * Create a new expression indicating a cryptographic coverage
         * requirement. If {@code type} is {@link CoverageType#SIGNED}, the
         * {@code scope} {@link CoverageScope#CONTENT} does not represent a
         * configuration supported in WS-Security.
         * 
         * @param xPath
         *            the XPath expression
         * @param type
         *            the type of coverage that the expression is meant to
         *            enforce
         * @param scope
         *            the scope of coverage that the expression is meant to
         *            enforce, defaults to {@link CoverageScope#ELEMENT}
         * 
         * @throws NullPointerException
         *             if {@code xPath} or {@code type} is {@code null}
         */
        public XPathExpression(String xPath, CoverageType type, CoverageScope scope) {
            if (xPath == null) {
                throw new NullPointerException("xPath cannot be null.");
            } else if (type == null) {
                throw new NullPointerException("type cannot be null.");
            }
            
            this.xPath = xPath;
            this.type = type;
            this.scope = scope;
        }

        /**
         * Returns the XPath expression.
         * @return the XPath expression
         */
        public String getXPath() {
            return this.xPath;
        }

        /**
         * Returns the coverage type.
         * @return the coverage type
         */
        public CoverageType getType() {
            return this.type;
        }

        /**
         * Returns the coverage scope.
         * @return the coverage scope
         */
        public CoverageScope getScope() {
            return this.scope;
        }
        
        @Override
        public boolean equals(Object xpathObject) {
            if (!(xpathObject instanceof XPathExpression)) {
                return false;
            }
            
            if (xpathObject == this) {
                return true;
            }
            
            XPathExpression xpath = (XPathExpression)xpathObject;
            if (xpath.getScope() != getScope()) {
                return false;
            }
            
            if (xpath.getType() != getType()) {
                return false;
            }
            
            if (getXPath() == null && xpath.getXPath() != null) {
                return false;
            } else if (getXPath() != null && !getXPath().equals(xpath.getXPath())) {
                return false;
            }
            
            return true;
        }
        
        @Override
        public int hashCode() {
            int result = 17;
            if (getXPath() != null) {
                result = 31 * result + getXPath().hashCode();
            }
            if (getType() != null) {
                result = 31 * result + getType().hashCode();
            }
            if (getScope() != null) {
                result = 31 * result + getScope().hashCode();
            }
            return result;
        }
    }
}
