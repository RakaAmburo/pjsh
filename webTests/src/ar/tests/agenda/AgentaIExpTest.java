package ar.tests.agenda;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AgentaIExpTest extends AgendaTest {

	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		File file = new File(
				"drivers/IEDriverServer32251.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		driver = new InternetExplorerDriver();
		baseUrl = System.getProperty("test.url");

		driver.get(baseUrl);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
