package ar.project.wclie.area;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.project.wclie.area.PaisesResponse.Return;
import ar.project.wclie.tools.WsSSLContext;


@Service("mathController")
public class Controller {
	
	@Value("${math.service}")
	private String serviceURL;
	
	private static final Logger LOGGER = Logger
			.getLogger(Controller.class);
	
	public List<Persona4Ws> getPersonas(){
	    WsSSLContext.init();
		LOGGER.debug("Devolvemos personas del weservice");
		LOGGER.info("webservice personas");
		URL url2 = null;
		try {
			url2 = new URL(serviceURL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		MathModuleImplService service2 = new MathModuleImplService(url2);
		MathModule port2 = service2.getMathModuleImplPort();
		List<Persona4Ws> lista = port2.getPersonas();
		
			
		return lista;
	}
	
	public void callPaises(){
		WsSSLContext.init();
		URL url2 = null;
		try {
			url2 = new URL(serviceURL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MathModuleImplService service2 = new MathModuleImplService(url2);
		MathModule port2 = service2.getMathModuleImplPort();
		Return r = port2.paises();
		System.out.println(r.getEntry().size());
	}

}
