package ar.project.persist.area.imp;

import org.springframework.stereotype.Repository;

import ar.project.ent.entities.area.User;
import ar.project.persist.area.interfaces.UserDao;
import ar.project.persist.base.AbstractHibernateDAO;

@Repository("userDao")
public class UserDaoImp extends AbstractHibernateDAO<User, String> implements UserDao {
	
	@Override
	protected Class<User> getEntClassName() {
		return User.class;
	}

	@Override
	protected Class<String> getIdClassName() {
		return String.class;
	}

}
