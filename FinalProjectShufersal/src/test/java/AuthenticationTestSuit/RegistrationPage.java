package AuthenticationTestSuit;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePOM.BasePage;

public class RegistrationPage extends BasePage {

	private WebDriver driver;
	private HashMap<String, By> selectorList;
	private HashMap<String, WebElement> elementList;
	private static String url = "https://www.shufersal.co.il/online/he/register";
	By saveButton = By.cssSelector(".btn-save");

	public RegistrationPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;

		selectorList = new HashMap<String, By>();
		elementList = new HashMap<String, WebElement>();
		openPage();
		initBy();
		initElements();
	}

	private void initBy() {
		selectorList.put("firstName", By.id("register_firstName"));
		selectorList.put("lastName", By.id("register_lastName"));
		selectorList.put("id", By.id("register_idNumber"));
		selectorList.put("phone", By.id("register_phone"));
		selectorList.put("password", By.id("password"));
		selectorList.put("confirmPassword", By.id("register.checkPwd"));
		selectorList.put("email", By.id("register_email"));
		selectorList.put("birthday", By.id("j_birthday"));
		selectorList.put("errorBirthday", By.id("register_dateOfBirth-error"));
		selectorList.put("errorPass", By.id("password-error"));
		selectorList.put("errorId", By.id("register_idNumber-error"));

	}

	private void initElements() {
		elementList.put("firstName", driver.findElement(selectorList.get("firstName")));
		elementList.put("lastName", driver.findElement(selectorList.get("lastName")));
		elementList.put("id", driver.findElement(selectorList.get("id")));
		elementList.put("phone", driver.findElement(selectorList.get("phone")));
		elementList.put("email", driver.findElement(selectorList.get("email")));
		elementList.put("birthday", driver.findElement(selectorList.get("birthday")));
		elementList.put("password", driver.findElement(selectorList.get("password")));
		elementList.put("confirmPassword", driver.findElement(selectorList.get("confirmPassword")));
//		System.out.println(elementList.get("confirmPassword").getText());
	}

	private void openPage() {
		visit(url);
	}

	public void fillElement(RegistrationData rd) throws Exception {

		elementList.get("firstName").sendKeys(rd.getFirstName());
		elementList.get("lastName").sendKeys(rd.getLastName());
		elementList.get("id").sendKeys(rd.getId());
		elementList.get("phone").sendKeys(rd.getPhone());
		elementList.get("email").sendKeys(rd.getEmail());
		elementList.get("birthday").sendKeys(rd.getBirthday());
		elementList.get("birthday").submit();
		elementList.get("password").sendKeys(rd.getPassword());
		elementList.get("confirmPassword").sendKeys(rd.getConfirmPassword());

	}

	public void saveReg() {
		click(saveButton);

	}

	public String getErrorString(String str) {

		return driver.findElement(selectorList.get(str)).getText();
	}

}