package ar.project.persist.area.interfaces;

import ar.project.ent.entities.area.Country;
import ar.project.persist.base.GenericHibernateDao;

public interface CountryDao extends GenericHibernateDao<Country, Integer> {
	
	public void auxExec();

}
