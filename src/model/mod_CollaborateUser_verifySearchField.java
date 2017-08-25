package model;

public class mod_CollaborateUser_verifySearchField 
{
	public String searchFieldTextBox = "html/body/div[3]/div/nav/div[2]/div[2]/div/div/form/input";
	public String toastProfileBox = "html/body/descendant::a[contains(@class,'name active ember-view')]/span";
	public String filter_keywordBox = "html/body/descendant::div[contains(@class,'grouped-facet-container')]/span/span";
	public String searchResult_NameCard = "html/body/descendant::div[contains(@class,'information')]/a[contains(@class,'name ember-view')]/span";
	
	
	public String getSearchFieldTextBox()
	{
		return searchFieldTextBox;
	}
	
	public String gettoastProfileBox()
	{
		return toastProfileBox;
	}
	
	public String getfilter_keywordBox()
	{
		return filter_keywordBox;
	}
	
	public String getsearchResult_NameCard()
	{
		return searchResult_NameCard;
	}
	
}
