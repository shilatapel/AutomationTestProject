package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePOM.BasePage;

public class ShoppingCartPage extends BasePage {

	WebDriver driver;
	WebDriverWait wait;
	final String[] STRING_TO_SEARCH = { "במבה", "גארפילד", "גרפילד" };
	String flagForFirstTimeAddToCart = "FALSE";
	By addToCartBy = null;
	By popUpBy = null;
	By closePopUpBy = null;
	By hoverBy = null;
	By searchNameBy = null;
	SearchPage sPage = null;

	public ShoppingCartPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		initBys();
	}

	private void initBys() {
		hoverBy = By.cssSelector(".miglog-prod-inStock");
		addToCartBy = By.cssSelector(".js-add-to-cart");
		popUpBy = By.id("assortmentModalTitle");
		closePopUpBy = By.cssSelector(".loginModalTop button");
		searchNameBy = By.cssSelector(".searchName");

	}

	public void goToPage() {
		visit("https://www.shufersal.co.il/online/he/S");
	}

	public void searchItem(int i) {
		this.sPage = new SearchPage(driver, wait);
		sPage.searchTerm(STRING_TO_SEARCH[i]);
	}

	public void enterToShoppingCart(int item) {
		scrollDown();
		List<WebElement> ls1 = findElems(hoverBy);
		mouseHover(ls1.get(item));
		List<WebElement> ls = findElems(addToCartBy);
		ls.get(0).click();
		if (flagForFirstTimeAddToCart == "FALSE") {
			waitForVisibility(popUpBy);
			driver.findElement(closePopUpBy).click();
			flagForFirstTimeAddToCart = "TRUE";
		}
	}

	public void filterItems(String termToSort) {
		// scrollDown();
		List<WebElement> ls = findElems(searchNameBy);
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i).getText().equals(termToSort)) {
				ls.get(i).click();
				break;
			}
		}

	}

}
