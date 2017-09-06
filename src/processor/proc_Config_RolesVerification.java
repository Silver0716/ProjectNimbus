package processor;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import model.mod_Config_RolesVerification;

public class proc_Config_RolesVerification extends mod_Config_RolesVerification
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
			File src = new File(xlsxFile);
			FileInputStream fis = new FileInputStream(src);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			int ctrA = 0;
			int ctrB = 0;
			
			try
			{
				sheet1 = wb.getSheetAt(0);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elAdmin)));
				dr.findElement(By.xpath(elAdmin)).click();
				
				if (isElementPresent("_pendo-close-guide_", dr))
				{
					dr.findElement(By.id("_pendo-close-guide_")).sendKeys(Keys.ENTER);
				}

				dr.switchTo().defaultContent();
				dr.switchTo().frame(dr.findElement(By.xpath(iframe1)));
				
				dr.findElement(By.xpath(RolesPerm)).click();
				
				rowcount = sheet1.getLastRowNum();

				System.out.println("Total Test Case is "+rowcount);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(RoleField)));
				WebElement search = dr.findElement(By.cssSelector(RoleField));
				search.sendKeys("admin");
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dDropdown)));
				WebElement elDropdown = dr.findElement(By.xpath(dDropdown));
				elDropdown.click();
				Select se = new Select(elDropdown);
				se.selectByVisibleText("Default");
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(defAdmin)));
				dr.findElement(By.xpath(defAdmin)).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(editButton)));
				dr.findElement(By.xpath(editButton)).click();
				
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(permTab)));
				dr.findElement(By.xpath(permTab)).click();

					for(int i=0; i<=rowcount; i++)
					{
						exPerms = sheet1.getRow(i).getCell(0).getStringCellValue();
						
//						System.out.println(permissions(exPerms));
						
						dr.switchTo().defaultContent();
						dr.switchTo().frame(dr.findElement(By.xpath(iframe1)));
						dr.findElement(By.xpath("html/body"));
						
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(permField)));
						dr.findElement(By.xpath(permField)).sendKeys(exPerms);
						
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(permissions(exPerms))));
						WebElement elPerms = dr.findElement(By.xpath(permissions(exPerms)));
						String foundPerms = elPerms.getText();
						ctrA = ctrA + 1;
						
//						System.out.println(foundPerms);
//						System.out.println(exPerms);
						
						if(exPerms.equals(foundPerms)) 
						{
							System.out.println(exPerms + " Pass");
							ctrB = ctrB + 1;
						}
						else
						{
							System.out.println(exPerms + " Fail");
							
						}
						
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(permField)));
						dr.findElement(By.xpath(permField)).clear();
						
						
						
					}
					if(ctrA == ctrB)
					{
						p_EO.setOutputValues(p_EO.CollaborateUser, "Verify User Import Information", "Pass", " ");
					}
					
					
				}
					catch(AssertionError e)
					{
						String failSentence = " ";
						for(String s:failRemarks)
						{
							failSentence = failSentence.concat(s+" "+"\n");
						}
						
						p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Roles", "Fail",failSentence);
						System.out.println(p_EO.ExcelOutputTable);
						wb.close();
						throw e;
					}
					catch(Exception ex)
					{
						p_EO.setOutputValues(p_EO.CollaborateUser, "Verify Roles", "Fail",ex.getMessage().toString());
						ex.printStackTrace();
						wb.close();
						throw ex;
					}
					wb.close();
					
				}
			
				public boolean rolesLocated(String[] roles, WebDriver dr) {
					for(int i = 0; i < roles.length; i++) {
						if(dr.findElement(By.xpath(roles[i])) == null)
							return false;
					}
					return true;
				}	
			
				public void compareValues(String excelValue, String value)
				{
					if(excelValue.equals(value))
					{
						totalValuePassed++;
					}
					else
					{
						add(excelValue, value);
					}
				}
				
				public boolean isElementPresent(String screenBox, WebDriver dr)
				{
					try
					{
						dr.findElement(By.id(screenBox));
						return true;
					}
					catch(org.openqa.selenium.NoSuchElementException e)
					{
						return false;
					}
				}
			}			
						
						
						
						