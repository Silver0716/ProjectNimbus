package processor;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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
				
				String textRole = role(sheet1.getRow(0).getCell(0).getStringCellValue());
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(textRole)));
				dr.findElement(By.xpath(textRole)).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(editButton)));
				dr.findElement(By.xpath(editButton)).click();
				
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(permTab)));
				dr.findElement(By.xpath(permTab)).click();
					
				int columnCount = 0;
				Row r = sheet1.getRow(0);
				int maxColCount = r.getLastCellNum();
				
				while(columnCount<maxColCount)
				{
					String roleName = sheet1.getRow(0).getCell(columnCount).getStringCellValue();
					listWebPermission(dr);
					listExcelPermission(dr, sheet1, columnCount);
					
					checkPermission(roleName);
					
					clearList();
					clearInput(dr,permField,wait);
					
					columnCount++;
					dr.findElement(By.xpath(returnPage)).click();
					
					if(columnCount<maxColCount)
					{
						repeat(dr, wait, role(sheet1.getRow(0).getCell(columnCount).getStringCellValue()));
					}
				}
					if(failRemarks.size()>0)
					{
						Assert.assertTrue(failRemarks.size()<0);
						
					}
					else
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
				
				public void clearInput(WebDriver dr, String xPathValue, WebDriverWait wait)
				{
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPathValue)));
					dr.findElement(By.xpath(xPathValue)).clear();
				}
				
				public void repeat(WebDriver dr, WebDriverWait wait, String xPathvalue)
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dDropdown)));
					WebElement elDropdown = dr.findElement(By.xpath(dDropdown));
					elDropdown.click();
					Select se = new Select(elDropdown);
					se.selectByVisibleText("Default");
					
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathvalue)));
					dr.findElement(By.xpath(xPathvalue)).click();
					
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathvalue.concat(editBtn))));
					dr.findElement(By.xpath(xPathvalue.concat(editBtn))).click();
					
					dr.findElement(By.xpath(permTab)).click();
				}
				
				public void listWebPermission(WebDriver dr)
				{
					List<WebElement> tempList = dr.findElements(By.xpath(listResultPermissions));
					for(WebElement element:tempList)
					{
						webPermissionsList.add(element.getText());
					}
				}
				
				public void listExcelPermission(WebDriver dr, XSSFSheet excelSheet, int colCount)
				{
					int totalRow = 0;
					
					for(Row row : excelSheet)
					{
						if(row.getCell(colCount)!=null)
						{
							totalRow +=1;
						}
					}
					
					for(int i=1; i<totalRow; i++)
					{
						excelPermissionsList.add(sheet1.getRow(i).getCell(colCount).getStringCellValue());
					
					}
				}
				
				public void checkPermission(String roleName)
				{
					
					Collection<String> excelList= new ArrayList<String>(excelPermissionsList);
					Collection<String> webList= new ArrayList<String>(excelPermissionsList);
					excelPermissionsList.removeAll(webList);
					for(String value:excelPermissionsList)
					{
						if(value.length()>0)
						{
							failRemarks.add("The permision '"+value+"' is not present. for Role: "+roleName);
						}
					}
					
					webPermissionsList.removeAll(excelList);
					for(String value:webPermissionsList)
					{
						if(value.length()>0)
						{
							failRemarks.add("The permision '"+value+"' shouldn't be added for this Role:"+roleName);
						}
					}
					
				}
						
				public void clearList()
				{
					webPermissionsList.clear();
					excelPermissionsList.clear();
				}
			
			}								
						