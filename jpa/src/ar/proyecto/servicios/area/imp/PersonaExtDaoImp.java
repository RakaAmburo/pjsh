package ar.proyecto.servicios.area.imp;

import org.springframework.stereotype.Repository;

import ar.project.ent.entities.area.PersonaExt;
import ar.proyecto.servicios.area.interfaces.PersonaExtDao;
import ar.proyecto.servicios.base.AbstractHibernateDAO;

@Repository("personaExtDao")
public class PersonaExtDaoImp extends AbstractHibernateDAO<PersonaExt, Integer> implements PersonaExtDao{

	@Override
	protected Class<PersonaExt> getClassName() {
		return PersonaExt.class;
	}

	

}
