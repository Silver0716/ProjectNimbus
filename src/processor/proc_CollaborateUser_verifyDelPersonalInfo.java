package processor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import model.mod_CollaborateUser_verifyDelPersonalInfo;

public class proc_CollaborateUser_verifyDelPersonalInfo extends mod_CollaborateUser_verifyDelPersonalInfo
{
	proc_UniversalLogin p_UL = new proc_UniversalLogin();
	proc_ExcelOutput p_EO = new proc_ExcelOutput();
	
	public void initSteps(WebDriver dr)
	{
		p_UL.start(dr);
	}
	
	public void init_Search(WebDriver dr)
	{
		WebDriverWait wait = new WebDriverWait(dr, 30);
		
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.className(sideProfile)));
			dr.findElement(By.className(sideProfile)).click();
			dr.findElement(By.className(selectProfile)).click();
			
			dr.switchTo().defaultContent();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(editSection)));
			dr.findElement(By.xpath(editSection)).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(remSection)));
			dr.findElement(By.xpath(remSection)).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(confirm)));
			dr.findElement(By.xpath(confirm)).click();
			
//			if (dr.findElement(By.xpath(sectionCreated)) == null)
//			{
//				
//			}
			Assert.assertTrue(dr.findElement(By.xpath(sectionCreated)).isDisplayed());
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Delete Personal Information", "Pass", " ");
			
			
		}
		catch(AssertionError e)
		{
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Delete Personal Information", "Fail","Unable to delete section: Skills & Certifications");
			throw e;
		}
		catch(Exception e)
		{
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Delete Personal Information", "Fail",e.getMessage().toString());
			e.printStackTrace();
			throw e;
		}
			
}
}

