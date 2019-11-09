package ar.project.persist.area.imp;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ar.project.ent.entities.area.PersonaOrder;
import ar.project.persist.area.interfaces.PersonaOrdenDao;
import ar.project.persist.base.AbstractHibernateDAO;

@Repository("personaOrdenDao")
public class PersonaOrdenDaoImp extends
		AbstractHibernateDAO<PersonaOrder, Integer> implements PersonaOrdenDao {

	@Override
	public void auxExec() {

		Session session = this.getCurrentSession();
		session.createSQLQuery("drop table if exists persona_orden")
				.executeUpdate();
		session.createSQLQuery(
				"create table persona_orden (id INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ "persona_id INTEGER NOT NULL, persona_orden INTEGER NOT NULL)")
				.executeUpdate();

		/*
		 * Map<String, Object> criteria = new HashMap<String, Object>();
		 * 
		 * List<Persona> personas = personaDao.find(criteria); Integer index =
		 * 1; for (Persona persona : personas) { PersonaOrder orden = new
		 * PersonaOrder(); orden.setPersonaId(persona.getId());
		 * orden.setOrden(index); personaOrdenDao.save(orden); index++; }
		 */

	}

	@Override
	protected Class<PersonaOrder> getEntClassName() {
		return PersonaOrder.class;
	}

	@Override
	protected Class<Integer> getIdClassName() {
		return Integer.class;
	}

}
