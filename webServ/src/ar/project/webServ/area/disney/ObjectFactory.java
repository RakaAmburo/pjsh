/**************************************************
 * FileName - ObjectFactory.java
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
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * com.disney.wdpro.service.payment.webservice.dvic.postback package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {
    private static final  String QNAMEURL = "http://cig.jpmchase.net/20110218/card/acq/";
    private static final  QName APPSTATUSREQUESTPARTNERFIELDS_QNAME = new QName(
            QNAMEURL, "partnerFields");
    private static final QName PARTNERFIELDSSESSIONID_QNAME = new QName(
            QNAMEURL, "sessionId");
    private static final QName PARTNERFIELDSCUSTOMERID_QNAME = new QName(
            QNAMEURL, "customerId");
    private static final QName PARTNERFIELDSREFERENCENUM_QNAME = new QName(
            QNAMEURL, "referenceNum");
    private static final QName CUSTOMERMIDDLEINITIAL_QNAME = new QName(
            QNAMEURL, "middleInitial");
    private static final QName CUSTOMEREMAILADDRESS_QNAME = new QName(
            QNAMEURL, "emailAddress");
    private static final QName POSTALADDRESSADDRLINE2_QNAME = new QName(
            QNAMEURL, "addrLine2");
    private static final QName DECISIONCREDITAPPSTATUS_QNAME = new QName(
            QNAMEURL, "creditAppStatus");
    private static final QName DECISIONAPPLOCATORKEY_QNAME = new QName(
            QNAMEURL, "appLocatorKey");
    private static final QName DECISIONACCOUNT_QNAME = new QName(QNAMEURL,
            "account");
    private static final QName ACCOUNTTEMPORARYSECURITYCODE_QNAME = new QName(
            QNAMEURL, "temporarySecurityCode");
    private static final QName ACCOUNTCARDMEMBERAGREEMENTURL_QNAME = new QName(
            QNAMEURL, "cardMemberAgreementURL");

    /**
     * Create an instance of {@link Account }.
     * @return Account object
     */
    public Account createAccount() {
        return new Account();
    }

    /**
     * Create an instance of {@link Decision }.
     * @return Decision object
     */
    public Decision createDecision() {
        return new Decision();
    }

    /**
     * Create an instance of {@link PostalAddress }.
     * @return PostalAddress object
     */
    public PostalAddress createPostalAddress() {
        return new PostalAddress();
    }

    /**
     * Create an instance of {@link Customer }.
     * @return Customer object
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link PartnerFields }.
     * @return PartnerFields object
     */
    public PartnerFields createPartnerFields() {
        return new PartnerFields();
    }

    /**
     * Create an instance of {@link TransactionContext }.
     * @return TransactionContext object
     */
    public TransactionContext createTransactionContext() {
        return new TransactionContext();
    }

    /**
     * Create an instance of {@link WebServiceResponse }.
     * @return WebServiceResponse object
     */
    public WebServiceResponse createWebServiceResponse() {
        return new WebServiceResponse();
    }

    /**
     * Create an instance of {@link AppStatusRequest }.
     * @return AppStatusRequest object
     */
    public AppStatusRequest createAppStatusRequest() {
        return new AppStatusRequest();
    }

    /**
     * Create an instance of {@link AppStatusResponse }.
     * @return AppStatusResponse object
     */
    public AppStatusResponse createAppStatusResponse() {
        return new AppStatusResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerFields }.
     * {@code >}
     * @param value object
     * @return JAXBElement object
     */
    @XmlElementDecl(namespace = QNAMEURL, name = "partnerFields", scope = AppStatusRequest.class)
    public JAXBElement<PartnerFields> createAppStatusRequestPartnerFields(
            PartnerFields value) {
        return new JAXBElement<PartnerFields>(
                APPSTATUSREQUESTPARTNERFIELDS_QNAME, PartnerFields.class,
                AppStatusRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}.
     * @param value object
     * @return JAXBElement object
     */
    @XmlElementDecl(namespace = QNAMEURL, name = "sessionId", scope = PartnerFields.class)
    public JAXBElement<String> createPartnerFieldsSessionId(String value) {
        return new JAXBElement<String>(PARTNERFIELDSSESSIONID_QNAME,
                String.class, PartnerFields.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}.
     * @param value object
     * @return JAXBElement object
     */
    @XmlElementDecl(namespace = QNAMEURL, name = "customerId", scope = PartnerFields.class)
    public JAXBElement<String> createPartnerFieldsCustomerId(String value) {
        return new JAXBElement<String>(PARTNERFIELDSCUSTOMERID_QNAME,
                String.class, PartnerFields.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}.
     * @param value object
     * @return JAXBElement object
     */
    @XmlElementDecl(namespace = QNAMEURL, name = "referenceNum", scope = PartnerFields.class)
    public JAXBElement<String> createPartnerFieldsReferenceNum(String value) {
        return new JAXBElement<String>(PARTNERFIELDSREFERENCENUM_QNAME,
                String.class, PartnerFields.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}.
     * @param value object
     * @return JAXBElement object
     */
    @XmlElementDecl(namespace = QNAMEURL, name = "middleInitial", scope = Customer.class)
    public JAXBElement<String> createCustomerMiddleInitial(String value) {
        return new JAXBElement<String>(CUSTOMERMIDDLEINITIAL_QNAME,
                String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}.
     * @param value object
     * @return JAXBElement object
     */
    @XmlElementDecl(namespace = QNAMEURL, name = "emailAddress", scope = Customer.class)
    public JAXBElement<String> createCustomerEmailAddress(String value) {
        return new JAXBElement<String>(CUSTOMEREMAILADDRESS_QNAME,
                String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}.
     * @param value object
     * @return JAXBElement object
     */
    @XmlElementDecl(namespace = QNAMEURL, name = "addrLine2", scope = PostalAddress.class)
    public JAXBElement<String> createPostalAddressAddrLine2(String value) {
        return new JAXBElement<String>(POSTALADDRESSADDRLINE2_QNAME,
                String.class, PostalAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditAppStatus }.
     * {@code >}
     * @param value object
     * @return JAXBElement object
     */
    @XmlElementDecl(namespace = QNAMEURL, name = "creditAppStatus", scope = Decision.class)
    public JAXBElement<CreditAppStatus> createDecisionCreditAppStatus(
            CreditAppStatus value) {
        return new JAXBElement<CreditAppStatus>(DECISIONCREDITAPPSTATUS_QNAME,
                CreditAppStatus.class, Decision.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}.
     * @param value object
     * @return JAXBElement object
     */
    @XmlElementDecl(namespace = QNAMEURL, name = "appLocatorKey", scope = Decision.class)
    public JAXBElement<String> createDecisionAppLocatorKey(String value) {
        return new JAXBElement<String>(DECISIONAPPLOCATORKEY_QNAME,
                String.class, Decision.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Account }{@code >}.
     * @param value object
     * @return JAXBElement object
     */
    @XmlElementDecl(namespace = QNAMEURL, name = "account", scope = Decision.class)
    public JAXBElement<Account> createDecisionAccount(Account value) {
        return new JAXBElement<Account>(DECISIONACCOUNT_QNAME, Account.class,
                Decision.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}.
     * @param value object
     * @return JAXBElement object
     */
    @XmlElementDecl(namespace = QNAMEURL, name = "temporarySecurityCode", scope = Account.class)
    public JAXBElement<String> createAccountTemporarySecurityCode(String value) {
        return new JAXBElement<String>(ACCOUNTTEMPORARYSECURITYCODE_QNAME,
                String.class, Account.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}.
     * @param value object
     * @return JAXBElement object
     */
    @XmlElementDecl(namespace = QNAMEURL, name = "cardMemberAgreementURL", scope = Account.class)
    public JAXBElement<String> createAccountCardMemberAgreementURL(String value) {
        return new JAXBElement<String>(ACCOUNTCARDMEMBERAGREEMENTURL_QNAME,
                String.class, Account.class, value);
    }

}
