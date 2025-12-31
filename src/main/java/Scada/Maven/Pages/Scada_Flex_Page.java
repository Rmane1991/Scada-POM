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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Scada.Maven.Resouces.Utility;

//public class Scada_Flex_Page extends Utility 
//{
//	WebDriver driver;
//	WebDriverWait wait;
//
//	public Scada_Flex_Page(WebDriver driver) 
//	{
//		//super(driver);
//		this.driver = driver;
//		PageFactory.initElements(driver, this);
//		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//	}
//
//	// ---------------- BATCH PAGE ----------------
//	@FindBy(id = "ctl00_cphBody_txtBatchFrom")
//	WebElement batchFrom;
//
//	@FindBy(id = "ctl00_cphBody_txtBatchTo")
//	WebElement batchTo;
//
//	@FindBy(id = "ctl00_cphBody_btnSearch")
//	WebElement btnSearch;
//
//	@FindBy(id = "ctl00_cphBody_gvBatchList_ctl02_lnkBatchNo")
//	WebElement openBatch;
//
//	// ---------------- QTY & GRADE ----------------
//	@FindBy(id = "ctl00_cphBody_txtPrintProductionQty")
//	WebElement txtQty;
//
//	@FindBy(xpath = "//button[contains(text(),'OK')]")
//	WebElement btnOK;
//
//	@FindBy(id = "ctl00_cphBody_ddlReceipeCode")
//	WebElement ddlGrade;
//
//	@FindBy(id = "ctl00_cphBody_btn_submit")
//	WebElement btnSubmit;
//	
//	@FindBy(id = "ctl00_cphBody_btn_clear")
//	WebElement btnClaer;
//	
//	
//	
//
//	@FindBy(xpath = "//p[contains(text(),'Batch created successfully.')]")
//	WebElement msgSuccess;
//
//	@FindBy(id = "ctl00_cphBody_drpCustomer")
//	WebElement ddlCustomer;
//
//	@FindBy(id = "ctl00_cphBody_drpsite")
//	WebElement ddlSite;
//
//	@FindBy(id = "ctl00_cphBody_ddlTruckNo")
//	WebElement ddlVehicle;
//
//	@FindBy(id = "ctl00_cphBody_txtPrintTruckDriver")
//	WebElement txtDriver;
//
//	@SuppressWarnings("resource")
//	public void Flex_Bathc(String excelpath) throws IOException, InterruptedException 
//	{
//		FileInputStream fis = new FileInputStream(excelpath + ".xlsx");
//		XSSFWorkbook wb = new XSSFWorkbook(fis);
//		XSSFSheet sheet = wb.getSheet("Sheet1");
//		
//		XSSFCell cell = null;
//
//		int rowCount = sheet.getPhysicalNumberOfRows() - 1;
//
//		System.out.println("Total Rows Found = " + rowCount);
//
//		for (int i = 1; i <= rowCount; i++) {
//			batchFrom.clear();
//			batchFrom.sendKeys(sheet.getRow(i).getCell(0).getRawValue());
//
//			batchTo.clear();
//			batchTo.sendKeys(sheet.getRow(i).getCell(0).getRawValue());
//
//			btnSearch.click();
//			Thread.sleep(2000);
//			waitForLoaderToDisappear(driver);
//			openBatch.click();
//			if (isDisaplyed(By.id("ctl00_cphBody_drpCustomer"), driver, 15) == true)
//				;
//			waitForLoaderToDisappear(driver);
//
//			// For Customer
//			Dropdown(ddlCustomer, sheet.getRow(i).getCell(2).getStringCellValue());
//
//			// For Site
//			Dropdown(ddlSite, sheet.getRow(i).getCell(3).getStringCellValue());
//
//			// For Vehicle
//			Dropdown(ddlVehicle, sheet.getRow(i).getCell(6).getStringCellValue());
//
//			//For Qty and Get ROws
//			txtQty.clear();
//			Thread.sleep(2000);
//			btnOK.click();
//			Thread.sleep(1000);
//			txtQty.sendKeys(sheet.getRow(i).getCell(4).getRawValue());
//			Thread.sleep(1500);
//			btnOK.click();
//			Thread.sleep(1000);
//			Select dropdown_grade = new Select(ddlGrade);// For select Hardware Type
//			dropdown_grade.selectByVisibleText(sheet.getRow(i).getCell(5).getStringCellValue());
//			waitForLoaderToDisappear(driver);
//			
//			if (isDisaplyed(By.xpath("//div[@class='sweet-alert showSweetAlert visible']//button[contains(text(),'OK')]"), driver, 10000) == true);
//			driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
//			waitForLoaderToDisappear(driver);
//			Thread.sleep(1500);
//			
//			btnSubmit.click();
//			Thread.sleep(2500);
//			waitForLoaderToDisappear(driver);
//			if (isDisaplyed(By.xpath("//p[contains(text(),'Batch created successfully.')]"), driver, 15) == true);
//			
//			cell = sheet.getRow(i).createCell(6);
//			
//			String text = msgSuccess.getText();
//
//			if (msgSuccess.isDisplayed()) 
//			{
//				cell.setCellValue("PASS");
//			} 
//			 
//			FileOutputStream outputStream = new FileOutputStream(excelpath+"_01.xlsx");
//			wb.write(outputStream);
//			
//			System.out.println(i +":-" + sheet.getRow(i).getCell(0).getRawValue() + ":" + text);
//			Thread.sleep(3000);
//			btnOK.click();
//			btnClaer.click();
//			Thread.sleep(2000);
//		}
//
//	}
//
//}

//New Code
public class Scada_Flex_Page extends Utility 
{

    WebDriver driver;
    WebDriverWait wait;

    XSSFWorkbook wb = null;
    XSSFSheet sheet = null;
    XSSFCell resultCell=null;

    @FindBy(id = "ctl00_cphBody_txtBatchFrom")
    WebElement batchFrom;

    @FindBy(id = "ctl00_cphBody_txtBatchTo")
    WebElement batchTo;

    @FindBy(id = "ctl00_cphBody_btnSearch")
    WebElement btnSearch;

    @FindBy(id = "ctl00_cphBody_gvBatchList_ctl02_lnkBatchNo")
    WebElement openBatch;

    @FindBy(id = "ctl00_cphBody_txtPrintProductionQty")
    WebElement txtQty;

    @FindBy(xpath = "//button[contains(text(),'OK')]")
    WebElement btnOK;

    @FindBy(id = "ctl00_cphBody_ddlReceipeCode")
    WebElement ddlGrade;

    @FindBy(id = "ctl00_cphBody_btn_submit")
    WebElement btnSubmit;

    @FindBy(id = "ctl00_cphBody_btn_clear")
    WebElement btnClear;

    @FindBy(xpath = "//p[contains(text(),'Batch created successfully.')]")
    WebElement msgSuccess;

    @FindBy(id = "ctl00_cphBody_drpCustomer")
    WebElement ddlCustomer;

    @FindBy(id = "ctl00_cphBody_drpsite")
    WebElement ddlSite;

    @FindBy(id = "ctl00_cphBody_ddlTruckNo")
    WebElement ddlVehicle;
    
    @FindBy(xpath="//i[@class='md md-details']")
    WebElement Batch ;
    
    @FindBy(xpath = "//tr[@class='gridHeader']")
    WebElement flexbatchHeader;

    public Scada_Flex_Page(WebDriver driver) 
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
    }

  

    public void Flex_Bathc(String excelpath) throws IOException, InterruptedException 
    {
    	Actions ac=new Actions(driver);

        try (FileInputStream fis = new FileInputStream(excelpath + ".xlsx")) 
        {
        	wb = new XSSFWorkbook(fis);
            sheet = wb.getSheet("Sheet1");
            
            int rowCount = sheet.getPhysicalNumberOfRows() - 1;
            System.out.println("Total Rows Found = " + rowCount);

            for (int i = 1; i <= rowCount; i++) 
            {
            	try 
                {
                	resultCell = sheet.getRow(i).createCell(6);
                    String batchNo = sheet.getRow(i).getCell(0).getRawValue();

                    clearAndSet(batchFrom, batchNo);
                    clearAndSet(batchTo, batchNo);

                    click(btnSearch);
                    Thread.sleep(2000);
                    
                    //For Batch Not found
                    if(isDisplayed(flexbatchHeader,driver,5)==false)
    				{
                    	resultCell.setCellValue("Batch Not found");
    					FileOutputStream outputStream = new FileOutputStream(excelpath + "_01.xlsx");
    					wb.write(outputStream);
    					Thread.sleep(1000);
    					System.out.println(i +":-" + sheet.getRow(i).getCell(0).getRawValue() + ": Batch Not found");
    					continue;
    				}
    				
                    waitForLoaderToDisappear(driver);

                    click(openBatch);
                    waitForLoaderToDisappear(driver);

                    Dropdown(ddlCustomer, sheet.getRow(i).getCell(2).getStringCellValue());
                    //Dropdown(ddlSite, sheet.getRow(i).getCell(3).getStringCellValue());
                    
                    //For Site not Found
                    
                    try 
                    {
                        Dropdown(ddlSite, sheet.getRow(i).getCell(3).getStringCellValue());
                    } 
                    catch (Exception siteEx) 
                    {
//                        if (sheet.getRow(i) == null) 
//                        {
//                            sheet.createRow(i);
//                        }

                        resultCell.setCellValue("Site not found");
                        System.out.println(i + " :- " + batchNo + " : Site not found");

                        try (FileOutputStream out = new FileOutputStream(excelpath + "_01.xlsx")) 
                        {
                            wb.write(out);
                            out.flush();
                            ac.moveToElement(Batch).build().perform();
                            driver.findElement(By.linkText("Batch List")).click();
                            waitForLoaderToDisappear(driver);
                        } 
                        catch (Exception ignored) {}

                        continue;
                    }

                    
                    
                   // Dropdown(ddlVehicle, sheet.getRow(i).getCell(6).getStringCellValue());

                    clearAndClickOK();
                    txtQty.sendKeys(sheet.getRow(i).getCell(4).getRawValue());
                    click(btnOK);
                    waitForLoaderToDisappear(driver);
                    //new Select(ddlGrade).selectByVisibleText(sheet.getRow(i).getCell(5).getStringCellValue());

                    try 
                    {
                    	new Select(ddlGrade).selectByVisibleText(sheet.getRow(i).getCell(5).getStringCellValue());
                    }
                    
                    catch (Exception GradeEx) 
                    {

                        resultCell.setCellValue("Grade not found");
                        System.out.println(i + " :- " + batchNo + " : Grade not found");

                        try (FileOutputStream out = new FileOutputStream(excelpath + "_01.xlsx")) 
                        {
                            wb.write(out);
                            out.flush();
                            ac.moveToElement(Batch).build().perform();
                            driver.findElement(By.linkText("Batch List")).click();
                            waitForLoaderToDisappear(driver);
                        } 
                        catch (Exception ignored) {}

                        continue;
                    }
                    
                    waitForLoaderToDisappear(driver);

                    By alertOk = By.xpath("//div[@class='sweet-alert showSweetAlert visible']//button[contains(text(),'OK')]");
                    
                    if (isDisaplyed(alertOk, driver, 5)) 
                    {
                        driver.findElement(alertOk).click();
                    }

                    click(btnSubmit);
                    waitForLoaderToDisappear(driver);

                   // XSSFCell resultCell = sheet.getRow(i).createCell(6);

                    if (isDisplayed(msgSuccess, driver, 5)) 
                    {
                        resultCell.setCellValue("PASS");
                        System.out.println(i + " :- " + batchNo + ": PASS");
                    } 
                    else 
                    {
                        resultCell.setCellValue("FAIL");
                        System.out.println(i + " :- " + batchNo + ": FAIL");
                    }

                    click(btnOK);
                    click(btnClear);
                    waitForLoaderToDisappear(driver);

                }
                catch (Exception e) 
                {
                    // Handle row-level failure
                    XSSFCell resultCell = sheet.getRow(i).createCell(6);
                    resultCell.setCellValue("FAIL");

                    System.out.println(i + " :- "+ sheet.getRow(i).getCell(0).getRawValue() + " : Exception -> " + e.getMessage());

                    try 
                    {
                        	if (driver.findElement(By.xpath("//p[contains(text(),'Batch Not updated')]")).isDisplayed()) 
                        {
                            click(driver.findElement(By.xpath("//button[contains(text(),'OK')]")));
                        }
                    } 
                    catch (Exception ignored) 
                    {
                        resultCell.setCellValue("Batch Not Found");
                        System.out.println(i + " :- Batch Not Found");
                    }

                    // move to Batch list & continue
                    try 
                    {
                       moveToElement(Batch);
                       driver.findElement(By.linkText("Batch List")).click();
                    } catch (Exception ignored) {}

                    continue;
                }
            }

            // success output
            try (FileOutputStream out = new FileOutputStream(excelpath + "_01.xlsx")) 
            {
                wb.write(out);
            }
        }
    }


    // ---------- Helper Methods ----------

    private void click(WebElement ele) 
    {
        wait.until(driver -> ele.isDisplayed() && ele.isEnabled());
        ele.click();
    }

    private void clearAndSet(WebElement ele, String val) 
    {
        wait.until(driver -> ele.isDisplayed());
        ele.clear();
        ele.sendKeys(val);
    }

    private void clearAndClickOK() 
    {
        txtQty.clear();
        click(btnOK);
    }
}
