package model;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class mod_Config_RolesVerification 
{
	public String elAdmin = "html/body/descendant::li[contains(@class,'menu-top-level-item menu-admin')]/descendant::a[contains(@title,'Admin')]";
	public String csvFile = "C:\\Users\\ngrandia\\Documents\\Automation (QA)\\Bulk Import CSV\\test_bulk_import.xlsx";
	public static XSSFSheet sheet1;
	
	public String People = ".//*[@id='home-welcomeV2']/descendant::div[contains(@class,'row')]/descendant::a[contains(text(),'People')]";
	public String iframe1 = ".//*[@name='admin']";
	public String iframe2 = ".//*[@id='directory-people-edit']/descendant::iframe[contains(@class,'directory')]";
	public String searchField = ".//*[@id='directory-people-index']/div[1]/div[2]/child::kelly-kidgloves-input/child::input";
	public String dDropdown = ".//*[@id='directory-people-edit']/descendant::select[contains(@class,'form-control input-sm ng-pristine ng-valid ng-empty ng-touched')]";
	
	public String Remployee = ".//*[@id='directory-people-edit']/descendant::table[contains(@class,'table-top-aligned-cells')]/descendant::span[contains(text(),'employee')]";
	public String Radmin = ".//*[@id='directory-people-edit']/descendant::table[contains(@class,'table-top-aligned-cells')]/descendant::span[contains(text(),'admin')]";
	public String RcomUser = ".//*[@id='directory-people-edit']/descendant::table[contains(@class,'table-top-aligned-cells')]/descendant::span[contains(text(),'Communicate - User')]";
	
	public int totalValuePassed = 0;
	public int totalColumns = 2;
	public int totalValueComparison;
	public int rowcount;

	public static List<String> failRemarks = new ArrayList<>();
	
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

