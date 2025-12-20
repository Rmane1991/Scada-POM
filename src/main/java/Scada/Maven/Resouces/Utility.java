package Scada.Maven.Resouces;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Utility {

	Actions act;

	@FindBy(xpath = "//div[@class='toast-message']")
	WebElement Alert_Toast_Msg;
	
	public static void login_Flex(WebDriver wd, String url, String User, String Pass, String refcode) 
	{
		wd.manage().window().maximize();
		wd.get(url);
		wd.findElement(By.id("txtLoginId")).sendKeys(User);
		wd.findElement(By.id("txtPassword")).sendKeys(Pass);
		wd.findElement(By.id("txtRefCode")).sendKeys(refcode);
		wd.findElement(By.id("btnSubmit")).click();
		System.out.println("Login Done");
		
		//Zoom Out set to 75%
		JavascriptExecutor js = (JavascriptExecutor) wd;
		js.executeScript("document.body.style.zoom='75%'");
		
	}

	public void print(WebDriver wd, String id, String cat) {
		WebElement print_msg = wd.findElement(By.id(id));
		String text = print_msg.getText();
		System.out.println(cat + " " + text);
	}

	public static void login_BMC(WebDriver wd, String url, String User, String Pass) throws InterruptedException {
		wd.manage().window().maximize();
		wd.get(url);
		wd.findElement(By.id("txtLoginId")).sendKeys(User);
		wd.findElement(By.id("txtPassword")).sendKeys(Pass);
		wd.findElement(By.id("btnSubmit")).click();
		Thread.sleep(2000);
		wd.findElement(By.xpath("//a[@id='dtlstModules_ctl00_lblModuleName']")).click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) wd;
		js.executeScript("document.body.style.zoom='75%'");

		System.out.println("Login Done");

	}
	
	public static void Win_Print(WebDriver driver ) throws InterruptedException 
	{
		((JavascriptExecutor) driver).executeScript("window.print();");
		Thread.sleep(1500);
	}
	
	
	
	public void pressEnter() 
	{
		act.sendKeys(Keys.ENTER).build().perform();
	}

	public void pressUpKeys() {
		act.sendKeys(Keys.UP).build().perform();
	}

	public void moveToElementAndClick(WebElement ele)

	{
		act.moveToElement(ele).click().build().perform();
	}

	public void moveToTop()
	{
		act.sendKeys(Keys.HOME).perform();
	}

//	public void scrollToElement(WebElement element) 
//	{
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", element);
//		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//	}
//
//	public void scrollToElementAndClick(WebElement element) 
//	{
//		try {
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//			Thread.sleep(2000);
//			wait.until(ExpectedConditions.elementToBeClickable(element));
//			element.click();
//		} catch (Exception e) 
//		{
//			System.out.println("Error while scrolling to bottom and clicking: " + e.getMessage());
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript("arguments[0].click();", element);
//		}
//	}


	
	public static void waitForLoaderToDisappear(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40)); // Set timeout as required
		wait.until(driver1 -> {
			JavascriptExecutor js = (JavascriptExecutor) driver1;
			String script = "return document.getElementById('ctl00_cphBody_upLoader').style.display === 'none';";
			return (Boolean) js.executeScript(script);
		});
	}

	public static boolean isDisaplyed(By Locator, WebDriver wd, long tm) {
		boolean isDisplayed = false;

		try {

			WebDriverWait wt = new WebDriverWait(wd, Duration.ofSeconds(tm));
			wt.until(ExpectedConditions.visibilityOfElementLocated(Locator));
			isDisplayed = true;
		} catch (Exception e)

		{
		}

		return isDisplayed;

	}

	public static boolean isInvisible(By Locator, WebDriver wd, long tm) {
		boolean isDisplayed = false;

		try {

			WebDriverWait wt = new WebDriverWait(wd, Duration.ofSeconds(tm));
			wt.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
			isDisplayed = true;
		} catch (Exception e)

		{
		}

		return isDisplayed;

	}

	public void hideElement(String xpath) {

	}

	public void Dropdown(WebElement cat, String visible) {
		// WebElement myEleDp = wd.findElement(By.id(cat));
		Select dropdown = new Select(cat);// For select Hardware Type
		dropdown.selectByVisibleText(visible);

	}
	

	
	

}
