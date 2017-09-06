package processor;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import model.mod_CollaborateUser_verifyDelExternalContacts;

public class proc_CollaborateUser_verifyDelExternalContacts extends mod_CollaborateUser_verifyDelExternalContacts  {

	proc_UniversalLogin p_UL = new proc_UniversalLogin();
	proc_ExcelOutput p_EO = new proc_ExcelOutput();
		
		public void initSteps(WebDriver dr)
		{
			p_UL.start(dr);
		}
		
		public void init_Search(WebDriver dr) throws Exception
		{
			WebDriverWait wait = new WebDriverWait(dr, 30);
		
			try
			{
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DirectoryText)));
				dr.findElement(By.xpath(DirectoryText)).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ExContacts)));
				dr.findElement(By.xpath(ExContacts)).click();

				dr.switchTo().frame(dr.findElement(By.id(iframe1)));
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
				WebElement searchContacts = dr.findElement(By.xpath(searchField));
				searchContacts.sendKeys(p_UL.collab_TestUserName+" "+p_UL.collab_TestUserLastName); 
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contactResultCountTrue)));
				searchContacts.sendKeys(Keys.ENTER);

			
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchResult)));
				dr.findElement(By.xpath(searchResult)).sendKeys(Keys.ENTER);
				
				dr.switchTo().defaultContent();
				dr.switchTo().frame(iframe1);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(editContact)));
				dr.findElement(By.xpath(editContact)).click();
				
		
				dr.findElement(By.id("contactInfo")).click();
				dr.findElement(By.xpath(delContact)).click();
				
				dr.switchTo().alert().accept();
				
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
				searchContacts = dr.findElement(By.xpath(searchField));
				searchContacts.clear();
				searchContacts.sendKeys(p_UL.collab_TestUserName+" "+p_UL.collab_TestUserLastName); 
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contactResultCountTrue)));
				searchContacts.sendKeys(Keys.ENTER);
				
				
				Assert.assertTrue(dr.findElement(By.xpath(contactResultCountTrue)).getText().equals("(0)"));
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Delete External Contacts", "Pass", " ");
			
				
			}
			catch(AssertionError e)
			{
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Delete External Contacts", "Fail","Unable to save Username: "+p_UL.collab_TestUserName+" "+p_UL.collab_TestUserLastName);
				throw e;
			}
			catch(Exception e)
			{
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Delete External Contacts", "Fail",e.getMessage().toString());
				e.printStackTrace();
				throw e;
			}
			
		}
	}
