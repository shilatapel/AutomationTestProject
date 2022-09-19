package ShoppingCartTestsSuit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.ShoppingCartPage;
import Utils.DriverUtils;

public class ShoppingCartTest {
	private WebDriver driver;
	private WebDriverWait wait;
	ShoppingCartPage SCP = null;

	final String FILTER_TERM = "במבה";

	@BeforeClass
	void setUp() {
		driver = DriverUtils.createDriverObj(2);
	}

	void helpTest() {
		SCP = new ShoppingCartPage(driver, wait);
		SCP.goToPage();
		SCP.searchItem(0);

	}

	@Test(priority = 1, description = "test if is add to cart")
	void addToCartTest() {
		helpTest();
		SCP.enterToShoppingCart(0);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SCP.takeScreenShot("addToCartTest");
	}

	@Test(priority = 2, description = "test for filter search result")
	void filterSearchTest() {
		helpTest();
		SCP.filterItems(FILTER_TERM);
		SCP.takeScreenShot("filterSearchTest");

	}

	@AfterClass
	public void MyTearDown() {
		driver.quit();
	}
}
