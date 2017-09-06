package processor;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import model.mod_Config_ImportVerification;

public class proc_Config_ImportVerification extends mod_Config_ImportVerification
{
	proc_UniversalLogin p_UL = new proc_UniversalLogin();
	proc_ExcelOutput p_EO = new proc_ExcelOutput();
			
		public void initSteps(WebDriver dr)
		{
			p_UL.start(dr);
		}
		
		@SuppressWarnings("deprecation")
		public void init_Search(WebDriver dr) throws Exception
		{
			WebDriverWait wait = new WebDriverWait(dr, 30);
			File src = new File(csvFile);
			FileInputStream fis = new FileInputStream(src);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet1 = wb.getSheetAt(0);
			XSSFCell cell;
			
			String value = null;
			
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
				dr.findElement(By.xpath("html/body")).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(iframe1)));
				dr.switchTo().frame(dr.findElement(By.xpath(iframe1)));
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(People)));
				dr.findElement(By.xpath(People)).click();
				
				rowcount = sheet1.getLastRowNum();

//---------------------------------------------------------------------------------
//Check Excel Rows and Columns
				
				
				System.out.println("Total rows is "+rowcount);
				
				//build table
				for(int y=0;y<=rowcount;y++) 
				{					
					colcount = sheet1.getRow(y).getLastCellNum();
					System.out.println("Total Column is "+colcount);
					
					temp_map = new HashMap<String, String>();
					for(int x=0;x<colcount;x++) 
					{
						cell = sheet1.getRow(y).getCell(x, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK);
						switch (cell.getCellType()) 
						{
			                case HSSFCell.CELL_TYPE_FORMULA:
			                    value = cell.getCellFormula();
			                    break;
			                case HSSFCell.CELL_TYPE_NUMERIC:
			                    value = Integer.toString((int)(cell.getNumericCellValue()));
			                    break;
			                case HSSFCell.CELL_TYPE_STRING:
			                    value = cell.getStringCellValue();
			                    break;
			                case HSSFCell.CELL_TYPE_BLANK:
			                    value="";
			                    break;
			                case HSSFCell.CELL_TYPE_ERROR:
			                    value="error";
			                    break;
			                default:
			                    break;
						}
						
						System.out.print(value + "\t");
						
						//get values of first row
						if(y == 0) 
						{
							headers.add(value);
						}
						else 
						{
							//build hashmap of user
							temp_map.put(headers.get(x), value);
						}	

					}
					System.out.println(temp_map);
					if(y != 0) 
					{
						entryArray.add(temp_map);
						System.out.println();
					}
				}
				
			
				System.out.println(entryArray.size());
				for(HashMap<String, String> hm : entryArray) 
				{
					System.out.println("\nEntry");
					for(Map.Entry<String, String> entry : hm.entrySet()) 
					{
						System.out.println(entry.getKey() + " " + entry.getValue());
					}
				}
				
//----------------------------------------------------------------------------------
				for(HashMap<String, String> hm : entryArray) 
				{
					if(hm.get("name") != "") 
					{
						checkName(wait, dr, hm.get("name"), hm.get("email"));
					}
					if(hm.get("email") != "") 
					{
						checkEmail(wait, dr, hm.get("email"));
					}
					if(hm.get("phone_work") != "") 
					{
						checkPhone(dr,hm.get("phone_work"));
					}
					if(hm.get("title")!="")
					{
						checkTitle(dr,hm.get("title"));
					}
					if(hm.get("department")!="")
					{
						checkDepartment(dr,hm.get("department"));
					}
					if(hm.get("email_manager")!="")
					{
						checkEmailManager(dr,hm.get("email_manager"));
					}
					if(hm.get("location_work")!="")
					{
						checkLocation(dr,hm.get("location_work"));
					}
					if(hm.get("roles")!="")
					{
						checkRoles(dr,wait,hm.get("roles"));
					}
					exitProfile(dr,wait);
					
				}
				
				
				/*If we confirm that the other columns like phone_work, title, department, etc. should be validated.
				 *Then we need to add up in the totalColumns attribute. Currently it is only set to two since
				 *We are only validating columns "Name" & "Email" */
				wb.close();
				
				Assert.assertTrue(failRemarks.size()<0 || failRemarks.size()==0);
				p_EO.setOutputValues(p_EO.Config, "Verify User Import Information", "Pass", " ");
			
			}
			catch(AssertionError e)
			{
				String failSentence = " ";
				for(String s:failRemarks)
				{
					failSentence = failSentence.concat(s+" "+"\n");
				}
				
				p_EO.setOutputValues(p_EO.Config, "Verify User Import Information", "Fail",failSentence);
				System.out.println(p_EO.ExcelOutputTable);
				wb.close();
				throw e;
			}
			catch(Exception ex)
			{
				p_EO.setOutputValues(p_EO.Config, "Verify User Import Information", "Fail",ex.getMessage().toString());
				ex.printStackTrace();
				wb.close();
				throw ex;
			}
			wb.close();
			
		}
		
		
		public void checkName(WebDriverWait wait, WebDriver dr, String exName, String exEmail) throws InterruptedException 
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
			WebElement searchUser = dr.findElement(By.xpath(searchField));
			searchUser.sendKeys(exEmail);
			Thread.sleep(3000);
			searchUser.sendKeys(Keys.BACK_SPACE);
			
			dr.findElement(By.linkText(exName)).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(personDetails)));
			dr.findElement(By.linkText(personDetails)).click();
		
			//dr.switchTo().defaultContent();
			dr.findElement(By.xpath("html/body")).click();
			wait.until(ExpectedConditions.elementToBeClickable(dr.findElement(By.xpath(iframe2))));
			dr.switchTo().frame(dr.findElement(By.xpath(iframe2)));
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(profileName)));
			String Name = dr.findElement(By.xpath(profileName)).getAttribute("innerHTML");
			System.out.println(Name);
			
			
			//compare here! Add new comparison Columns when needed*/
			compareValues(exName, Name);
			
		}
		
		public void checkEmail(WebDriverWait wait, WebDriver dr, String exEmail) throws InterruptedException 
		{
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
//			WebElement searchUser = dr.findElement(By.xpath(searchField));
//			searchUser.sendKeys(exEmail);
//			Thread.sleep(3000);
//			searchUser.sendKeys(Keys.BACK_SPACE);
//			
//			dr.findElement(By.linkText(exName)).click();
//			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(personDetails)));
//			dr.findElement(By.linkText(personDetails)).click();
//		
//			//dr.switchTo().defaultContent();
//			dr.findElement(By.xpath("html/body")).click();
//			wait.until(ExpectedConditions.elementToBeClickable(dr.findElement(By.xpath(iframe2))));
//			dr.switchTo().frame(dr.findElement(By.xpath(iframe2)));
			
			WebElement elEmail = dr.findElement(By.cssSelector(".domain"));
			String Email = elEmail.findElement(By.xpath("./parent::a")).getText();
			System.out.println(Email);
			
			//compare here! Add new comparison Columns when needed*/
			compareValues(exEmail, Email);
			
		}
		
		public void checkPhone(WebDriver dr, String exPhone) throws InterruptedException 
		{
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
//			WebElement searchUser = dr.findElement(By.xpath(searchField));
//			searchUser.sendKeys(exPhone);
//			Thread.sleep(3000);
//			searchUser.sendKeys(Keys.BACK_SPACE);
//			
//			dr.findElement(By.linkText(exPhone)).click();
//			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(personDetails)));
//			dr.findElement(By.linkText(personDetails)).click();
//		
//			dr.switchTo().defaultContent();
//			dr.findElement(By.xpath("html/body")).click();
//			wait.until(ExpectedConditions.elementToBeClickable(dr.findElement(By.xpath(iframe2))));
//			dr.switchTo().frame(dr.findElement(By.xpath(iframe2)));
			
//			WebElement elEmail = dr.findElement(By.cssSelector(".domain"));
//			String Email = elEmail.findElement(By.xpath("./parent::a")).getText();
//			System.out.println(Email);
			
			// ENTER ELEMENT LOCATOR for PHONE_WORK
			WebElement elPhone = dr.findElement(By.xpath(profilePhoneStart+exPhone+profileClose));
			String Phone = elPhone.getText();
			
			compareValues(exPhone, Phone);
			
		}
		
		public void checkTitle(WebDriver dr, String exTitle) throws InterruptedException
		{
//			WebElement elTitle = dr.findElement(By.xpath(profileTitle)).getAttribute("innerHTML");
			String Title = dr.findElement(By.xpath(profileTitle)).getAttribute("innerHTML");
			
			compareValues(exTitle,Title);
		}
		
		public void checkDepartment(WebDriver dr, String exDepartment) throws InterruptedException
		{
//			WebElement elDepartment = dr.findElement(By.xpath(profileDepartment));
			String Department = dr.findElement(By.xpath(profileDepartment)).getAttribute("innerHTML");
			
			compareValues(exDepartment,Department);
		}
		
		public void checkEmailManager(WebDriver dr,  String exManagerEmail) throws InterruptedException
		{
			String actualValue = mouseOver(dr, profileManagerEmail, hoverDirectory);
			compareValues("mailto:"+exManagerEmail,actualValue);
		}
		
		public void checkLocation(WebDriver dr, String exLocation) throws InterruptedException
		{
			WebElement elLocation = dr.findElement(By.xpath(profileLocation));
			String Location = elLocation.getText();
			
			compareValues(exLocation,Location);
		}
		
		public void checkRoles(WebDriver dr, WebDriverWait wait, String exRole) throws InterruptedException
		{
			dr.switchTo().defaultContent();
			dr.switchTo().frame(dr.findElement(By.xpath(iframe1)));
			dr.findElement(By.linkText(rolesLicenesLink)).click();
			
			
//			dr.findElement(By.xpath(profileRoleDropDown)).click();
			
			Select select = new Select(dr.findElement(By.xpath(profileRoleDropDown)));
			select.selectByVisibleText("Assigned");
			
//			dr.findElement(By.xpath(profileRoleSelect)).sendKeys(Keys.ENTER);
//			dr.switchTo().frame(dr.findElement(By.xpath(iframe2)));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(profileRoleTable)));
			
			WebElement elRole = dr.findElement(By.xpath(profileRole+exRole+profileCloseContains));
			String Role = elRole.getText();
			
			compareValues(exRole,Role);
		}
		
		public void compareValues(String excelValue, String value)
		{
			this.delaySleep(1);
			if(excelValue.equals(value))
			{
				//This is intended
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
		
		public void exitProfile(WebDriver dr, WebDriverWait wait)
		{
			//exit one user profile to proceed to next profile
			dr.switchTo().defaultContent();
			dr.switchTo().frame(dr.findElement(By.xpath(iframe1)));
	
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cancel)));
			dr.findElement(By.xpath(cancel)).click();
				
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
			dr.findElement(By.xpath(searchField)).clear();
		}
		
		public String mouseOver(WebDriver dr, String exValueElement, String exHoverElement)
		{
			Actions action = new Actions(dr);
			WebElement element = dr.findElement(By.xpath(exValueElement));
			
			
			
			action.moveToElement(element);
			action.perform();
			this.delaySleep(2);

			
			String actualValue = dr.findElement(By.xpath(exHoverElement)).getAttribute("href");
			
			return actualValue;
		}
		
		public void delaySleep(int seconds)
		{
			try
			{
				Thread.sleep(seconds*1000);
			}
			catch(Exception e)
			{
				
			}
		}
		
}