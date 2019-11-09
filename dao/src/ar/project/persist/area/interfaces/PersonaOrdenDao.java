package ar.project.persist.area.interfaces;

import ar.project.ent.entities.area.PersonaOrder;
import ar.project.persist.base.GenericHibernateDao;

public interface PersonaOrdenDao extends GenericHibernateDao<PersonaOrder, Integer> {
	
	public void auxExec();

}
