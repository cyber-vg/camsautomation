package superAdminSmokeTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppConstant.AppConstants;
import baseTestMethod.BaseTest;

public class SuperAdminTest extends BaseTest{
    

	@Test(priority=1)
	public void TitleTest()
	{
	String actualTitle= superAdmin.getHomePageTitle();
	System.out.println(actualTitle);
	Assert.assertEquals(actualTitle, AppConstants.SuperAdmin_Page_Title);	
	}
	
}
