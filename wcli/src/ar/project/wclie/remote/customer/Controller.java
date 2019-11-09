package ar.project.wclie.remote.customer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("controller")
public class Controller {
	
	@Value("${remote.customer}")
	private String serviceURL;
	
	public List<CustomerType> getAll() throws MalformedURLException{
		URL url = new URL(serviceURL);
		CustomerService service = new CustomerService(url);
		CRMServicePT port = service.getCRMServicePTPort();
		List<CustomerType> lista = port.getAll();
		return lista;
	}

}
