package ar.project.persist.area.imp;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ar.project.ent.entities.area.Persona;
import ar.project.persist.area.interfaces.PersonaDao;
import ar.project.persist.base.AbstractHibernateDAO;

@Repository("personaDao")
public class PersonaDaoImp extends AbstractHibernateDAO<Persona, Integer>
		implements PersonaDao {

	public void reiniciate() {

		Session session = this.getCurrentSession();

		/*
		 * session.createSQLQuery("drop table if exists persona").executeUpdate()
		 * ; session.createSQLQuery(
		 * "create table persona (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
		 * "nombre TEXT NOT NULL, apellido TEXT NOT NULL, " +
		 * "direccion TEXT NOT NULL, telefono TEXT NOT NULL)").executeUpdate();
		 */

		session.flush();
	}

	public void cargaInicial() {
		/*
		 * Persona per1 = new Persona(); //per1.setOrden(1);
		 * per1.setNombre("hernan"); per1.setApellido("gonzales");
		 * per1.setDireccion("juncal 333"); per1.setTelefono("4569-7894");
		 * Persona per2 = new Persona(); //per2.setOrden(2);
		 * per2.setNombre("pablo"); per2.setApellido("juhal");
		 * per2.setDireccion("mitre 587"); per2.setTelefono("4579-8523");
		 * Persona per3 = new Persona(); //per3.setOrden(3);
		 * per3.setNombre("gerardo"); per3.setApellido("grazo");
		 * per3.setDireccion("santa fe 753"); per3.setTelefono("1236-9874");
		 * 
		 * save(per1); save(per2); save(per3);
		 */

	}

	public void auxExec() {

		// reiniciate();
		cargaInicial();
	}

	@Override
	protected Class<Persona> getEntClassName() {

		return Persona.class;
	}

	@Override
	protected Class<Integer> getIdClassName() {
		return Integer.class;
	}

}
