package processor;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import model.mod_CollaborateUser_verifyViewOrg;

public class proc_CollaborateUser_verifyViewOrg extends mod_CollaborateUser_verifyViewOrg
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
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DirectoryText)));
			dr.findElement(By.xpath(DirectoryText)).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ExContacts)));
			dr.findElement(By.xpath(ExContacts)).click();
			
			dr.switchTo().frame(dr.findElement(By.id(iframe1)));
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id(orgTab)));
			dr.findElement(By.id(orgTab)).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
			WebElement searchContacts = dr.findElement(By.xpath(searchField));
			searchContacts.sendKeys(p_UL.collab_TestOrg); 
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(orgResultCountFalse)));
			searchContacts.sendKeys(Keys.ENTER);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.className(searchResult)));
			dr.findElement(By.className(searchResult)).click();
			
			dr.switchTo().defaultContent();
			dr.switchTo().frame(iframe1);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(confirmInfo)));
			
			Assert.assertTrue(dr.findElements(By.xpath(confirmInfo)).size()!=0);
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify View External Contacts", "Pass", " ");
			
		}
		catch(AssertionError e)
		{
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify View External Contacts", "Fail","Unable to view Contact Information: "+p_UL.collab_TestUserName+" "+p_UL.collab_TestUserLastName);
			throw e;
		}
		catch(Exception e)
		{
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify View External Contacts", "Fail",e.getMessage().toString());
			e.printStackTrace();
			throw e;
		}
		
	}
}
