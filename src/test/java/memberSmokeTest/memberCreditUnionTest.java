package memberSmokeTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppConstant.AppConstants;
import baseTestMethod.BaseTest;

public class memberCreditUnionTest extends BaseTest {
	private String unionUrl="https://cams-dev5.wakandi.com/#/credit-union";
	private String LoanPageUrl="https://cams-dev5.wakandi.com/#/credit-union/yourloans";
	private String contributionPageUrl="https://cams-dev5.wakandi.com/#/credit-union/mycontributions";
private String ShareUrl="https://cams-dev5.wakandi.com/#/credit-union/yourshares";
private String addContributionUrl="https://cams-dev5.wakandi.com/#/credit-union/addContribution";
private String applyLoanUrl="https://cams-dev5.wakandi.com/#/credit-union/apply-loan";

	@Test (priority = 1 )
	public void creditunionPageTest() {
		String actualTitle= unionList.getTHomePageTitle();
		Assert.assertEquals(actualTitle, AppConstants.Login_Page_Title);
		String actualURL= unionList.getTHomePageURL();
		Assert.assertEquals(actualURL, prop.getProperty("memberurl"));
		Boolean cardVisible= unionList.validDLoginusingEmail(prop.getProperty("memberEmail"),prop.getProperty("memberPassword"));
		Assert.assertEquals(cardVisible,true);
		String unionPage_Url=unionList.IsUnionPageVsible();
		page.waitForTimeout(2000);
		Assert.assertEquals(unionPage_Url,unionUrl);
	
	}
//	, retryAnalyzer = commonFiles.PlaywrightRetryAnalyzer.class 
	@Test (priority = 2 )
	public void LoanPageTest() {
		String loanurl=unionList.IsLoanPageVisible();
		Assert.assertEquals(loanurl,LoanPageUrl);
	
	}
	@Test (priority = 3 )
	public void MyContributionTest() {
		String contributionUrl=unionList.isMyContributtonVisible();
		Assert.assertEquals(contributionUrl,contributionPageUrl);

	}
	@Test (priority = 4  )
	public void SharePageTest() {
		String SharePageUrl=unionList.IsSharePageVisble();
		Assert.assertEquals(SharePageUrl,ShareUrl);
	
	}
	
	@Test (priority = 5)
	public void LoanCardPageTest(){
		String ShareUrl=unionList.isLoanCardPageVisible();
		Assert.assertEquals(ShareUrl,LoanPageUrl);
		
	}
	@Test (priority = 6)
	public void addContributionPageTest() {
		String ShareUrl=unionList.isaddContributtonVisible();
		Assert.assertEquals(ShareUrl,addContributionUrl);
		
	}
	@Test(priority = 7)
	public void ApplyLoanPageTest() {
		String ShareUrl=unionList.isApplyLoanVisible();
		Assert.assertEquals(ShareUrl,applyLoanUrl);
	}
}
