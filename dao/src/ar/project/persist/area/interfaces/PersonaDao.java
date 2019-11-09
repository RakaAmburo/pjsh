package ar.project.persist.area.interfaces;

import ar.project.ent.entities.area.Persona;
import ar.project.persist.base.GenericHibernateDao;


public interface PersonaDao extends GenericHibernateDao<Persona, Integer> {
	
	public void auxExec();
	
	

}
