package ar.project.persist.area.imp;

import org.springframework.stereotype.Repository;

import ar.project.ent.entities.area.PersonaExt;
import ar.project.persist.area.interfaces.PersonaExtDao;
import ar.project.persist.base.AbstractHibernateDAO;

@Repository("personaExtDao")
public class PersonaExtDaoImp extends AbstractHibernateDAO<PersonaExt, Integer> implements PersonaExtDao{

	@Override
	protected Class<PersonaExt> getEntClassName() {
		return PersonaExt.class;
	}

	@Override
	protected Class<Integer> getIdClassName() {
		return Integer.class;
	}

	

}
