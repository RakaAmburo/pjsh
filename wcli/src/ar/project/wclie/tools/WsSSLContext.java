package ar.project.wclie.tools;

import java.io.IOException;
import java.net.URL;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.springframework.core.io.ClassPathResource;

public class WsSSLContext {

	private static WsSSLContext SSL_CONTEXT;

	private WsSSLContext() {
		
		try {
			SpringBusFactory bf = new SpringBusFactory();
			URL busFile = new ClassPathResource("secureClient.xml").getURL();
			Bus bus = bf.createBus(busFile.toString());
			BusFactory.setDefaultBus(bus);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	private static class SingletonHolder {

		public static final WsSSLContext INSTANCE = new WsSSLContext();

	}

	public static void init() {

		if (SSL_CONTEXT == null) {
			SSL_CONTEXT = SingletonHolder.INSTANCE;

		}

	}

}
