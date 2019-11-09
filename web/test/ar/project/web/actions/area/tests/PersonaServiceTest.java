package ar.project.web.actions.area.tests;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.project.ent.entities.area.Language;
import ar.project.ent.entities.area.PersonTag;
import ar.project.ent.entities.area.Persona;
import ar.project.ent.entities.area.PersonaExt;
import ar.project.ent.tools.jarLoader4Tests.SpringJUnitRunner;
import ar.project.services.area.interfaces.PersonaService;
import ar.project.services.exceptions.ServException;

@RunWith(SpringJUnitRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "txnManager", defaultRollback = false)
@Transactional(propagation = Propagation.REQUIRES_NEW)
@FixMethodOrder(MethodSorters.DEFAULT)
public class PersonaServiceTest {
	
	@Autowired
	private PersonaService personaService;

	/*@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private PersonaOrdenDao personaOrdenDao;*/

	private String nombre = "TEST_nombre_TEST", apellido = "TEST_ape_TEST",
			direccion = "TEST_dir_TEST", telefono = "TEST_tel_TEST",
			updateTAG = "_UPDATED";

	@Test
	@Ignore
	public void iniciate() {

		// personaDao.auxExec();
		// countryDao.auxExec();
		//personaOrdenDao.auxExec();
		

	}
	
	@Test
	public void saveLoadUpdate() throws ServException {

		Persona persona = new Persona();
		persona.setNombre(nombre);
		persona.setApellido(apellido);
		persona.setDireccion(direccion);
		persona.setTelefono(telefono);
		PersonaExt pe = new PersonaExt();
		pe.setCodigoPostal("111");
		pe.setEdad(23);
		persona.setPersonaExt(pe);
		Language lang = new Language();
		lang.setId(20);
		lang.setName("Spanish");
		Language lang2 = new Language();
		lang2.setId(21);
		lang2.setName("Armenia");
		Set<Language> langs = new HashSet<Language>();
		langs.add(lang);
		langs.add(lang2);
		//persona.setLanguages(langs);
		
		Integer id = personaService.save(persona);
		
		PersonTag tag1 = new PersonTag();
		PersonTag tag2 = new PersonTag();
		
		tag1.setTag("garcando");
		
		tag2.setTag("ando");
		Set<PersonTag> tags =  new HashSet<PersonTag>();
		tags.add(tag1);tags.add(tag2);
		//persona.setTags(tags);
		
		personaService.update(persona);
		

		Persona saved = personaService.getPersona(id);

		Assert.assertEquals(nombre, saved.getNombre());
		Assert.assertEquals(apellido, saved.getApellido());
		Assert.assertEquals(direccion, saved.getDireccion());
		Assert.assertEquals(telefono, saved.getTelefono());

		saved.setNombre(nombre + updateTAG);
		
		//Set<PersonTag> taglist =  persona.getTags();
		//taglist.remove(taglist.iterator().next());
		PersonTag tag3 = new PersonTag();
		tag3.setTag("fackings");
		//taglist.clear();
		//taglist.add(tag3);
		//persona.setTags(taglist);
		
		personaService.update(saved);

		Persona updated = personaService.getPersona(id);

		Assert.assertEquals(nombre + updateTAG, updated.getNombre());
		
		
	}

	@Test
	public void findDelete() throws ServException {

		/*Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("nombre", nombre + updateTAG);*/
		List<Persona> personas = personaService.getPersonasByName(nombre + updateTAG);

		Assert.assertFalse(personas.isEmpty());

		for (Persona persona : personas) {
			personaService.delete(persona);
		}

		List<Persona> borradas = personaService.getPersonasByName(nombre + updateTAG);

		Assert.assertTrue(borradas.isEmpty());

	}
	
	@Test
	@Ignore
	public void getPaises() throws ServException{
		Map<Integer, String> paises = personaService.getPaises("Ar");
		Assert.assertFalse(paises.isEmpty());
	}

}
