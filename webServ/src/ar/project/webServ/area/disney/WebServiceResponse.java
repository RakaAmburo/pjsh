/**************************************************
 * FileName - WebServiceResponse.java
 *
 * (c) Disney. All rights reserved.
 *
 * $Author: ppaparin $
 * $Revision: #1 $
 * $Change: 1580469 $
 * $Date: 2015/06/12 $
 **************************************************/
package ar.project.webServ.area.disney;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for webServiceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="webServiceResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="responseCode" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="responseMessage" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="exceptionCode" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="exceptionMessage" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="transactionIdentifier" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="traceIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="transactionTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "webServiceResponse", propOrder = {
    "responseCode",
    "responseMessage",
    "exceptionCode",
    "exceptionMessage",
    "transactionIdentifier",
    "traceIdentifier",
    "transactionTimestamp"
})
public class WebServiceResponse {

    private long responseCode;
    @XmlElement(required = true)
    private String responseMessage;
    private long exceptionCode;
    @XmlElement(required = true, nillable = true)
    private String exceptionMessage;
    private long transactionIdentifier;
    @XmlElement(required = true)
    private String traceIdentifier;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    private XMLGregorianCalendar transactionTimestamp;

    /**
     * Gets the value of the responseCode property.
     * @return responseCode
     */
    public long getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the value of the responseCode property.
     * @param value object
     */
    public void setResponseCode(long value) {
        this.responseCode = value;
    }

    /**
     * Gets the value of the responseMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Sets the value of the responseMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseMessage(String value) {
        this.responseMessage = value;
    }

    /**
     * Gets the value of the exceptionCode property.
     * @return long object
     */
    public long getExceptionCode() {
        return exceptionCode;
    }

    /**
     * Sets the value of the exceptionCode property.
     * @param value object
     */
    public void setExceptionCode(long value) {
        this.exceptionCode = value;
    }

    /**
     * Gets the value of the exceptionMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionMessage() {
        return exceptionMessage;
    }

    /**
     * Sets the value of the exceptionMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionMessage(String value) {
        this.exceptionMessage = value;
    }

    /**
     * Gets the value of the transactionIdentifier property.
     * @return long object
     */
    public long getTransactionIdentifier() {
        return transactionIdentifier;
    }

    /**
     * Sets the value of the transactionIdentifier property.
     * @param value object
     */
    public void setTransactionIdentifier(long value) {
        this.transactionIdentifier = value;
    }

    /**
     * Gets the value of the traceIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTraceIdentifier() {
        return traceIdentifier;
    }

    /**
     * Sets the value of the traceIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTraceIdentifier(String value) {
        this.traceIdentifier = value;
    }

    /**
     * Gets the value of the transactionTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTransactionTimestamp() {
        return transactionTimestamp;
    }

    /**
     * Sets the value of the transactionTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTransactionTimestamp(XMLGregorianCalendar value) {
        this.transactionTimestamp = value;
    }

}
