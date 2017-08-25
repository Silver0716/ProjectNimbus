package processor;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import processor.proc_UniversalLogin;
import model.mod_CollaborateUser_verifyDirectorySearch;

public class proc_CollaborateUser_verifyDirectorySearch extends mod_CollaborateUser_verifyDirectorySearch
{
	proc_UniversalLogin p_UL = new proc_UniversalLogin();
	
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
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MyOrganizationText)));
			dr.findElement(By.xpath(MyOrganizationText)).click();
			
			//Used to wait for loading complete and wait for results to be displayed.
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Tab_AllButton)));
			
			
			//Verify if the tabs are clickable and can retrieve result
			dr.findElement(By.xpath(Tab_PeopleButton)).click();
			Boolean isContent = dr.findElements(By.xpath(Tab_PeopleResultContent)).size()>0;
			
			dr.findElement(By.xpath(Tab_GroupButton)).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Tab_GroupResult)));
			
			dr.findElement(By.xpath(Tab_LocationsButton)).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Tab_LocationResult)));
			
			dr.findElement(By.xpath(Tab_ContactsButton)).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Tab_ContactResult)));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}
}
