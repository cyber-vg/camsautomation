package memberSmokeTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import AppConstant.AppConstants;
import baseTestMethod.BaseTest;


public class memberSignInTest extends BaseTest
{	
	@Test(priority=1 )
	public void memberHomePageTitleTest()
	{
	String actualTitle= signin.getHomePageTitle();
	Assert.assertEquals(actualTitle, AppConstants.Login_Page_Title);	
	}
	@Test(priority=2  )
	public void memberSignInURLTest()
	{
		String actualURL= signin.getHomePageURL();
		Assert.assertEquals(actualURL, prop.getProperty("memberurl"));
	}
	@Test(priority=3  )
	public void signInbyValidEmail()
	{
	Boolean cardVisible= signin.validLoginusingEmail(prop.getProperty("memberEmail"),prop.getProperty("memberPassword"));
	Assert.assertEquals(cardVisible,true);
	}
	@Test(priority=4 )
	public void signInbyvalidMobile()
	{
	Boolean cardVisiblea= signin.validLoginUsingPhone(prop.getProperty("memberMobileNumber").trim(),prop.getProperty("memberPassword").trim());
	Assert.assertTrue(cardVisiblea);
	}
	@Test  (priority=5  )
	
	public void signInByInvalidmail() {
		Boolean erroroccur = signin.invalidLoginUsingEmail(prop.getProperty("invalidemail"), prop.getProperty("invalidpassword"));
		Assert.assertTrue(erroroccur);
	}
@Test  (priority=6)
	
	public void signInByInvalidMobile() {
		Boolean erroroccur = signin.invalidLoginUsingPhoneNo(prop.getProperty("invalidmoblieNo"), prop.getProperty("invalidpassword"));
		Assert.assertTrue(erroroccur);
	}

@Test(priority=7 )

public void signInByWrongMailFormat() {
	Boolean erroroccur = signin.wrongemailformat(prop.getProperty("wrongemailFormat"), prop.getProperty("invalidpassword"));
	Assert.assertTrue(erroroccur);
}
@Test(priority=8  )

public void signInByWrongPhoneFormat() {
	Boolean erroroccur = signin.wrongPhoneformat(prop.getProperty("wrongmoblieFormat"), prop.getProperty("invalidpassword"));
	Assert.assertTrue(erroroccur);
}
@Test(priority=9  )

public void signInForgotPassByInvalidmail() {
	Boolean erroroccur = signin.forgotpasswordUsindInvalidPhone(prop.getProperty("invalidmoblieNo"));
	Assert.assertTrue(erroroccur);
}

@Test(priority=10 )

public void signInForgotPassByInvalidPhone() {

	Boolean erroroccur = signin.forgotpasswordUsindInvalidEmail(prop.getProperty("invalidemail"));
	Assert.assertTrue(!erroroccur);
}
}
