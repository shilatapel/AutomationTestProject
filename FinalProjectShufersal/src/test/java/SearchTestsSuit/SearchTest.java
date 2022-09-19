package SearchTestsSuit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.SearchPage;
import Utils.DriverUtils;

public class SearchTest {
	private WebDriver driver;
	private WebDriverWait wait;
	final String[] STRING_TO_SEARCH = { "במבה", "גארפילד", "גרפילד" };
	String actualError = "FAILED";
	String expectedError = "אין תוצאות חיפוש עבור: \"גארפילד\"";

	@BeforeClass
	void setUp() {
		driver = DriverUtils.createDriverObj(2);

	}

	@Test(priority = 2, description = "test for search result displayed")
	void searchResultDisplayedTest() {
		SearchPage sPage = new SearchPage(driver, wait);
		sPage.goToPage();
		String resultCount = sPage.searchTermAndGetResultCount(STRING_TO_SEARCH[0]);
		resultCount.replaceAll("[^0-9]+", "");
		sPage.takeScreenShot("searchResultDisplayedTest");

		if (resultCount != null)
			actualError = "PASS";
		Assert.assertEquals(actualError, "PASS");
	}

	@Test(priority = 1, description = "test for search invalid keyword")
	void enteringInvalidKeywordTest() {
		SearchPage sPage = new SearchPage(driver, wait);
		sPage.goToPage();
		actualError = sPage.searchAndGetInvalidSearch(STRING_TO_SEARCH[1]);
		Assert.assertEquals(actualError, expectedError);
		sPage.takeScreenShot("enteringInvalidKeywordTest");
	}

	@AfterClass
	public void MyTearDown() {
		driver.quit();
	}

}
