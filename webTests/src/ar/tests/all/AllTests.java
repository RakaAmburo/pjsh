package ar.tests.all;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ar.tests.agenda.AgendaBackGroundTest;
import ar.tests.agenda.AgentaChromeTest;
import ar.tests.agenda.AgendaFirefoxTest;
import ar.tests.agenda.AgentaIExpTest;

@RunWith(Suite.class)
@SuiteClasses({ AgentaChromeTest.class, AgendaFirefoxTest.class,
	AgentaIExpTest.class,AgendaBackGroundTest.class })
public class AllTests {

}