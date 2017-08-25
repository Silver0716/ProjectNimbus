package controller.testsuite.testcase2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import processor.proc_CollaborateUser_verifySearchField;
import processor.proc_SeleniumDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import processor.proc_SeleniumDriver;

public class Func_CollaborateUser2
{
	proc_CollaborateUser_verifySearchField pc_vSF = new proc_CollaborateUser_verifySearchField();
	proc_SeleniumDriver proc_SD = new proc_SeleniumDriver();
	

	@BeforeClass
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
	
	@Test
	public void verifySearchField()
	{
		WebDriver dr = proc_SD.getWebDriver();
		
		pc_vSF.initSteps(dr);
		pc_vSF.init_Search(dr);
	}
	
	
}
