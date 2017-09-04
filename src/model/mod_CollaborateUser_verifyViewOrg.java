package model;

public class mod_CollaborateUser_verifyViewOrg 
{
	public String DirectoryText = "html/body/descendant::li[contains(@class,'menu-top-level-item menu-search')]/descendant::a[contains(@title,'Directory')]";
	public String ExContacts = ".//*[@title='External Contacts']";
	
	public String orgTab = "OrganizationsTab";
	public String iframe1 = "relateFrame";
	
	public String searchField = ".//*[@id='searchOrganizations']";
	public String orgResultCountFalse = ".//*[@id='OrganizationsTab']/span[contains(@aria-hidden,'false')]";
	
	public String searchResult = "orgCard";
	public String confirmInfo = ".//*[@id='contactsPanelHeader']";
}
