package controller.testsuite.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import processor.proc_SeleniumDriver;
import processor.proc_Config_ImportVerification;
import processor.proc_Config_RolesVerification;

public class Config_Test 
{
	proc_Config_ImportVerification pc_IV = new proc_Config_ImportVerification();
	proc_Config_RolesVerification pc_RV = new proc_Config_RolesVerification();
	
	proc_SeleniumDriver proc_SD = new proc_SeleniumDriver();
	
	/*@BeforeMethod
	public void initSeleniumDriver()
	{
		proc_SD.setDriver();
	}
	
	@AfterMethod
	public void deleteSeleniumDriver()
	{
		proc_SD.endWebDriver();
		proc_SD.deleteDirectory();
	}

	@Test
	public void ImportVerification() throws Exception
	{
		WebDriver dr = proc_SD.getWebDriver();
		pc_IV.initSteps(dr);
		pc_IV.init_Search(dr);
	}*/
	

	
}

