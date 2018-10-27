package ws.invoice.v21;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * Get and Create An Invoice.
 *
 * This class was generated by Apache CXF 3.0.1
 * 2014-09-22T22:29:14.163+01:00
 * Generated source version: 3.0.1
 * 
 */
@WebService(targetNamespace = "http://soapui.cookbook.samples/contract/invoice", name = "InvoicePortType")
@XmlSeeAlso({ObjectFactory.class})
public interface InvoicePortType {

    @RequestWrapper(localName = "createInvoice", targetNamespace = "http://soapui.cookbook.samples/schema/invoice", className = "ws.invoice.v21.InvoiceDocumentType")
    @WebMethod(action = "http://soapui.cookbook.samples/createInvoiceRequest/request")
    @ResponseWrapper(localName = "Acknowledgement", targetNamespace = "http://soapui.cookbook.samples/schema/invoice", className = "ws.invoice.v21.InvoiceRefType")
    public void createInvoice(
        @WebParam(mode = WebParam.Mode.INOUT, name = "invoiceNo", targetNamespace = "http://soapui.cookbook.samples/schema/invoice")
        javax.xml.ws.Holder<java.lang.String> invoiceNo,
        @WebParam(name = "company", targetNamespace = "http://soapui.cookbook.samples/schema/invoice")
        java.lang.String company,
        @WebParam(name = "amount", targetNamespace = "http://soapui.cookbook.samples/schema/invoice")
        double amount,
        @WebParam(name = "file", targetNamespace = "http://soapui.cookbook.samples/schema/invoice")
        javax.activation.DataHandler file
    );

    @RequestWrapper(localName = "getInvoice", targetNamespace = "http://soapui.cookbook.samples/schema/invoice", className = "ws.invoice.v21.InvoiceRefType")
    @WebMethod(action = "http://soapui.cookbook.samples/getInvoiceRequest/request")
    @ResponseWrapper(localName = "InvoiceDocument", targetNamespace = "http://soapui.cookbook.samples/schema/invoice", className = "ws.invoice.v21.InvoiceDocumentType")
    public void getInvoice(
        @WebParam(mode = WebParam.Mode.INOUT, name = "invoiceNo", targetNamespace = "http://soapui.cookbook.samples/schema/invoice")
        javax.xml.ws.Holder<java.lang.String> invoiceNo,
        @WebParam(mode = WebParam.Mode.OUT, name = "company", targetNamespace = "http://soapui.cookbook.samples/schema/invoice")
        javax.xml.ws.Holder<java.lang.String> company,
        @WebParam(mode = WebParam.Mode.OUT, name = "amount", targetNamespace = "http://soapui.cookbook.samples/schema/invoice")
        javax.xml.ws.Holder<java.lang.Double> amount,
        @WebParam(mode = WebParam.Mode.OUT, name = "file", targetNamespace = "http://soapui.cookbook.samples/schema/invoice")
        javax.xml.ws.Holder<javax.activation.DataHandler> file
    );
}
