package SignIn;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.SignInPage;
import utils.ConvertUtils;

public class SignInTest extends BaseTest {

	SignInPage signInPage;
	HashMap<String, Object> dataTest;

	@BeforeClass
	public void prepareData() {
		String dataFilePath = testDataFolderPath.concat(fileSeperator).concat("signIn.json");
		dataTest = ConvertUtils.convertJsonToHashMap(dataFilePath);
	}

	@Test
	public void SignInSuccessfully() {
		HashMap<String, String> signInAccount = (HashMap) dataTest.get("signIn");
		signInPage = new SignInPage(driver);
		signInPage.inputEmail(signInAccount.get("username"));
		signInPage.inputPassword(signInAccount.get("password"));
		signInPage.clickSignInButton();
	}
}
