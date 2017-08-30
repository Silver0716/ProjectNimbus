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
import org.openqa.selenium.support.ui.ExpectedConditions;
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
				totalValueComparison = totalColumns*rowcount;
//---------------------------------------------------------------------------------
//Check Excel Rows and Columns
				
				
				System.out.println("Total rows is "+rowcount);
				
				//build table
				for(int y=0;y<=rowcount;y++) {					
					colcount = sheet1.getRow(y).getLastCellNum();
					System.out.println("Total Column is "+colcount);
					
					temp_map = new HashMap<String, String>();
					for(int x=0;x<colcount;x++) {
						cell = sheet1.getRow(y).getCell(x, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK);
						switch (cell.getCellType()) {
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
						if(y == 0) {
							headers.add(value);
						}else {
							//build hashmap of user
							temp_map.put(headers.get(x), value);
						}	

					}
					System.out.println(temp_map);
					if(y != 0) {
						entryArray.add(temp_map);
						System.out.println();
					}
				}
				
			
				System.out.println(entryArray.size());
				for(HashMap<String, String> hm : entryArray) {
					System.out.println("\nEntry");
					for(Map.Entry<String, String> entry : hm.entrySet()) {
						System.out.println(entry.getKey() + " " + entry.getValue());
					}
				}
				
				
//----------------------------------------------------------------------------------
				for(HashMap<String, String> hm : entryArray) {
					if(hm.get("name") != "") {
						checkName(wait, dr, hm.get("name"));
					}
					if(hm.get("email") != "") {
						checkEmail(wait, dr, hm.get("email"));
					}
				}
				
				
				/*If we confirm that the other columns like phone_work, title, department, etc. should be validated.
				 *Then we need to add up in the totalColumns attribute. Currently it is only set to two since
				 *We are only validating columns "Name" & "Email" */
				wb.close();
				
				Assert.assertTrue(totalValuePassed==totalValueComparison);
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify User Import Information", "Pass", " ");
			
			}


			catch(AssertionError e)
			{
				String failSentence = " ";
				for(String s:failRemarks)
				{
					failSentence = failSentence.concat(s+" "+"\n");
				}
				
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify User Import Information", "Fail",failSentence);
				System.out.println(p_EO.ExcelOutputTable);
				wb.close();
				throw e;
			}
			catch(Exception ex)
			{
				p_EO.setOutputValues(p_EO.CollaborateUser, "Verify User Import Information", "Fail",ex.getMessage().toString());
				ex.printStackTrace();
				wb.close();
				throw ex;
			}
			wb.close();
			
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
		
		public void checkName(WebDriverWait wait, WebDriver dr, String exName) throws InterruptedException 
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
			WebElement searchUser = dr.findElement(By.xpath(searchField));
			searchUser.sendKeys(exName);
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
			
			//exit one user profile to proceed to next profile
			dr.switchTo().defaultContent();
			dr.switchTo().frame(dr.findElement(By.xpath(iframe1)));
	
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cancel)));
			dr.findElement(By.xpath(cancel)).click();
				
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
			dr.findElement(By.xpath(searchField)).clear();
		}
		
		public void checkEmail(WebDriverWait wait, WebDriver dr, String exEmail) throws InterruptedException 
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
			WebElement searchUser = dr.findElement(By.xpath(searchField));
			searchUser.sendKeys(exEmail);
			Thread.sleep(3000);
			searchUser.sendKeys(Keys.BACK_SPACE);
			
			dr.findElement(By.linkText(exEmail)).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(personDetails)));
			dr.findElement(By.linkText(personDetails)).click();
		
			//dr.switchTo().defaultContent();
			dr.findElement(By.xpath("html/body")).click();
			wait.until(ExpectedConditions.elementToBeClickable(dr.findElement(By.xpath(iframe2))));
			dr.switchTo().frame(dr.findElement(By.xpath(iframe2)));
			
			WebElement elEmail = dr.findElement(By.cssSelector(".domain"));
			String Email = elEmail.findElement(By.xpath("./parent::a")).getText();
			System.out.println(Email);
			
			//compare here! Add new comparison Columns when needed*/
			compareValues(exEmail, Email);
			
			//exit one user profile to proceed to next profile
			dr.switchTo().defaultContent();
			dr.switchTo().frame(dr.findElement(By.xpath(iframe1)));
	
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cancel)));
			dr.findElement(By.xpath(cancel)).click();
				
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
			dr.findElement(By.xpath(searchField)).clear();
		}
		
		public void checkPhone(WebDriverWait wait, WebDriver dr, String exPhone) throws InterruptedException 
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
			WebElement searchUser = dr.findElement(By.xpath(searchField));
			searchUser.sendKeys(exPhone);
			Thread.sleep(3000);
			searchUser.sendKeys(Keys.BACK_SPACE);
			
			dr.findElement(By.linkText(exPhone)).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(personDetails)));
			dr.findElement(By.linkText(personDetails)).click();
		
			//dr.switchTo().defaultContent();
			dr.findElement(By.xpath("html/body")).click();
			wait.until(ExpectedConditions.elementToBeClickable(dr.findElement(By.xpath(iframe2))));
			dr.switchTo().frame(dr.findElement(By.xpath(iframe2)));
			
//			WebElement elEmail = dr.findElement(By.cssSelector(".domain"));
//			String Email = elEmail.findElement(By.xpath("./parent::a")).getText();
//			System.out.println(Email);
			
			// ENTER ELEMENT LOCATOR for PHONE_WORK
			
			//compareValues(exPhone, Phone);
			
			//exit one user profile to proceed to next profile
			dr.switchTo().defaultContent();
			dr.switchTo().frame(dr.findElement(By.xpath(iframe1)));
	
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cancel)));
			dr.findElement(By.xpath(cancel)).click();
				
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
			dr.findElement(By.xpath(searchField)).clear();
		}
}