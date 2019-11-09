/**************************************************
 * FileName - Decision.java
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
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for decision complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="decision"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="creditAppStatus" type="{http://cig.jpmchase.net/20110218/card/acq/}creditAppStatus" minOccurs="0"/&gt;
 *         &lt;element name="appLocatorKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="account" type="{http://cig.jpmchase.net/20110218/card/acq/}account" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "decision", propOrder = {
    "creditAppStatus",
    "appLocatorKey",
    "account"
})
public class  Decision {
    
    @XmlElementRef(name = "creditAppStatus", namespace = "http://cig.jpmchase.net/20110218/card/acq/", type = JAXBElement.class)
    private JAXBElement<CreditAppStatus> creditAppStatus;
    @XmlElementRef(name = "appLocatorKey", namespace = "http://cig.jpmchase.net/20110218/card/acq/", type = JAXBElement.class)
    private JAXBElement<String> appLocatorKey;
    @XmlElementRef(name = "account", namespace = "http://cig.jpmchase.net/20110218/card/acq/", type = JAXBElement.class)
    private JAXBElement<Account> account;

    /**
     * Gets the value of the creditAppStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CreditAppStatus }{@code >}
     *     
     */
    public JAXBElement<CreditAppStatus> getCreditAppStatus() {
        return creditAppStatus;
    }

    /**
     * Sets the value of the creditAppStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CreditAppStatus }{@code >}
     *     
     */
    public void setCreditAppStatus(JAXBElement<CreditAppStatus> value) {
        this.creditAppStatus = value;
    }

    /**
     * Gets the value of the appLocatorKey property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAppLocatorKey() {
        return appLocatorKey;
    }

    /**
     * Sets the value of the appLocatorKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAppLocatorKey(JAXBElement<String> value) {
        this.appLocatorKey = value;
    }

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Account }{@code >}
     *     
     */
    public JAXBElement<Account> getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Account }{@code >}
     *     
     */
    public void setAccount(JAXBElement<Account> value) {
        this.account = value;
    }

}
