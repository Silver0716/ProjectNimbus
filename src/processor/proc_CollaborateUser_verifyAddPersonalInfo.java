package processor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import model.mod_CollaborateUser_verifyAddPersonalInfo;

public class proc_CollaborateUser_verifyAddPersonalInfo extends mod_CollaborateUser_verifyAddPersonalInfo
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
				
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(addSection)));
				dr.findElement(By.cssSelector(addSection)).click();
				
				dr.findElement(By.className(sectionOptions));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sectionSkill)));
				dr.findElement(By.xpath(sectionSkill)).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(editSection)));
				dr.findElement(By.xpath(editSection)).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(skillField)));
				dr.findElement(By.xpath(skillField)).sendKeys("Test");
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(save)));
				dr.findElement(By.xpath(save));
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sectionCreated)));
		
				Assert.assertTrue(dr.findElements(By.xpath(sectionCreated)).size()!=0);
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Personal Information", "Pass", " ");
				
				
			}
			catch(AssertionError e)
			{
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Add Personal Information", "Fail","Unable to add section: Skills & Certifications");
				throw e;
			}
			catch(Exception e)
			{
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Personal Information", "Fail",e.getMessage().toString());
				e.printStackTrace();
				throw e;
			}
				
	}
}
			