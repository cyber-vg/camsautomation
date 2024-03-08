package memberSmokePages;

import com.microsoft.playwright.Page;


import io.qameta.allure.Allure;

public class moreTabsDetails {
private	Page page;
private String moreTab ="(//a[@routerlink='/more'])[1]";
//private String moreTextString="//div//h5[text()=' More ']";
private String languageCardString="//li[@routerlink='./language']";
private String documentCardString="//li[@routerlink='./documents']";
private String bankCardString="//li[ @routerlink='./bank-details']";
private String mnoCardString="//li[@routerlink='./mno-details']";
private String yourGroupString="//div//span[text()='Your Groups']";
private String card_sacco_tag = "//div[@class='row']//a";
private String engString="//div//input[@id=0]";
private String docSearch="//div//input[@type='text']";
private String myContibutionAmtString="//div//h6[contains(text(),'CR')]";
	public moreTabsDetails(Page page) {
		this.page=page;
	}
	
	public Boolean isMoreTabVisible() {
		page.waitForSelector(card_sacco_tag);
		page.locator(card_sacco_tag).click();
		page.waitForSelector(moreTab);
		page.waitForSelector( myContibutionAmtString);
	Boolean moreTabs=page.locator(moreTab).isVisible();
return moreTabs;
	}
	public String isMoreTabPageVisible() {
	page.waitForTimeout(2000);
	page.locator(moreTab).click();
//	page.waitForSelector(moreTextString);
//	page.locator(moreTextString).isVisible();
	page.waitForTimeout(2000);
	page.waitForSelector(languageCardString);
	String pageUrlString=page.url();
	Allure.step(pageUrlString);
	return pageUrlString;
	}
	
public Boolean isMorePageContentVisible() {
	page.waitForTimeout(2000);
	if(page.locator(yourGroupString).isVisible()) {
	Allure.step("Your Group Visible");}
	else return false;
	
	if(page.locator(languageCardString).isVisible()) {
		Allure.step("Language Card Visible");}
	else return false;
	
	if(page.locator(documentCardString).isVisible()) {
		Allure.step("Document Card Visible");}
	else return false;
	
	if(page.locator(bankCardString).isVisible()) {
		Allure.step("Bank Card Visible");}
	else return false;
	
	if(page.locator(mnoCardString).isVisible()) {
		Allure.step("MNO Card Visible");}
	else return false;
	
	
return true;
}

public String isYourGroupPageVisible() {
	page.locator(yourGroupString).isVisible();
	page.waitForTimeout(2000);
//	page.waitForSelector(moreTextString);
	page.locator(yourGroupString).click();
	page.waitForSelector(card_sacco_tag);
	String urlString =page.url();
	return urlString;
}
public String isLanguagePageVisible() {
	page.goBack();
//	page.waitForSelector(moreTextString);
	page.waitForTimeout(2000);
	page.locator(languageCardString).isVisible();
	page.locator(languageCardString).click();
	page.waitForSelector(engString);
	String urlString=page.url();
return urlString	;
}
public String isDocumentPageVisible() {
	page.goBack();
//	page.waitForSelector(moreTextString);
	page.waitForTimeout(2000);
	page.locator(documentCardString).isVisible();
	page.locator(documentCardString).click();
	page.waitForSelector(docSearch);
	String urlString=page.url();
return urlString;
}
public String isBankDetailPageVisible() {
	page.goBack();
//	page.waitForSelector(moreTextString);
	page.waitForTimeout(2000);
	page.locator(bankCardString).isVisible();
	page.locator(bankCardString).click();
	page.waitForSelector(docSearch);
	String urlString=page.url();
return urlString;
}
public String isMNOString() {
	page.goBack();
//	page.waitForSelector(moreTextString);
	page.waitForTimeout(2000);
	page.locator(mnoCardString).isVisible();
	page.locator(mnoCardString).click();
	page.waitForSelector(docSearch);
	String urlString=page.url();
return urlString;

}
}
