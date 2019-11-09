package ar.tests.agenda;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gargoylesoftware.htmlunit.BrowserVersion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AgendaBackGroundTest extends AgendaTest {

	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_24);
		((HtmlUnitDriver) driver).setJavascriptEnabled(true);
		baseUrl = System.getProperty("test.url");

		driver.get(baseUrl);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();

	}

}
