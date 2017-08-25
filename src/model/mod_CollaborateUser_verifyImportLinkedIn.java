
package model;

public class mod_CollaborateUser_verifyImportLinkedIn 
{
	public String sideProfile = "mini-card-component-wrapper";
	public String selectProfile = "user-info";
	
	public String LnkImpButton = "//*[@class='main-actions']/descendant::button[contains(@class,'main-action-button')]/descendant::i[contains(@class,'pc pc-linkedin')]";
	public String LinkedIn = "LinkedIn";
	
	public String logEmail = ".//*[@class='wrapper']/descendant::input[contains(@id,'login-email')]";
	public String logPass = ".//*[@class='wrapper']/descendant::input[contains(@id,'login-password')]";
	public String signIn = ".//*[@class='wrapper']/descendant::input[contains(@id,'login-submit')]";
	
	public String Email = "TestUserForTestingPurposes@gmail.com";
	public String Pass = "test.user";
	public String close = "close";
	
	public String signLinkedIn = ".//*[@class='IN-widget']/descendant::a";
	
	public String emailField = ".//*[@id='session_key-oauthAuthorizeForm']";
	public String passField = ".//*[@id='session_password-oauthAuthorizeForm']";
	
	public String allow = ".//*[@class='actions']/descendant::input[contains(@name,'authorize')]";
	public String proceed = ".//*[@class='button-bar']/descendant::button[contains(@class,'btn btn-info')]";
	
	public String messageBox = "html/body/ul/li[1]/div/div/span/p";
}

