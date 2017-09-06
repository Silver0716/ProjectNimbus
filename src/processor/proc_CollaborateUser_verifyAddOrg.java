package processor;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import model.mod_CollaborateUser_verifyAddOrg;

public class proc_CollaborateUser_verifyAddOrg extends mod_CollaborateUser_verifyAddOrg 
{
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
				
				//dr.findElement(By.xpath("html/body")).click();
				dr.switchTo().frame(dr.findElement(By.id(iframe1)));
				
				wait.until(ExpectedConditions.elementToBeClickable(By.id(orgTab)));
				dr.findElement(By.id(orgTab)).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AddButton)));
				dr.findElement(By.xpath(AddButton)).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.id(addOrg)));
				dr.findElement(By.id(addOrg)).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.id(orgName)));
				WebElement orgNameField = dr.findElement(By.id(orgName));
				orgNameField.sendKeys(p_UL.collab_TestOrg);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.id(proceed)));
				dr.findElement(By.id(proceed)).click();
				
				//dr.findElement(By.xpath("html/body")).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(save)));
				Thread.sleep(1000);
				dr.findElement(By.xpath(save)).sendKeys(Keys.ENTER);
				
				
				dr.switchTo().defaultContent();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DirectoryText)));
				dr.findElement(By.xpath(DirectoryText)).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ExContacts)));
				dr.findElement(By.xpath(ExContacts)).click();
				
				//dr.findElement(By.xpath("html/body")).click();
				dr.switchTo().frame(dr.findElement(By.id(iframe1)));
				
				wait.until(ExpectedConditions.elementToBeClickable(By.id(orgTab)));
				dr.findElement(By.id(orgTab)).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
				WebElement searchContacts = dr.findElement(By.xpath(searchField));
				searchContacts.sendKeys(p_UL.collab_TestOrg); 
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(orgResultCount)));
								
				Assert.assertTrue(dr.findElements(By.xpath(orgResultCount)).size()!=0);
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Add Organization", "Pass", " ");
				
			}
			catch(AssertionError e)
			{
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Add Organization", "Fail","Unable to add Organization: "+p_UL.collab_TestOrg);
				throw e;
			}
			catch(Exception e)
			{
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Add Organization", "Fail",e.getMessage().toString());
				e.printStackTrace();
				throw e;
			}
			
		}
	}
