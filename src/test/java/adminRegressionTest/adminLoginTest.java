package adminRegressionTest;
 
import org.testng.Assert;
import org.testng.annotations.Test;
 
import AppConstant.AppConstants;
import baseTestMethod.BaseTest;
import io.qameta.allure.Allure;
 
public class adminLoginTest  extends BaseTest
{
@Test(priority = 1)
public void getadminPageTitleTest()
{
	String actualtitle=adminLogin.getadminPageTitle();
	System.out.println(actualtitle);
	Allure.step(actualtitle);
	Assert.assertEquals(actualtitle, AppConstants.Admin_Page_Title);
}
@Test(priority = 2)
public void adminLoginEmailTest()
{
Assert.assertTrue(adminLogin.doadminLoginWithEmail(prop.getProperty("adminEmail"), prop.getProperty("adminPassword")));
Allure.step("Admin Login With Email Successfully Done");
}
//@Test(priority = 3)
//public void adminLoginPhoneTest()
//{
//Assert.assertTrue(adminLogin.doadminLoginWithPhone(prop.getProperty("adminPhone"), prop.getProperty("adminPassword")));
//Allure.step("Admin Login With PhoneNumber Successfully Done");
//}
 
}