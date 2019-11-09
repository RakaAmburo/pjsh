package ar.tests.agenda.direct;

import java.lang.management.ManagementFactory;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.hibernate.SessionFactory;
import org.hibernate.jmx.StatisticsService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.project.ent.tools.jarLoader4Tests.Load;
import ar.project.web.actions.area.tests.Thread4PersonaLoadTest;

@SuppressWarnings("deprecation")
public class LaunchPerLoadTest {

	private int concurrentes = 300;
	private int personasCreadasPorUsuario = 20;
	private CountDownLatch latch = new CountDownLatch(concurrentes);

	public void launchTest() throws Exception {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"appCtx4Test.xml");
		
		SessionFactory sf = (SessionFactory) ctx
				.getBean("sessionFactory");
			
		
		StatisticsService statsMBean = new StatisticsService();
		statsMBean.setSessionFactory(sf);
		statsMBean.setStatisticsEnabled(true);

		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		try {
			mBeanServer.registerMBean(statsMBean, new ObjectName("Hibernate:application=Statistics"));
		} catch (InstanceAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MBeanRegistrationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotCompliantMBeanException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedObjectNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < concurrentes; i++) {
			Thread4PersonaLoadTest hilo = new Thread4PersonaLoadTest(ctx, latch, sf);
			hilo.setPersonasCreadasPorUsuario(personasCreadasPorUsuario);
			hilo.start();

			Thread.sleep(600);
		}

		System.out.println("SE TERMINO EL FOR");
		latch.await();
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    
	    String time = String.format("%02d:%02d:%02d:%02d", 
	    		TimeUnit.MILLISECONDS.toHours(elapsedTime),
	    		TimeUnit.MILLISECONDS.toMinutes(elapsedTime) -  
	    		TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(elapsedTime)), 
	    		TimeUnit.MILLISECONDS.toSeconds(elapsedTime) - 
	    		TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(elapsedTime)),
	    		elapsedTime - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(elapsedTime)));   
	    
	    System.out.println("Time: " +  time);
	}

	public static void main(String[] args) throws Exception {
		Load.execute();
		LaunchPerLoadTest test = new LaunchPerLoadTest();
		test.launchTest();
	}

}


//RESULTADOS:
//mysql bone 300 20 0 Time: 00:00:08:379 / Time: 00:00:12:328 more partitions takes more
//mysql c3po 300 20 0 Time: 00:00:07:89
//postgre c3po 300 20 0 Time: 00:00:09:679
//postgre bone 300 20 0 Time: 00:00:10:442
//oracle bone 300 20 0 Time: 00:00:12:882
//ocacle c3po 300 20 0 SE MUERE

//mysql bone 300 20 (600 160 30 10) Time: 00:09:41:353
//mysql spring 300 20 (600 160 30 10) Time: 00:09:41:582
//mysql c3p0 300 20 (600 160 30 10) Time: 00:09:41:582

//posgre bone 300 20 (600 160 30 10) Time: 00:09:41:35
//posgre c3po 300 20 (600 160 30 10) Time: 00:09:40:861
//posgre srping 300 20 (600 160 30 10) Time: Time: 00:09:41:643

//oracle bone 300 20 (600 160 30 10) Time: 00:09:41:329
//oracle c3p0 300 20 (600 160 30 10) Time: 00:09:41:329
//oracle spring falla
