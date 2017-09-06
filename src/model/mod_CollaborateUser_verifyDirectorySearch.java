package model;

import java.util.HashMap;

public class mod_CollaborateUser_verifyDirectorySearch 
{
	public String DirectoryText = "html/body/descendant::li[contains(@class,'menu-top-level-item menu-search')]/descendant::a[contains(@title,'Directory')]/span";
	public String MyOrganizationText = "html/body/descendant::li[contains(@class,'menu-top-level-item menu-search')]/descendant::ul/li[contains(@title,'Organization')]";
	
	//Tab Button
	public String Tab_AllButton = ".//*[contains(@class,'filter all active')]";
	public String Tab_PeopleButton = ".//*[contains(@class,'filter people')]";
	public String Tab_GroupButton = ".//*[contains(@class,'filter groups')]";
	public String Tab_LocationsButton = ".//*[contains(@class,'filter locations')]";
	public String Tab_ContactsButton = ".//*[contains(@class,'filter externalContacts')]";
	
	public String allBtnValue = "Tab_AllButton";
	public String pplBtnValue = "Tab_PeopleButton";
	public String grpBtnValue = "Tab_GroupButton";
	public String locBtnValue = "Tab_LocationsButton";
	public String ctcBtnValue = "Tab_ContactsButton";
	public String filterBtnValue = "FilterAll_entityclose";
	public String filterallBtnValue = "Filter_AllButton";
	public String filterpplBtnValue = "Filter_PeopleButton";
	public String filtergrpBtnValue = "Filter_GroupButton";
	public String filterlocBtnValue = "Filter_LocationsButton";
	public String filterctcBtnValue = "Filter_ContactsButton";
	
	//Tab Result
	public String Tab_AllResult = ".//*[@class,'search-results']/div";
	public String Tab_AllResultContent = "/html/body/descendant::div[contains(@class,'search-results ')]/descendant::div";
	public String Tab_PeopleResult = ".//*[@class='search-results people']/div";
	public String Tab_PeopleResultContent = ".//*[contains(@class,'search-results people')]/descendant::div";
	public String Tab_GroupResult = ".//*[@class='search-results groups']/div";
	public String Tab_GroupResultContent = ".//*[contains(@class,'search-results groups')]/descendant::div";
	public String Tab_LocationResult = ".//*[@class='search-results locations']/div";
	public String Tab_LocationResultContent = ".//*[contains(@class,'search-results locations')]/descendant::div";
	public String Tab_ContactResult = ".//*[@class='search-results externalContacts']/div";
	public String Tab_ContactResultContent = ".//*[contains(@class,'search-results externalContacts')]/descendant::div";
	
	//Filter Verification
	public String FilterLocation = ".//*[@id='facets']/div/p";
	public String text_FilterAll = "No filters for all";
	public String text_FilterPeople = "No filters for people";
	public String text_FilterGroups = "No filters for groups";
	public String text_FilterLocation = "No filters for locations";
	public String text_FilterContact = "No filters for external contacts";
	
	//Filter Checking
	public String FilterAll = ".//*[@id='facets']/div[1]/div/div/div[1]/div/span/a";
	public String FilterAll_entitytype = ".//*[@class='facet ']";
	public String FilterAll_entityclose = ".//*[@class='remove clickable']/i";
	
	//Error Value Counter
	public int switchValue = 0;
	public int errorValue = 0;
	public int totalPassed =0;
	public String collectedError = "";
	public HashMap<String, String> errorMap = new HashMap<String, String>();

	public mod_CollaborateUser_verifyDirectorySearch()
	{
		errorMap.put("0", "Can't retrieve results from Directory > My Organization > All Tab");
		errorMap.put("1", "Can't do filter for All Tab");
		errorMap.put("2", "Can't retrieve results from Directory > My Organization > People Tab");
		errorMap.put("3", "Can't do filter for People Tab");
		errorMap.put("4", "Can't retrieve results from Directory > My Organization > Groups Tab");
		errorMap.put("5", "Can't do filter for Group Tab");
		errorMap.put("6", "Can't retrieve results from Directory > My Organization > Locations Tab");
		errorMap.put("7", "Can't do filter for Location Tab");
		errorMap.put("8", "Can't retrieve results from Directory > My Organization > Contacts Tab");
		errorMap.put("9", "Can't do filter for Contacts Tab");
		errorMap.put("Tab_AllButton", "Can't click All Button");
		errorMap.put("Tab_PeopleButton", "Can't click People Button");
		errorMap.put("Tab_GroupButton", "Can't click Group Button");
		errorMap.put("Tab_LocationsButton","Can't click Locations Button");
		errorMap.put("Tab_ContactsButton","Can't click Contacts Button");
		errorMap.put("FilterAll_entityclose","Can't click close(X) button on the selected filter or missing");
		errorMap.put("Filter_AllButton", "Can't click filter under All Tab or it is missing");
		errorMap.put("Filter_PeopleButton", "Can't click filter under People Tab or it is missing");
		errorMap.put("Filter_GroupButton", "Can't click filter under Group Tab or it is missing");
		errorMap.put("Filter_LocationsButton","Can't click filter under Locations Tab or it is missing");
		errorMap.put("Filter_ContactsButton","Can't click filter under Contacts Tab or it is missing");
		
	}
}
