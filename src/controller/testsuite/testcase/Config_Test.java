package controller.testsuite.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import processor.proc_SeleniumDriver;
import processor.proc_Config_ImportVerification;
import processor.proc_ExcelOutput;


public class Config_Test 
{
	proc_Config_ImportVerification pc_vIV = new proc_Config_ImportVerification();
	proc_ExcelOutput p_EO = new proc_ExcelOutput();
	proc_SeleniumDriver proc_SD = new proc_SeleniumDriver();
	
	@BeforeMethod
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
	
	@AfterSuite
	public void printResult()
	{
		p_EO.printOutputValues();
	}

//	@Test(priority=0)
//	public void Config_ImportVerification() throws Exception
//	{
//		WebDriver dr = proc_SD.getWebDriver();
//		pc_vIV.initSteps(dr);
//		pc_vIV.init_Search(dr);
//	}
	
}

