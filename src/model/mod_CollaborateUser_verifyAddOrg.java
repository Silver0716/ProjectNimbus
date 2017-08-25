package model;

public class mod_CollaborateUser_verifyAddOrg 
{
	public String DirectoryText = "html/body/descendant::li[contains(@class,'menu-top-level-item menu-search')]/descendant::a[contains(@title,'Directory')]";
	public String ExContacts = ".//*[@title='External Contacts']";
	
	public String orgTab = "OrganizationsTab";
	public String iframe1 = "relateFrame";
	
	public String AddButton = ".//*[@id='addBtnWrapper']/div/a/span[2]";
	public String addOrg = "addOrgLink";
	public String orgName = "name";
	public String proceed = "saveOrgLink";
	public String save = ".//*[@id='saveOrgLink']";
	
	public String searchField = ".//*[@id='searchOrganizations']";
	public String orgResultCount = ".//*[@id='OrganizationsTab']/span[contains(@aria-hidden,'false')]";
	public String searchResult = "orgCard";

}
