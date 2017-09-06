package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;


public class mod_Config_ImportVerification 
{
	public String elAdmin = "html/body/descendant::li[contains(@class,'menu-top-level-item menu-admin')]/descendant::a[contains(@title,'Admin')]";
	//public String csvFile = getClass().getResource("/resources/people_import_example.xlsx").getPath().toString().replaceAll("%20", " ");
	public String csvFile = "C:\\Users\\ngrandia\\Documents\\Automation (QA)\\Bulk Import CSV\\test_bulk_import.xlsx";
	public static XSSFSheet sheet1;
	
	public String People = ".//*[@id='home-welcomeV2']/descendant::div[contains(@class,'row')]/descendant::a[contains(text(),'People')]";
	public String iframe1 = ".//*[@name='admin']";
	public String searchField = ".//*[@id='directory-people-index']/div[1]/div[2]/child::kelly-kidgloves-input/child::input";
	public String personDetails = "Person Details";
	public String rolesLicenesLink = "Roles & Licenses";
	public String iframe2 = ".//*[@id='directory-people-edit']/descendant::iframe[contains(@class,'directory')]";
	public String hoverDirectory = ".//*[contains(@class,'entity-contact-group compact ember-view')]/descendant::div[contains(@class,'contact email')]/a";
	public String profileName = ".//*[@class='information']/descendant::div[contains(@class,'field-component text-field name text-field on-profile ember-view')]/descendant::span[contains(@class,'off-page')]";
	public String profilePhoneStart = ".//*[contains(@class,'field-group entries field-entries contact-info-phone-type')]/div[contains(@class,'group-values entry-values')]/descendant::a[text()='";
	public String profileClose = "']";
	public String profileCloseContains = "')]";
	public String profileTitle = ".//*[@class='information']/descendant::div[contains(@class,'field-component text-field title text-field on-profile ember-view')]/descendant::span[contains(@class,'off-page')]";
	public String profileDepartment = ".//*[@class='information']/descendant::div[contains(@class,'field-component text-field department text-field on-profile ember-view')]/descendant::span[contains(@class,'off-page')]";
	public String profileManagerEmail = ".//*[contains(@class,'field-group management-relationship relationship-container')]//*[@class='person']/descendant::a[1]";
	public String profileLocation = ".//*[contains(@class,'field-section location-section')]/descendant::div[contains(@class,'information location-details')]/h5";
	public String cancel = ".//*[@id='directory-people-edit']/menu/div[1]/a" ;
	public String profileRole = ".//*[@id='directory-people-edit']/div/div/div[4]/div[1]/div[1]/div/div[3]/table/tbody/tr/td/span[contains(text(),'";
	public String profileRoleDropDown = ".//*[text()='Assigned']/parent::select";
//	public String profileRoleSelect = ".//*[@id='directory-people-edit']/div/div/div[4]/div[1]/div[1]/div/div[3]/table/thead/tr[2]/th[5]/div/select/option[text()='Assigned']";
	public String profileRoleTable = ".//*[@id='directory-people-edit']/div/div/div[4]/div[1]/div[1]/div/div[3]/table/tbody/tr";
	public int rowcount;
	public int colcount;
	public static HashMap<String, String> temp_map;
	public static List<HashMap<String, String>> entryArray = new ArrayList<HashMap<String, String>>();
	public static List<String> headers = new ArrayList<>();
	
	public static List<String> failRemarks = new ArrayList<>();
	
	public void add(String excelValue, String value)
	{
		
		if(value.length()>0)
		{
			failRemarks.add("The expected value: "+excelValue+" doesn't match the retrieved value: "+value);
			System.out.println("The expected value: "+excelValue+" doesn't match the retrieved value: "+value);
		}
		else
		{
			failRemarks.add("The expected value: "+excelValue+" wasn't retrieved.");
			System.out.println("The expected value: "+excelValue+" doesn't match the retrieved value: "+value);
		}
		
	}
}
