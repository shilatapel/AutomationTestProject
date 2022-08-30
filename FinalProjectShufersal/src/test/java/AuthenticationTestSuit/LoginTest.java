package AuthenticationTestSuit;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utils.DriverUtils;
import Utils.ExcelUtils;

public class LoginTest {
	private WebDriver driver;
	private WebDriverWait wait;
	List<RegistrationData> lst;
	ExcelUtils eu = new ExcelUtils();
	final int COLUMN_ACTUAL_ERROR = 11;
	final String EXCEL_FILE_NAME = "xl_data.xlsx";

	private void helpTestError(int row, String nameOfErr, String nameTest) throws Exception {

		LoginPage LP = new LoginPage(driver, wait);
		LP.fillElement(lst.get(row));
		String actualError = LP.getErrorString(nameOfErr);
		String expectedError = lst.get(row).getExpectedResult();
		Assert.assertEquals(actualError, expectedError);
		eu.setCellValue(row + 1, COLUMN_ACTUAL_ERROR, EXCEL_FILE_NAME, actualError);
		LP.takeScreenShot(nameTest);
	}

	@BeforeClass
	void setUp() {
		driver = DriverUtils.createDriverObj(2);
		lst = new ArrayList<RegistrationData>();
		eu.readExcelFile(lst, EXCEL_FILE_NAME);

	}

	@Test(priority = 2, description = "test positive login- all filled correct")
	public void succeedLoginTest() throws Exception {

		LoginPage LP = new LoginPage(driver, wait);
		LP.fillElement(lst.get(4));
		LP.takeScreenShot("succeedLoginTest");
	}

	@Test(priority = 1, description = "test illegal email")
	public void illegalEmailTest() throws Exception {
		helpTestError(5, "errorEmail", "illegalEmailTest");

	}

	@AfterClass
	public void MyTearDown() {
		driver.quit();
	}
}