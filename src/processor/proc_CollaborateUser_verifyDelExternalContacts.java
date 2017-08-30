package processor;

import java.util.concurrent.TimeUnit;

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
			dr.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
			try
			{
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DirectoryText)));
				dr.findElement(By.xpath(DirectoryText)).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ExContacts)));
				dr.findElement(By.xpath(ExContacts)).click();
				
				dr.findElement(By.xpath("html/body")).click();
				dr.switchTo().frame(dr.findElement(By.id(iframe1)));
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
				WebElement searchContacts = dr.findElement(By.xpath(searchField));
				searchContacts.sendKeys(p_UL.collab_TestUserName+" "+p_UL.collab_TestUserLastName); 
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contactResultCount)));
				//searchContacts.sendKeys(Keys.ENTER);

				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(results)));
				dr.findElement(By.xpath("html/body")).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchResult)));
				//dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				dr.findElement(By.xpath(searchResult)).click();
				
				dr.switchTo().defaultContent();
				dr.findElement(By.xpath("html/body")).click();
				dr.switchTo().frame(iframe1);
				
				
				dr.findElement(By.xpath("html/body")).click();
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(editContact)));
				dr.findElement(By.xpath(editContact)).click();
				
		
				dr.findElement(By.id("contactInfo")).click();
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("delContact")));
				dr.findElement(By.xpath(delContact)).click();
				
				dr.switchTo().alert().accept();
				
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
				searchContacts = dr.findElement(By.xpath(searchField));
				searchContacts.clear();
				searchContacts.sendKeys(p_UL.collab_TestUserName+" "+p_UL.collab_TestUserLastName); 
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contactResultCountTrue)));
				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResult)));
				
				
				//Assert.assertTrue(dr.findElements(By.xpath(contactResultCountTrue)).size()!=0);
				Assert.assertTrue(dr.findElements(By.xpath(searchResult)).size()== 0);
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
