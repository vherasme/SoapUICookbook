//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.22 at 09:56:33 p. m. CEST 
//


package org.apache.cxf.configuration.security;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TLSClientParametersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TLSClientParametersType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="keyManagers" type="{http://cxf.apache.org/configuration/security}KeyManagersType" minOccurs="0"/>
 *         &lt;element name="trustManagers" type="{http://cxf.apache.org/configuration/security}TrustManagersType" minOccurs="0"/>
 *         &lt;element name="cipherSuites" type="{http://cxf.apache.org/configuration/security}CipherSuites" minOccurs="0"/>
 *         &lt;element name="cipherSuitesFilter" type="{http://cxf.apache.org/configuration/security}FiltersType" minOccurs="0"/>
 *         &lt;element name="secureRandomParameters" type="{http://cxf.apache.org/configuration/security}SecureRandomParameters" minOccurs="0"/>
 *         &lt;element name="certConstraints" type="{http://cxf.apache.org/configuration/security}CertificateConstraintsType" minOccurs="0"/>
 *         &lt;element name="certAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *       &lt;attribute name="useHttpsURLConnectionDefaultSslSocketFactory" type="{http://cxf.apache.org/configuration/parameterized-types}ParameterizedBoolean" default="false" />
 *       &lt;attribute name="useHttpsURLConnectionDefaultHostnameVerifier" type="{http://cxf.apache.org/configuration/parameterized-types}ParameterizedBoolean" default="false" />
 *       &lt;attribute name="disableCNCheck" type="{http://cxf.apache.org/configuration/parameterized-types}ParameterizedBoolean" default="false" />
 *       &lt;attribute name="jsseProvider" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="secureSocketProtocol" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sslCacheTimeout" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TLSClientParametersType", namespace = "http://cxf.apache.org/configuration/security", propOrder = {

})
public class TLSClientParametersType {

    @XmlElement(namespace = "http://cxf.apache.org/configuration/security")
    protected KeyManagersType keyManagers;
    @XmlElement(namespace = "http://cxf.apache.org/configuration/security")
    protected TrustManagersType trustManagers;
    @XmlElement(namespace = "http://cxf.apache.org/configuration/security")
    protected CipherSuites cipherSuites;
    @XmlElement(namespace = "http://cxf.apache.org/configuration/security")
    protected FiltersType cipherSuitesFilter;
    @XmlElement(namespace = "http://cxf.apache.org/configuration/security")
    protected SecureRandomParameters secureRandomParameters;
    @XmlElement(namespace = "http://cxf.apache.org/configuration/security")
    protected CertificateConstraintsType certConstraints;
    @XmlElement(namespace = "http://cxf.apache.org/configuration/security")
    protected String certAlias;
    @XmlAttribute(name = "useHttpsURLConnectionDefaultSslSocketFactory")
    protected String useHttpsURLConnectionDefaultSslSocketFactory;
    @XmlAttribute(name = "useHttpsURLConnectionDefaultHostnameVerifier")
    protected String useHttpsURLConnectionDefaultHostnameVerifier;
    @XmlAttribute(name = "disableCNCheck")
    protected String disableCNCheck;
    @XmlAttribute(name = "jsseProvider")
    protected String jsseProvider;
    @XmlAttribute(name = "secureSocketProtocol")
    protected String secureSocketProtocol;
    @XmlAttribute(name = "sslCacheTimeout")
    protected Integer sslCacheTimeout;

    /**
     * Gets the value of the keyManagers property.
     * 
     * @return
     *     possible object is
     *     {@link KeyManagersType }
     *     
     */
    public KeyManagersType getKeyManagers() {
        return keyManagers;
    }

    /**
     * Sets the value of the keyManagers property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyManagersType }
     *     
     */
    public void setKeyManagers(KeyManagersType value) {
        this.keyManagers = value;
    }

    /**
     * Gets the value of the trustManagers property.
     * 
     * @return
     *     possible object is
     *     {@link TrustManagersType }
     *     
     */
    public TrustManagersType getTrustManagers() {
        return trustManagers;
    }

    /**
     * Sets the value of the trustManagers property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrustManagersType }
     *     
     */
    public void setTrustManagers(TrustManagersType value) {
        this.trustManagers = value;
    }

    /**
     * Gets the value of the cipherSuites property.
     * 
     * @return
     *     possible object is
     *     {@link CipherSuites }
     *     
     */
    public CipherSuites getCipherSuites() {
        return cipherSuites;
    }

    /**
     * Sets the value of the cipherSuites property.
     * 
     * @param value
     *     allowed object is
     *     {@link CipherSuites }
     *     
     */
    public void setCipherSuites(CipherSuites value) {
        this.cipherSuites = value;
    }

    /**
     * Gets the value of the cipherSuitesFilter property.
     * 
     * @return
     *     possible object is
     *     {@link FiltersType }
     *     
     */
    public FiltersType getCipherSuitesFilter() {
        return cipherSuitesFilter;
    }

    /**
     * Sets the value of the cipherSuitesFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltersType }
     *     
     */
    public void setCipherSuitesFilter(FiltersType value) {
        this.cipherSuitesFilter = value;
    }

    /**
     * Gets the value of the secureRandomParameters property.
     * 
     * @return
     *     possible object is
     *     {@link SecureRandomParameters }
     *     
     */
    public SecureRandomParameters getSecureRandomParameters() {
        return secureRandomParameters;
    }

    /**
     * Sets the value of the secureRandomParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecureRandomParameters }
     *     
     */
    public void setSecureRandomParameters(SecureRandomParameters value) {
        this.secureRandomParameters = value;
    }

    /**
     * Gets the value of the certConstraints property.
     * 
     * @return
     *     possible object is
     *     {@link CertificateConstraintsType }
     *     
     */
    public CertificateConstraintsType getCertConstraints() {
        return certConstraints;
    }

    /**
     * Sets the value of the certConstraints property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateConstraintsType }
     *     
     */
    public void setCertConstraints(CertificateConstraintsType value) {
        this.certConstraints = value;
    }

    /**
     * Gets the value of the certAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertAlias() {
        return certAlias;
    }

    /**
     * Sets the value of the certAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertAlias(String value) {
        this.certAlias = value;
    }

    /**
     * Gets the value of the jsseProvider property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJsseProvider() {
        return jsseProvider;
    }

    /**
     * Sets the value of the jsseProvider property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJsseProvider(String value) {
        this.jsseProvider = value;
    }

    /**
     * Gets the value of the secureSocketProtocol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecureSocketProtocol() {
        return secureSocketProtocol;
    }

    /**
     * Sets the value of the secureSocketProtocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecureSocketProtocol(String value) {
        this.secureSocketProtocol = value;
    }

    /**
     * Gets the value of the sslCacheTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSslCacheTimeout() {
        return sslCacheTimeout;
    }

    /**
     * Sets the value of the sslCacheTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSslCacheTimeout(Integer value) {
        this.sslCacheTimeout = value;
    }

    /**
     * Sets the value of the useHttpsURLConnectionDefaultSslSocketFactory property.
     * 
     */
    public void setUseHttpsURLConnectionDefaultSslSocketFactory(String value) {
        useHttpsURLConnectionDefaultSslSocketFactory = value;
    }

    public void unsetUseHttpsURLConnectionDefaultSslSocketFactory() {
        useHttpsURLConnectionDefaultSslSocketFactory = null;
    }

    /**
     * Gets the value of the useHttpsURLConnectionDefaultSslSocketFactory property.
     * 
     */
    public String getUseHttpsURLConnectionDefaultSslSocketFactory() {
        if (null == useHttpsURLConnectionDefaultSslSocketFactory) {
            return "false";
        }
        return useHttpsURLConnectionDefaultSslSocketFactory;
    }

    /**
     * Sets the value of the useHttpsURLConnectionDefaultHostnameVerifier property.
     * 
     */
    public void setUseHttpsURLConnectionDefaultHostnameVerifier(String value) {
        useHttpsURLConnectionDefaultHostnameVerifier = value;
    }

    public void unsetUseHttpsURLConnectionDefaultHostnameVerifier() {
        useHttpsURLConnectionDefaultHostnameVerifier = null;
    }

    /**
     * Gets the value of the useHttpsURLConnectionDefaultHostnameVerifier property.
     * 
     */
    public String getUseHttpsURLConnectionDefaultHostnameVerifier() {
        if (null == useHttpsURLConnectionDefaultHostnameVerifier) {
            return "false";
        }
        return useHttpsURLConnectionDefaultHostnameVerifier;
    }

    /**
     * Sets the value of the disableCNCheck property.
     * 
     */
    public void setDisableCNCheck(String value) {
        disableCNCheck = value;
    }

    public void unsetDisableCNCheck() {
        disableCNCheck = null;
    }

    /**
     * Gets the value of the disableCNCheck property.
     * 
     */
    public String getDisableCNCheck() {
        if (null == disableCNCheck) {
            return "false";
        }
        return disableCNCheck;
    }

}
