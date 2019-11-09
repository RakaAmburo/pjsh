package ar.tests.agenda;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AgentaChromeTest extends AgendaTest {

	private String baseUrl;


	@Before
	public void setUp() throws Exception {
		File file = new File(
				"drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		baseUrl = System.getProperty("test.url");
		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get(baseUrl);

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
