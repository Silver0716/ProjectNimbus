package model;

import java.io.File;

public class mod_SeleniumDriver 
{
	public String chromedrive = "C:\\Users\\ngrandia\\Downloads\\SELENIUM\\chromedriver_win32\\chromedriver.exe";
//	public String drive = getClass().getResource("/resources/chromedriver.exe").getPath().toString().replaceAll("%20"," ");
    public String chrome_webDrive = "webdriver.chrome.driver";
    String ivrSprintName;
    String syslocalSprintName;
    String sysremoteSprintName;
    String speechSprintName;
    String caSprintName;
    File existingDriver = null;
    File folder = null;
    
    public String getChromeDriver()
    {
    	return chromedrive;
    }
    
    public String getChromeWebDriver()
    {
    	return chrome_webDrive;
    }
    
    public void setIVRSprintName(String sprintName)
    {
        ivrSprintName = sprintName;
    }

    public void setSYSLocalSprintName(String sprintName)
    {
        syslocalSprintName = sprintName;
    }

    public void setSYSRemoteSprintName(String sprintName)
    {
        sysremoteSprintName = sprintName;
    }

    public void setSpeechSprintName(String sprintName)
    {
        speechSprintName = sprintName;
    }

    public void setCASprintName(String sprintName)
    {
        caSprintName = sprintName;
    }

    public String getIVRSprintName()
    {
        return ivrSprintName;
    }

    public String getSYSLOcalSprintName()
    {
        return syslocalSprintName;
    }

    public String getSYSRemoteSprintName()
    {
        return sysremoteSprintName;
    }

    public String getSpeechSprintName()
    {
        return speechSprintName;
    }

    public String getCaSprintName()
    {
        return caSprintName;
    }
    
}
