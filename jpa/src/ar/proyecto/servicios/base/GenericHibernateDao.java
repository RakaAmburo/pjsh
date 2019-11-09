package ar.proyecto.servicios.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import ar.proyecto.servicios.exceptions.DaoException;

public interface GenericHibernateDao<ENT, ID extends Serializable> {
	
	public ENT load(ID id);
	public ID save(ENT entity);
	public void delete(ENT entity);
	public ENT update(ENT entity) throws DaoException;
	public List<ENT> find(Map<String, Object> criteria);
	public List<ENT> findLike(Map<String, String> criteria);
	public void truncateTable();
	public void batchInsert(List<ENT> inserts);

}
