package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);	
	}
	
	@FindBy(xpath="//h2[text()='My Account']")  //myAccout Page heading
	WebElement msgHeading;
	

	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")  //added in steps6
	WebElement lnkLogout;
	
	
	public boolean isMyAccountPageExists()
	{
		try
		{
		return(msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}


	public boolean clickLogout() {
	    lnkLogout.click();
	    return driver.getCurrentUrl().contains("expected_logout_url"); // Replace with actual URL or validation logic
	}

	
}
