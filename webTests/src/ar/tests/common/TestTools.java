package ar.tests.common;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestTools {
	
	
	/**
	 * Add extra time for load testing emulating human behavior by delaying flow.
	 * @param humanFactor
	 * @param centesimas
	 * @throws InterruptedException
	 */
	public static void addHumanFactor(boolean humanFactor, int centesimas)
			throws InterruptedException {

		if (humanFactor) {
			for (int i = 0; i < centesimas; i++) {
				Thread.sleep(100);
			}
		}

	}
	
	
	/**
	 * Wait for specific status on the element selected.
	 * 
	 * @param driver
	 * @param timeOut
	 * @param status
	 * @param elementId
	 * @param sleep
	 * @throws InterruptedException
	 */
	public static void wait4elementStatus(WebDriver driver, int timeOut, String status, String elementId, int sleep) throws InterruptedException {
		// testing if order is saved
		for (int second = 0;; second++) {
			if (second >= timeOut)
				Assert.fail("Timeout waiting for " + elementId);
			try {
				if (status.equals(driver.findElement(By.id(elementId))
						.getAttribute("value")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(sleep);
		}
	}
	
	
	/**
	 * Wait specified time.
	 * @param centesimas
	 * @throws InterruptedException
	 */
	public static void sleep(int centesimas) throws InterruptedException {

		for (int i = 0; i < centesimas; i++) {
			Thread.sleep(100);

		}

	}

}
