package memberSmokePages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


import io.qameta.allure.Allure;

public class memberCreditUnion {
	private	Page page;
	private String emailtag ="//input[@type='email']";
	private String passwordtag = "//input[@type='password']";
	private String rememberMe="//label[@for='rememberMe-input']";
	private String loginbtn = "//button[normalize-space()='Log in']";
	private String card_sacco_tag = "//div[@class='row']//a";
	private String  useEmailText=  "//a[normalize-space()='Login using email']";
	private String  frogotPass="//a[normalize-space()='Forgot password']";
	private String  usePhoneText ="(//a[normalize-space()='Login using phone number'])[1]";
	private String totalLoan="//div[@class='d-flex flex-lg-row']//div//div[2]//h6[2]";
	private String LoanAmt="//div//h6[contains(text(),'DR' )]";
	private String loanBtn2="//div//button[@routerlink='./yourloans']";
	private String myContibutionAmtString="//div//h6[contains(text(),'CR')]";
	private String myContributinBtn="//div//div[@routerlink='./mycontributions']";
	private String myContrivutioncardBtn="//div//button[@routerlink='./addContribution']";
	private String seeDetailLoan="//div//div[@routerlink='./yourloans' and normalize-space()='See Details']";
	private String shareAmtString="//div[contains(@class, 'd-flex mt-2')]//h6[contains(text(),'INR')]";
	private String shareBtn="//div//div[@routerlink='./yourshares']";
	private String sharenum ="//div[contains(@class,'d-flex mt-2')]//div[@class='text-center'][2]//h6[2]";
	private String addContibutionCard="//div[@class='card']";
	private String visbleCard="//div[@id='tabsCustom']";
//	private String noItemShow="//div[normalize-space()='There are no items to show.']//div";
	private String isLoanBtnVisible="//button[@routerlink='./apply-loan']";
	private String errormsg = "//div[@class='toast-message']";
	public memberCreditUnion(Page page) {
		this.page=page;
	}
	
	public String getTHomePageTitle() {
	String title = page.title();
	System.out.println(title);
	Allure.step("Navigated Page Title "+title);
	return title;
		}
	public String getTHomePageURL() {
		Allure.step("Navigate to URL");
		String url = page.url();
		System.out.println(url);
		Allure.step(url);
		return url;
		}
	public Boolean validDLoginusingEmail( String validemail,String validPassword) {
		Allure.step("Test case valid login use email");
		
			page.locator(useEmailText).click( new Locator.ClickOptions().setPosition(275,15)  );
			Allure.step(" Email value  : "+validemail+"	Password value : "+validPassword);
			page.fill(emailtag,validemail);
			page.waitForTimeout(500);

			page.locator(validPassword);
			page.isVisible(usePhoneText);
			page.fill(passwordtag, validPassword);
			page.waitForTimeout(500);			
//		    page.getByRole(AriaRole.GROUP, new Page.GetByRoleOptions().setName("Your Password")).locator("a").click();
		    page.click(rememberMe);
			page.isVisible(frogotPass);
			page.waitForTimeout(500);
			page.click(loginbtn);
			if (page.isVisible(errormsg))
			{
			    System.out.println("Internal Error message displayed after clicking login button");
			    return false;
			}
			page.waitForSelector(card_sacco_tag);
			page.waitForTimeout(3000);
			Boolean card_sacco= page.isVisible(card_sacco_tag);
			if(card_sacco)
			Allure.step("Succesfull  Login by Email");
			System.out.println("Succesfull  Login by Email");
			return card_sacco;
			}

	public String IsUnionPageVsible() {
	page.waitForSelector(card_sacco_tag);
	page.waitForTimeout(2000);
	page.click(card_sacco_tag);
	
	page.waitForTimeout(2000);
	page.waitForSelector(myContibutionAmtString);
	String unionPageUrl= page.url();
	page.waitForTimeout(1000);
	page.locator(LoanAmt).isVisible();
	String LoanamtString = page.locator(LoanAmt).textContent();
	Allure.step("Loan Amount is"+LoanamtString);
	page.locator(totalLoan).isVisible();
	String NumberLoan=  page.locator(totalLoan).textContent();
	Allure.step("Total Loan "+NumberLoan);
	page.locator(myContibutionAmtString).isVisible();
	String contributionAmtString= page.locator(myContibutionAmtString).textContent();
	Allure.step("Contribution Amount"+contributionAmtString);
	page.locator(shareAmtString).isVisible();
	String shareAmt=page.locator(shareAmtString).textContent();
	Allure.step(shareAmt);
	page.locator(sharenum).isVisible();
	String Totalshare=page.locator(sharenum).textContent();
	Allure.step(Totalshare);
	System.out.println(Totalshare);
	return unionPageUrl;
	}
	
	public String IsLoanPageVisible() {
		page.waitForTimeout(1000);
		page.waitForSelector(LoanAmt);
		page.locator(LoanAmt).isVisible();
		String loanAmString=page.locator(LoanAmt).textContent();
		Allure.step("Loan amount is :" +loanAmString);
		page.locator(totalLoan).isVisible();
		String totalString=page.locator(totalLoan).textContent();
		Allure.step("Total number of loan is:"+totalString);
		page.click(seeDetailLoan);
		page.waitForSelector(visbleCard);
		page.waitForTimeout(3000);
		 String loanUrl=page.url();
		 Allure.step("Page Url is : "+loanUrl);
		 return loanUrl;
		 


	}
	public String isMyContributtonVisible() {
		page.waitForTimeout(1000);
		page.goBack();
		page.waitForSelector(myContibutionAmtString);
		page.locator(myContibutionAmtString).isVisible();
		String contriamtString= page.locator(myContibutionAmtString).textContent();
		Allure.step("Contribution Amount"+ contriamtString);
		page.locator(myContributinBtn).click();
		page.waitForSelector(visbleCard);
		page.waitForTimeout(2000);
		String contributionURlString=page.url();
		Allure.step(contributionURlString);
		return contributionURlString;
		
	}
	public String IsSharePageVisble() {
		page.goBack();
		page.waitForSelector(shareAmtString);
        page.locator(shareAmtString).isVisible();
        String shareString =page.locator(shareAmtString).textContent();
        Allure.step("Shares Amount :"+shareString);
        page.locator(sharenum).isVisible();
        String shareNumString=  page.locator(sharenum).textContent();
        Allure.step("Number of Share  :"+shareNumString); 
        page.waitForTimeout(1000);
        page.click(shareBtn);
        String sharepageUrlString=page.url();
        Allure.step("Shares Page Url: "+sharepageUrlString);
        return sharepageUrlString;     
	}
	public String isLoanCardPageVisible() {
		page.goBack();
		page.waitForSelector(loanBtn2);
		page.isEnabled(loanBtn2);
		page.click(loanBtn2);
		page.waitForSelector(visbleCard) ;
		String loanString=page.url();
	Allure.step("Loan Page Card Url"+loanString);
		return loanString;
		 }
	public String isaddContributtonVisible() {
		page.goBack();
		page.waitForSelector(myContrivutioncardBtn);
		page.isEnabled(myContrivutioncardBtn);
		page.click(myContrivutioncardBtn);
		page.waitForSelector(addContibutionCard);
		String pageURLString=page.url();		
		Allure.step("Add Contribution Page Url"+pageURLString);
		return pageURLString;
	}
	public String isApplyLoanVisible() {
		page.goBack();
		String pageUrlString;
		page.waitForSelector(isLoanBtnVisible);
		page.waitForTimeout(3000);
		page.isEnabled(isLoanBtnVisible);
			page.locator(isLoanBtnVisible).click();
			page.waitForTimeout(2000);
			pageUrlString=	page.url();
			
		
		Allure.step("Apply Loan Page Url"+pageUrlString);
	return pageUrlString;	
		
	}
}
