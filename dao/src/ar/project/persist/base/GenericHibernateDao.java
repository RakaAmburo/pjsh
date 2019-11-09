package ar.project.persist.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import ar.project.persist.exceptions.DaoException;

public interface GenericHibernateDao<ENT, ID extends Serializable> {
	
	public ENT load(ID id) throws DaoException;
	public ID save(ENT entity) throws DaoException;
	public void delete(ENT entity) throws DaoException;
	public ENT update(ENT entity) throws DaoException;
	public List<ENT> find(Map<String, Object> criteria) throws DaoException;
	public List<ENT> findLike(Map<String, String> criteria) throws DaoException;
	public void truncateTable() throws DaoException;
	public void batchInsert(List<ENT> inserts) throws DaoException;
	public void delExcept(String field, String match) throws DaoException;
	public void delOnly(String field, Integer match) throws DaoException;
	public List<ENT> findNe(String field, String value) throws DaoException;
	public ENT merge(ENT entity) throws DaoException;

}
