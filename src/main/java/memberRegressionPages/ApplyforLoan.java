package memberRegressionPages;

import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

import io.qameta.allure.Allure;

public class ApplyforLoan {
	private	Page page;
	
	private String card_sacco_tag = "//div[@class='row']//a";
	private String myContibutionAmtString="//div//h6[contains(text(),'CR')]";
	private String seeDetailLoan="//div//div[@routerlink='./yourloans' and normalize-space()='See Details']";
	private String LoanAmt="//div//h6[contains(text(),'DR' )]";
	private String totalLoan="//div[@class='d-flex flex-lg-row']//div//div[2]//h6[2]";
	private String visbleCard="(//div[@id='tabsCustom']//app-loan-list)[1]";
	private String loanvisbleCard="(//div[@id='tabsCustom']//app-loan-list)";
	private String pendingbtn="(//tabset//li//a[@role='tab'])[2]";
	private String errormsg = "//div[@class='toast-message']";
	private String applyloanBtn="//div[@routerlink='./apply-loan']";
	private String loantypeinput="//div[@class='card-body']//form";
	private String loantypebox="//div[@role='listbox']";
	private String loanType="//div[@role='listbox']//mat-option[2]";
	private String principleAmt="(//div[@class='card']//form//legend)[1]";
	private String tenureString="(//div[@class='card']//form//legend)[2]";
	private String tenureInput="//input[@formcontrolname='tenure']";
	private String principleInput="//input[@formcontrolname='principleAmount']";
	private String loanPurpose ="//input[@formcontrolname='loanPurpose']";
	private String currentResidenceAddress="//textarea[@id='currentResidenceAddress']";
	private String typeOfBusiness ="//input[@formcontrolname='typeOfBusiness']";
	private String ecoSector="//mat-select[@formcontrolname='economicSector']//div";
	private String ecoOptionbox="//div[@role='listbox']";
	private String ecoOption="//div[@role='listbox']//mat-option[2]";
	private String subSector="//mat-select[@formcontrolname='economicsubSector']//div";
	private String subOptionBox="//div[@role='listbox']";
	private String subOption="//div[@role='listbox']//mat-option[1]";
	private String currentIncome ="//input[@formcontrolname='currentIncome']";
	private String genderoption="//mat-select[@formcontrolname='gender']";
	private String gender="//div[@role='listbox']//mat-option[1]";
	private String addKin="//div//mat-icon[@role='img']";
	private String kinFirstname="//input[@id='doctype0']";
	private String kinGender="//mat-select[@formcontrolname='nextOfKinGender']";
	private String kinGenderOption="//div[@role='listbox']//mat-option[1]";
	private String kinrelation="//mat-select[@formcontrolname='relationshipId']";
	private String kinrelationOption="//div[@role='listbox']//mat-option[1]";
	private String kinDOB="//input[@id='nextOfKinDOB0']";
	private String kinPhoneNo="//input[@id='nextOfKinPhoneNumber0']";
	private String kinEmail="//input[@id='nextOfKinEmailId0']";
	private String collateralCheck="//mat-checkbox[@id='Others']";
	private String collateralform="//div[@class='form-group']";	
	private String collName="//td//input[@formcontrolname='assetName']";
	private String collType="//td//input[@formcontrolname='assetType']";
	private String collvalue="//td//input[@formcontrolname='currentValuation']";
	private String collblock="//td//input[@formcontrolname='blockAmount']";
	private String collcomment="//td//textarea[@formcontrolname='comment']";
	private String collUpload="//input[@type='file']";
	private String collfilename ="//td//mat-label"; 
	private String destType ="//mat-select[@formcontrolname='destinationType']";
	private String destTypeOption = "//div[@role='listbox']//mat-option[3]";
	private String marriedCheckBox="//mat-checkbox[@id='isMarried']";
	private String tncChecked="//mat-checkbox[@id='tncChecked']";
	private String loanCosting="//app-loan-costing//mat-icon";
	private String monthlyPayment="//app-loan-costing//small[text()='Monthly Payment:']/following-sibling::span";
	private String totalPayment = "//app-loan-costing//small[contains(text(),'Over')]/following-sibling::span";
	private String totalIntrest = "//app-loan-costing//small[contains(text(),\"Total interest\")]/following-sibling::span";
	private String DueDate="//mat-dialog-container//tbody//tr[1]//td[@data-label='Due Date']";
	private String installmentAmt="//mat-dialog-container//tbody//tr[1]//td[@data-label='Installment']";
	private String princiAmt="//mat-dialog-container//tbody//tr[1]//td[@data-label='Principal Amount']";
	private String intrestAmt="//mat-dialog-container//tbody//tr[1]//td[@data-label='Interest Amount']";
	private String loandelBtn="//app-loan-list//button";
	
	
	
	
	
	int minimumAmount = 0;
	int tenure=4;
	String tenureperiod ="4" ; 
	
	
	private int pending=0;
	public ApplyforLoan(Page page) {
		this.page=page;
	}
	 
	public Boolean noPendingLoan() {
		page.waitForSelector(card_sacco_tag);
		page.waitForTimeout(2000);
		page.click(card_sacco_tag);
		page.waitForSelector(myContibutionAmtString);
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
		page.waitForTimeout(1000);
		page.click(pendingbtn);
		page.waitForTimeout(2000);
//		if (page.isVisible(errormsg))
//		{   System.out.println("Internal Error message displayed after clicking login button");
//		    return false;
//		}
		if(page.isVisible(loanvisbleCard)) {
			pending=1;
//			ElementHandle button= page.querySelector(loandelBtn);
//            delbutton.evaluate("button => button.dispatchEvent(new MouseEvent('click'))");
//			 button.focus();
//			 page.keyboard().press("Enter");
			page.locator(loandelBtn).press("Enter");;
//			button.evaluate("button => button.click()");	
//			page.locator(loandelBtn).click(new Locator.ClickOptions().setForce(true));
			page.waitForTimeout(3000);
			page.onDialog(dialog -> dialog.accept());
			page.waitForTimeout(2000);
//			page.locator("//button[]").click();
			if(!page.isVisible(visbleCard)) {
				System.out.println("Previous loan delete sucessfully");
			}
		}
		return true;
	}
	public Boolean applyforLoan(String intrest) {
		Double intrestRate = Double.parseDouble(intrest);
		if(pending==1) {
			Allure.step("You already applied for loan");
			return false;
		}
		page.goBack();
		page.waitForSelector(myContibutionAmtString);
		page.waitForTimeout(4000);
		page.isEnabled(applyloanBtn);
		page.click(applyloanBtn);
		if (page.isVisible(errormsg))
		{   System.out.println("Internal Error message displayed after clicking login button");
		    return false;
		}
		page.click(loantypeinput);
		page.waitForSelector(loantypebox);
		page.click(loanType);
		
		if (page.isVisible(errormsg))
		{   System.out.println("Internal Error message displayed after clicking login button");
		    return false;
		}
		//to find minimum principle amount
		String principleString= page.locator(principleAmt).textContent();
		 Pattern pattern = Pattern.compile("\\d+");
		 Matcher matcher = pattern.matcher(principleString);
		
	        if (matcher.find()) {
	            minimumAmount = Integer.parseInt(matcher.group())+2;
	        }
	        String minimumAmountString = String.valueOf(minimumAmount);
	     page.locator(principleInput).fill(minimumAmountString);
	     //to find minimum Tenure
		String tenurecontent=  page.locator(tenureString).textContent();
		System.out.println(tenurecontent);
		page.fill(tenureInput, tenureperiod);
		//more details
		page.fill(loanPurpose,"Testing");
		page.waitForTimeout(500);
		page.fill(currentResidenceAddress, "Goa");
		page.waitForTimeout(500);
		page.fill(typeOfBusiness, "Test");
		page.waitForTimeout(1500);
//		page.click(ecoSector);
		page.getByLabel("Choose Economic Sector").click();;
		page.waitForSelector(subOptionBox);
		page.waitForTimeout(2000);	
		if(page.isVisible(ecoOptionbox)) {
			page.waitForTimeout(1500);
			page.click(ecoOption);
		}
		page.waitForTimeout(1500);
		page.click(subSector);
		page.waitForSelector(subOptionBox);
		if(page.isVisible(subOptionBox)) {
			page.waitForTimeout(1500);
			page.click(subOption);
		}
		page.fill(currentIncome, "10000");
		page.click(genderoption);
		page.waitForTimeout(500);
		page.click(gender);
		
		page.click(addKin);
		if(page.isVisible(kinFirstname)) {
			page.fill(kinFirstname,"Xyz" );
			page.waitForTimeout(500);
			page.click(	kinGender);
			page.waitForTimeout(500);
			page.click(kinGenderOption);
			page.waitForTimeout(500);
			page.click(kinrelation);
			page.waitForTimeout(500);
			page.click(kinrelationOption);
			page.fill(kinDOB, "11/30/1991");
			page.waitForTimeout(500);
			page.fill(kinPhoneNo, "008094545613");
			page.waitForTimeout(500);
			page.fill(kinEmail, "vaibhav@yopmail.com");
			
		
		}
		page.click(collateralCheck);
		if(page.isVisible(collateralform)) {
			page.fill(collName, "test collateral");
			page.waitForTimeout(500);
			page.fill(collType, "test");
			page.waitForTimeout(500);
			page.fill(collvalue,minimumAmountString);
			page.waitForTimeout(500);
			page.fill(collblock, minimumAmountString);
			page.fill(collcomment, "test");
			page.waitForTimeout(500);
			page.setInputFiles(collUpload, Paths.get("C:\\Users\\hp\\Downloads\\vaibhav.pdf"));
			page.waitForTimeout(500);
			page.waitForSelector(collfilename);
			System.out.println("file upload sucessfull");
		}
		page.click(destType);
		page.waitForTimeout(1500);
		page.click(destTypeOption);
		//
		page.waitForSelector(loanCosting);
		
		//calculate intrest 
		Boolean intrestresultBoolean= checkIntrest(intrestRate);
		if(intrestresultBoolean=false) {
			return false;
		}
		
		
		page.click(marriedCheckBox);
		page.click(tncChecked);
		page.click("body");
		page.waitForTimeout(2000);
		page.click("//button[text()=' Apply loan ']");
		page.waitForTimeout(5000);
		return true;
		
	}

	
	public Boolean checkIntrest(Double intrestrate ) {
		
		   
Double permonthDouble = minimumAmount*(intrestrate/100); //37.074
String calpermounthIntrest = String.format("%.2f", permonthDouble); //37.07
Double convertedIntrestValue = Double.parseDouble(calpermounthIntrest);
Double caltotalintrest = convertedIntrestValue*tenure; //148.28
Double caltotalPay = minimumAmount+caltotalintrest;
Double calpmprinci =  (minimumAmount/(double)tenure);
Double calinstallment =calpmprinci+convertedIntrestValue;//287.57
 
System.out.println(calinstallment+"  " +caltotalPay+" "+caltotalintrest);

		  
		  ////
		  String monthlyPayString =  page.locator(monthlyPayment).textContent();
		  String totalPayString	= page.locator(totalPayment).textContent();
		  String totalIntrestString=page.locator(totalIntrest).textContent();
		  
		  String numericmonthlyPayString = monthlyPayString.replaceAll("[^\\d.]", "");
		  Double ValuemonthlyPayString = Double.parseDouble(numericmonthlyPayString);
		  
		  String numerictotalPayString = totalPayString.replaceAll("[^\\d.]", "");
		  Double ValuetotalPayString = Double.parseDouble(numerictotalPayString);
		  
		  String numerictotalIntrestString = totalIntrestString.replaceAll("[^\\d.]", "");
		  Double ValuetotalIntrestString = Double.parseDouble(numerictotalIntrestString);
		  
		  
		  System.out.println(ValuemonthlyPayString+"  "+ValuetotalPayString +" " + ValuetotalIntrestString);

		  
		  if(calinstallment!=ValuemonthlyPayString || caltotalPay !=ValuetotalPayString||caltotalintrest !=ValuetotalIntrestString)
			  return false;
		  
		  page.click(loanCosting);
		  page.waitForSelector(DueDate);
		  String installmentDate=page.locator(DueDate).textContent();
		  String installmentAmtvalue=page.locator(installmentAmt).textContent() ;
		  String princiAmtValue = page.locator(princiAmt).textContent();
		  String intrestValue = page.locator(intrestAmt).textContent();
		  Double installmentvalue = Double.parseDouble(installmentAmtvalue);
		  Double princiValue = Double.parseDouble(princiAmtValue);
		  Double intrestValueAmt = Double.parseDouble(intrestValue);
		  
		  if(calinstallment!= installmentvalue && calpmprinci!=princiValue&&convertedIntrestValue!=intrestValueAmt) {
			  return false;
		  }
	  	return true;
	}
}
