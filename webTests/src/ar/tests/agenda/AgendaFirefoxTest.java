package ar.tests.agenda;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AgendaFirefoxTest extends AgendaTest {

	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = System.getProperty("test.url");

		driver.get(baseUrl);
		System.out.println(baseUrl);
		driver.manage().window().maximize();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();

	}
}
