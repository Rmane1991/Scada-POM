package Scada.Maven.Testcases;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public static WebDriver driver;

	public WebDriver launchBrowser() {

		ChromeOptions options = new ChromeOptions();

		// Preferences
		HashMap<String, Object> prefs = new HashMap<>();
		prefs.put("download.default_directory", System.getProperty("user.dir") + "\\downloadFiles\\");
		prefs.put("browser.download.manager.closeWhenDone", true);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("autofill.profile_enabled", false);
		prefs.put("autofill.credit_card_enabled", false);

		options.setExperimentalOption("prefs", prefs);

		// Arguments
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--kiosk-printing");
		options.addArguments("--disable-features=InsecureDownloadWarnings");
		// options.addArguments("--disable-print-preview");
		options.addArguments("--force-device-scale-factor=0.75");
		options.addArguments("--high-dpi-support=0.75");

		// Setup & launch
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);

		// Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;


	}

	public static void login_Flex(String User, String Pass, String refcode) {
		driver.manage().window().maximize();
		driver.get("http://www.bmc-scada.online/flex/login.aspx");
		driver.findElement(By.id("txtLoginId")).sendKeys(User);
		driver.findElement(By.id("txtPassword")).sendKeys(Pass);
		driver.findElement(By.id("txtRefCode")).sendKeys(refcode);
		driver.findElement(By.id("btnSubmit")).click();
		System.out.println("Login Done");

		// Zoom Out set to 75%
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='75%'");

	}

	public static void login_BMC() throws InterruptedException ////String url, String User, String Pass
	{ 
		driver.manage().window().maximize();
		driver.get("http://www.bmc-scada.online/app/default.aspx");
		driver.findElement(By.id("txtLoginId")).sendKeys("ME");
		driver.findElement(By.id("txtPassword")).sendKeys("MRMA!@489");
		driver.findElement(By.id("btnSubmit")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='dtlstModules_ctl00_lblModuleName']")).click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='75%'");

		System.out.println("Login Done");

	}

}
