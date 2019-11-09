package ar.tests.agenda;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static ar.tests.common.TestTools.addHumanFactor;
import static ar.tests.common.TestTools.wait4elementStatus;
import static ar.tests.common.TestTools.sleep;

public class AgendaInstructions {

	private static final String INPUT_NAME = "(//input[@name='%s'])[%s]";
	private static final String BUTTON_NAME = "(//button[@name='%s'])[%s]";
	private static final String A_TEXT = "//a[text()=\"%s\"]";
	private static final String NOMBRE = "nombre";
	private static final String APELLIDO = "apellido";
	private static final String TELEFONO = "telefono";
	private static final String PAIS = "pais";
	private static final String SAVE = "btnSave";
	private static final String ADD_DOWN = "addDown";
	private static final String ADD_UP = "addUp";

	private void fillRow(WebDriver driver, boolean humanFactor, boolean addMore,
			String... fields) throws InterruptedException {

		driver.findElement(
				By.xpath(String.format(INPUT_NAME, NOMBRE, fields[0])))
				.sendKeys(fields[1]);
		driver.findElement(
				By.xpath(String.format(INPUT_NAME, APELLIDO, fields[0])))
				.sendKeys(fields[2]);
		driver.findElement(
				By.xpath(String.format(INPUT_NAME, TELEFONO, fields[0])))
				.sendKeys(fields[3]);
		driver.findElement(By.xpath(String.format(INPUT_NAME, PAIS, fields[0])))
				.sendKeys(fields[4]);
		(new WebDriverWait(driver, 600)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(String.format(A_TEXT,
						fields[4]))));
		addHumanFactor(humanFactor, 12);
		/* Save an add */
		driver.findElement(By.xpath(String.format(A_TEXT, fields[4]))).click();
		addHumanFactor(humanFactor, 30);
		driver.findElement(
				By.xpath(String.format(BUTTON_NAME, SAVE, fields[0]))).click();
		addHumanFactor(humanFactor, 15);
		
		if (addMore){
			driver.findElement(
					By.xpath(String.format(BUTTON_NAME, fields[5], fields[6])))
					.click();
			addHumanFactor(humanFactor, 15);
		}
		
		sleep(5);

	}

	public void executeInstructions(WebDriver driver, String userName,
			boolean humanFactor) throws Exception {

		// Login
		driver.findElement(By.name("j_username")).clear();
		driver.findElement(By.name("j_username")).sendKeys(userName);
		addHumanFactor(humanFactor, 20);
		driver.findElement(By.name("j_password")).clear();
		driver.findElement(By.name("j_password")).sendKeys("123456");
		addHumanFactor(humanFactor, 15);
		driver.findElement(By.name("submit")).click();

		sleep(20);
		addHumanFactor(humanFactor, 30);

		// Filling data:

		fillRow(driver, humanFactor, true, "1", "Pablo", "Paparini", "4802-4419",
				"Cameroon", ADD_DOWN, "1");
		
		fillRow(driver, humanFactor, true, "2", "Hernan", "Paparini", "1234-1234",
				"Canada", ADD_DOWN, "2");

		fillRow(driver, humanFactor, true, "3", "Agustin", "Paparini", "1234-1234",
				"Iraq", ADD_DOWN, "3");

		fillRow(driver, humanFactor, true, "4", "Tomas", "Paparini", "1234-1234",
				"Spain", ADD_DOWN, "4");

		fillRow(driver, humanFactor, true, "5", "Gerardo", "Martino", "4321-4321",
				"Zambia", ADD_DOWN, "2");

		fillRow(driver, humanFactor, true, "3", "Estefi", "Graff", "1234-1234",
				"United States", ADD_UP, "5");

		fillRow(driver, humanFactor, true, "5", "Gandalf", "ElMago", "3333-4444",
				"Australia", ADD_UP, "1");

		fillRow(driver, humanFactor, true, "1", "Fernando", "Garrido", "333-2222",
				"France", ADD_DOWN, "8");

		fillRow(driver, humanFactor, false, "9", "Pandolfi", "Astulio", "1111-1111",
				"Italy");

		wait4elementStatus(driver, 60, "false", "orderChanged", 1000);
		wait4elementStatus(driver, 60, "0", "queue", 1000);
		
		sleep(5);

		addHumanFactor(humanFactor, 20);

		// Agregue este deslogue por que estba fallanod en SAVE por tags
		// ARREGLAAAARR
		sleep(5);
		addHumanFactor(humanFactor, 20);

		// Change places
		driver.findElement(By.xpath("(//button[@name='mvUp'])[8]")).click();
		wait4elementStatus(driver, 60, "false", "orderChanged", 1000);
		driver.findElement(By.xpath("(//button[@name='mvDown'])[2]")).click();
		wait4elementStatus(driver, 60, "false", "orderChanged", 1000);
		driver.findElement(By.xpath("(//button[@name='mvDown'])[3]")).click();
		wait4elementStatus(driver, 60, "false", "orderChanged", 1000);
		driver.findElement(By.xpath("(//button[@name='mvUp'])[4]")).click();
		wait4elementStatus(driver, 60, "false", "orderChanged", 1000);
		driver.findElement(By.xpath("(//button[@name='mvDown'])[5]")).click();
		wait4elementStatus(driver, 60, "false", "orderChanged", 1000);
		driver.findElement(By.xpath("(//button[@name='mvDown'])[7]")).click();
		wait4elementStatus(driver, 60, "false", "orderChanged", 1000);
		driver.findElement(By.xpath("(//button[@name='mvUp'])[8]")).click();
		wait4elementStatus(driver, 60, "false", "orderChanged", 1000);
		driver.findElement(By.xpath("(//button[@name='mvDown'])[2]")).click();
		wait4elementStatus(driver, 60, "false", "orderChanged", 1000);

		// Modifying one
		driver.findElement(By.xpath("(//input[@name='nombre'])[1]")).clear();
		driver.findElement(By.xpath("(//input[@name='nombre'])[1]")).sendKeys(
				"Ferdinando");
		sleep(10);
		addHumanFactor(humanFactor, 20);
		driver.findElement(By.xpath("(//button[@name='btnSave'])[1]")).click();

		addHumanFactor(humanFactor, 30);

		// Modifying a few and then save
		driver.findElement(By.xpath("(//input[@name='nombre'])[3]")).clear();
		driver.findElement(By.xpath("(//input[@name='nombre'])[3]")).sendKeys(
				"Estefania");
		driver.findElement(By.xpath("(//input[@name='nombre'])[8]")).clear();
		driver.findElement(By.xpath("(//input[@name='nombre'])[8]")).sendKeys(
				"Pandolfo");
		driver.findElement(By.xpath("(//input[@name='nombre'])[9]")).clear();
		driver.findElement(By.xpath("(//input[@name='nombre'])[9]")).sendKeys(
				"Paul");
		sleep(10);
		addHumanFactor(humanFactor, 30);
		driver.findElement(By.xpath("(//button[@name='btnSave'])[3]")).click();
		addHumanFactor(humanFactor, 10);
		driver.findElement(By.xpath("(//button[@name='btnSave'])[8]")).click();
		addHumanFactor(humanFactor, 10);
		driver.findElement(By.xpath("(//button[@name='btnSave'])[9]")).click();
		addHumanFactor(humanFactor, 10);

		wait4elementStatus(driver, 60, "0", "queue", 1000);
		
		sleep(5);
		addHumanFactor(humanFactor, 20);

		// Logout login
		driver.findElement(By.xpath(String.format(A_TEXT, "Logout"))).click();
		driver.findElement(By.name("j_username")).clear();
		driver.findElement(By.name("j_username")).sendKeys(userName);
		addHumanFactor(humanFactor, 20);
		driver.findElement(By.name("j_password")).clear();
		driver.findElement(By.name("j_password")).sendKeys("123456");
		addHumanFactor(humanFactor, 15);
		driver.findElement(By.name("submit")).click();

		sleep(5);
		addHumanFactor(humanFactor, 30);

		// Assertiing everything is ok
		
		Assert.assertEquals("Ferdinando",
				driver.findElement(By.xpath("(//input[@name='nombre'])[1]"))
						.getAttribute("value"));
		Assert.assertEquals("Pablo",
				driver.findElement(By.xpath("(//input[@name='nombre'])[2]"))
						.getAttribute("value"));
		Assert.assertEquals("Estefania",
				driver.findElement(By.xpath("(//input[@name='nombre'])[3]"))
						.getAttribute("value"));
		Assert.assertEquals("Estefi",
				driver.findElement(By.xpath("(//input[@name='nombre'])[4]"))
						.getAttribute("value"));
		Assert.assertEquals("Gandalf",
				driver.findElement(By.xpath("(//input[@name='nombre'])[5]"))
						.getAttribute("value"));
		Assert.assertEquals("Agustin",
				driver.findElement(By.xpath("(//input[@name='nombre'])[6]"))
						.getAttribute("value"));
		Assert.assertEquals("Gerardo",
				driver.findElement(By.xpath("(//input[@name='nombre'])[7]"))
						.getAttribute("value"));
		Assert.assertEquals("Pandolfo",
				driver.findElement(By.xpath("(//input[@name='nombre'])[8]"))
						.getAttribute("value"));
		Assert.assertEquals("Paul",
				driver.findElement(By.xpath("(//input[@name='nombre'])[9]"))
						.getAttribute("value"));
		 

		addHumanFactor(humanFactor, 30);

		// Asserts that the numbers are in place
		Assert.assertTrue(driver.findElements(
				By.xpath("//td[contains(text(),'1')]")).size() == 1);
		Assert.assertTrue(driver.findElements(
				By.xpath("//td[contains(text(),'2')]")).size() == 1);
		Assert.assertTrue(driver.findElements(
				By.xpath("//td[contains(text(),'3')]")).size() == 1);
		Assert.assertTrue(driver.findElements(
				By.xpath("//td[contains(text(),'4')]")).size() == 1);
		Assert.assertTrue(driver.findElements(
				By.xpath("//td[contains(text(),'5')]")).size() == 1);
		Assert.assertTrue(driver.findElements(
				By.xpath("//td[contains(text(),'6')]")).size() == 1);
		Assert.assertTrue(driver.findElements(
				By.xpath("//td[contains(text(),'7')]")).size() == 1);
		Assert.assertTrue(driver.findElements(
				By.xpath("//td[contains(text(),'8')]")).size() == 1);
		Assert.assertTrue(driver.findElements(
				By.xpath("//td[contains(text(),'9')]")).size() == 1);

		addHumanFactor(humanFactor, 30);
		
		

		// Delete rows
		driver.findElement(By.xpath("(//button[@name='btnDel'])[9]")).click();
		sleep(5);
		addHumanFactor(humanFactor, 10);
		driver.findElement(By.xpath("(//button[@name='btnDel'])[8]")).click();
		sleep(5);
		addHumanFactor(humanFactor, 10);
		driver.findElement(By.xpath("(//button[@name='btnDel'])[7]")).click();
		sleep(5);
		addHumanFactor(humanFactor, 10);
		driver.findElement(By.xpath("(//button[@name='btnDel'])[6]")).click();
		sleep(5);
		addHumanFactor(humanFactor, 10);
		driver.findElement(By.xpath("(//button[@name='btnDel'])[5]")).click();
		sleep(5);
		addHumanFactor(humanFactor, 10);
		driver.findElement(By.xpath("(//button[@name='btnDel'])[4]")).click();
		sleep(5);
		addHumanFactor(humanFactor, 10);
		driver.findElement(By.xpath("(//button[@name='btnDel'])[3]")).click();
		sleep(5);
		addHumanFactor(humanFactor, 10);
		driver.findElement(By.xpath("(//button[@name='btnDel'])[2]")).click();
		sleep(5);
		addHumanFactor(humanFactor, 10);
		driver.findElement(By.xpath("(//button[@name='btnDel'])[1]")).click();
		
		wait4elementStatus(driver, 60, "false", "orderChanged", 1000);
		
		wait4elementStatus(driver, 180, "0", "queue", 1000);
		
		sleep(5);
		addHumanFactor(humanFactor, 50);
		driver.findElement(By.xpath(String.format(A_TEXT, "Logout"))).click();

	}

}
