package projectMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver; // If can't resolved: remove alpha from selenium-java dependency
import org.openqa.selenium.chrome.ChromeDriver; // If can't resolved: remove alpha from selenium-java dependency
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;



public class Base {
	public WebDriver driver;
	public Properties prop;	
	
     //Step-1: For SauceLab - To Pass Values.
	public static final String USERNAME = "ahamaz";
	public static final String ACCESS_KEY = "cf576093-9f03-4e7e-a25f-c826450b0825";
	public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.eu-central-1.saucelabs.com:80/wd/hub";
	
	
	public WebDriver initializeDriver() throws IOException {
		// TODO Auto-generated method stub

		//Add the data.properties file address and than throws FileNotFoundException
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\projectMethods\\data.properties"); // Dynamic 228
		prop=new Properties(); // Object to fetch data from data.properties
		prop.load(fis); // load data from file using fis address. To remove red Add throw IOException will replace previous throws.
		
//		String browserName=System.getProperty("browser"); //Get the Browser value from command....225 further improvement [Run from Jenkins only]
		String browserName=prop.getProperty("browser"); //Get the Browser value from data.properties. [Run from cmd with mvn test only]
		System.out.println("The Browser requested to use: " +browserName);
		
		// Checking and Selecting the available Browser.
		if(browserName.contains("chrome")) //229 add contains
		{
//			System.setProperty("webdriver.chrome.driver", "C:\\Users\\ahasan.manzoor.ali\\eclipse Tools\\chromedriver.exe"); // Normal
			System.setProperty("webdriver.chrome.driver", "ResourcesProject\\drivers\\chromedriver.exe"); // Dynamic 228
			
			 //These two lines plus add in thrird for headless test running: 229
			 ChromeOptions options=new ChromeOptions(); // import property 229
			 
			 if (browserName.contains("headless"))
			 {
				 options.addArguments("headless"); //229 > use with if option to run with and without headless.
			 }
		}
		else if (browserName.equals("firefox")) // using [equals] instead of [==], because getting value of attribute [browser=chrome]from another file. if [==], Nullpointer error.
		{
//			System.setProperty("webdriver.gecko.driver", "C:\\Users\\ahasan.manzoor.ali\\eclipse Tools\\geckodriver.exe"); // Normal
			System.setProperty("webdriver.gecko.driver", "ResourcesProject\\drivers\\geckodriver.exe"); // Dynamic 228
//			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\geckodriver.exe"); // Dynamic 228
			driver= new FirefoxDriver();
			//firefox code
		}
		else if (browserName.equals("IE"))
		{
//			 System.setProperty("webdriver.chrome.driver", "C:\\Users\\ahasan.manzoor.ali\\eclipse Tools\\chromedriver.exe"); // Normal
			 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe"); // Dynamic 228

			 driver= new ChromeDriver(); // now we can use with [Webdriver] extension.
		}
		
		// For One Browser on same PC
//		 driver= new ChromeDriver(options); // now we can use with [Webdriver] extension.
		 
		// For Cross Browser Testing.
//		 RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/"), new ChromeOptions());// Not the PC-B Url.
		 
		//Working in Selenium 4, code from SauceLab.
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("98");
		Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("name", "Maven SauceLab CloudTesting"); // Sending Name.
		browserOptions.setCapability("sauce:options", sauceOptions);
		driver=new RemoteWebDriver(new java.net.URL(URL), browserOptions);
			
			
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Setting time for all test cases before they fail, incase load is taking time.
		return driver; // once all done, driver is being returned to others to use it. plus change [void > webdiver] to remove error.
	}

	// Implement Screen shot method to use with Listener class.
	//218. Screenshots on failure- TestNG Listeners - 13min
	//219. How to send Driver object of TestInstance to Listener on Test failure - 8m
//	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException // 222 :  change void to string.
//	{
//		TakesScreenshot ts=(TakesScreenshot) driver;
//		File source =ts.getScreenshotAs(OutputType.FILE);
//		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
//		FileUtils.copyFile(source,new File(destinationFile));
//		return destinationFile; // 222
//	}

	
} 
