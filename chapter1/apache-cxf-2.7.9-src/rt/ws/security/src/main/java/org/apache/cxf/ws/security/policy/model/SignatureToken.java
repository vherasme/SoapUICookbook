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
package org.apache.cxf.ws.security.policy.model;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.cxf.ws.policy.PolicyBuilder;
import org.apache.cxf.ws.security.policy.SP12Constants;
import org.apache.cxf.ws.security.policy.SPConstants;

public class SignatureToken extends TokenWrapper {

    public SignatureToken(SPConstants version, PolicyBuilder b) {
        super(version, b);
    }

    /**
     * @return Returns the signatureToken.
     */
    public Token getSignatureToken() {
        return getToken();
    }
    /**
     * @param signatureToken The signatureToken to set.
     */
    public void setSignatureToken(Token signatureToken) {
        setToken(signatureToken);
    }


    public QName getRealName() {
        return constants.getSignatureToken();
    }
    public QName getName() {
        return SP12Constants.INSTANCE.getSignatureToken();
    }

    public void serialize(XMLStreamWriter writer) throws XMLStreamException {

        String localname = getRealName().getLocalPart();
        String namespaceURI = getRealName().getNamespaceURI();

        String prefix;
        String writerPrefix = writer.getPrefix(namespaceURI);

        if (writerPrefix == null) {
            prefix = getRealName().getPrefix();
            writer.setPrefix(prefix, namespaceURI);

        } else {
            prefix = writerPrefix;
        }

        // <sp:SignatureToken>
        writer.writeStartElement(prefix, localname, namespaceURI);

        if (writerPrefix == null) {
            // xmlns:sp=".."
            writer.writeNamespace(prefix, namespaceURI);
        }

        String wspNamespaceURI = SPConstants.POLICY.getNamespaceURI();

        String wspPrefix;

        String wspWriterPrefix = writer.getPrefix(wspNamespaceURI);

        if (wspWriterPrefix == null) {
            wspPrefix = SPConstants.POLICY.getPrefix();
            writer.setPrefix(wspPrefix, wspNamespaceURI);

        } else {
            wspPrefix = wspWriterPrefix;
        }

        // <wsp:Policy>
        writer.writeStartElement(wspPrefix, SPConstants.POLICY.getLocalPart(), wspNamespaceURI);

        if (wspWriterPrefix == null) {
            // xmlns:wsp=".."
            writer.writeNamespace(wspPrefix, wspNamespaceURI);
        }

        if (token == null) {
            throw new RuntimeException("EncryptionToken is not set");
        }

        token.serialize(writer);

        // </wsp:Policy>
        writer.writeEndElement();

        // </sp:SignatureToken>
        writer.writeEndElement();
    }
}
