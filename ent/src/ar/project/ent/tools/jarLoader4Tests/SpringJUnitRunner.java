package ar.project.ent.tools.jarLoader4Tests;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class SpringJUnitRunner extends SpringJUnit4ClassRunner {

	public SpringJUnitRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
		Load.execute();
		
	}

}
