package model;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public class mod_Config_ImportVerifyMultiRoles 
{
	public String elAdmin = "html/body/descendant::li[contains(@class,'menu-top-level-item menu-admin')]/descendant::a[contains(@title,'Admin')]";
//	public String csvFile = this.getClass().getClassLoader().getResource("resources\testImport.xlsx").getPath().toString().replaceAll("%20", " ");
	public String xlsxFile = "C:\\Users\\ngrandia\\Documents\\Automation (QA)\\testImport.xlsx";
	public static XSSFSheet sheet1;
	
	public String iframe1 = ".//*[@name='admin']";
	public String iframe2 = ".//*[@id='directory-people-edit']/descendant::iframe[contains(@class,'directory')]";
	
	public String People = ".//*[@id='home-welcomeV2']/descendant::div[contains(@class,'row')]/descendant::a[contains(text(),'People')]";
	public String searchField = ".//*[@id='directory-people-index']/div[1]/div[2]/child::kelly-kidgloves-input/child::input";
	public String dDropdown = ".//*[@id='directory-people-edit']/div/div/div[4]/div[1]/div[1]/div/div[3]/table/thead/tr[2]/th[5]/div/select";
	public String ddAssigned = ".//*[@id='directory-people-edit']/descendant::select[contains(@class,'form-control input-sm ng-pristine ng-valid ng-empty ng-touched')]/descendant::option[contains(@value,'0')]";
	
	public String cancel = ".//*[@id='directory-people-edit']/menu/div[1]/a";
	
	public String Remployee = ".//*[@id='directory-people-edit']/descendant::table[contains(@class,'table-top-aligned-cells')]/descendant::span[contains(text(),'employee')]";
	public String Radmin = ".//*[@id='directory-people-edit']/descendant::table[contains(@class,'table-top-aligned-cells')]/descendant::span[contains(text(),'admin')]";
	public String RcomUser = ".//*[@id='directory-people-edit']/descendant::table[contains(@class,'table-top-aligned-cells')]/descendant::span[contains(text(),'Communicate - User')]";
	  	
	
	
	public static List<String> failRemarks = new ArrayList<>();

	public int totalValuePassed = 0;
	public int totalValueComparison;
	public int rowcount;
	
	public void add(String excelName, ArrayList<String> wrongRoles)
	{
		
		/*if(value.length()>0)
		{
			failRemarks.add("The expected value: "+excelValue+" doesn't match the retrieved value: "+value);
		}
		else
		{
			failRemarks.add("The expected value: "+excelValue+" wasn't retrieved.");
		}*/
	}

}
