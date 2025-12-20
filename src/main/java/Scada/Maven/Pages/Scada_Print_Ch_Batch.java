package Scada.Maven.Pages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Scada_Print_Ch_Batch 
{

	WebDriver driver;
	WebDriverWait wait;

	public Scada_Print_Ch_Batch(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	
	@FindBy(xpath = "//*[@id='popup']/button")
	WebElement pop_RMC;
	
	@FindBy(xpath = "(//i[@class='fa fa-caret-down'])[3]")
	WebElement Menu_List;
	

	@FindBy(xpath = "//a[normalize-space()='Batch List']")
	WebElement Menu_BatchList;
	
	
	@FindBy(xpath = "//input[@id='ctl00_ctpContent_txtBatchFrom']")
	WebElement BatchFrom;
	
	@FindBy(xpath = "//input[@id='ctl00_ctpContent_txtBatchTo']")
	WebElement BatchTo;
	
	@FindBy(xpath = "//input[@id='ctl00_ctpContent_btnSearch']")
	WebElement Btn_Search;
	
	@FindBy(xpath = "//input[@id='ctl00_ctpContent_gvBatchList_ctl02_imgPrint']")
	WebElement Click_Batch_report;
	
	@FindBy(xpath = "//input[@id='ctl00_ctpContent_gvBatchList_ctl02_imgNoteC1']")
	WebElement Click_Challan;
	
	@FindBy(xpath = "//input[@id='ctl00_ctpContent_btnSave']")
	WebElement Btn_Save_Challan;
	
	@FindBy(xpath = "//div[@class='alert alert-success']")
	WebElement Ch_Save_Message;
	
	@FindBy(xpath = "//input[@id='ctl00_ctpContent_btnPrint']")
	WebElement Btn_Print_Challan;
	
//	@FindBy(xpath = "//input[@id='ctl00_ctpContent_txtBatchFrom']")
//	WebElement BatchFrom;
	
	
	@SuppressWarnings("resource")
	public void Print_CH_Batch(String excelpath) throws IOException, InterruptedException 
	{
		FileInputStream fis = new FileInputStream(excelpath + ".xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		XSSFCell cell = null;

		int rowCount = sheet.getPhysicalNumberOfRows() - 1;

		pop_RMC.click();
		Menu_List.click();
		Menu_BatchList.click();
		
		System.out.println("No of Record Found Into Excel :- " + rowCount);
		
		for (int i = 1; i <=rowCount; i++) 
		{

			try 
			{
				//For Batch Report Print
				BatchFrom.sendKeys(sheet.getRow(i).getCell(0).getRawValue());
				BatchTo.sendKeys(sheet.getRow(i).getCell(0).getRawValue());
				Btn_Search.click();
				Thread.sleep(3000);
				Click_Batch_report.click();
				Thread.sleep(2000);
				((JavascriptExecutor) driver).executeScript("window.print();"); 
				Thread.sleep(1000);
				driver.navigate().back();
				Thread.sleep(2000);
				System.out.println(i + ": Batch Report print Done for batch no :-" + sheet.getRow(i).getCell(0).getRawValue());
				
				//For CHallan Print
				BatchFrom.sendKeys(sheet.getRow(i).getCell(0).getRawValue());
				BatchTo.sendKeys(sheet.getRow(i).getCell(0).getRawValue());
				Btn_Search.click();
				Thread.sleep(3000);
				Click_Challan.click();
				Btn_Save_Challan.click();
				
				//Verified Save CHallan Message
				if(Ch_Save_Message.getText().contains("Delivery Challan Created Successfully."))
				{
					Btn_Print_Challan.click();
					Thread.sleep(3000);
					((JavascriptExecutor) driver).executeScript("window.print();"); 
					Thread.sleep(2000);
					driver.navigate().back();
					Thread.sleep(2000);
					driver.navigate().back();
					Thread.sleep(2000);
					System.out.println(i + ": Challan Print Done for batch no :-" + sheet.getRow(i).getCell(0).getRawValue());
					cell = sheet.getRow(i).createCell(3);
					cell.setCellValue("PASS");

					FileOutputStream outputStream = new FileOutputStream(excelpath+"_01.xlsx");
					wb.write(outputStream);
				}
				
				else 
				{
					System.out.println(Ch_Save_Message.getText()+"Fail to save Challn");
				}
			}
		
			catch (Exception e) 
			{
				// Write Into excel
				cell = sheet.getRow(i).createCell(3);
				cell.setCellValue("Fail");
				FileOutputStream outputStream = new FileOutputStream(excelpath+"_02.xlsx");
				wb.write(outputStream);

				// For restart Loop Again
				Menu_List.click();
				Thread.sleep(3000);
				driver.findElement(By.linkText("Batch List")).click();
				continue;
			}
		}
	}
	
	
	
}
