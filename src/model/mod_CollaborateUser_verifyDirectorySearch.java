package model;

public class mod_CollaborateUser_verifyDirectorySearch 
{
	public String DirectoryText = "html/body/descendant::li[contains(@class,'menu-top-level-item menu-search')]/descendant::a[contains(@title,'Directory')]";
	public String MyOrganizationText = "html/body/descendant::li[contains(@class,'menu-top-level-item menu-search')]/descendant::ul/li[contains(@title,'Organization')]";
	
	//Tab Button
	public String Tab_AllButton = ".//*[@class='filter all active']";
	public String Tab_PeopleButton = ".//*[@class='filter people']";
	public String Tab_GroupButton = ".//*[@class='filter groups']";
	public String Tab_LocationsButton = ".//*[@class='filter locations']";
	public String Tab_ContactsButton = ".//*[@class='filter externalContacts']";
	
	//Tab Result
	public String Tab_AllResult = ".//*[@class,'search-results all']/div";
	public String Tab_AllResultContent = ".//*[@class='search-results all']/descendant::div";
	public String Tab_PeopleResult = ".//*[@class='search-results people']/div";
	public String Tab_PeopleResultContent = ".//*[@class='search-results people']/descendant::div";
	public String Tab_GroupResult = ".//*[@class='search-results groups']/div";
	public String Tab_GroupResultContent = ".//*[@class='search-results groups']/descendant::div";
	public String Tab_LocationResult = ".//*[@class='search-results locations']/div";
	public String Tab_LocationResultContent = ".//*[@class='search-results locations']/descendant::div";
	public String Tab_ContactResult = ".//*[@class='search-results externalContacts']/div";
	public String Tab_ContactResultContent = ".//*[@class='search-results externalContacts']/descendant::div";
	
	//Filter Verification
	public String FilterLocation = ".//*[@id='facets']/div/p";
	public String text_FilterAll = "No filters for all";
	public String text_FilterPeople = "No filters for people";
	public String text_FilterGroups = "No filters for groups";
	public String text_FilterLocation = "No filters for locations";
	public String text_FilterContact = "No filters for external contacts";
	
	//Filter Checking
	public String FilterAll = ".//*[@id='facets']/div[1]/div/div/div[1]";
	public String FilterAll_entitytype = ".//*[@class='facet']";
	public String FilterAll_entityclose = ".//*[@class='remove clickable']";
	
	
}
