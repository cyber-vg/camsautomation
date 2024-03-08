package memberRegressionTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppConstant.AppConstants;
import baseTestMethod.BaseTest;

public class memberContributionTest  extends BaseTest{
	private String unionUrl="https://cams-dev5.wakandi.com/#/credit-union";


	@Test (priority = 1)
	public void ContributionPageTest() {
		String actualTitle= unionList.getTHomePageTitle();
		Assert.assertEquals(actualTitle, AppConstants.Login_Page_Title);
		String actualURL= unionList.getTHomePageURL();
		Assert.assertEquals(actualURL, prop.getProperty("memberurl"));
		Boolean cardVisible= unionList.validDLoginusingEmail(prop.getProperty("memberEmail"),prop.getProperty("memberPassword"));
		Assert.assertEquals(cardVisible,true);
		String unionPage_Url=unionList.IsUnionPageVsible();
		page.waitForTimeout(2000);
		Assert.assertEquals(unionPage_Url,unionUrl);
		Boolean contribtnvisile=addcontribution.isContributionPageVisible();
		Assert.assertEquals(contribtnvisile, true);
		
	}

	
}
