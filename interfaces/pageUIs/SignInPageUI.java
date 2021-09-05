package pageUIs;

import org.openqa.selenium.By;

public class SignInPageUI {

	public static final By EMAIL_TEXTBOX = By.xpath(
			"//android.widget.LinearLayout[@resource-id=\"com.karrostech.parentportal.android.prod:id/loginEmail\"]//android.widget.EditText");
	public static final By PASSWORD_TEXT_BOX = By.xpath(
			"//android.widget.LinearLayout[@resource-id=\"com.karrostech.parentportal.android.prod:id/loginPassword\"]//android.widget.EditText");
	public static final By SIGNIN_BUTTON = By.id("com.karrostech.parentportal.android.prod:id/loginAction");

}