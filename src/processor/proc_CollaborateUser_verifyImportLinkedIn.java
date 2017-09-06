package processor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import model.mod_CollaborateUser_verifyImportLinkedIn;

public class proc_CollaborateUser_verifyImportLinkedIn extends mod_CollaborateUser_verifyImportLinkedIn
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
			wait.until(ExpectedConditions.elementToBeClickable(By.className(sideProfile)));
			dr.findElement(By.className(sideProfile)).click();
			dr.findElement(By.className(selectProfile)).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LnkImpButton)));
			dr.findElement(By.xpath(LnkImpButton)).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(LinkedIn)));
			dr.findElement(By.linkText(LinkedIn)).click();
			
			ArrayList<String> tabs2 = new ArrayList<String> (dr.getWindowHandles());
			dr.switchTo().window(tabs2.get(1));
			
			dr.close();
			dr.switchTo().window(tabs2.get(0));
			
			wait.until(ExpectedConditions.elementToBeClickable(By.className(close)));
			dr.findElement(By.className(close)).click();
			
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LnkImpButton)));
			dr.findElement(By.xpath(LnkImpButton)).click();
			
			dr.findElement(By.xpath(signLinkedIn)).click();
			
			Set<String> windowId = dr.getWindowHandles();    
		    Iterator<String> iterator = windowId.iterator();  
		    String mainWindow = iterator.next();
	        String  newWindow = iterator.next();

	        dr.switchTo().window(newWindow);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(emailField)));
			dr.findElement(By.xpath(emailField)).sendKeys(Email);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(passField)));
			dr.findElement(By.xpath(passField)).sendKeys(Pass);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(allow)));
			dr.findElement(By.xpath(allow)).click();
			
			dr.switchTo().window(mainWindow);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(proceed)));
			dr.findElement(By.xpath(proceed)).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(messageBox)));
			
			Assert.assertTrue(dr.findElements(By.xpath(messageBox)).size()!=0);
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Import LinkedIn", "Pass", " ");
			
		}
		catch(AssertionError e)
		{
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Import LinkedIn", "Fail","Unable to import LinkedIn account: "+p_UL.collab_TestLinkedInAcc);
			throw e;
		}
		catch(Exception e)
		{
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Import LinkedIn", "Fail",e.getMessage().toString());
			e.printStackTrace();
			throw e;
		}
		
	}
}

