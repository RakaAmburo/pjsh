package ar.project.persist.area.imp;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ar.project.ent.entities.area.Country;
import ar.project.persist.area.interfaces.CountryDao;
import ar.project.persist.base.AbstractHibernateDAO;
import ar.project.persist.base.CountryList;
import ar.project.persist.exceptions.DaoException;

@Repository("CountryDao")
public class CountryDaoImp extends AbstractHibernateDAO<Country, Integer>
		implements CountryDao {

	@Override
	public void auxExec() {

		Session session = this.getCurrentSession();
		session.createSQLQuery("drop table if exists countries")
				.executeUpdate();
		session.createSQLQuery(
				"create table countries (id INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ "name TEXT NOT NULL, code TEXT NOT NULL)")
				.executeUpdate();

		List<Country> countries = CountryList.getPaises();
		for (Country country : countries) {
			try {
				save(country);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected Class<Country> getEntClassName() {
		return Country.class;
	}

	@Override
	protected Class<Integer> getIdClassName() {
		return Integer.class;
	}

}
