package memberRegressionPages;
import com.microsoft.playwright.ElementHandle;

import com.microsoft.playwright.Page;

import io.qameta.allure.Allure;

public class memberContribution {
	private	Page page;
	private String myContrivutioncardBtn="//div//button[@routerlink='./addContribution']";
//	private String card_sacco_tag = "//div[@class='row']//a";
	private String myContibutionAmtString="//div[@class='row']//h6[contains(text(),'CR')]";
	private String contributionSelect="//div//mat-select[@formcontrolname='savingProductId']";
	private String contributionAmtInputString="//div//input[@formcontrolname='amount']";
	private String messageInputString="//div//input[@formcontrolname='message']";
	private String contributeBtnString="//button[normalize-space()='Contribute']";
	private String listBoxString="//div[@role='listbox']";
	private String listContentString="//div[@role='listbox']//mat-option[@role='option'][2]";
	private String maxminamt="//div[ contains(  text(),' Note:- Please enter Transaction amount between')]";
	private String paymentCard="//div[@class='card-body']";
	private String paymentOption="//div[contains(@class,'ng-star-inserted')][2]";
	private String paymentOptionName="//div[contains(@class,'ng-star-inserted')][2]//div//div//div";
	private String amountString ="//span[contains(text(),'Amount')]/following-sibling::span";
	private String transactionString="//span[contains(text(),'Transaction Fees')]/following-sibling::span";
	private String contributionsString="//span[contains(text(),'Contribution amount')]/following-sibling::span";
	private String paymentconfirm ="(//app-payment-confirmation-modal)[1]";
	private String acceptBtn="//modal-container[1]//button[text()='Accept']";
	private String sucessString="//h6[text()='SUCCESS']";
	private String receiptAmt="//div//h6[text()='Amount']/following-sibling::h6";
	private String receipttransID="//div//h6[text()='Trans. ID']/following-sibling::div//h6";
	private String receiptTime="//div//h6[text()='Time']/following-sibling::h6";
	private String okBtn="//button[text()='OK']";
	private String errortagString="//div[contains(@class,'toast-error' ) ]";
	private String payment_receipt_Url="https://cams-dev5.wakandi.com/#/credit-union/payment-receipt";
	public memberContribution(Page page) {
		this.page=page;
	}
	public Boolean isContributionPageVisible() {
		page.waitForSelector(myContibutionAmtString);
		page.waitForTimeout(2000);
		String contributionStringamt =page.locator(myContibutionAmtString).textContent();
        String numericalValue = contributionStringamt.replaceAll("[^\\d.,]", "");
         double numericAmount = Double.parseDouble(numericalValue.replaceAll(",", ""));
         System.out.println(numericAmount);
		page.click(myContrivutioncardBtn);
		page.waitForTimeout(2000);
		page.locator(contributionSelect).click();
		page.waitForSelector(listBoxString);
		page.waitForTimeout(1000);
		page.locator(listContentString).click();
		page.waitForSelector(maxminamt);
		page.waitForTimeout(1000);
		String minmaxString=page.locator(maxminamt).textContent();
        String[] parts = minmaxString.split("\\s+");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (String part : parts) {
            if (part.matches("\\d+")) {
                int amountt = Integer.parseInt(part);
                if (amountt >= 10 && amountt <= 1000000000) {
                    min = Math.min(min, amountt);
                    max = Math.max(max, amountt);
                }
            }
        }
        String value=Integer.toString(min+1);
		page.fill(contributionAmtInputString,value);
		page.fill(messageInputString,"Test");
		page.locator(contributeBtnString).click();
		page.waitForSelector(paymentCard);
		String amtString=page.locator(amountString).textContent();
		String trnString =page.locator(transactionString).textContent();
		String contrString=page.locator(contributionsString).textContent();
		Allure.step("Amount value "+amtString);
		Allure.step("Transaction Fees "+trnString);
		Allure.step("Contribution amount"+contributionStringamt);
		System.out.println(amtString);
		System.out.println(trnString);
		System.out.println(contrString);
		String paymentName =  page.locator(paymentOptionName).textContent();
		page.locator(paymentOption).click();
		Allure.step(paymentName);
		page.waitForSelector(paymentconfirm);
		page.waitForTimeout(4000);
		page.locator(acceptBtn).isVisible();
		ElementHandle button= page.querySelector(acceptBtn);
		button.evaluate("element => element.click()");	
		if(page.locator(errortagString).isVisible()) {
			String errorString = page.locator(errortagString).first().textContent();
			Allure.step(errorString);
			System.err.println(errorString);
			return false;
		}else {

		// after loading page code
		page.waitForTimeout(30000);
		page.waitForURL(payment_receipt_Url);;
		page.waitForSelector(sucessString);
		page.waitForSelector(okBtn);
		String reciptamt=page.locator(receiptAmt).textContent();
		Allure.step(reciptamt);
		String trndIdString=page.locator(receipttransID).textContent();
		Allure.step(trndIdString);
		String trnsTimeString=page.locator(receiptTime).textContent();
		Allure.step(trnsTimeString);
		page.locator(okBtn).click();
		page.waitForSelector(myContibutionAmtString);
		String updateContribution=page.locator(myContibutionAmtString).textContent();
		 String numericalValueupdated = updateContribution.replaceAll("[^\\d.,]", "");
         double numericAmountupdated = Double.parseDouble(numericalValueupdated.replaceAll(",", ""));
         System.out.println(numericAmountupdated);
         if(numericAmount>=numericAmountupdated) {
        	 Allure.step("test failed bcz contributuion amount is not incresed");
        	 return false;
         }
         Allure.step("previous amount is \n :"+numericalValue+"updated value is "+numericalValueupdated);
		return true;
		}
	}
	
	
	

}
