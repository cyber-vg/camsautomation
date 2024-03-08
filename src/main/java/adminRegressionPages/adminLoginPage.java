package adminRegressionPages;
import com.microsoft.playwright.Page;
 
public class adminLoginPage
{
private String acceptcookie = "//a[contains(text(),'Accept')]";
private String emailaddress = "//input[@id='emailAddress']";
private String password = "//input[@id='password']";
private String loginbtn = "button[type='submit']";
private String errormsg = "//div[@class='toast-message']";
private String forgetpsw = "//a[normalize-space()='Forgot password']";
private String cuIcon = ".icon-box-big.mx-auto.mb-3";
private String byphonenumlink = "";
private String phonenum = "";
 
private Page page;
public adminLoginPage(Page page)
{
	this.page=page;
}
public String getadminPageTitle()
{
page.click(acceptcookie);	
String title=page.title();
return title;
}
public Boolean doadminLoginWithEmail(String adminEmail, String adminPassword)
{
	System.out.println("Admin Credential: " + adminEmail + ": " + adminPassword);
	page.fill(emailaddress, adminEmail);
	page.fill(password, adminPassword);
	page.click(loginbtn);
	// Check if Internal error message is visible after clicking login button
	/*if (page.isVisible(errormsg))
	{
	    System.out.println("Internal Error message displayed after clicking login button");
	    return false;
	}*/
	page.waitForSelector(cuIcon);
	if (page.isVisible(cuIcon)) {
	    if (!page.isVisible(errormsg)) {
	        System.out.println("Admin is Successfully LoggedIn With Email");
	        return true;
	    } else {
	        System.out.println("Getting Internal Server Error");
	    }
	} else {
	    System.out.println("cuIcon not visible");
	}
	System.out.println("Something Problem in Login");
	return false;
}
//public Boolean doadminLoginWithPhone(String adminPhone, String adminPassword)
//{
//	System.out.println("Admin Credential: " +adminPhone + ": " +adminPassword);
//	page.click(byphonenumlink);
//	page.fill(phonenum, adminPhone);
//	page.fill(password, adminPassword);
//	page.click(loginbtn);
//	// Check if Internal error message is visible after clicking login button
//		/*if (page.isVisible(errormsg))
//		{
//		    System.out.println("Internal Error message displayed after clicking login button");
//		    return false;
//		}*/
//	page.waitForSelector(cuIcon);
//	if (page.isVisible(cuIcon)) {
//	    if (!page.isVisible(errormsg)) {
//	        System.out.println("Admin is Successfully LoggedIn With PhoneNumber");
//	        return true;
//	    } else {
//	        System.out.println("Getting Internal Server Error");
//	    }
//	} else {
//	    System.out.println("cuIcon not visible");
//	}
//	System.out.println("Something Problem in Login");
//	return false;
//}
// 
}