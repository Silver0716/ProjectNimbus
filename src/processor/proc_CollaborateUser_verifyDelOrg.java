package processor;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import model.mod_CollaborateUser_verifyDelOrg;

public class proc_CollaborateUser_verifyDelOrg extends mod_CollaborateUser_verifyDelOrg
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
				
				dr.findElement(By.xpath(editOrg)).click();
				
				dr.findElement(By.id("orgForm"));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(delOrg)));
				dr.findElement(By.xpath(delOrg)).click();
				
				dr.switchTo().alert().accept();
				
				dr.switchTo().defaultContent();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DirectoryText)));
				dr.findElement(By.xpath(DirectoryText)).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ExContacts)));
				dr.findElement(By.xpath(ExContacts)).click();
				
				dr.switchTo().frame(dr.findElement(By.id(iframe1)));
				
				wait.until(ExpectedConditions.elementToBeClickable(By.id(orgTab)));
				dr.findElement(By.id(orgTab)).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
				searchContacts = dr.findElement(By.xpath(searchField));
				searchContacts.clear();
				searchContacts.sendKeys(p_UL.collab_TestOrg); 
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(orgResultCountTrue)));
				
				Assert.assertTrue(dr.findElements(By.xpath(orgResultCountTrue)).size()!=0);
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Delete Organization", "Pass", " ");
				
			}
			catch(AssertionError e)
			{
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Delete External Contacts", "Fail","Unable to delete Organization: "+p_UL.collab_TestOrg);
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
