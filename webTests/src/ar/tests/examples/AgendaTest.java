package ar.tests.examples;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AgendaTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  File file = new File("C:/Users/Pablo/Dropbox/REPOSITORIOS/PJSH/webTests/drivers/IEDriverServer32.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
	// The Firefox driver supports javascript 
	driver = new InternetExplorerDriver();
    baseUrl = "http://localhost:8080/web/login";
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    
    driver.get(baseUrl);
  }

  @Test
  public void testAgenda() throws Exception {
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
    driver.findElement(By.name("j_username")).clear();
    driver.findElement(By.name("j_username")).sendKeys("pablo");
    driver.findElement(By.name("j_password")).clear();
    driver.findElement(By.name("j_password")).sendKeys("123456");
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.name("nombre")).sendKeys("Pablo");
    driver.findElement(By.name("apellido")).sendKeys("Paparini");
    driver.findElement(By.name("direccion")).sendKeys("Araoz 2879");
    driver.findElement(By.name("telefono")).sendKeys("4802-4419");
    driver.findElement(By.xpath("(//input[@name='pais'])[1]")).sendKeys("Cameroon");
    (new WebDriverWait(driver, 600))
     		  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()=\"Cameroon\"]")));
    driver.findElement(By.xpath("//*[text()=\"Cameroon\"]")).click();
    driver.findElement(By.name("personaExt.edad")).sendKeys("39");
    driver.findElement(By.name("personaExt.codigoPostal")).sendKeys("4545");
    driver.findElement(By.xpath("//button[@type='button']")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
    driver.findElement(By.xpath("(//input[@name='nombre'])[2]")).sendKeys("Hernan");
    driver.findElement(By.xpath("(//input[@name='apellido'])[2]")).sendKeys("Paparini");
    driver.findElement(By.xpath("(//input[@name='direccion'])[2]")).sendKeys("Sarguento");
    driver.findElement(By.xpath("(//input[@name='telefono'])[2]")).sendKeys("1234-1234");
    driver.findElement(By.xpath("(//input[@name='pais'])[2]")).sendKeys("Canada");
    (new WebDriverWait(driver, 600))
  		  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()=\"Canada\"]")));
    driver.findElement(By.xpath("//*[text()=\"Canada\"]")).click();
    driver.findElement(By.xpath("(//input[@name='personaExt.edad'])[2]")).sendKeys("41");
    driver.findElement(By.xpath("(//input[@name='personaExt.codigoPostal'])[2]")).sendKeys("1234");
    driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[10]")).click();
    driver.findElement(By.xpath("(//input[@name='nombre'])[3]")).sendKeys("Agustin");
    driver.findElement(By.xpath("(//input[@name='apellido'])[3]")).sendKeys("Paparini");
    driver.findElement(By.xpath("(//input[@name='direccion'])[3]")).sendKeys("Tower Ridge");
    driver.findElement(By.xpath("(//input[@name='telefono'])[3]")).sendKeys("1234-1234");
    driver.findElement(By.xpath("(//input[@name='pais'])[3]")).sendKeys("Iraq");
    (new WebDriverWait(driver, 600))
    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()=\"Iraq\"]")));
    driver.findElement(By.xpath("//*[text()=\"Iraq\"]")).click();
    driver.findElement(By.xpath("(//input[@name='personaExt.edad'])[3]")).sendKeys("4");
    driver.findElement(By.xpath("(//input[@name='personaExt.codigoPostal'])[3]")).sendKeys("1234");
    driver.findElement(By.xpath("(//button[@type='button'])[13]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[16]")).click();
    driver.findElement(By.xpath("(//input[@name='nombre'])[4]")).sendKeys("Tomas");
    driver.findElement(By.xpath("(//input[@name='apellido'])[4]")).sendKeys("Paparini");
    driver.findElement(By.xpath("(//input[@name='direccion'])[4]")).sendKeys("Recoleta");
    driver.findElement(By.xpath("(//input[@name='telefono'])[4]")).sendKeys("1234-1234");
    driver.findElement(By.xpath("(//input[@name='pais'])[4]")).sendKeys("Spain");
    (new WebDriverWait(driver, 600))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()=\"Spain\"]")));
    driver.findElement(By.xpath("//*[text()=\"Spain\"]")).click();
    driver.findElement(By.xpath("(//input[@name='personaExt.edad'])[4]")).sendKeys("1");
    driver.findElement(By.xpath("(//input[@name='personaExt.codigoPostal'])[4]")).sendKeys("3333");
    driver.findElement(By.xpath("(//button[@type='button'])[19]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[22]")).click();
    driver.findElement(By.xpath("(//input[@name='nombre'])[5]")).sendKeys("Gerardo");
    driver.findElement(By.xpath("(//input[@name='apellido'])[5]")).sendKeys("Martino");
    driver.findElement(By.xpath("(//input[@name='direccion'])[5]")).sendKeys("Palermo");
    driver.findElement(By.xpath("(//input[@name='telefono'])[5]")).sendKeys("4321-4321");
    driver.findElement(By.xpath("(//input[@name='pais'])[5]")).sendKeys("Zambia");
    (new WebDriverWait(driver, 600))
      .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()=\"Zambia\"]")));
    driver.findElement(By.xpath("//*[text()=\"Zambia\"]")).click();
    driver.findElement(By.xpath("(//input[@name='personaExt.edad'])[5]")).sendKeys("36");
    driver.findElement(By.xpath("(//input[@name='personaExt.codigoPostal'])[5]")).sendKeys("4444");
    driver.findElement(By.xpath("(//button[@type='button'])[25]")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  @SuppressWarnings("unused")
private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  @SuppressWarnings("unused")
private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  @SuppressWarnings("unused")
private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
