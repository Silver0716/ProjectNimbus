package processor;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.apache.commons.io.FileUtils.copyURLToFile;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.mod_SeleniumDriver;

@SuppressWarnings( "deprecation" )
public class proc_SeleniumDriver 
{

	public WebDriver driver;
	mod_SeleniumDriver mod_SD = new mod_SeleniumDriver();
	File existingDriver = null;
    File folder = null;
	
	public void setDriver()
	{
		 File devFile = new File(mod_SD.getChromeDriver());

	        try
	        {
	            if(devFile.exists())
	            {
	                System.setProperty(mod_SD.getChromeWebDriver(),mod_SD.getChromeDriver());
	                driver = new ChromeDriver();
	                
	                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	                setWebDriver(driver);
	            }
	            else
	            {
	                URL resource = getClass().getResource("/resources/chromedriver.exe");
	                File f = new File("Driver");
	                if (!f.exists())
	                {
	                    f.mkdirs();
	                    folder = f;
	                }

	                File chromeDriver = new File("Driver" + File.separator + "chromedriver.exe");
	                
	                if (!chromeDriver.exists())
	                {
	                    chromeDriver.createNewFile();
	                    copyURLToFile(resource, chromeDriver);
	                }
	                
	                existingDriver = chromeDriver.getAbsoluteFile();
	                System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
	                driver = new ChromeDriver();
	                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	                setWebDriver(driver);
	            }
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	}
	
	public void endWebDriver()
    {
        driver.close();
		driver.quit();
    }

    public void deleteDirectory()
    {
    	try
    	{
    		if(existingDriver!=null)
        	{
        		folder = existingDriver.getParentFile();
                existingDriver.delete();
                folder.delete();
        	}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
        
    }
	
    public void setWebDriver(WebDriver dDriver)
    {
        driver = dDriver;
    }

    public WebDriver getWebDriver()
    {
        return driver;
    }
	
    public void WindowOpen(WebDriver driver)
    {
        ((JavascriptExecutor)driver).executeScript("window.open()");
    }
    
    public boolean isPresent(By by)
    {
        try
        {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            driver.findElement(by);
            return true;
        }
        catch(NoSuchElementException e)
        {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            return false;
        }
    }

    public void isChild(String sName, List<WebElement> child)
    {
        for(WebElement el:child)
        {
            if(el.getText().contains(sName))
            {
                el.click();
                break;
            }
        }
    }

    public void scriptClick(String sName, WebDriver wDriver)
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("//*[text() = '" + sName + "']"), sName));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = '" + sName + "']/ancestor::div[1]/ancestor::div[1]/descendant::button[1]")));
        WebElement elTestScriptName = wDriver.findElement(By.xpath("//*[text() = '" + sName + "']/ancestor::div[1]/ancestor::div[1]/descendant::button[1]"));
        elTestScriptName.sendKeys(Keys.RETURN);
    }

    public void mapScenario(String importText,String mappedLocation,String mappedList, String tick, WebDriver wDriver) throws InterruptedException
    {
        List<WebElement> importScenarios = wDriver.findElements(By.xpath(importText));
        List<WebElement> mappedLocations = wDriver.findElements(By.xpath(mappedLocation));

        WebDriverWait wait = new WebDriverWait(wDriver,30);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tick)));
                driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL,Keys.HOME));
                    Thread.sleep(500);
                        wDriver.findElement(By.xpath(tick)).click();

        int s = importScenarios.size();

        for(int c=0; c<s;c++)
        {
            WebElement el_importScenario = importScenarios.get(c);
            WebElement el_mappedLocation = mappedLocations.get(c);

            el_mappedLocation.click();
            List<WebElement> mappedLists = el_mappedLocation.findElements(By.xpath("./parent::div/ul/descendant::li"));
            for (WebElement el : mappedLists)
            {
                if(el.getText().contains(el_importScenario.getText()))
                {
                    el.click();
                    break;
                }
            }
        }
    }

    public void delayLoading(WebDriver wDriver, String sName) throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(wDriver, 200);
        Long startTime, endTime;

        startTime = System.nanoTime();
            List<WebElement> element = driver.findElements(By.xpath(sName));
                wait.until(ExpectedConditions.invisibilityOfAllElements(element));
        endTime = System.nanoTime();

        if(((endTime-startTime)/1000000)<3000)
        {
            Thread.sleep(2000);
        }
    }
}
