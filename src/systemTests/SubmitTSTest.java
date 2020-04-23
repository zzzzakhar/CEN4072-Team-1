package systemTests;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.ModelFacade;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;

import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

@RunWith(Parameterized.class)
public class SubmitTSTest {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	private String username;
	private String pwd;

	public SubmitTSTest(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}


	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\kianm\\repos\\CEN4072-Team-1\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
		// Processing other test environment requirements using ModelFacade
		// Employee Login Requirement
		String loginResult = ModelFacade.Userauthenticate("1", "adam", "adam");
	}

	@Parameterized.Parameters
	public static Collection input() {
		return Arrays.asList(new Object[][] {  {"abcdefghijklmno", "1234"
		}, {"errorologin", "234354"}, {"user1", "user1"}, {"mcdlr", "1234$"},
			{"test", "pass"}, {"user", "pass"}});
	}


	@After
	public void tearDown() {
		driver.quit();
	}
	@Test
	public void submitTS() {
		// Test name: SubmitTS
		// Step # | name | target | value | comment
		// 1 | open | http://localhost:8080/PMS/employeehome.jsp |  | 
		driver.get("http://localhost:8080/PMS/employeehome.jsp");
		// 2 | setWindowSize | 1200x781 |  | 
		driver.manage().window().setSize(new Dimension(1200, 781));
		
		
	    WebElement searchBtn = driver.findElement(By.linkText("Time Sheets"));

		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		
		// 3 | click | linkText=Add Time Sheets |  | 
		driver.findElement(By.linkText("Add Time Sheets")).click();
		// 4 | click | name=button |  | 
		driver.findElement(By.name("button")).click();
		
		try 
		{
			//Handle the alert pop-up 
			Alert alert = driver.switchTo().alert();

			//get the message which is present on pop-up
			String message = alert.getText();

			assertTrue(message.contains("time sheet submitted"));

			//print the pop-up message
			System.out.println(message);
			//Click on OK button on pop-up
			alert.accept();
		} 
		catch (NoAlertPresentException e) 
		{
			//if alert is not present print message
			System.out.println("alert is not present");
		}

		
		// 5 | close |  |  | 
		driver.close();
	}
}
