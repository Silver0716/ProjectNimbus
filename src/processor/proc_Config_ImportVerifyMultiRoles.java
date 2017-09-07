package processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
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

import model.mod_Config_ImportVerifyMultiRoles;

public class proc_Config_ImportVerifyMultiRoles extends mod_Config_ImportVerifyMultiRoles
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
			boolean success = true;
			
			try
			{
				sheet1 = wb.getSheetAt(0);
				
				dr.switchTo().defaultContent();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elAdmin)));
				dr.findElement(By.xpath(elAdmin)).click();
				
				if (isElementPresent("_pendo-close-guide_", dr))
				{
					dr.findElement(By.id("_pendo-close-guide_")).sendKeys(Keys.ENTER);
				}

				dr.switchTo().defaultContent();
				dr.switchTo().frame(dr.findElement(By.xpath(iframe1)));
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(People)));
				dr.findElement(By.xpath(People)).click();
				
				rowcount = sheet1.getLastRowNum();
				System.out.println("Total Test Case is "+rowcount);
				for(int i=1; i<=rowcount; i++)
				{
					String exName = sheet1.getRow(i).getCell(0).getStringCellValue();
					String exRoles[] = sheet1.getRow(i).getCell(7).getStringCellValue().split(",");
					
					
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
					WebElement searchUser = dr.findElement(By.xpath(searchField));
					searchUser.sendKeys(exName);
					Thread.sleep(3000);
					searchUser.sendKeys(Keys.BACK_SPACE);
					dr.findElement(By.linkText(exName)).click();
					
					dr.switchTo().defaultContent();
					dr.switchTo().frame(dr.findElement(By.xpath(iframe1)));
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dDropdown)));
					WebElement elDropdown = dr.findElement(By.xpath(dDropdown));
					elDropdown.click();
					Select se = new Select(elDropdown);
					dr.findElement(By.cssSelector(".form-control.input-sm.ng-pristine.ng-valid.ng-empty.ng-touched"));
					Thread.sleep(1000);
					se.selectByVisibleText("Assigned");

					
					WebElement roleTable = dr.findElement(By.xpath(".//*[@id='directory-people-edit']/descendant::table[contains(@class,'table-top-aligned-cells')]/tbody"));
					List<WebElement> trCollection = roleTable.findElements(By.xpath(".//*[@id='directory-people-edit']/descendant::table[contains(@class,'table-top-aligned-cells')]/tbody"));
					System.out.println("NUMBER OF ROWS IN THIS TABLE = "+trCollection.size());
					
					System.out.println(exName + " Role: " + Arrays.toString(exRoles));
				
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Remployee)));
					WebElement ConfirmEmployee = dr.findElement(By.xpath(Remployee));
					System.out.println(ConfirmEmployee.getText());
					
					for(int j = 0; j < exRoles.length; j++) {		
						if (exRoles[j].equals("admin")) 
						{
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Radmin)));
							WebElement ConfirmAdmin = dr.findElement(By.xpath(Radmin));
							System.out.println(ConfirmAdmin.getText());
						}
						else if(exRoles[j].equals("Communicate - User"))
						{
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RcomUser)));
							WebElement ConfirmComUser = dr.findElement(By.xpath(RcomUser));
							System.out.println(ConfirmComUser.getText());
						}
						else 
						{
							//In Excel but not in PC
							
							success = false;
						}
					}
					
					dr.switchTo().defaultContent();
					dr.switchTo().frame(dr.findElement(By.xpath(iframe1)));
			
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cancel)));
					dr.findElement(By.xpath(cancel)).click();
					
					dr.switchTo().defaultContent();
					dr.switchTo().frame(dr.findElement(By.xpath(iframe1)));
					
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
					dr.findElement(By.xpath(searchField)).clear();
					
				}
			
				wb.close();
					
				//String[] foundRoles = {Remployee, Radmin};
				Assert.assertTrue(success);
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
	
		public boolean rolesLocated(String[] roles, WebDriver dr) 
		{
			for(int i = 0; i < roles.length; i++) 
			{
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
				//add(excelValue, value);
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
	

