package AuthenticationTestSuit;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePOM.BasePage;

public class LoginPage extends BasePage {

	private WebDriver driver;
	private HashMap<String, By> selectorList;
	private HashMap<String, WebElement> elementList;
	private static String url = "https://www.shufersal.co.il/online/he/login";
	By saveButton = By.cssSelector(".btn-login");

	public LoginPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		selectorList = new HashMap<String, By>();
		elementList = new HashMap<String, WebElement>();
		openPage();
		initBy();
		initElements();
	}

	private void initBy() {
		selectorList.put("email", By.id("j_username"));
		selectorList.put("password", By.id("j_password"));
		selectorList.put("errorEmail", By.cssSelector(".register-message-validation"));

	}

	private void initElements() {
		elementList.put("email", driver.findElement(selectorList.get("email")));
		elementList.put("password", driver.findElement(selectorList.get("password")));
	}

	private void openPage() {
		visit(url);
	}

	public void fillElement(RegistrationData rd) {
		elementList.get("email").sendKeys(rd.getEmail());
		elementList.get("password").sendKeys(rd.getPassword());

	}

	public void saveLogin() {
		click(saveButton);

	}

	public String getErrorString(String str) {
		return driver.findElement(selectorList.get(str)).getText();
	}

}
