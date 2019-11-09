package ar.proyecto.servicios.area.imp;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ar.project.ent.entities.area.Country;
import ar.proyecto.servicios.area.interfaces.CountryDao;
import ar.proyecto.servicios.base.AbstractHibernateDAO;
import ar.proyecto.servicios.base.CountryList;

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
			save(country);
		}

	}

	@Override
	protected Class<Country> getClassName() {
		return Country.class;
	}

}
