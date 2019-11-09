package ar.project.restCli.area;

import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.project.ent.entities.area.Persona;
import ar.project.ent.entities.ent.jsonExample.Users;

@Service("firstClient")
public class FirstClient {

	// avoid localhost denial check
	static {
		// for localhost testing only
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				if (hostname.equals("localhost")) {
					return true;
				}
				return false;
			}
		});
	}

	public Client createRestClient() {
		// secure
		SslConfigurator sslConfig = SslConfigurator.newInstance()
				.trustStoreFile(FirstClient.class.getResource("/tomcatTrustStore.jks").getPath())
				.trustStorePassword("123456")
				.keyStoreFile(FirstClient.class.getResource("/wclieKeyStore.jks").getPath()).keyPassword("123456");
		SSLContext sslContext = sslConfig.createSSLContext();

		Client restClient = ClientBuilder.newBuilder().sslContext(sslContext).build();
		return restClient;
	}

	private static final Logger LOGGER = Logger.getLogger(FirstClient.class);

	@Value("${remote.rest.service}")
	private String remoteServiceURL;

	@Value("${local.rest.service}")
	private String localServiceURL;

	public List<Users> getUsers() {
		LOGGER.debug("Obtenemos user de servicio remoto jsonplaceholder");
		Client client = ClientBuilder.newClient(new ClientConfig());
		List<Users> users = client.target(remoteServiceURL).path("users").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Users>>() {
				});
		return users;
	}

	public List<Persona> getPersonas() {
		LOGGER.debug("Obtenemos personas de nuestro servicio rest");
		LOGGER.info("rest personas");

		// unsecure
		// Client client = ClientBuilder.newClient(new ClientConfig());
		Client client = createRestClient();

		List<Persona> personas = client.target(localServiceURL).path("hello").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Persona>>() {
				});
		return personas;
	}

	public void callPaises() {
		// Client client = ClientBuilder.newClient(new ClientConfig());
		Client client = createRestClient();
		Map<Integer, String> p = client.target(localServiceURL).path("hello").path("a")
				.request(MediaType.APPLICATION_JSON).post(null, new GenericType<Map<Integer, String>>() {
				});
		System.out.println(p.size());
		/*
		 * Iterator it = p.entrySet().iterator(); while (it.hasNext()) {
		 * Map.Entry pairs = (Map.Entry)it.next();
		 * System.out.println(pairs.getKey() + " = " + pairs.getValue());
		 * 
		 * }
		 */
	}

}
