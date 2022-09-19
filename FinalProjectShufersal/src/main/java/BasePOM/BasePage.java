package BasePOM;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	private WebDriver driver;
	private WebDriverWait wait;
	Actions action = null;

	public BasePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void waitForVisibility(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitUntilClickabilityElementLocated(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public WebElement findElem(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> findElems(By locator) {
		return driver.findElements(locator);
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}

	// --------------------------------------------------
	// adding ability to concatenate a few methods on a webelement e.g:
	// typeInto("hello", By.cssSelector("[name=\"q\"]")).click();
	public WebElement typeInto(String inputText, By locator) {
		findElem(locator).sendKeys(inputText);
		return findElem(locator);
	}

	public void click(By locator) {
		findElem(locator).click();
//		return findElem(locator);
	}

//   -----------------------------------------------------
	public void doubleClick(WebElement doubleClickElement) {
		action = new Actions(driver);
		action.doubleClick(doubleClickElement).perform();
	}

	// --------------------------------------------------
	public void mouseHover(WebElement elem) {
		action = new Actions(driver);
		action.moveToElement(elem).perform();
	}
	// --------------------------------------------------

	public boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// --------------------------------------------------

	/*
	 * <select> <option value="paris"> Paris the beautiful city </option> <option
	 * value="nyc"> New York the big apple </option> <option value="vienna"> Vienna
	 * the great place to visit </option> </select>
	 */
	public void selectFromDropDownListByValue(By locator, String value) {
		Select select = new Select(driver.findElement(locator));
		select.selectByValue(value);
	}

	public void selectFromDropDownListByVisibleText(By locator, String text) {
		Select select = new Select(driver.findElement(locator));
		select.selectByVisibleText(text);
	}

	public void selectFromDropDownListByIndex(By locator, int indx) {
		Select select = new Select(driver.findElement(locator));
		select.selectByIndex(indx);
	}
	// --------------------------------------------------

	public void visit(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	// --------------------------------------------------

	public void takeScreenShot(String testName) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("./screenShot/ScreenShotTest_" + testName + ".png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void scrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
	}

}
