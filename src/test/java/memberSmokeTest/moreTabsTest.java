package memberSmokeTest;



import org.testng.Assert;
import org.testng.annotations.Test;

import AppConstant.AppConstants;
import baseTestMethod.BaseTest;

public class moreTabsTest extends BaseTest {
	private String morePageUrl="https://cams-dev5.wakandi.com/#/more";
	private String unionListUrl="https://cams-dev5.wakandi.com/#/credit-union-list";
	private String languagePageUrl="https://cams-dev5.wakandi.com/#/more/language";
	private String documentPageUrl="https://cams-dev5.wakandi.com/#/more/documents";
	private String bankPageUrl="https://cams-dev5.wakandi.com/#/more/bank-details/list";
	private String mnoDetailPageUrl="https://cams-dev5.wakandi.com/#/more/mno-details/list-mno";

	@Test (priority=1  )
	public void moreTabPageTitleTest()
	{
		String actualTitle= signin.getHomePageTitle();
		Assert.assertEquals(actualTitle, AppConstants.Login_Page_Title);	
	}
	@Test(priority=2  )
	public void moreTabPageSignInURLTest()
	{
		String actualURL= signin.getHomePageURL();
		Assert.assertEquals(actualURL, prop.getProperty("memberurl"));
	}
	
	@Test(priority = 3 )
	public void MoreTabTest() {
		Boolean cardVisible= unionList.validDLoginusingEmail(prop.getProperty("memberEmail"),prop.getProperty("memberPassword"));
		Assert.assertEquals(cardVisible,true);
		Boolean MoreTabVisible=moreTabs.isMoreTabVisible();
		Assert.assertEquals(MoreTabVisible, true);
		String  MoreTabPageVisible=moreTabs.isMoreTabPageVisible();
		Assert.assertEquals(MoreTabPageVisible, morePageUrl);
	}
	@Test(priority = 4 )
	public void  MorePageContentTest() {
		Boolean Content= moreTabs.isMorePageContentVisible();
		Assert.assertEquals(Content, true);
				}
	
	@Test(priority = 5 )
	public void YourGroupPageTest() {
		String urlString=moreTabs.isYourGroupPageVisible();
		Assert.assertEquals(urlString, unionListUrl);
	}
	
	@Test(priority = 6  )
	public void LanguagePageTest() {
		String urlString=moreTabs.isLanguagePageVisible();
		Assert.assertEquals(urlString,languagePageUrl);
}

	@Test(priority = 7 )
	public void DocumentPageTest() {
	String urlString=moreTabs.isDocumentPageVisible();
	Assert.assertEquals(urlString,documentPageUrl);
}
	@Test(priority = 8  )
	public void BankDetailPageTest() {
	String urlString=moreTabs.isBankDetailPageVisible();
	Assert.assertEquals(urlString,bankPageUrl);
}

	@Test(priority =9 	 )
	public void MNOPageTest() {
	String urlString=moreTabs.isMNOString();
	Assert.assertEquals(urlString, mnoDetailPageUrl);
}
	
}
