/**************************************************
 * FileName - AppStatusResponse.java
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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for appStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="appStatusResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="response" type="{http://cig.jpmchase.net/20110218/card/acq/}webServiceResponse"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "appStatusResponse", propOrder = {
    "response"
})
public class AppStatusResponse {

    @XmlElement(required = true)
    private WebServiceResponse response;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link WebServiceResponse }
     *     
     */
    public WebServiceResponse getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link WebServiceResponse }
     *     
     */
    public void setResponse(WebServiceResponse value) {
        this.response = value;
    }

}
