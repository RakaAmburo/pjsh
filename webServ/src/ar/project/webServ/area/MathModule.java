package ar.project.webServ.area;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

@WebService
public interface MathModule {

	public boolean isOdd(Integer number);

	public Map<Integer, String> paises();
	
	public List<Persona4Ws> getPersonas();

}