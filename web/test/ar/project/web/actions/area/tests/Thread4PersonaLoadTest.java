package ar.project.web.actions.area.tests;

import java.util.concurrent.CountDownLatch;

import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.project.ent.entities.area.User;
import ar.project.services.exceptions.ServException;

public class Thread4PersonaLoadTest extends Thread {
	
	private PersonaLoadTest personaLoadTest;
	
	private ClassPathXmlApplicationContext ctx;
	
	private int personasCreadasPorUsuario;
	
	private CountDownLatch latch;
	
	private SessionFactory sf;
	
	
	
	public Thread4PersonaLoadTest(ClassPathXmlApplicationContext ctx,
			CountDownLatch latch, SessionFactory sf) {
		super();
		this.ctx = ctx;
		this.latch = latch;
		this.sf = sf;
	}



	public void run() {
		
		personaLoadTest = ctx.getBean(PersonaLoadTest.class);
		personaLoadTest.setSf(sf);
		personaLoadTest.setPersonasCreadasPorUsuario(personasCreadasPorUsuario);
		try {
			User user = personaLoadTest.A_CreatePersonas();
			personaLoadTest.B_UpdatePersonas(user);
			personaLoadTest.C_DeletePersonas(user);
		} catch (ServException e) {
			e.printStackTrace();
			System.exit(0);
		}
		latch.countDown();
	}
	
	
	public int getPersonasCreadasPorUsuario() {
		return personasCreadasPorUsuario;
	}



	public void setPersonasCreadasPorUsuario(int personasCreadasPorUsuario) {
		this.personasCreadasPorUsuario = personasCreadasPorUsuario;
	}

}
