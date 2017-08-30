package processor;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
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
		
		public void init_Search(WebDriver dr) throws Exception
		{
			WebDriverWait wait = new WebDriverWait(dr, 30);
			File src = new File(csvFile);
			FileInputStream fis = new FileInputStream(src);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
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
				
				//column check
				colcount = sheet1.getRow(0).getLastCellNum();
				Iterator rows = sheet1.rowIterator();
				List data = new ArrayList();
				
				while (rows.hasNext()) {
	                HSSFRow row = (HSSFRow) rows.next();
	                Iterator cells = row.cellIterator();
				
				System.out.println("Total Test Case is "+rowcount);
				System.out.println("Total columns is "+colcount);

				for( int x=1; x<=colcount; x++)
                { // Loop through cells
				     HSSFCell cell;

	                    if( row.getCell(x) == null )
	                    {
	                        cell = row.createCell(x);
	                    } 
	                    else 
	                    {
	                        cell = row.getCell(x);
	                    }

	                    data.add(cell);

	            }

	                sheetData.add(data);
	                
	                
	            //original row loop	
				for(int i=1; i<=rowcount; i++)
				{
					String exName = sheet1.getRow(i).getCell(0).getStringCellValue();
					String exEmail = sheet1.getRow(i).getCell(1).getStringCellValue();
					
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
					//dr.switchTo().frame(dr.findElement(By.xpath(iframe1)));
					wait.until(ExpectedConditions.elementToBeClickable(dr.findElement(By.xpath(iframe2))));
					dr.switchTo().frame(dr.findElement(By.xpath(iframe2)));
					
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(profileName)));
					String Name = dr.findElement(By.xpath(profileName)).getAttribute("innerHTML");
					System.out.println(Name);
					
					WebElement elEmail = dr.findElement(By.cssSelector(".domain"));
					String Email = elEmail.findElement(By.xpath("./parent::a")).getText();
					System.out.println(Email);
					
					/*Comparing Names*/
					compareValues(exName, Name);
					
					/*Comparing Email*/
					compareValues(exEmail, Email);
					
					/*Add new comparison Columns when needed*/
					
					dr.switchTo().defaultContent();
					dr.switchTo().frame(dr.findElement(By.xpath(iframe1)));
			
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cancel)));
					dr.findElement(By.xpath(cancel)).click();
						
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
					dr.findElement(By.xpath(searchField)).clear();;
				}
				
				/*If we confirm that the other columns like phone_work, title, department, etc. should be validated.
				 *Then we need to add up in the totalColumns attribute. Currently it is only set to two since
				 *We are only validating columns "Name" & "Email" */
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
	}