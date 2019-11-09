package ar.tests.agenda;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class Thread4LoadTest extends Thread {

	public Thread4LoadTest(String userName, CountDownLatch latch) {
		super();

		this.userName = userName;
		this.latch = latch;
	}

	private WebDriver driver;
	private String baseUrl;
	private String userName;
	private CountDownLatch latch;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"HH:mm:ss");

	public void run() {

		for (int i = 0; i < 1; i++) {

			String start = dateFormat.format(new Date());

			System.out.println(start + " Empieza el usuairo " + userName);
			driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_24);
			((HtmlUnitDriver) driver).setJavascriptEnabled(true);
			baseUrl = System.getProperty("test.url");
			driver.get(baseUrl);

			AgendaInstructions instructions = new AgendaInstructions();

			try {
				instructions.executeInstructions(driver, userName, true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			driver.quit();

			String stop = dateFormat.format(new Date());
			System.out.println(stop + " Termino el usuairo " + userName);

		}

		latch.countDown();

	}

}
