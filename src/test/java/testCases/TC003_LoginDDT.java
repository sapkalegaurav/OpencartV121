package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;



public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="Datadriven") // Getting data provider from a different class
    public void verify_loginDDT(String email, String pwd, String exp) {
        logger.info("****** Starting TC_003_LoginDDT *****");
        
        try
        {
        // HomePage
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickLogin();

        // LoginPage
        LoginPage lp = new LoginPage(driver);
        lp.setEmail(email);
        lp.setPassword(pwd);
        lp.clickLogin();

        // MyAccountPage
        MyAccountPage macc = new MyAccountPage(driver);
        boolean targetPage = macc.isMyAccountPageExists();

        // Validation Logic
        if (exp.equalsIgnoreCase("valid")) {
            if (targetPage) {
                macc.clickLogout();
                Assert.assertTrue(true, "Login succeeded as expected for valid data.");
            } else {
                Assert.fail("Login failed unexpectedly for valid data.");
            }
        } else if (exp.equalsIgnoreCase("invalid")) {
            if (targetPage) {
                macc.clickLogout();
                Assert.fail("Login succeeded unexpectedly for invalid data.");
            } else {
                Assert.assertTrue(true, "Login failed as expected for invalid data.");
            }
        }
        }catch(Exception e)
        {
        	Assert.fail();
        }
        logger.info("****** Finished TC_003_LoginDDT *****");
    }
}


	


