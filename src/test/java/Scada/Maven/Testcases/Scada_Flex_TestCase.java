package Scada.Maven.Testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Scada.Maven.Pages.Scada_Flex_Page;
import Scada.Maven.Pages.Scada_Print_Ch_Batch;



public class Scada_Flex_TestCase extends Base
{

	WebDriver driver =launchBrowser();
	Scada_Flex_Page SC_Flex;
	Scada_Print_Ch_Batch SC_pcpb;

	//@Test(priority = 1) 
	public void Flex_Batch() throws Exception

	{
		SC_Flex = new Scada_Flex_Page(driver);
		login_Flex("meflex","meflex","0825476931");
		SC_Flex.Flex_Bathc("D:\\D Data\\ME_Data\\FLex\\Check");
	}

	
	@Test(priority = 2) 
	public void Print_Ch_Batch() throws Exception

	{
		SC_pcpb =new Scada_Print_Ch_Batch(driver);
		login_BMC();
		SC_pcpb.Print_CH_Batch("D:\\ME_Data\\Excel\\Book");
	}
	
	
	
	 @AfterMethod
	    public void tearDown() 
	 {
	        if(driver != null) 
	        {
	            driver.quit();
	        }
	 }
	 
	
}
