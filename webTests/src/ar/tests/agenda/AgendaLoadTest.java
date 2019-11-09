package ar.tests.agenda;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.project.ent.entities.area.User;
import ar.project.ent.tools.jarLoader4Tests.SpringJUnitRunner;
import ar.project.services.tools.UserProvider;

@RunWith(SpringJUnitRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "txnManager", defaultRollback = false)
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AgendaLoadTest {
	
	
	
	
	@Autowired
	private UserProvider userProvider;
	
	public String userName;
	
	//concurrentes 160, entre usuarios 1150 tarda 400s (6m) cada usuario tarda 2 minutos
	private int concurrentes = 150;
	
	//hay que probar cuanto tarda una grabada
	// modificar esto para que se sigan probando usuarios memory liking OK
	//y finalmente equlizar el todo sumano el tiempo del usuario
	// ver que peta
	
	//219.811 100p 500ms bonecp
	//231.224 100p 500ms org.springframework.jdbc.datasource.DriverManagerDataSource
	//------- 100p 500ms sqlite not working no concurrencies
	//228.896 100p 500ms c3po
	
	//649 300p 1600ms
	//701.439 80  1000 tomcat bone posgre
	//697.694 80  1000 tomcat c3p0 posgre
	
	//697.512 80  1000 tomcat bone mysql
	//693.426 80  1000 tomcat c3p0 mysql
	
	//701.251 80  1000 tomcat bone oracle
	//701.115 80  1000 tomcat c3p0 oracle
	
	//702.496 80  1000 webfear bone oracle
	//702.753 80  1000 webfear c3p0 oracle
	
	//704.913 80  1000 webfear bone posgre
	//694.799 80  1000 webfear c3p0 posgre
	
	//696.701 80  1000 webfear bone mysql
	//702.079 80  1000 webfear c3p0 mysql
	
	//708.620 80  1000 glassFish bone mysql
	//710.620 80  1000 glassFish c3p0 mysql
	
	//702.586 80  1000 glassFish bone posgre
	//703.808 80  1000 glassFish c3p0 posgre
	
	//711.829 80  1000 glassFish bone oracle
	//713.453 80  1000 glassFish c3p0 oracle
	
	@Test
	@Repeat(value = 1)
	public void loadTest() throws Exception{
		
		
		CountDownLatch latch = new CountDownLatch(concurrentes);
		
		for (int i = 0; i < concurrentes; i++) {
			
			User user = userProvider.take();
			userName = user.getUsername();
			Thread4LoadTest thread = new Thread4LoadTest(userName, latch);
			thread.start();
			
			Thread.sleep(500);
		}
		
		System.out.println("Se termino de lanzar todos los usuarios");
		latch.await();
	}

}

// RESULTADOS:
// springDriver funca muy mal con oracle (org.hibernate.exception.GenericJDBCException: Could not open connection) con mysql y postgres va como piña
