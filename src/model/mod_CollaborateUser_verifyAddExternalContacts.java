package model;

public class mod_CollaborateUser_verifyAddExternalContacts {

	public String DirectoryText = "html/body/descendant::li[contains(@class,'menu-top-level-item menu-search')]/descendant::a[contains(@title,'Directory')]";
	public String ExContacts = ".//*[@title='External Contacts']";
	
	public String AddButton = ".//*[@id='addBtnWrapper']/div/a/span[2]";
	public String Contacts = "addContactLink";
	
	public String iframe1 = "relateFrame";
	public String firstName = "firstName";
	public String lastName = "lastName";
	public String proceed = "saveContactLink";
	public String save = ".//*[@id='saveContactLink']";
	public String MessageBox = "html/body/ul/li/div/div/h6";
	public String MessageBox_AddContact = "New Contact Created";
	
	public String searchField = ".//*[@id='searchContacts']";
	public String searchResult = ".//*[@id='contentContainer']/descendant::td[contains(@class,'contactNameCell')]/parent::tr";
	public String contactResultCount = ".//*[@id='ContactsTab']/span[contains(@aria-hidden,'false')]";
	
	
}
