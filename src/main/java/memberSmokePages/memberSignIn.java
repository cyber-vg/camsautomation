package memberSmokePages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import io.qameta.allure.Allure;

public class memberSignIn {
private	Page page;

private String emailtag ="//input[@type='email'  and @formcontrolname='emailAddress']";
private String passwordtag = "//input[@type='password'  or @formcontrolname='password']";
private String rememberMe="//label[@for='rememberMe-input']";
private String loginbtn = "//button[normalize-space()='Log in']";
private String card_sacco_tag = "//div[@class='row']//div//a";
private String  useEmailText=  ".text-green.d-block.text-right.mat-15";
private String  usePhoneText ="(//a[normalize-space()='Login using phone number'])[1]";
private String  frogotPass="//a[normalize-space()='Forgot password']";
private String counturyCode="//input[@formcontrolname='countryCode']";
private String phoneNoInput="//input[@formcontrolname='phoneNumber']";
private String errortag="//div[@class='toast-message']";
private String logoutBtn="//div[@class='navbar-nav']//a//img";
private String invalidEmailFormat="//span[normalize-space()='Email address is not valid']";
private String invalidPhoneFormat="//span[contains(text(),'Must have a minimum of 9 and a maximum of 10 digit')]";
private String sendBtnString="//button[normalize-space()='Send' and @class='btn btn-primary d-block w-100']";
private String backBtnString="//button[normalize-space()='Back' and @type='button']";
private String ForgotPassUseEmailText="//a[normalize-space()='Using email']";
private String otpfield="//input[@id='otp' or @type='tel']";
private String SendBtnOtp="//button[normalize-space()='Send']";
	//Page Constructor
	public memberSignIn(Page page) {
		this.page=page;
	}
	
	//page action/method
	public String getHomePageTitle() {
		String title = page.title();
		System.out.println(title);
		return title;
		}
	public String getHomePageURL() {
		Allure.step("Navigate to URL");
		String url = page.url();
		System.out.println(url);
		Allure.step(url);
		return url;
		}	
	
public Boolean validLoginusingEmail( String validemail,String validPassword) {
	Allure.step("Test case valid login use email start");
		page.locator(useEmailText).click( new Locator.ClickOptions().setPosition(275,15)  );
		Allure.step(" Email value  : "+validemail+" Password value : "+validPassword);
		page.fill(emailtag,validemail);
		page.locator(validPassword);
		page.isVisible(usePhoneText);
		page.fill(passwordtag, validPassword);
	    page.getByRole(AriaRole.GROUP, new Page.GetByRoleOptions().setName("Your Password")).locator("a").click();
	    page.click(rememberMe);
		page.isVisible(frogotPass);
		page.click(loginbtn);
		page.waitForSelector(card_sacco_tag);
		Boolean card_sacco= page.isVisible(card_sacco_tag);
		if(card_sacco)
			Allure.step("Succesfull  Login by Email");
		page.click(logoutBtn);
		return card_sacco;
		}

public Boolean validLoginUsingPhone (String validPhone,String validPassword) {

page.waitForSelector(passwordtag);
if(page.isVisible(usePhoneText)) {
page.locator(usePhoneText).click(  );
}
	String codeString=page.locator(counturyCode).textContent();
if(page.locator(counturyCode).first().textContent().contentEquals("+255")) {
	Allure.step("Correct country code"+codeString);
}else {
	Allure.step("wrong country code" + page.locator(counturyCode).textContent( ));}
page.fill(phoneNoInput, validPhone);
page.isVisible(useEmailText);
page.fill(passwordtag, validPassword);
page.getByRole(AriaRole.GROUP, new Page.GetByRoleOptions().setName("Your Password")).locator("a").click();
page.click(rememberMe);
page.isVisible(frogotPass);
Allure.step("Valid Phone No value:"+validPhone+" Valid Password"+validPassword);
page.click(loginbtn);

page.waitForSelector(card_sacco_tag);
Boolean card_sacco= page.isVisible(card_sacco_tag);
if(card_sacco)
	Allure.step("Succesfull  Login by Phone Number");
page.click(logoutBtn);
return card_sacco;
}

public Boolean invalidLoginUsingEmail(String invalidemail,String invalidpassword) {
	page.waitForSelector(passwordtag);

	if(page.isVisible(useEmailText)) {
		page.locator(useEmailText).click( new Locator.ClickOptions().setPosition(275,15)  );
		}
	Allure.step("invalidEmail value:"+invalidemail+"  InvalidPassword: "+invalidpassword);
	page.fill(emailtag,invalidemail);
	page.isVisible(usePhoneText);
	page.fill(passwordtag, invalidpassword);
	page.getByRole(AriaRole.GROUP, new Page.GetByRoleOptions().setName("Your Password")).locator("a").click();
	page.click(rememberMe);
	page.isVisible(frogotPass);
	
	page.click(loginbtn);
	page.waitForTimeout(500);
	String showErrortag=page.locator(errortag).first().textContent();
Allure.step(showErrortag);
	Boolean logerror=page.locator(errortag).first().isVisible();
	if(logerror) {
		System.out.println("login test using invalid email pass");
Allure.step("login test using invalid email pass");

	}
	else {
		Allure.step("login test using invalid email fail ");
	}
	return logerror;
}
public Boolean invalidLoginUsingPhoneNo(String invalidPhone,String invalidpassword) {
	page.waitForSelector(passwordtag);
	if(page.isVisible(usePhoneText)) {
		page.locator(usePhoneText).click();
		}
	if(page.locator(counturyCode).first().textContent().contentEquals("+255")) {
		Allure.step("Correct country code"+ page.locator(counturyCode).textContent());
	}else {Allure.step("wrong country code" + page.locator(counturyCode).textContent( ));}
	Allure.step("InvalidPhone value:"+invalidPhone+" InvalidPassword :"+invalidpassword);
	page.fill(phoneNoInput, invalidPhone);
	page.isVisible(useEmailText);
	page.fill(passwordtag, invalidpassword);
	page.getByRole(AriaRole.GROUP, new Page.GetByRoleOptions().setName("Your Password")).locator("a").click();
	page.click(rememberMe);
	page.isVisible(frogotPass);
	page.click(loginbtn);
	page.waitForTimeout(500);
	Boolean logerror=page.locator(errortag).first().isVisible();
	if(logerror) {
		Allure.step("login test using invalid email pass");
	}
	else {
		Allure.step("Login test using invalid email fail ");};
		return logerror;

}

public Boolean wrongemailformat(String emailwrongFromat,String validPassword) {
	page.waitForSelector(passwordtag);

	if(page.isVisible(useEmailText)) {
		page.locator(useEmailText).click( new Locator.ClickOptions().setPosition(275,15)  );
		}
	page.fill(emailtag,emailwrongFromat);
	Boolean invalidFormat= page.locator(invalidEmailFormat).isVisible();
	page.fill(passwordtag,validPassword);
	Allure.step("Wrong Email Fromat value:"+emailwrongFromat+"Password value: "+validPassword);
	return invalidFormat;
}
public Boolean wrongPhoneformat(String phonewrongFromat,String validPassword) {
	page.waitForTimeout(1000);
	if(page.isVisible(usePhoneText)) {
		page.locator(usePhoneText).click();
		}
	page.fill(phoneNoInput,phonewrongFromat);
	Boolean invalidFormat= page.locator(invalidPhoneFormat).isVisible();
	page.fill(passwordtag,validPassword);
	Allure.step("Phone No wrong Fromat value"+phonewrongFromat+" Password value: "+validPassword);
	return invalidFormat;
}
public  Boolean forgotpasswordUsindInvalidPhone(String invalidPhone ) {
	page.waitForSelector(passwordtag);
	page.click(frogotPass);
	page.fill(phoneNoInput, invalidPhone);
	page.locator(backBtnString).isVisible();
	page.click(sendBtnString);
	page.waitForTimeout(500);
	Boolean error = page.locator(errortag).first().isVisible(); 
	if(error){
	Allure.step("Forgot password link test pass using Invalid phone No Passed");
	}
	else {
		error =false;
		Allure.step("Forgot password link test failed using Invalid phone No Passed");
	}
	
	Allure.step("InvalidPhone value:" +invalidPhone);
	page.click(backBtnString);
	return error;
	
}
public  Boolean forgotpasswordUsindInvalidEmail(String invalidEmail ) {
	page.waitForSelector(passwordtag);
	
	page.click(frogotPass);
	page.locator(ForgotPassUseEmailText).click( new Locator.ClickOptions().setPosition(275,20)  );
	page.fill(emailtag, invalidEmail);
	page.locator(backBtnString).isVisible();
	page.click(sendBtnString);
	page.waitForTimeout(500);
	Boolean error = page.locator(errortag).first().isVisible();
	if(error){
		Allure.step("Forgot password link test pass using Invalid phone No Passed");
		System.out.println("Forgot password link test pass using Invalid phone No Passed");
	}
	else {
		error =false;
		Allure.step("Forgot password link test failed using Invalid phone No Passed");
	}
	Allure.step("InvalidEmail value for forgot password :"+invalidEmail);
	page.click(backBtnString);
	return error;
}
public  Boolean forgotPasswordUsingValidEmailInvalidOTP(String validEmail,String InvalidOPT ) {
	page.waitForTimeout(3000);
	page.waitForSelector(passwordtag);

	page.click(frogotPass);
	page.locator(ForgotPassUseEmailText).click( new Locator.ClickOptions().setPosition(275,20)  );
	page.fill(emailtag, validEmail);
	page.click(SendBtnOtp);
	if(page.locator(errortag).isVisible()) {
		System.out.println("Not able to get Opt Field ");
		return false;
	}
page.waitForTimeout(2000);
page.fill(otpfield, InvalidOPT);
Boolean errorTag=page.locator(errortag).isVisible();
String errormsgString=page.locator(errortag).textContent();
if(errorTag) {
	Allure.step("Login Failed Using Invalid Otp"+errormsgString);
	System.out.println("Login Failed Using Invalid Otp"+errormsgString);
}else {
	Allure.step("Login Pass Using Invalid Otp and Test Failed"+errormsgString);
	System.out.println("Login Succesfull Using Invalid Otp and Test Failed"+errormsgString);
	
return false;
}
return errorTag;
}
}

