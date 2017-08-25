package controller.testsuite.suite;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import processor.proc_SeleniumDriver;

public class ctr_SeleniumDriver 
{
	proc_SeleniumDriver proc_SD = new proc_SeleniumDriver();
	
	@BeforeSuite
	public void initSeleniumDriver()
	{
		proc_SD.setDriver();
	}
	
//	@AfterSuite
//	public void deleteSeleniumDriver()
//	{
//		proc_SD.endWebDriver();
//		proc_SD.deleteDirectory();
//	}
}
