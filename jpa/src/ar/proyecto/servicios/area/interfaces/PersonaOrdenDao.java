package ar.proyecto.servicios.area.interfaces;

import ar.project.ent.entities.area.PersonaOrder;
import ar.proyecto.servicios.base.GenericHibernateDao;

public interface PersonaOrdenDao extends GenericHibernateDao<PersonaOrder, Integer> {
	
	public void auxExec();

}
