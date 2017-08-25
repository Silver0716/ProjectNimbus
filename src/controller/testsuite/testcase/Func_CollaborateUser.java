package controller.testsuite.testcase;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import processor.proc_CollaborateUser_verifySearchField;
import processor.proc_SeleniumDriver;
import processor.proc_CollaborateUser_verifyAddExternalContacts;
import processor.proc_CollaborateUser_verifyAddOrg;
import processor.proc_CollaborateUser_verifyAddPersonalInfo;
import processor.proc_CollaborateUser_verifyDelExternalContacts;
import processor.proc_CollaborateUser_verifyDelOrg;
import processor.proc_CollaborateUser_verifyDelPersonalInfo;
import processor.proc_CollaborateUser_verifyImportLinkedIn;


public class Func_CollaborateUser extends proc_SeleniumDriver
{
	proc_CollaborateUser_verifySearchField pc_vSF = new proc_CollaborateUser_verifySearchField();
	proc_CollaborateUser_verifySearchField pc_vDF = new proc_CollaborateUser_verifySearchField();
	proc_CollaborateUser_verifyAddExternalContacts pc_vAEC = new proc_CollaborateUser_verifyAddExternalContacts();
	proc_CollaborateUser_verifyDelExternalContacts pc_vDEC = new proc_CollaborateUser_verifyDelExternalContacts();
	proc_CollaborateUser_verifyAddOrg pc_vAO = new proc_CollaborateUser_verifyAddOrg();
	proc_CollaborateUser_verifyDelOrg pc_vDO = new proc_CollaborateUser_verifyDelOrg();
	proc_CollaborateUser_verifyAddPersonalInfo pc_vAPI = new proc_CollaborateUser_verifyAddPersonalInfo();
	proc_CollaborateUser_verifyDelPersonalInfo pc_vDPI = new proc_CollaborateUser_verifyDelPersonalInfo();
	proc_CollaborateUser_verifyImportLinkedIn pc_vILI = new proc_CollaborateUser_verifyImportLinkedIn();
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
	
	/*@Test(priority=0)
	public void verifySearchField()
	{
		WebDriver dr = proc_SD.getWebDriver();		
		pc_vSF.initSteps(dr);
		pc_vSF.init_Search(dr);
	}*/
	
	/*@Test(priority=1)
	public void verifyDirectoryField()
	{
		WebDriver dr = proc_SD.getWebDriver();
		pc_vDF.initSteps(dr);
		pc_vDF.init_Search(dr);
	}
	
	@Test(priority=2)
	public void verifyAddExternalContacts() throws Exception
	{
		WebDriver dr = proc_SD.getWebDriver();
		pc_vAEC.initSteps(dr);
		pc_vAEC.init_Search(dr);
		
	}
	
	@Test(priority=3)
	public void verifyDelExternalContacts() throws Exception
	{
		WebDriver dr = proc_SD.getWebDriver();
		pc_vDEC.initSteps(dr);
		pc_vDEC.init_Search(dr);
	}
	
	@Test(priority=4)
	public void verifyAddOrg() throws Exception
	{
		WebDriver dr = proc_SD.getWebDriver();
		pc_vAO.initSteps(dr);
		pc_vAO.init_Search(dr);
	}
	
	
	@Test(priority=5)
	public void verifyDelOrg()
	{
		WebDriver dr = proc_SD.getWebDriver();
		pc_vDO.initSteps(dr);
		pc_vDO.init_Search(dr);
	}
	
	@Test(priority=6)
	public void verifyAddPersonalInfo()
	{
		WebDriver dr = proc_SD.getWebDriver();
		pc_vAPI.initSteps(dr);
		pc_vAPI.init_Search(dr);
	}
	
	@Test(priority=7)
	public void verifyImportLinkedIn() throws Exception
	{
		WebDriver dr = proc_SD.getWebDriver();
		pc_vILI.initSteps(dr);
		pc_vILI.init_Search(dr);
	}*/
	
	@Test(priority=8)
	public void verifyDelPersonalInfo()
	{
		WebDriver dr = proc_SD.getWebDriver();
		pc_vDPI.initSteps(dr);
		pc_vDPI.init_Search(dr);
	}
	
}
