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
	public String csvFile = "C:\\Users\\ngrandia\\Documents\\Automation (QA)\\Bulk Import CSV\\test_bulk_import.xlsx";
	public static XSSFSheet sheet1;
	
	public String RolesPerm = ".//*[@id='home-welcomeV2']/descendant::div[contains(@class,'row')]/descendant::a[contains(text(),'Roles')]";
	public String iframe1 = ".//*[@name='admin']";
	public String iframe2 = ".//*[@id='directory-people-edit']/descendant::iframe[contains(@class,'directory')]";
	public String RoleField = ".form-control.input-sm.ng-pristine.ng-valid.ng-empty.ng-touched";
	public String dDropdown = ".//*[@id='directory-rolesV2-index']/descendant::select";
	
	public String defAdmin = ".//*[@id='directory-rolesV2-index']/descendant::div[contains(@class,'fixtable-inner')]/descendant::div[contains(@class,'roleInfo break-text')]/descendant::strong[contains(text(),'admin')]";
	public String editButton = ".//*[@id='directory-rolesV2-index']/descendant::a[contains(@class,'btn btn-default btn-sm')]";
	
	public String permTab = ".//*[@id='directory-rolesV2-edit']/descendant::a[contains(text(),'Permissions')]";
	
	
	public static List<String> failRemarks = new ArrayList<>();

	public int totalValuePassed = 0;
	public int totalValueComparison;
	public int rowcount;

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

