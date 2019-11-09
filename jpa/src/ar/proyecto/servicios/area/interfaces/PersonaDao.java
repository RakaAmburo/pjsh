package ar.proyecto.servicios.area.interfaces;

import ar.project.ent.entities.area.Persona;
import ar.proyecto.servicios.base.GenericHibernateDao;


public interface PersonaDao extends GenericHibernateDao<Persona, Integer> {
	
	public void auxExec();
	
	

}
