package processor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import model.mod_UniversalURL;

public class proc_UniversalLogin extends mod_UniversalURL
{

	public void start(WebDriver dr)
	{
		routetoLogin(dr);
		maximize(dr);
		routetoOrganization(dr);
		enterOrganizationName(dr);
		returntoLogin(dr);
		enterUserName(dr);
		enterPassword(dr);
		clickLoginButton(dr);
	}
	
	
	public void routetoLogin(WebDriver dr)
	{
		dr.get(pureProject_loginURL);
	}
	
	public void maximize(WebDriver dr) 
	{
		dr.manage().window().maximize();
	}
	
	public void routetoOrganization(WebDriver dr)
	{
		dr.findElement(By.xpath(pureProject_organizationButton)).click();
	}
	
	public void enterOrganizationName(WebDriver dr)
	{
		dr.findElement(By.xpath(pureProject_organizationBox)).sendKeys(orgText);
	}
	
	public void returntoLogin(WebDriver dr)
	{
		dr.findElement(By.xpath(pureProject_previousNext)).click();
	}
	
	public void enterUserName(WebDriver dr)
	{
		dr.findElement(By.xpath(pureProject_usernameBox)).sendKeys(collab_Username);
	}
	
	public void enterPassword(WebDriver dr)
	{
		dr.findElement(By.xpath(pureProject_passwordBox)).sendKeys(collab_password);
	}
	
	public void clickLoginButton(WebDriver dr)
	{
		ActionsPerform(dr,pureProject_loginButton);
	}
	
	
	
	/*Action Perform*/
	
	public void ActionsPerform(WebDriver d, String s)
	{
		WebElement el = d.findElement(By.xpath(s));
		
        Actions actions = new Actions(d);
        actions.moveToElement(el);
        actions.click();
        actions.build().perform();
	}
	
}
