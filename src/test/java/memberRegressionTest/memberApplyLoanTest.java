package memberRegressionTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppConstant.AppConstants;
import baseTestMethod.BaseTest;

public class memberApplyLoanTest extends BaseTest {



	@Test (priority = 1)
	public void LoanPendingTest() {
	
		String actualTitle= unionList.getTHomePageTitle();
		Assert.assertEquals(actualTitle, AppConstants.Login_Page_Title);
		String actualURL= unionList.getTHomePageURL();
		Assert.assertEquals(actualURL, prop.getProperty("memberurl"));
		Boolean cardVisible= unionList.validDLoginusingEmail(prop.getProperty("memberEmail"),prop.getProperty("memberPassword"));
		Assert.assertEquals(cardVisible,true);
		Boolean pendingLoan = applyLoan.noPendingLoan();
		Assert.assertEquals(pendingLoan,true);
		
 
		
	}

	@Test (priority = 2)
	public void memberLoanTest() {
		Boolean Loan = applyLoan.applyforLoan(prop.getProperty("intrestrate"));
		Assert.assertEquals(Loan, true);

}}
