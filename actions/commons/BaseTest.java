package commons;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseTest {

	protected AndroidDriver<MobileElement> driver;
	protected WebDriverWait wait;
	protected static String environment = null;
	protected static String envFolderPath;
	protected static String configsFolderPath;
	protected static String testDataFolderPath;
	protected static String projectPath = Constants.PROJECT_PATH;
	protected static String fileSeperator = Constants.FILE_SEPERATOR;

	@BeforeTest
	@Parameters({"env"})
	public void setups(@Optional String env) throws Exception {
		setupDriver();
		environment = env;
		if (environment == null || environment.equalsIgnoreCase("${env}")) {
			envFolderPath = projectPath + fileSeperator + "environments" + fileSeperator + "testing";
			configsFolderPath = envFolderPath + fileSeperator + "configs";
			testDataFolderPath = envFolderPath + fileSeperator + "testData";
		} else if (environment.equalsIgnoreCase("testing") | environment.equalsIgnoreCase("demo")) {
			envFolderPath = projectPath + fileSeperator + "environments" + fileSeperator + environment;
			configsFolderPath = envFolderPath + fileSeperator + "configs";
			testDataFolderPath = envFolderPath + fileSeperator + "testData";
		} else {
			System.out.println("Invalid environment. Please check again!");
		}
	}

	public void setupDriver() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Pixel4_API_29");
		caps.setCapability("udid", "emulator-5554");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "10.0");
		caps.setCapability("skipUnlock", "false");
		caps.setCapability("appPackage", "com.karrostech.parentportal.android.prod");
		caps.setCapability("appActivity", "com.edulog.parentportal.busfollowed.core.navigation.RouteActivity");
		caps.setCapability("noReset", "false");
		caps.setCapability("autoGrantPermissions", "true");
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
	}

	@AfterTest
	public void quitDriver() {
		driver.quit();
	}

}
