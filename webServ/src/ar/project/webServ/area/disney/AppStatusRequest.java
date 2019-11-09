/**************************************************
 * FileName - AppStatusRequest.java
 *
 * (c) Disney. All rights reserved.
 *
 * $Author: ppaparin $
 * $Revision: #1 $
 * $Change: 1580469 $
 * $Date: 2015/06/12 $
 **************************************************/
package ar.project.webServ.area.disney;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for appStatusRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="appStatusRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="decision" type="{http://cig.jpmchase.net/20110218/card/acq/}decision" minOccurs="0"/&gt;
 *         &lt;element name="customer" type="{http://cig.jpmchase.net/20110218/card/acq/}customer" minOccurs="0"/&gt;
 *         &lt;element name="partnerFields" type="{http://cig.jpmchase.net/20110218/card/acq/}partnerFields" minOccurs="0"/&gt;
 *         &lt;element name="transactionContext" type="{http://cig.jpmchase.net/20110218/card/acq/}transactionContext"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "appStatusRequest", propOrder = {
    "decision",
    "customer",
    "partnerFields",
    "transactionContext"
})
public class AppStatusRequest {

    private Decision decision;
    private Customer customer;
    @XmlElementRef(name = "partnerFields", namespace = "http://cig.jpmchase.net/20110218/card/acq/", type = JAXBElement.class)
    private JAXBElement<PartnerFields> partnerFields;
    @XmlElement(required = true)
    private TransactionContext transactionContext;

    /**
     * Gets the value of the decision property.
     * 
     * @return
     *     possible object is
     *     {@link Decision }
     *     
     */
    public Decision getDecision() {
        return decision;
    }

    /**
     * Sets the value of the decision property.
     * 
     * @param value
     *     allowed object is
     *     {@link Decision }
     *     
     */
    public void setDecision(Decision value) {
        this.decision = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link Customer }
     *     
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Customer }
     *     
     */
    public void setCustomer(Customer value) {
        this.customer = value;
    }

    /**
     * Gets the value of the partnerFields property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PartnerFields }{@code >}
     *     
     */
    public JAXBElement<PartnerFields> getPartnerFields() {
        return partnerFields;
    }

    /**
     * Sets the value of the partnerFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PartnerFields }{@code >}
     *     
     */
    public void setPartnerFields(JAXBElement<PartnerFields> value) {
        this.partnerFields = value;
    }

    /**
     * Gets the value of the transactionContext property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionContext }
     *     
     */
    public TransactionContext getTransactionContext() {
        return transactionContext;
    }

    /**
     * Sets the value of the transactionContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionContext }
     *     
     */
    public void setTransactionContext(TransactionContext value) {
        this.transactionContext = value;
    }

}
