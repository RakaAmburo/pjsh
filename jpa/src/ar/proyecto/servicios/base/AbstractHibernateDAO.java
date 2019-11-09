package ar.proyecto.servicios.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import ar.proyecto.servicios.exceptions.DaoException;

public abstract class AbstractHibernateDAO<ENT, ID extends Serializable> {

	protected abstract Class<ENT> getClassName();

	protected static final Logger LOGGER = Logger
			.getLogger(AbstractHibernateDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	public ENT load(ID id) {

		Session session = this.getCurrentSession();
		@SuppressWarnings("unchecked")
		ENT ent = (ENT) session.get(this.getClassName(), id);
		
		return ent;
	}

	public ID save(ENT entity) {

		Session session = this.getCurrentSession();
		@SuppressWarnings("unchecked")
		ID entityId = (ID) session.save(entity);
		session.flush();
		
		return entityId;

	}
	
	public void batchInsert(List<ENT> inserts){
		
		Session session = this.getCurrentSession();
		for (ENT ent : inserts) {
			session.save(ent);
		}
		session.flush();
		
	}

	public void delete(ENT entity) {

		Session session = this.getCurrentSession();
		session.delete(entity);
		session.flush();
		
	}

	public ENT update(ENT entity) throws DaoException {

		Session session = this.getCurrentSession();

		try {
			session.update(entity);
			session.flush();
		} catch (StaleStateException e) {
			LOGGER.error("ID NOT FOUND FOR UPDATE", e);
			throw new DaoException("ID NOT FOUND FOR UPDATE", e);
		} catch (Exception e) {
			LOGGER.error("FATAL HIBERNATE EXCEPTION", e);
			throw new DaoException("FATAL HIBERNATE EXCEPTION", e);
		}
		
		return entity;
	}

	public List<ENT> find(Map<String, Object> criteria) {

		Session session = this.getCurrentSession();
		
		Criteria hibCriteria = session.createCriteria(this.getClassName());

		hibCriteria.addOrder(Order.desc("id"));

		for (Map.Entry<String, Object> entry : criteria.entrySet()) {
			if (entry.getValue() instanceof List) {
				hibCriteria.add(Restrictions.in(entry.getKey(),
						(List<?>) entry.getValue()));
				break;
			}
			hibCriteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}

		return castList(this.getClassName(), hibCriteria.list());
	}

	public List<ENT> findLike(Map<String, String> criteria) {

		Session session = this.getCurrentSession();
		Criteria hibCriteria = session.createCriteria(this.getClassName());

		for (Map.Entry<String, String> entry : criteria.entrySet()) {

			hibCriteria.add(Restrictions.like(entry.getKey(), entry.getValue()));
		}
		
		return castList(this.getClassName(), hibCriteria.list());
	}

	public Session getCurrentSession() {
		Session session = this.sessionFactory.getCurrentSession();
		return session;
	}

	private static <T> List<T> castList(Class<? extends T> clazz,
			Collection<?> c) {
		List<T> r = new ArrayList<T>(c.size());
		for (Object o : c)
			r.add(clazz.cast(o));
		return r;
	}
	
	public void truncateTable() {

		Session session = this.getCurrentSession();
		String tableName = this.getClassName().getSimpleName();
		String hql = String.format("delete from %s", tableName);
		Query query = session.createQuery(hql);
		query.executeUpdate();

	}

}
