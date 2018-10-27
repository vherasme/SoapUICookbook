//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.22 at 09:56:33 p. m. CEST 
//


package org.apache.cxf.configuration.security;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         This structure holds a list of regular expressions that corresponds to a sequence of
 *         Certificate Constraints on either the Subject or Issuer DN.
 *         
 * 
 * <p>Java class for CertificateConstraintsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CertificateConstraintsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SubjectDNConstraints" type="{http://cxf.apache.org/configuration/security}DNConstraintsType" minOccurs="0"/>
 *         &lt;element name="IssuerDNConstraints" type="{http://cxf.apache.org/configuration/security}DNConstraintsType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateConstraintsType", namespace = "http://cxf.apache.org/configuration/security", propOrder = {
    "subjectDNConstraints",
    "issuerDNConstraints"
})
public class CertificateConstraintsType {

    @XmlElement(name = "SubjectDNConstraints", namespace = "http://cxf.apache.org/configuration/security")
    protected DNConstraintsType subjectDNConstraints;
    @XmlElement(name = "IssuerDNConstraints", namespace = "http://cxf.apache.org/configuration/security")
    protected DNConstraintsType issuerDNConstraints;

    /**
     * Gets the value of the subjectDNConstraints property.
     * 
     * @return
     *     possible object is
     *     {@link DNConstraintsType }
     *     
     */
    public DNConstraintsType getSubjectDNConstraints() {
        return subjectDNConstraints;
    }

    /**
     * Sets the value of the subjectDNConstraints property.
     * 
     * @param value
     *     allowed object is
     *     {@link DNConstraintsType }
     *     
     */
    public void setSubjectDNConstraints(DNConstraintsType value) {
        this.subjectDNConstraints = value;
    }

    /**
     * Gets the value of the issuerDNConstraints property.
     * 
     * @return
     *     possible object is
     *     {@link DNConstraintsType }
     *     
     */
    public DNConstraintsType getIssuerDNConstraints() {
        return issuerDNConstraints;
    }

    /**
     * Sets the value of the issuerDNConstraints property.
     * 
     * @param value
     *     allowed object is
     *     {@link DNConstraintsType }
     *     
     */
    public void setIssuerDNConstraints(DNConstraintsType value) {
        this.issuerDNConstraints = value;
    }

}
