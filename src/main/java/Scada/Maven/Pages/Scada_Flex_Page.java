package Scada.Maven.Pages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Scada.Maven.Resouces.Utility;

public class Scada_Flex_Page extends Utility 
{
	WebDriver driver;
	WebDriverWait wait;

	public Scada_Flex_Page(WebDriver driver) 
	{
		//super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	// ---------------- BATCH PAGE ----------------
	@FindBy(id = "ctl00_cphBody_txtBatchFrom")
	WebElement batchFrom;

	@FindBy(id = "ctl00_cphBody_txtBatchTo")
	WebElement batchTo;

	@FindBy(id = "ctl00_cphBody_btnSearch")
	WebElement btnSearch;

	@FindBy(id = "ctl00_cphBody_gvBatchList_ctl02_lnkBatchNo")
	WebElement openBatch;

	// ---------------- QTY & GRADE ----------------
	@FindBy(id = "ctl00_cphBody_txtPrintProductionQty")
	WebElement txtQty;

	@FindBy(xpath = "//button[contains(text(),'OK')]")
	WebElement btnOK;

	@FindBy(id = "ctl00_cphBody_ddlReceipeCode")
	WebElement ddlGrade;

	@FindBy(id = "ctl00_cphBody_btn_submit")
	WebElement btnSubmit;
	
	@FindBy(id = "ctl00_cphBody_btn_clear")
	WebElement btnClaer;
	
	
	

	@FindBy(xpath = "//p[contains(text(),'Batch created successfully.')]")
	WebElement msgSuccess;

	@FindBy(id = "ctl00_cphBody_drpCustomer")
	WebElement ddlCustomer;

	@FindBy(id = "ctl00_cphBody_drpsite")
	WebElement ddlSite;

	@FindBy(id = "ctl00_cphBody_ddlTruckNo")
	WebElement ddlVehicle;

	@FindBy(id = "ctl00_cphBody_txtPrintTruckDriver")
	WebElement txtDriver;

	@SuppressWarnings("resource")
	public void Flex_Bathc(String excelpath) throws IOException, InterruptedException 
	{
		FileInputStream fis = new FileInputStream(excelpath + ".xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		XSSFCell cell = null;

		int rowCount = sheet.getPhysicalNumberOfRows() - 1;

		System.out.println("Total Rows Found = " + rowCount);

		for (int i = 1; i <= rowCount; i++) {
			batchFrom.clear();
			batchFrom.sendKeys(sheet.getRow(i).getCell(0).getRawValue());

			batchTo.clear();
			batchTo.sendKeys(sheet.getRow(i).getCell(0).getRawValue());

			btnSearch.click();
			Thread.sleep(2000);
			waitForLoaderToDisappear(driver);
			openBatch.click();
			if (isDisaplyed(By.id("ctl00_cphBody_drpCustomer"), driver, 15) == true)
				;
			waitForLoaderToDisappear(driver);

			// For Customer
			Dropdown(ddlCustomer, sheet.getRow(i).getCell(2).getStringCellValue());

			// For Site
			Dropdown(ddlSite, sheet.getRow(i).getCell(3).getStringCellValue());

			// For Vehicle
			Dropdown(ddlVehicle, sheet.getRow(i).getCell(6).getStringCellValue());

			//For Qty and Get ROws
			txtQty.clear();
			Thread.sleep(2000);
			btnOK.click();
			Thread.sleep(1000);
			txtQty.sendKeys(sheet.getRow(i).getCell(4).getRawValue());
			Thread.sleep(1500);
			btnOK.click();
			Thread.sleep(1000);
			Select dropdown_grade = new Select(ddlGrade);// For select Hardware Type
			dropdown_grade.selectByVisibleText(sheet.getRow(i).getCell(5).getStringCellValue());
			waitForLoaderToDisappear(driver);
			
			if (isDisaplyed(By.xpath("//div[@class='sweet-alert showSweetAlert visible']//button[contains(text(),'OK')]"), driver, 10000) == true);
			driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
			waitForLoaderToDisappear(driver);
			Thread.sleep(1500);
			
			btnSubmit.click();
			Thread.sleep(2500);
			waitForLoaderToDisappear(driver);
			if (isDisaplyed(By.xpath("//p[contains(text(),'Batch created successfully.')]"), driver, 15) == true);
			
			cell = sheet.getRow(i).createCell(6);
			
			String text = msgSuccess.getText();

			if (msgSuccess.isDisplayed()) 
			{
				cell.setCellValue("PASS");
			} 
			 
			FileOutputStream outputStream = new FileOutputStream(excelpath+"_01.xlsx");
			wb.write(outputStream);
			
			System.out.println(i +":-" + sheet.getRow(i).getCell(0).getRawValue() + ":" + text);
			Thread.sleep(3000);
			btnOK.click();
			btnClaer.click();
			Thread.sleep(2000);
		}

	}

}
