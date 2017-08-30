package model;

public class mod_UniversalURL 
{
	public String pureProject_loginURL = "http://apps.mypurecloud.com/";
	public String pureProject_passwordBox = ".//*[@id='password']";
	public String pureProject_usernameBox = ".//*[@id='email']";
	public String pureProject_organizationBox = ".//*[@id='org']";
	public String pureProject_organizationButton = "html/body/descendant::a[contains(@href,'#/authenticate-adv/org')]";
	public String pureProject_loginButton = ".//*[text()='Log In']";
	public String pureProject_previousNext = ".//*[text()='Next']";
	
	/*Developer test attributes START
	 * 
	 * Author: John Tongson 08/14/2017
	 * 
	 * Please ensure that these attributes aren't used on UAT/LIVE 
	 * and should be replaced on the OOP attributes.
	 * 
	 * Make sure that this attributes are categorized which class will be using it.
	 * Sample Format: Class Name - Purpose
	 * 
	 * */
	
	//proc_CollaborateUser_verifySearchField - Collaborate User Credentials
//	public String collab_Username = "testuser@domain.com";
//	public String collab_password = "St4rw4rs!";
	
	public String collab_Username = "nhel.grandia@genesys.com";
	public String collab_password = "AMayasuki.3";
	
	public String orgText = "genesysmanilaenablementteam";
	
//	public String admin_Username = "nhel.grandia@genesys.com";
//	public String admin_password = "AMayasuki.3";
//	
	
	
	public String collab_TestUserName = "Test";
	public String collab_TestUserLastName = "User";
	public String collab_TestOrg = "Test Org";
	
	public String collab_TestRandomUser = "Antonio";
	public String collab_TestLinkedInAcc = "Test User";
	
	
	
	/*Developer test attributes END*/
}
