
package ar.project.wclie.area;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.project.wclie.area package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPersonas_QNAME = new QName("http://area.webServ.project.ar/", "getPersonas");
    private final static QName _GetPersonasResponse_QNAME = new QName("http://area.webServ.project.ar/", "getPersonasResponse");
    private final static QName _IsOdd_QNAME = new QName("http://area.webServ.project.ar/", "isOdd");
    private final static QName _IsOddResponse_QNAME = new QName("http://area.webServ.project.ar/", "isOddResponse");
    private final static QName _Paises_QNAME = new QName("http://area.webServ.project.ar/", "paises");
    private final static QName _PaisesResponse_QNAME = new QName("http://area.webServ.project.ar/", "paisesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.project.wclie.area
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PaisesResponse }
     * 
     */
    public PaisesResponse createPaisesResponse() {
        return new PaisesResponse();
    }

    /**
     * Create an instance of {@link PaisesResponse.Return }
     * 
     */
    public PaisesResponse.Return createPaisesResponseReturn() {
        return new PaisesResponse.Return();
    }

    /**
     * Create an instance of {@link GetPersonas }
     * 
     */
    public GetPersonas createGetPersonas() {
        return new GetPersonas();
    }

    /**
     * Create an instance of {@link GetPersonasResponse }
     * 
     */
    public GetPersonasResponse createGetPersonasResponse() {
        return new GetPersonasResponse();
    }

    /**
     * Create an instance of {@link IsOdd }
     * 
     */
    public IsOdd createIsOdd() {
        return new IsOdd();
    }

    /**
     * Create an instance of {@link IsOddResponse }
     * 
     */
    public IsOddResponse createIsOddResponse() {
        return new IsOddResponse();
    }

    /**
     * Create an instance of {@link Paises }
     * 
     */
    public Paises createPaises() {
        return new Paises();
    }

    /**
     * Create an instance of {@link Persona4Ws }
     * 
     */
    public Persona4Ws createPersona4Ws() {
        return new Persona4Ws();
    }

    /**
     * Create an instance of {@link PaisesResponse.Return.Entry }
     * 
     */
    public PaisesResponse.Return.Entry createPaisesResponseReturnEntry() {
        return new PaisesResponse.Return.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://area.webServ.project.ar/", name = "getPersonas")
    public JAXBElement<GetPersonas> createGetPersonas(GetPersonas value) {
        return new JAXBElement<GetPersonas>(_GetPersonas_QNAME, GetPersonas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://area.webServ.project.ar/", name = "getPersonasResponse")
    public JAXBElement<GetPersonasResponse> createGetPersonasResponse(GetPersonasResponse value) {
        return new JAXBElement<GetPersonasResponse>(_GetPersonasResponse_QNAME, GetPersonasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsOdd }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://area.webServ.project.ar/", name = "isOdd")
    public JAXBElement<IsOdd> createIsOdd(IsOdd value) {
        return new JAXBElement<IsOdd>(_IsOdd_QNAME, IsOdd.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsOddResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://area.webServ.project.ar/", name = "isOddResponse")
    public JAXBElement<IsOddResponse> createIsOddResponse(IsOddResponse value) {
        return new JAXBElement<IsOddResponse>(_IsOddResponse_QNAME, IsOddResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Paises }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://area.webServ.project.ar/", name = "paises")
    public JAXBElement<Paises> createPaises(Paises value) {
        return new JAXBElement<Paises>(_Paises_QNAME, Paises.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaisesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://area.webServ.project.ar/", name = "paisesResponse")
    public JAXBElement<PaisesResponse> createPaisesResponse(PaisesResponse value) {
        return new JAXBElement<PaisesResponse>(_PaisesResponse_QNAME, PaisesResponse.class, null, value);
    }

}
