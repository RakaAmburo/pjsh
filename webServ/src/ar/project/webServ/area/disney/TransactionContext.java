/**************************************************
 * FileName - TransactionContext.java
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
 * <p>Java class for transactionContext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transactionContext"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="channel" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="traceIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="loopbackIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
@XmlType(name = "transactionContext", propOrder = {
    "channel",
    "traceIdentifier",
    "loopbackIndicator",
    "transactionTimestamp"
})
public class TransactionContext {

    @XmlElement(required = true)
    private String channel;
    @XmlElement(required = true, nillable = true)
    private String traceIdentifier;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    private Boolean loopbackIndicator;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    private XMLGregorianCalendar transactionTimestamp;

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannel(String value) {
        this.channel = value;
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
     * Gets the value of the loopbackIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLoopbackIndicator() {
        return loopbackIndicator;
    }

    /**
     * Sets the value of the loopbackIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLoopbackIndicator(Boolean value) {
        this.loopbackIndicator = value;
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
