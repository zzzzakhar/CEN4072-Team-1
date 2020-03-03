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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

@RunWith(Parameterized.class)
public class AddEmployeeTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  
  private String empID;
	private String empFirstName;
	private String empLastName;
	private String empDOB;
	private String empJob;
	private String empContact;
	private String empEmail;
	private String empAddress;
	private String empAccno;
	private String empBankName;
	

	public AddEmployeeTest(String empID, String empFirstName, String empLastName,
			String empDOB, String empJob, String empContact, String empEmail,
			String empAddress, String empAccno, String empBankName) {
		super();
		this.empID = empID;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empDOB = empDOB;
		this.empJob = empJob;
		this.empContact = empContact;
		this.empEmail = empEmail;
		this.empAddress = empAddress;
		this.empAccno = empAccno;
		this.empBankName = empBankName;
		
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
		return Arrays.asList(new Object[][] {  
			{"2", "Sara", "Smith", "1990-01-01", "Sales Assistant", "3053458989", "sara.smith@email.com ", "3456 NW 10th Ave", "1234567999", "Bank of America"},
			{"2", "John", "Smith", "1980-01-01", "Finance Manager", "3050000000", "john.smith@email.com ", "3456 NW 10th Ave", "1234567777", "Bank of America"},
			{"3", "Albert", "Lee", "1996-11-05", "Finance Manager", "8574567890", "alber.lee@gmx.com ", "", "009878678", "Wells Fargo"},
			{"2", "John", "Smith", "1980-21-10", "Finance Manager", "3050000000", "john.smith@email.com ", "3456 NW 10th Ave", "1234567777", "Bank of America"},
			{"2", "John", "Smith", "1980-01-01", "Finance Manager", "305-000-0000", "john.smith@email.com ", "3456 NW 10th Ave", "1234567777", "Bank of America"},
			{"2", "John", "Smith", "1980-01-01", "", "3050000000", "john.smith@email.com ", "3456 NW 10th Ave", "1234567777", "Bank of America"}
		});
	}
  
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void addEmployee() {
    // Test name: AddEmployee
    // Step # | name | target | value | comment
    // 1 | open | http://localhost:8080/PMS/emplrhome.jsp |  | 
    driver.get("http://localhost:8080/PMS/add_emp.jsp");
    // 2 | setWindowSize | 1200x781 |  | 
    driver.manage().window().setSize(new Dimension(1200, 781));
    // 3 | click | linkText=Add Employee |  | 
    //driver.findElement(By.linkText("Add Employee")).click();
    // 4 | click | id=c |  | 
    driver.findElement(By.id("c")).click();
    // 5 | type | id=c | 99 | 
    driver.findElement(By.id("c")).sendKeys(empID);
    // 6 | type | name=fname | John | 
    driver.findElement(By.name("fname")).sendKeys(empFirstName);
    // 7 | type | name=lname | Smith | 
    driver.findElement(By.name("lname")).sendKeys(empLastName);
    // 8 | click | name=gen |  | 
    driver.findElement(By.name("gen")).click();

    driver.findElement(By.name("dob")).sendKeys(empDOB);

    driver.findElement(By.name("job")).click();
    // 24 | type | name=job | Tester | 
    driver.findElement(By.name("job")).sendKeys(empJob);
    // 25 | type | name=contact | 1234567890 | 
    driver.findElement(By.name("contact")).sendKeys(empContact);
    // 26 | click | name=email |  | 
    driver.findElement(By.name("email")).click();
    // 27 | type | name=email | jsmith@email.com | 
    driver.findElement(By.name("email")).sendKeys(empEmail);
    // 28 | type | name=addr | 21 SW 10th ST | 
    driver.findElement(By.name("addr")).sendKeys(empAddress);
    // 29 | type | name=accno | 99999999 | 
    driver.findElement(By.name("accno")).sendKeys(empAccno);
    // 30 | click | name=bname |  | 
    driver.findElement(By.name("bname")).click();
    // 31 | type | name=bname | Bank Of America | 
    driver.findElement(By.name("bname")).sendKeys(empBankName);
    // 32 | click | id=submit |  | 
    driver.findElement(By.id("submit")).click();
    
    String result = driver.findElement(By.cssSelector("h1")).getText();
    String expectedAlertMessage = "Employee Detials Addded";
    String actualAlertMessage = driver.switchTo().alert().getText();
    assertTrue((actualAlertMessage.contains(expectedAlertMessage)) || (result.contains("Employee Details")));
    
  }
}