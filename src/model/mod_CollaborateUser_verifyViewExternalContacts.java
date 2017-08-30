package model;

public class mod_CollaborateUser_verifyViewExternalContacts 
{
	
	public String DirectoryText = "html/body/descendant::li[contains(@class,'menu-top-level-item menu-search')]/descendant::a[contains(@title,'Directory')]";
	public String ExContacts = ".//*[@title='External Contacts']";

	public String iframe1 = "relateFrame";
	public String searchField = ".//*[@id='searchContacts']";
	public String searchResult = ".//*[@id='contentContainer']/descendant::td[contains(@class,'contactNameCell')]/parent::tr";
	public String editContact = ".//*[@id='editContactLink']";
	public String contactResultCountFalse = ".//*[@id='ContactsTab']/span[contains(@aria-hidden,'false')]";
	
	public String confirmInfo = ".//*[@id='contactInfo']/descendant::div[contains(text(),'Contact Information')]";
}
