package ar.project.persist.area.imp;

import org.springframework.stereotype.Repository;

import ar.project.ent.entities.area.PrgmLanguage;
import ar.project.persist.area.interfaces.PrgmLanguageDao;
import ar.project.persist.base.AbstractHibernateDAO;

@Repository("PrgmLanguageDao")
public class PrgmLanguageDaoImp extends AbstractHibernateDAO<PrgmLanguage, Integer> implements PrgmLanguageDao{

	@Override
	protected Class<PrgmLanguage> getEntClassName() {
		return PrgmLanguage.class;
	}

	@Override
	protected Class<Integer> getIdClassName() {
		return Integer.class;
	}

}
