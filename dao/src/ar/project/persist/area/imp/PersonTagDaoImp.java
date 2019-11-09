package ar.project.persist.area.imp;

import org.springframework.stereotype.Repository;

import ar.project.ent.entities.area.PersonTag;
import ar.project.persist.area.interfaces.PersonTagDao;
import ar.project.persist.base.AbstractHibernateDAO;

@Repository("personTagDao")
public class PersonTagDaoImp extends AbstractHibernateDAO<PersonTag, Integer> implements PersonTagDao{

	@Override
	protected Class<PersonTag> getEntClassName() {
		return PersonTag.class;
	}

	@Override
	protected Class<Integer> getIdClassName() {
		return Integer.class;
	}

}
