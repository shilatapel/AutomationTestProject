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

public class RegistrationTest {
	private WebDriver driver;
	private WebDriverWait wait;
	List<RegistrationData> lst;
	ExcelUtils eu = new ExcelUtils();
	final int COLUMN_ACTUAL_ERROR = 11;
	final String EXCEL_FILE_NAME = "xl_data.xlsx";

	private void helpTestError(int row, String nameOfErr, String nameTest) throws Exception {

		RegistrationPage RP = new RegistrationPage(driver, wait);
		RP.fillElement(lst.get(row));
		String actualError = RP.getErrorString(nameOfErr);
		String expectedError = lst.get(row).getExpectedResult();
		Assert.assertEquals(actualError, expectedError);
		eu.setCellValue(row + 1, COLUMN_ACTUAL_ERROR, EXCEL_FILE_NAME, actualError);
		RP.takeScreenShot(nameTest);
	}

	@BeforeClass
	void setUp() {
		driver = DriverUtils.createDriverObj(2);
		lst = new ArrayList<RegistrationData>();
		eu.readExcelFile(lst, EXCEL_FILE_NAME);

	}

	@Test(priority = 4, description = "test positive registration- all filled correct")
	public void succeedRegistrationTest() throws Exception {

		RegistrationPage RP = new RegistrationPage(driver, wait);
		RP.fillElement(lst.get(0));
		RP.saveReg();
		RP.takeScreenShot("succeedRegistrationTest");
	}

	@Test(priority = 1, description = "test password too short message")
	public void passTooShortTest() throws Exception {
		helpTestError(1, "errorPass", "passTooShortTest");
	}

	@Test(priority = 2, description = "test if age under eighteen in birthday date message")
	public void birthdayUnderEighteenTest() throws Exception {
		helpTestError(2, "errorBirthday", "birthdayUnderEighteenTest");

	}

	@Test(priority = 3, description = "test for illegal id message")
	public void illegalIdTest() throws Exception {
		helpTestError(3, "errorId", "illegalIdTest");

	}

	@AfterClass
	public void MyTearDown() {
		driver.quit();
	}

}
