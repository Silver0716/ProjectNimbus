package model;

public class mod_CollaborateUser_verifyDelExternalContacts {

	public String DirectoryText = "html/body/descendant::li[contains(@class,'menu-top-level-item menu-search')]/descendant::a[contains(@title,'Directory')]";
	public String ExContacts = ".//*[@title='External Contacts']";
	
	public String iframe1 = "relateFrame";
	
	public String searchField = ".//*[@id='searchContacts']";
	public String results = ".//*[@id='contentContainer']/descendant::td[contains(@class,'contactNameCell')]/parent::tr";
	public String searchResult = ".//*[@class='fixtable-inner']/descendant::td[contains(@class,'contactNameCell')]/descendant::span[contains(text(),'Test')]";
	public String editContact = ".//*[@id='editContactLink']";
	public String delContact = ".//*[@id='delContactLink']";
	public String contactResultCountTrue = ".//*[@id='ContactsTab']/span[contains(text(),'')]";
	public String contactResultCount = ".//*[@id='ContactsTab']/span[contains(@aria-hidden,'false')]";

}
