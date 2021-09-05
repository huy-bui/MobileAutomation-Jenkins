package pageObjects;

import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pageUIs.SignInPageUI;

public class SignInPage extends BasePage {

	public SignInPage(AndroidDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void inputEmail(String email) {
		waitForElementVisible(SignInPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(SignInPageUI.EMAIL_TEXTBOX, email);
	}

	public void inputPassword(String password) {
		sendKeysToElement(SignInPageUI.PASSWORD_TEXT_BOX, password);
	}

	public HomePage clickSignInButton() {
		clickToElement(SignInPageUI.SIGNIN_BUTTON);
		return new HomePage(driver);
	}
}
