package ar.proyecto.servicios.area.imp;

import org.springframework.stereotype.Repository;

import ar.project.ent.entities.area.User;
import ar.proyecto.servicios.area.interfaces.UserDao;
import ar.proyecto.servicios.base.AbstractHibernateDAO;

@Repository("userDao")
public class UserDaoImp extends AbstractHibernateDAO<User, Integer> implements UserDao {
	
	@Override
	protected Class<User> getClassName() {
		return User.class;
	}

}
