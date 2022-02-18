package ahamaz21;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import ahamaz21.Log4j2Alpha.demo;
import projectMethods.Base;
import projectMethods.BaseAndDataFiles_Calls_Driver_Url;
import projectMethods.DataDrivenExcelFetchDataMethod;

public class SeleniumBrowserTest extends Base{
	public static Logger log = LogManager.getLogger(SeleniumBrowserTest.class.getName());
	WebDriver driver;
	
	

	@Test
	public void SeleniumAutomation1() throws InterruptedException, IOException, SQLException {

		System.out.println("SeleniumBrowserTest - 3");
		// Create for Excel Destination:
//		DataDrivenExcelFetchDataMethod d = new DataDrivenExcelFetchDataMethod();
//		ArrayList<String> data = d.getData("Login"); // Getting data only from [Delete Profile] record/row.
//		log.info("Printing the URL of landed page.");
		
		// Create for MySQL Destination:
		String host="localhost"; //Host: where the database is, on our PC than use: localhost otherwise IP.
		String port="3306"; //that we selected while setting the database: 3306
		String UserName = "root";
		String Password = "EASY2say*";
		Connection con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/ahamaz", UserName, Password);
		// Create Road to to Destination:  Statement, Import [java.sql]
		Statement s=con.createStatement();
		// Use the Road to reach Destination and save data in ResultSet Varaible. Run command first in MySQL to confirm working fine and user exist.
		ResultSet rs=s.executeQuery("select * from LoginCredentials where name='Manzoor'");
		// In Maven: Error - Before start of result set. Following, error changed to
		while(rs.next()) {
//		      String name=rs.getString("name");
//		      String username=rs.getString("username");
//		      String password=rs.getString("password");
//		      String pincode=rs.getString("pincode");
//		}

		//Print What have been got from DataBase
		System.out.println("Data Fetched from MySQL DataBase");
		System.out.println(rs.getString("username"));
		System.out.println(rs.getString("password"));

		// Run Project in Sauce Lab.
		
		
        // Initializing the Driver.
		log.debug("Setting chrome driver property");
		
		// Directly from Driver from this Class: 
//		System.setProperty("webdriver.chrome.driver", "ResourcesProject\\drivers\\chromedriver.exe");
//		driver = new ChromeDriver();
	
		// From the Test File using Method below and call it inside:
		basePageNavigationIn(); // Calling from Same Class - Pass
		System.out.println(driver.getCurrentUrl()); //Getting Page Url.
		
//		BaseAndDataFiles_Calls_Driver_Url b = new BaseAndDataFiles_Calls_Driver_Url(); // Calling from other Package - Pass but unable to click a link as below.
//		b.basePageNavigationOut();
//		driver.navigate().forward();
//		System.out.println(driver.getCurrentUrl()); //Getting Page Url.
		
		// Confirm if we are on expected page using Page Title:
		System.out.println(driver.getTitle()); //Getting Page Url.
		Assert.assertEquals("Tesco.ie - online shopping; bringing the supermarket to you.", driver.getTitle());


		log.info("Accepting the Cookies.");
		driver.findElement(By.xpath("//*[@id=\'header\']/div[1]/div/div[2]/form/button/span/span")).click(); // Accept Cookies.
		driver.findElement(By.xpath("//*[@id=\'appbar.my-account\']/span[1]")).click(); // Click Sign-In button.

		driver.navigate().forward();
		
		log.info("Entering the Login Credentials.");
		// Getting Login Data from code here:
//		driver.findElement(By.id("txtUserName")).sendKeys("ahasanfsm@hotmail.com"); // Enter UserName
//		driver.findElement(By.id("txtPassword")).sendKeys("EASY2say*"); // Enter Password

		// Getting Login Data from Excel File:
//		driver.findElement(By.id("txtUserName")).sendKeys(data.get(1)); // Enter UserName
//		driver.findElement(By.id("txtPassword")).sendKeys(data.get(2)); // Enter Password
//		driver.findElement(By.id("btnSubmit")).click();

		// Getting Login Data from MySQL Database:
		// MySQL has been setup just before Initializing the Driver from Method:
		// Send data to website to login or for other purposes.
		driver.findElement(By.id("txtUserName")).sendKeys(rs.getString("username"));
		driver.findElement(By.id("txtPassword")).sendKeys(rs.getString("password"));
	    driver.findElement(By.id("btnSubmit")).click();

		
		
		System.out.println(driver.getCurrentUrl()); // Getting 2 Page Url to check if the same previous URL.
		//driver.close();
	}
	}

	@Test
	public void SeleniumAutomation2() {

		System.out.println("SeleniumBrowserTest - 4");
		// driver.findElement(By.xpath("//body/app-root[1]/div[1]/app-home[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]")).click();
		// // Click Join_Now button.
		driver.findElement(By.xpath("//iv[1]/div[1]/div[1]/div[1]/a[1]")).click(); // Click Join_Now button.

	}
	
//	@AfterTest
//	public void tearDown()
//	{
//		driver.close();
//	}
	
	public void basePageNavigationIn() throws IOException, InterruptedException{
	driver = initializeDriver();
	log.debug("Now hitting Tesco Server");
	driver.get(prop.getProperty("url"));
//	driver.get("https://www.tesco.ie/");
	log.info("Landed on amazon home page");
	
	log.info("Window Maximized");
	driver.manage().window().maximize();
	log.info("Waiting for page to upload fully.");
	Thread.sleep(5000);
	}	
}