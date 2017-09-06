package model;

public class mod_CollaborateUser_verifyAddPersonalInfo 
{
	public String sideProfile = "mini-card-component-wrapper";
	public String selectProfile = "user-info";
	public String addSection = ".btn.btn-flat.add-new-section.main-action-button";
	public String sectionOptions = "content";
	
	public String sectionSkill = "//div[@class='section-icon clickable skills ember-view']";
	public String editSection = ".//*[@class='masonry-container']/descendant::div[contains(@class,'field-section skills-section')]/descendant::a[contains(@title,'Edit')]";
	
	public String skillField = ".//*[@class='field-group entries field-entries field-group-skills num-entries-1']/descendant::div[contains(@class,'add-tag')]/descendant::input[contains(@class,'form-control ember-text-field ember-view tt-input')]";
	public String suggested = ".//*[@class='add-tag']/descendant::span[contains(@class,'twitter-typeahead')]/descendant::div[contains(@class,'tt-suggestion tt-selectable')]";
	
	public String save = ".//*[@class='field-section-footer']/descendant::button[contains(@class,'btn btn-flat save-section')]";
	public String sectionCreated = ".//*[@class='masonry-container']/descendant::div[contains(@class,'field-section skills-section')]";
}
