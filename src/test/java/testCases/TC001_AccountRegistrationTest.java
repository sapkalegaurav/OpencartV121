package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void verify_account_registration() {
        logger.info("***** Starting TC001_AccountRegistrationTest *****");
        logger.debug("this is a debug log message");;

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account link");

            hp.clickRegister();
            logger.info("Clicked on Register link");

            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

            logger.info("Providing customer details...");
            regpage.setFirstName(randomeString().toUpperCase());
            regpage.setLastName(randomeString().toUpperCase());
            regpage.setEmail(randomeString() + "@gmail.com");
            regpage.setTelephone(randomeNumber());

            String password = "randomeAlphaNumeric()";

            regpage.setPassword(password);
            regpage.setConfirmPassword(password);

            regpage.acceptPrivacyPolicy();
            regpage.clickContinue();

            logger.info("Validating expected confirmation message...");
            String confmsg = regpage.getConfirmationMsg();

            if (confmsg.equals("Your Account Has Been Created!")) {
                logger.info("Account registration successful.");
                Assert.assertTrue(true);
            } else {
                logger.error("Test failed: Unexpected confirmation message: " + confmsg);
                Assert.assertTrue(false);
            }
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            Assert.fail();
        }

        logger.info("***** Finished TC001_AccountRegistrationTest *****");
    }
}


