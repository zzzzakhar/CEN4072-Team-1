import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Testests {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  
  private String username;
  private String pwd;
  
  
  public Testests(String username, String pwd) {
	  super();
	  this.username = username;
	  this.pwd = pwd;
  }
  

  @Before
  public void setUp() {
	  // Setting up path to Google Chrome driver (chdriver)
	System.setProperty("webdriver.chrome.driver", "/Users/kianmaroofi/git/repository/PMS/chdriver");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  
  @Parameterized.Parameters
  public static Collection input() {
	  return Arrays.asList(new Object[][] {  {"abcdefghijklmno", "1234"
	  }, {"errorologin", "234354"}, {"user1", "user1"}});
  }
  
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void test() throws Exception {
	    driver.get("http://localhost:8080/PMS/index.jsp");
	    //driver.manage().window().setSize(new Dimension(1440, 822));
	    driver.findElement(By.cssSelector("li:nth-child(2) span")).click();
	    driver.findElement(By.id("c")).click();
	    driver.findElement(By.id("c")).sendKeys(username);
	    driver.findElement(By.name("pwd")).sendKeys(pwd);
	    driver.findElement(By.name("pwd")).sendKeys(Keys.ENTER);
	    driver.findElement(By.cssSelector("h1")).click();
	    driver.findElement(By.cssSelector("h1")).click();
	    driver.findElement(By.cssSelector("h1")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector("h1"));
	      Actions builder = new Actions(driver);
	      builder.doubleClick(element).perform();
	    }
	    driver.findElement(By.cssSelector("h1")).click();
	    driver.findElement(By.cssSelector("h1")).click();
	    driver.findElement(By.cssSelector("h1")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector("h1"));
	      Actions builder = new Actions(driver);
	      builder.doubleClick(element).perform();
	    }
	    driver.findElement(By.cssSelector("h1")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector("h1"));
	      Actions builder = new Actions(driver);
	      builder.doubleClick(element).perform();
	    }
	    driver.findElement(By.cssSelector("h1")).click();
	    driver.findElement(By.cssSelector("h1")).click();
	    driver.findElement(By.cssSelector("h1")).click();
	    driver.findElement(By.cssSelector("h1")).click();
	    driver.findElement(By.cssSelector("h1")).click();
	    driver.findElement(By.cssSelector("h1")).click();
	    String result = driver.findElement(By.cssSelector("h1")).getText();

	    assertNotNull(result);
	    if (result.equals("Error :Admin account not found")) {
	    	throw new NotFoundException();
	    }
	    assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Payroll Management System : Employer Module");
	        
	}
}
