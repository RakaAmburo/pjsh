package ar.project.persist.area.imp;

import org.springframework.stereotype.Repository;

import ar.project.ent.entities.area.Language;
import ar.project.persist.area.interfaces.LanguageDao;
import ar.project.persist.base.AbstractHibernateDAO;

@Repository("LanguageDao")
public class LanguageDaoImp extends AbstractHibernateDAO<Language, Integer>
		implements LanguageDao {

	@Override
	protected Class<Language> getEntClassName() {
		return Language.class;
	}

	@Override
	protected Class<Integer> getIdClassName() {
		return Integer.class;
	}

}
