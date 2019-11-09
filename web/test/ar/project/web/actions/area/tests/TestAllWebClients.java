package ar.project.web.actions.area.tests;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.project.ent.tools.jarLoader4Tests.SpringJUnitRunner;
import ar.project.services.area.interfaces.PersonaService;

@RunWith(SpringJUnitRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "txnManager", defaultRollback = false)
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TestAllWebClients {
	
	/*static { // not necessary <http:tlsClientParameters disableCNCheck="true">
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
	}*/

	@Autowired
	private PersonaService personaService;

	@Test
	public void testController(){
		boolean isEmpty = personaService.getPersonasFromWebService();
		Assert.assertFalse(isEmpty);
	}

	
	@Test
	public void testArticlesController() throws MalformedURLException{
		boolean isEmpty = personaService.getArticlesFromWebService();
		Assert.assertFalse(isEmpty);
	}
}
