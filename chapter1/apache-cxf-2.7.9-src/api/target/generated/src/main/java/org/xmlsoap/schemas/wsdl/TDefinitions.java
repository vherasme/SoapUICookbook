//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.22 at 09:56:33 p. m. CEST 
//


package org.xmlsoap.schemas.wsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3c.dom.Element;


/**
 * <p>Java class for tDefinitions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tDefinitions">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.xmlsoap.org/wsdl/}tExtensibleDocumented">
 *       &lt;sequence minOccurs="0">
 *         &lt;group ref="{http://schemas.xmlsoap.org/wsdl/}anyTopLevelOptionalElement"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;group ref="{http://schemas.xmlsoap.org/wsdl/}anyTopLevelOptionalElement"/>
 *           &lt;any processContents='lax' namespace='##other'/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="targetNamespace" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tDefinitions", namespace = "http://schemas.xmlsoap.org/wsdl/", propOrder = {
    "_import",
    "types",
    "message",
    "portType",
    "binding",
    "service",
    "importOrTypesOrMessage"
})
public class TDefinitions
    extends TExtensibleDocumented
{

    @XmlElement(name = "import", namespace = "http://schemas.xmlsoap.org/wsdl/")
    protected TImport _import;
    @XmlElement(namespace = "http://schemas.xmlsoap.org/wsdl/")
    protected TTypes types;
    @XmlElement(namespace = "http://schemas.xmlsoap.org/wsdl/")
    protected TMessage message;
    @XmlElement(namespace = "http://schemas.xmlsoap.org/wsdl/")
    protected TPortType portType;
    @XmlElement(namespace = "http://schemas.xmlsoap.org/wsdl/")
    protected TBinding binding;
    @XmlElement(namespace = "http://schemas.xmlsoap.org/wsdl/")
    protected TService service;
    @XmlElementRefs({
        @XmlElementRef(name = "import", namespace = "http://schemas.xmlsoap.org/wsdl/", type = JAXBElement.class),
        @XmlElementRef(name = "service", namespace = "http://schemas.xmlsoap.org/wsdl/", type = JAXBElement.class),
        @XmlElementRef(name = "binding", namespace = "http://schemas.xmlsoap.org/wsdl/", type = JAXBElement.class),
        @XmlElementRef(name = "portType", namespace = "http://schemas.xmlsoap.org/wsdl/", type = JAXBElement.class),
        @XmlElementRef(name = "message", namespace = "http://schemas.xmlsoap.org/wsdl/", type = JAXBElement.class),
        @XmlElementRef(name = "types", namespace = "http://schemas.xmlsoap.org/wsdl/", type = JAXBElement.class)
    })
    @XmlAnyElement(lax = true)
    protected List<Object> importOrTypesOrMessage;
    @XmlAttribute(name = "targetNamespace")
    @XmlSchemaType(name = "anyURI")
    protected String targetNamespace;
    @XmlAttribute(name = "name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String name;

    /**
     * Gets the value of the import property.
     * 
     * @return
     *     possible object is
     *     {@link TImport }
     *     
     */
    public TImport getImport() {
        return _import;
    }

    /**
     * Sets the value of the import property.
     * 
     * @param value
     *     allowed object is
     *     {@link TImport }
     *     
     */
    public void setImport(TImport value) {
        this._import = value;
    }

    /**
     * Gets the value of the types property.
     * 
     * @return
     *     possible object is
     *     {@link TTypes }
     *     
     */
    public TTypes getTypes() {
        return types;
    }

    /**
     * Sets the value of the types property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTypes }
     *     
     */
    public void setTypes(TTypes value) {
        this.types = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link TMessage }
     *     
     */
    public TMessage getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link TMessage }
     *     
     */
    public void setMessage(TMessage value) {
        this.message = value;
    }

    /**
     * Gets the value of the portType property.
     * 
     * @return
     *     possible object is
     *     {@link TPortType }
     *     
     */
    public TPortType getPortType() {
        return portType;
    }

    /**
     * Sets the value of the portType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPortType }
     *     
     */
    public void setPortType(TPortType value) {
        this.portType = value;
    }

    /**
     * Gets the value of the binding property.
     * 
     * @return
     *     possible object is
     *     {@link TBinding }
     *     
     */
    public TBinding getBinding() {
        return binding;
    }

    /**
     * Sets the value of the binding property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBinding }
     *     
     */
    public void setBinding(TBinding value) {
        this.binding = value;
    }

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link TService }
     *     
     */
    public TService getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link TService }
     *     
     */
    public void setService(TService value) {
        this.service = value;
    }

    /**
     * Gets the value of the importOrTypesOrMessage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the importOrTypesOrMessage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImportOrTypesOrMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link TImport }{@code >}
     * {@link Element }
     * {@link JAXBElement }{@code <}{@link TService }{@code >}
     * {@link JAXBElement }{@code <}{@link TBinding }{@code >}
     * {@link JAXBElement }{@code <}{@link TPortType }{@code >}
     * {@link Object }
     * {@link JAXBElement }{@code <}{@link TMessage }{@code >}
     * {@link JAXBElement }{@code <}{@link TTypes }{@code >}
     * 
     * 
     */
    public List<Object> getImportOrTypesOrMessage() {
        if (importOrTypesOrMessage == null) {
            importOrTypesOrMessage = new ArrayList<Object>();
        }
        return this.importOrTypesOrMessage;
    }

    /**
     * Gets the value of the targetNamespace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetNamespace() {
        return targetNamespace;
    }

    /**
     * Sets the value of the targetNamespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetNamespace(String value) {
        this.targetNamespace = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
