package ar.project.persist.base;

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

import ar.project.persist.exceptions.DaoException;

public abstract class AbstractHibernateDAO<ENT, ID extends Serializable> {

	protected abstract Class<ENT> getEntClassName();
	protected abstract Class<ID> getIdClassName();

	protected static final Logger LOGGER = Logger
			.getLogger(AbstractHibernateDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	public ENT load(ID id) throws DaoException {
        LOGGER.debug("Persist generic load");
		Session session = this.getCurrentSession();
		ENT ent = null;
		try {
			Object obj = session.get(this.getEntClassName(), id);
			ent = this.getEntClassName().cast(obj);
		} catch (Exception e) {
			LOGGER.error("SQL EXCEPTION", e);
			throw new DaoException("SQL EXCEPTION", e);
		}
		
		return ent;
	}

	public ID save(ENT entity) throws DaoException {
		LOGGER.debug("Persist generic save");
		Session session = this.getCurrentSession();
				
		try {
			Object obj =  session.save(entity);
			ID entityId = this.getIdClassName().cast(obj);
			session.flush();
			return entityId;
		} catch (Exception e) {
			LOGGER.error("SQL EXCEPTION", e);
			throw new DaoException("SQL EXCEPTION", e);
		}
	}
	
	public void batchInsert(List<ENT> inserts) throws DaoException{
		LOGGER.debug("Persist generic batch insert");
		Session session = this.getCurrentSession();
		try {
			for (ENT ent : inserts) {
				session.save(ent);
			}
			session.flush();
		} catch (Exception e) {
			LOGGER.error("SQL EXCEPTION", e);
			throw new DaoException("SQL EXCEPTION", e);
		}		
	}

	public void delete(ENT entity) throws DaoException{
		LOGGER.debug("Persist generic delete");
		Session session = this.getCurrentSession();
		try {
			session.delete(entity);
			session.flush();
		} catch (Exception e) {
			LOGGER.error("SQL EXCEPTION", e);
			throw new DaoException("SQL EXCEPTION", e);
		}
		
		
	}

	public ENT update(ENT entity) throws DaoException {
		LOGGER.debug("Persist generic update");
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

	public List<ENT> find(Map<String, Object> criteria) throws DaoException {

		LOGGER.info("find method");
		LOGGER.debug("Persist Generic find");
		Session session = this.getCurrentSession();
		
		Criteria hibCriteria = session.createCriteria(this.getEntClassName());

		hibCriteria.addOrder(Order.desc("id"));

		for (Map.Entry<String, Object> entry : criteria.entrySet()) {
			if (entry.getValue() instanceof List) {
				hibCriteria.add(Restrictions.in(entry.getKey(),
						(List<?>) entry.getValue()));
				break;
			}
			hibCriteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		
		try {
			return castList(this.getEntClassName(), hibCriteria.list());
		} catch (Exception e) {
			LOGGER.error("FATAL HIBERNATE EXCEPTION", e);
			throw new DaoException("FATAL HIBERNATE EXCEPTION", e);
		}

	}
	
	public ENT merge(ENT entity) throws DaoException {

		Session session = this.getCurrentSession();
		ENT ent;

		try {
			Object obj = (Object) session.merge(entity);
			ent = this.getEntClassName().cast(obj);
			session.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}

		return ent;
	}
	
	public List<ENT> findNe(String field, String value) throws DaoException {

		LOGGER.info("find not equal method");
		LOGGER.debug("Persist Generic find not equal");
		Session session = this.getCurrentSession();
		
		Criteria hibCriteria = session.createCriteria(this.getEntClassName());

		hibCriteria.addOrder(Order.desc("id"));

		hibCriteria.add(Restrictions.ne(field, value));
		
		try {
			return castList(this.getEntClassName(), hibCriteria.list());
		} catch (Exception e) {
			LOGGER.error("FATAL HIBERNATE EXCEPTION", e);
			throw new DaoException("FATAL HIBERNATE EXCEPTION", e);
		}

	}

	public List<ENT> findLike(Map<String, String> criteria) throws DaoException{// no deberia ser map
        
		LOGGER.debug("Persist Generic find (like)");
		Session session = this.getCurrentSession();
		Criteria hibCriteria = session.createCriteria(this.getEntClassName());

		for (Map.Entry<String, String> entry : criteria.entrySet()) {

			hibCriteria.add(Restrictions.like(entry.getKey(), entry.getValue()));
		}
		
		try {
			return castList(this.getEntClassName(), hibCriteria.list());
		} catch (Exception e) {
			LOGGER.error("FATAL HIBERNATE EXCEPTION", e);
			throw new DaoException("FATAL HIBERNATE EXCEPTION", e);
		}finally{
			/*Object obj = session.get(this.getEntClassName(), 12);
			System.err.println(obj);
			System.err.println(session.getSessionFactory().getStatistics().getSecondLevelCacheHitCount());
			System.err.println(session.getSessionFactory().getStatistics().getSecondLevelCachePutCount());
			System.err.println(session.getSessionFactory().getStatistics().getSecondLevelCacheMissCount());*/
		}
		
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
	
	public void truncateTable() throws DaoException {
		LOGGER.debug("Persist Generic truncate table");
		Session session = this.getCurrentSession();
		String tableName = this.getEntClassName().getSimpleName();
		String hql = String.format("delete from %s", tableName);
		Query query = session.createQuery(hql);
		try {
			query.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("FATAL HIBERNATE EXCEPTION", e);
			throw new DaoException("FATAL HIBERNATE EXCEPTION", e);
		}
	}
	
	public void delOnly(String field, Integer match) throws DaoException{
		LOGGER.debug("Persist del delWhere");
		Session session = this.getCurrentSession();
		String tableName = this.getEntClassName().getSimpleName();
		String hql = String.format("DELETE FROM %s WHERE %s =  :match ", tableName, field);
		Query query = session.createQuery(hql);
		query.setInteger("match", match);
		try {
			query.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("FATAL HIBERNATE EXCEPTION", e);
			throw new DaoException("FATAL HIBERNATE EXCEPTION", e);
		}
	}
	
	public void delExcept(String field, String match) throws DaoException{
		LOGGER.debug("Persist del excep");
		Session session = this.getCurrentSession();
		String tableName = this.getEntClassName().getSimpleName();
		String hql = String.format("DELETE FROM %s WHERE %s <>  :match ", tableName, field);
		Query query = session.createQuery(hql);
		query.setString("match", match);
		try {
			query.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("FATAL HIBERNATE EXCEPTION", e);
			throw new DaoException("FATAL HIBERNATE EXCEPTION", e);
		}
	}

}
