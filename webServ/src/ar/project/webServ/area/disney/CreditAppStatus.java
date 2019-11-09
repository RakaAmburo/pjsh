/**************************************************
 * FileName - CreditAppStatus.java
 *
 * (c) Disney. All rights reserved.
 *
 * $Author: ppaparin $
 * $Revision: #1 $
 * $Change: 1580469 $
 * $Date: 2015/06/12 $
 **************************************************/
package ar.project.webServ.area.disney;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for creditAppStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="creditAppStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="approved"/&gt;
 *     &lt;enumeration value="pending"/&gt;
 *     &lt;enumeration value="declined"/&gt;
 *     &lt;enumeration value="error"/&gt;
 *     &lt;enumeration value="appReceived"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "creditAppStatus")
@XmlEnum
public enum CreditAppStatus {

    @XmlEnumValue("approved")
    APPROVED("approved"),
    @XmlEnumValue("pending")
    PENDING("pending"),
    @XmlEnumValue("declined")
    DECLINED("declined"),
    @XmlEnumValue("error")
    ERROR("error"),
    @XmlEnumValue("appReceived")
    APP_RECEIVED("appReceived");
    private final String value;

    CreditAppStatus(String v) {
        value = v;
    }

    /**
     * @return value object
     */
    public String value() {
        return value;
    }

    
    /**
     * @param v object
     * @return c object
     */
    public static CreditAppStatus fromValue(String v) {
        for (CreditAppStatus c: CreditAppStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
