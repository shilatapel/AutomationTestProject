package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePOM.BasePage;

public class SearchPage extends BasePage {

	WebDriver driver;
	WebElement searchTextBox = null;
	By theByForTheSearchTextBox = null;
	By theSearchResultCountBy = null;
	By theErorrForInvalidSearchBy = null;

	public SearchPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		initBys();
	}

	private void initBys() {
		theByForTheSearchTextBox = By.id("js-site-search-input");
		theSearchResultCountBy = By.id("searchResults_count_label");
		theErorrForInvalidSearchBy = By.cssSelector(".titleSection");

	}

	private void initElems() {
		waitForVisibility(theByForTheSearchTextBox);
		searchTextBox = driver.findElement(theByForTheSearchTextBox);
	}

	public void goToPage() {
		visit("https://www.shufersal.co.il/online/he/S");

	}

	public void searchTerm(String termToSearch) {
		initElems();
		searchTextBox.sendKeys(termToSearch + "\n");

	}

	public String searchTermAndGetResultCount(String termToSearch) {
		searchTerm(termToSearch);
		String resultsStr = driver.findElement(theSearchResultCountBy).getText();
		return resultsStr;
	}

	public String searchAndGetInvalidSearch(String termToSearch) {
		searchTerm(termToSearch);
		String resultsStr = driver.findElement(theErorrForInvalidSearchBy).getText();
		return resultsStr;
	}

}
