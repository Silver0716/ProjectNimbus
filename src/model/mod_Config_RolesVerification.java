package model;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;

//.getPath().toString().replaceAll("%20","");

public class mod_Config_RolesVerification 
{
	public String elAdmin = "html/body/descendant::li[contains(@class,'menu-top-level-item menu-admin')]/descendant::a[contains(@title,'Admin')]";
	public String textFile = "resources/testImport.xlsx";
//	public String csvFile = this.getClass().getClassLoader().getResource("resources\testImport.xlsx").getPath().toString().replaceAll("%20", " ");
	public String xlsxFile = "C:\\Users\\ngrandia\\Documents\\Automation (QA)\\Roles and Permissions\\admin permissions.xlsx";
	public static XSSFSheet sheet1;
	
	public String RolesPerm = ".//*[@id='home-welcomeV2']/descendant::div[contains(@class,'row')]/descendant::a[contains(text(),'Roles')]";
	public String iframe1 = ".//*[@name='admin']";
	public String iframe2 = ".//*[@id='directory-people-edit']/descendant::iframe[contains(@class,'directory')]";
	public String RoleField = ".form-control.input-sm.ng-pristine.ng-valid.ng-empty.ng-touched";
	public String dDropdown = ".//*[@id='directory-rolesV2-index']/descendant::select";
	
	public String defAdmin = ".//*[@id='directory-rolesV2-index']/descendant::div[contains(@class,'fixtable-inner')]/descendant::div[contains(@class,'roleInfo break-text')]/descendant::strong[contains(text(),'admin')]";
	public String editButton = ".//*[@id='directory-rolesV2-index']/descendant::a[contains(@class,'btn btn-default btn-sm')]";
	
	public String permTab = ".//*[@id='directory-rolesV2-edit']/descendant::li[contains(@class,'uib-tab nav-item')]/descendant::a[contains(text(),'Permissions')]";
	//public String permField = ".//*[@class='fixtable-inner']/descendant::tr[contains(@class,'fixtable-column-filters')]/descendant::input[contains(@class,'form-control input-sm ng-valid ng-touched ng-dirty ng-valid-parse ng-empty')]";
	public String permField = ".//*[@id='directory-rolesV2-edit']/form/div[1]/div/div[2]/div/div[3]/table/thead/tr[2]/th[3]/div/div/input";
	
	public String exPerms;
	//public String permissions = ".//*[@id='directory-rolesV2-edit']/descendant::div[@class='fixtable-inner']/descendant::span[contains(text(),'"+exPerms+"')]";
	public boolean results;
	
	
	public static List<String> failRemarks = new ArrayList<>();

	public int totalValuePassed = 0;
	public int totalValueComparison;
	public int rowcount;


	public String permissions(String exP)
	{
		return ".//*[@id='directory-rolesV2-edit']/descendant::div[@class='fixtable-inner']/descendant::span[contains(text(),'"+exP+"')]";
	}
	
	public void add(String excelValue, String value)
	{
		
		if(value.length()>0)
		{
			failRemarks.add("The expected value: "+excelValue+" doesn't match the retrieved value: "+value);
		}
		else
		{
			failRemarks.add("The expected value: "+excelValue+" wasn't retrieved.");
		}
		
	}
}

