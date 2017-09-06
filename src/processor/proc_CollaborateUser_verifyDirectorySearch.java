package processor;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import processor.proc_UniversalLogin;
import model.mod_CollaborateUser_verifyDirectorySearch;

public class proc_CollaborateUser_verifyDirectorySearch extends mod_CollaborateUser_verifyDirectorySearch
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
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(DirectoryText)));
			dr.findElement(By.xpath(DirectoryText)).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MyOrganizationText)));
			dr.findElement(By.xpath(MyOrganizationText)).click();
			
			checkAllResults(dr,0,wait);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	public void checkAllResults(WebDriver dr, int s, WebDriverWait wait)
	{
		while(switchValue!=10 || errorValue !=10)
		{
			try
			{
				switch(switchValue)
				{
					case 0:
						
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Tab_AllButton)));
						dr.findElement(By.xpath("/html/body")).click();
						hasResult(dr,Tab_AllResultContent);
					/*--------------------------*/
					case 1:
						
						if(isPresent(dr,text_FilterAll))
						{
							switchValue = switchValue + 1;
							errorValue = errorValue+1;
							totalPassed = totalPassed + 1;
						}
						else
						{
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FilterAll)));
							ActionPerform(dr,FilterAll,filterallBtnValue);
							hasResult(dr,Tab_AllResultContent);
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FilterAll_entityclose)));
							ActionPerform(dr,FilterAll_entityclose,filterBtnValue);
						}
					/*--------------------------*/
					case 2:
						
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Tab_PeopleButton)));
						ActionPerform(dr,Tab_PeopleButton,pplBtnValue);
						hasResult(dr,Tab_PeopleResultContent);
					/*--------------------------*/
					case 3:
						
						if(isPresent(dr,text_FilterPeople))
						{
							switchValue = switchValue + 1;
							errorValue = errorValue+1;
							totalPassed = totalPassed + 1;
						}
						else
						{
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FilterAll)));
							ActionPerform(dr,FilterAll,filterpplBtnValue);
							hasResult(dr,Tab_PeopleResultContent);
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FilterAll_entityclose)));
							ActionPerform(dr,FilterAll_entityclose,filterBtnValue);
						}
					/*--------------------------*/						
					case 4:
						
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Tab_GroupButton)));
						ActionPerform(dr,Tab_GroupButton,grpBtnValue);
						hasResult(dr,Tab_GroupResultContent);
					/*--------------------------*/
					case 5:
						
						if(isPresent(dr,text_FilterGroups))
						{
							errorValue = errorValue+1;
							switchValue = switchValue + 1;
							totalPassed = totalPassed + 1;
						}
						else
						{
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FilterAll)));
							ActionPerform(dr,FilterAll,filtergrpBtnValue);
							hasResult(dr,Tab_GroupResultContent);
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FilterAll_entityclose)));
							ActionPerform(dr,FilterAll_entityclose,filterBtnValue);
							
						}
					/*--------------------------*/						
					case 6:
						
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Tab_LocationsButton)));
						ActionPerform(dr,Tab_LocationsButton,locBtnValue);
						hasResult(dr,Tab_LocationResultContent);
					/*--------------------------*/	
					case 7:
						
						if(isPresent(dr,text_FilterLocation))
						{
							switchValue = switchValue + 1;
							errorValue = errorValue+1;
							totalPassed = totalPassed + 1;
						}
						else
						{
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FilterAll)));
							ActionPerform(dr,FilterAll,filterlocBtnValue);
							hasResult(dr,Tab_LocationResultContent);
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FilterAll_entityclose)));
							ActionPerform(dr,FilterAll_entityclose,filterBtnValue);
						}
					/*--------------------------*/	
					case 8:
						
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Tab_ContactsButton)));
						ActionPerform(dr,Tab_ContactsButton,ctcBtnValue);
						hasResult(dr,Tab_ContactResultContent);
					/*--------------------------*/	
					case 9:
						
						if(isPresent(dr,text_FilterContact))
						{
							errorValue = errorValue+1;
							switchValue = switchValue + 1;
							totalPassed = totalPassed + 1;
						}
						else
						{
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FilterAll)));
							ActionPerform(dr,FilterAll,filterctcBtnValue);
							hasResult(dr,Tab_ContactResultContent);
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FilterAll_entityclose)));
							ActionPerform(dr,FilterAll_entityclose,filterBtnValue);
						}
					/*--------------------------*/
					default:
						
						if(totalPassed==10)
						{
							
							p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Directory Search", "Passed", " ");
							break;
						}
						else
						{
							Assert.assertTrue(totalPassed==10);
							p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Directory Search", "Fail", collectedError);
							break;
						}
				}
			}
			
			catch(AssertionError ae)
			{
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Directory Search", "Fail", collectedError);
				throw ae;
			}
			catch(Exception e)
			{
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Searech Directory", "Fail", e.getMessage().toString());
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	
	public boolean isPresent(WebDriver dr, String value)
	{
		try
		{
			dr.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebDriverWait w = new WebDriverWait(dr,5);
			
			w.until(ExpectedConditions.textToBePresentInElement(By.xpath(FilterLocation), value));
			dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;
		}
		catch(org.openqa.selenium.NoSuchElementException e)
		{
			dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return false;
		}
		catch(org.openqa.selenium.TimeoutException ex)
		{
			dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return false;
		}
	}
	
	public void hasResult(WebDriver dr, String value)
	{
		try
		{
			Thread.sleep(1000);

			dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			if(dr.findElements(By.xpath(value)).size()>0)
			{
				switchValue = switchValue + 1;
				errorValue = errorValue+1;
				totalPassed = totalPassed + 1;
				dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
			else
			{
				errorMapping(Integer.toString(errorValue),dr);
			}
			
		}
		catch(org.openqa.selenium.NoSuchElementException nse)
		{
			dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return;
		}
		catch(org.openqa.selenium.TimeoutException ex)
		{
			dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return;
		}
		catch(Exception e)
		{
			dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return;
		}
	}
	
	/*Action Perform*/
	public void ActionPerform(WebDriver d, String s, String errorBtnValue)
	{
		try
		{
			Thread.sleep(1000);
			
			WebElement el = d.findElement(By.xpath(s));
			
	        Actions actions = new Actions(d);
	        actions.moveToElement(el);
	        actions.click();
	        actions.build().perform();
		}
		
		catch(org.openqa.selenium.NoSuchElementException nsee)
		{
			errorMapping(errorBtnValue,d);
		}
		catch(org.openqa.selenium.ElementNotSelectableException ense)
		{
			errorMapping(errorBtnValue,d);
		}
		catch(org.openqa.selenium.ElementNotVisibleException enve)
		{
			errorMapping(errorBtnValue,d);
		}
		catch(Exception e)
		{
			errorMapping(errorBtnValue,d);
		}
	}
	
	public void errorMapping(String mapValue,WebDriver d)
	{
		if(collectedError.equals(""))
		{
			collectedError = (String) errorMap.get(mapValue);
		}
		else
		{
			collectedError = collectedError.concat("\n" + (String) errorMap.get(mapValue));
		}
		switchValue = switchValue + 1;
		errorValue = errorValue+1;
		d.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}
