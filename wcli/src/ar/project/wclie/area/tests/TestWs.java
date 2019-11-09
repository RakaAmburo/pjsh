package ar.project.wclie.area.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;

import ar.project.wclie.area.MathModule;
import ar.project.wclie.area.MathModuleImplService;
import ar.project.wclie.area.Persona4Ws;

public class TestWs {
	
	static {
	    //for localhost testing only
	    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
	    new javax.net.ssl.HostnameVerifier(){

	        public boolean verify(String hostname,
	                javax.net.ssl.SSLSession sslSession) {
	            if (hostname.equals("localhost")) {
	                return true;
	            }
	            return false;
	        }
	    });
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, GeneralSecurityException {
		
		URL url2 = null;
		try {
			//url2 = new URL("http://localhost:8080/webServ/MathModule?wsdl");
			url2 = new URL("https://localhost:8442/webServ/MathModule?wsdl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MathModuleImplService service2 = new MathModuleImplService(url2);
		MathModule port2 = service2.getMathModuleImplPort();
		
		setupTLS(port2);
				
		List<Persona4Ws> lista = port2.getPersonas();
		
		for (Iterator<Persona4Ws> iterator = lista.iterator(); iterator.hasNext();) {
			Persona4Ws persona4Ws = (Persona4Ws) iterator.next();
			System.out.println("nombre");
			System.out.println(persona4Ws.getNombre());
			
		}
		
	}
	
	private static void setupTLS(Object port) 
	        throws FileNotFoundException, IOException, GeneralSecurityException {
	        String contextPath = "";
	        try {
	            //contextPath = new TestWs().getClass().getResource("/certs").toURI().getPath();
	        	contextPath = "/home/pablo.paparini/Dropbox/REPOSITORIOS/certificados";
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        HTTPConduit httpConduit = (HTTPConduit) ClientProxy.getClient(port).getConduit();
	 
	        TLSClientParameters tlsCP = new TLSClientParameters();
	        String keyPassword = "1234563";
	        KeyStore keyStore = KeyStore.getInstance("JKS");
	        String keyStoreLoc = contextPath + "/web/webKeyStore.jks";
	        keyStore.load(new FileInputStream(keyStoreLoc), keyPassword.toCharArray());
	        KeyManager[] myKeyManagers = getKeyManagers(keyStore, keyPassword);
	        tlsCP.setKeyManagers(myKeyManagers);
	 
	        
	        KeyStore trustStore = KeyStore.getInstance("JKS");
	        String trustStoreLoc = contextPath + "/webServ/webServTrustStore.jks";
	        trustStore.load(new FileInputStream(trustStoreLoc), keyPassword.toCharArray());
	        TrustManager[] myTrustStoreKeyManagers = getTrustManagers(trustStore);
	        tlsCP.setTrustManagers(myTrustStoreKeyManagers);
	        
	        //The following is not recommended and would not be done in a prodcution environment,
	        //this is just for illustrative purpose
	        tlsCP.setDisableCNCheck(true);
	 
	        httpConduit.setTlsClientParameters(tlsCP);

	    }
	
	
	private static TrustManager[] getTrustManagers(KeyStore trustStore) 
	        throws NoSuchAlgorithmException, KeyStoreException {
	        String alg = KeyManagerFactory.getDefaultAlgorithm();
	        TrustManagerFactory fac = TrustManagerFactory.getInstance(alg);
	        fac.init(trustStore);
	        return fac.getTrustManagers();
	    }
	    
	    private static KeyManager[] getKeyManagers(KeyStore keyStore, String keyPassword) 
	        throws GeneralSecurityException, IOException {
	        String alg = KeyManagerFactory.getDefaultAlgorithm();
	        char[] keyPass = keyPassword != null
	                     ? keyPassword.toCharArray()
	                     : null;
	        KeyManagerFactory fac = KeyManagerFactory.getInstance(alg);
	        fac.init(keyStore, keyPass);
	        return fac.getKeyManagers();
	    }

}
