package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BasePage {

	protected AndroidDriver<MobileElement> driver;

	public BasePage(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
	}

	// Web Browser

	public void openPageURL(String url) {
		driver.get(url);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public Alert waitForAlertPresent() {
		explicitWait = new WebDriverWait(driver, timeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert() {
		alert = waitForAlertPresent();
		alert.accept();
	}

	public void cancelAlert() {
		alert = waitForAlertPresent();
		alert.dismiss();
	}

	public void sendKeysToAlert(String value) {
		alert = waitForAlertPresent();
		alert.sendKeys(value);
	}

	public String getAlertText() {
		alert = waitForAlertPresent();
		return alert.getText();
	}

	public void switchToWindowById(String parentID) {
		Set<String> allWindowsId = driver.getWindowHandles();
		for (String windowId : allWindowsId) {
			if (!windowId.equals(parentID)) {
				driver.switchTo().window(windowId);
				break;
			}
		}
	}

	public void switchToWindowByTitle(String windowTitle) {
		Set<String> allWindowsId = driver.getWindowHandles();
		for (String windowId : allWindowsId) {
			driver.switchTo().window(windowId);
			String actualWindowTitle = driver.getTitle();
			if (actualWindowTitle.equals(windowTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowsExceptParent(String parentId) {
		Set<String> allWindowsId = driver.getWindowHandles();
		for (String windowId : allWindowsId) {
			if (!windowId.equals(parentId)) {
				driver.switchTo().window(windowId);
				driver.close();
				sleepInSecond(1);
			}
			if (driver.getWindowHandles().size() == 1) {
				driver.switchTo().window(parentId);
				break;
			}
		}
	}

	public void sleepInSecond(long timeSleep) {
		try {
			Thread.sleep(timeSleep * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void backToPreviousPage() {
		driver.navigate().back();
	}

	public void forwardToPage() {
		driver.navigate().forward();
		;
	}

	public void refreshCurrentPage() {
		driver.navigate().refresh();
		;
	}

	// Mobile Element

	public MobileElement findElement(By locator) {
		return driver.findElement(locator);
	}

	public List<MobileElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	public void clickToElement(By locator) {
		findElement(locator).click();
	}

	public void sendKeysToElement(By locator, String value) {
		findElement(locator).clear();
		findElement(locator).sendKeys(value);
	}

	public int getNumberOfElements(By locator) {
		return findElements(locator).size();
	}

	public void selectDropDownByText(By locator, String itemText) {
		select = new Select(findElement(locator));
		select.selectByVisibleText(itemText);
	}

	public String getSelectedItemDropDown(By locator) {
		select = new Select(findElement(locator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropDownMultiple(By locator) {
		select = new Select(findElement(locator));
		return select.isMultiple();
	}

	public void selectItemInCustomDropDownByText(By parentLocator, By childLocator, String itemText) {
		findElement(parentLocator).click();
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, timeout);
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(childLocator));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(itemText)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public String getElementAttribute(By locator, String attributeName) {
		return findElement(locator).getAttribute(attributeName);
	}

	public String getElementText(By locator) {
		return findElement(locator).getText();
	}

	public void checkToCheckboxOrRadio(By locator) {
		if (!isElementSelected(locator)) {
			findElement(locator).click();
		}
	}

	public void uncheckToCheckbox(By locator) {
		if (isElementSelected(locator)) {
			findElement(locator).click();
		}
	}

	public boolean isElementSelected(By locator) {
		return findElement(locator).isSelected();
	}

	public boolean isElementEnbaled(By locator) {
		return findElement(locator).isEnabled();
	}

	public boolean isElementDisplayed(By locator) {
		return findElement(locator).isDisplayed();
	}

	public WebDriver switchToIframeByElement(By locator) {
		return driver.switchTo().frame(findElement(locator));
	}

	public WebDriver switchToDefaultContent(WebDriver driver) {
		return driver.switchTo().defaultContent();
	}

	// Actions

	public void hoverToElement(By locator) {
		actions = new Actions(driver);
		actions.moveToElement(findElement(locator)).perform();
	}

	public void doubleClickToElement(By locator) {
		actions = new Actions(driver);
		actions.doubleClick(findElement(locator)).perform();
	}

	public void rightClickToElement(By locator) {
		actions = new Actions(driver);
		actions.contextClick(findElement(locator)).perform();
	}

	public void dragAndDropElement(By sourceLocator, By targetLocator) {
		actions = new Actions(driver);
		actions.dragAndDrop(findElement(sourceLocator), findElement(targetLocator)).perform();
	}

	public void pressKeyToElement(By locator, Keys keyboard) {
		actions = new Actions(driver);
		actions.sendKeys(findElement(locator), keyboard).perform();
	}

	// JavaScripts Executor - Not implemented yet

	// Wait handling

	public void waitForElementVisible(By locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void waitForAllElementsVisible(By locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void waitForElementInvisible(By locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForElementClickable(By locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(findElement(locator)));
	}

	private Alert alert;
	private Select select;
	private Actions actions;
	private long timeout = 30;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
}