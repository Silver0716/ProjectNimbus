package processor;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import model.mod_CollaborateUser_verifyAddExternalContacts;

public class proc_CollaborateUser_verifyAddExternalContacts extends mod_CollaborateUser_verifyAddExternalContacts {

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
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AddButton)));
			dr.findElement(By.xpath(AddButton)).click();
			
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id(Contacts)));
			dr.findElement(By.id(Contacts)).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id(firstName)));
			WebElement FirstNameBox = dr.findElement(By.id(firstName));
			FirstNameBox.sendKeys(p_UL.collab_TestUserName);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id(lastName)));
			WebElement LastNameBox = dr.findElement(By.id(lastName));
			dr.findElement(By.id(firstName)).click();
			LastNameBox.sendKeys(p_UL.collab_TestUserLastName);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id(proceed)));
			dr.findElement(By.id(proceed)).click();

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(save)));
			Thread.sleep(1000);
			dr.findElement(By.xpath(save)).sendKeys(Keys.ENTER);
			
			
			
			dr.switchTo().defaultContent();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MessageBox)));
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DirectoryText)));
			dr.findElement(By.xpath(DirectoryText)).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ExContacts)));
			dr.findElement(By.xpath(ExContacts)).click();
			
			dr.switchTo().frame(dr.findElement(By.id(iframe1)));

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
			WebElement searchContacts = dr.findElement(By.xpath(searchField));
			searchContacts.sendKeys(p_UL.collab_TestUserName+" "+p_UL.collab_TestUserLastName); 
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contactResultCount)));
			searchContacts.sendKeys(Keys.ENTER);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchResult)));
			
			
			Assert.assertTrue(dr.findElements(By.xpath(searchResult)).size()!=0);
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Add External Contacts", "Pass", " ");
			
		}
		catch(AssertionError e)
		{
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Add External Contacts", "Fail","Unable to save Username: "+p_UL.collab_TestUserName+" "+p_UL.collab_TestUserLastName);
			throw e;
		}
		catch(Exception e)
		{
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Add External Contacts", "Fail",e.getMessage().toString());
			e.printStackTrace();
			throw e;
		}
		
	}
}
