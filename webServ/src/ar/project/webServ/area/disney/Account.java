/**************************************************
 * FileName - Account.java
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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for account complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="account"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}gYearMonth"/&gt;
 *         &lt;element name="temporaryCreditLine" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="temporarySecurityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cardMemberAgreementURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "account", propOrder = {
    "accountNumber",
    "expirationDate",
    "temporaryCreditLine",
    "temporarySecurityCode",
    "cardMemberAgreementURL"
})
public class Account {

    @XmlElement(required = true)
    private String accountNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "gYearMonth")
    private XMLGregorianCalendar expirationDate;
    private long temporaryCreditLine;
    @XmlElementRef(name = "temporarySecurityCode", namespace = "http://cig.jpmchase.net/20110218/card/acq/", type = JAXBElement.class)
    private JAXBElement<String> temporarySecurityCode;
    @XmlElementRef(name = "cardMemberAgreementURL", namespace = "http://cig.jpmchase.net/20110218/card/acq/", type = JAXBElement.class)
    private JAXBElement<String> cardMemberAgreementURL;

    /**
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDate(XMLGregorianCalendar value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the temporaryCreditLine property.
     * @return temporaryCreditLine object
     */
    public long getTemporaryCreditLine() {
        return temporaryCreditLine;
    }

    /**
     * Sets the value of the temporaryCreditLine property.
     * @param value object
     */
    public void setTemporaryCreditLine(long value) {
        this.temporaryCreditLine = value;
    }

    /**
     * Gets the value of the temporarySecurityCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTemporarySecurityCode() {
        return temporarySecurityCode;
    }

    /**
     * Sets the value of the temporarySecurityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTemporarySecurityCode(JAXBElement<String> value) {
        this.temporarySecurityCode = value;
    }

    /**
     * Gets the value of the cardMemberAgreementURL property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCardMemberAgreementURL() {
        return cardMemberAgreementURL;
    }

    /**
     * Sets the value of the cardMemberAgreementURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCardMemberAgreementURL(JAXBElement<String> value) {
        this.cardMemberAgreementURL = value;
    }

}
