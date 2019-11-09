package ar.proyecto.servicios.area.interfaces;

import ar.project.ent.entities.area.Country;
import ar.proyecto.servicios.base.GenericHibernateDao;

public interface CountryDao extends GenericHibernateDao<Country, Integer> {
	
	public void auxExec();

}
