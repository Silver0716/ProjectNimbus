package processor;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import processor.proc_UniversalLogin;
import model.mod_CollaborateUser_verifySearchField;
import processor.proc_ExcelOutput;

public class proc_CollaborateUser_verifySearchField extends mod_CollaborateUser_verifySearchField
{
	proc_UniversalLogin p_UL = new proc_UniversalLogin();
	proc_ExcelOutput p_EO = new proc_ExcelOutput();
	
	public void initSteps(WebDriver dr)
	{
		p_UL.start(dr);
	}
	
	public void init_Search(WebDriver dr)
	{
		WebDriverWait wait = new WebDriverWait(dr,30);
		
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchFieldTextBox)));
			WebElement searchBox = dr.findElement(By.xpath(searchFieldTextBox));
			searchBox.sendKeys(p_UL.collab_Username);
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(toastProfileBox)));
			searchBox.sendKeys(Keys.ENTER);
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(filter_keywordBox)));
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResult_NameCard)));
			 dr.findElements(By.xpath(searchResult_NameCard)).size();
			
			Assert.assertTrue(dr.findElements(By.xpath(searchResult_NameCard)).size()!=0);
			
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Search Field", "Pass", " ");
		}
		catch(AssertionError e)
		{
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Search Field", "Fail","Can't find the result, after searching this username: "+p_UL.collab_Username);
			throw e;
		}
		catch(Exception e)
		{
			p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Search Field", "Fail",e.getMessage().toString());
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
}
