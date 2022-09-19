package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Pages.RegistrationData;

public class ExcelUtils {

	// Read all rows from Excel, for each row create an object of type
	// "RegistraionData" (firstName, lastName, password, confirmPassword, ...)
	// add all objects to a List

	public void readExcelFile(List<RegistrationData> lst, String fileName) {
		// FileInputStream - will be used to read file content
		// receives a string - the file name to read
		// XSSFWorkbook - abstraction of an excel file in the memory
		// receives a FileInputStream which is already initialized with excel file data
		FileInputStream f = null;
		XSSFWorkbook workbook = null;
		try {
			f = new FileInputStream(fileName);
			workbook = new XSSFWorkbook(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		// Skip the headers of row
		rowIterator.next();
		while (rowIterator.hasNext()) {
			RegistrationData newRegData = new RegistrationData();
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				// System.out.print(cell.getCellType() + ", ");
				// System.out.print(cell.getStringCellValue() + ", ");
				String str = cell.getStringCellValue();
				switch (cell.getColumnIndex()) {
				case 0:
					newRegData.setFirstName(str);
					break;
				case 1:
					newRegData.setLastName(str);
					break;
				case 2:
					newRegData.setId(str);
					break;
				case 3:
					newRegData.setPhone(str);
					break;
				case 4:
					newRegData.setEmail(str);
					break;
				case 5:
					newRegData.setBirthday(str);
					break;
				case 6:
					newRegData.setPassword(str);
					break;
				case 7:
					newRegData.setConfirmPassword(str);
					break;
				case 8:
					newRegData.setExpectedResult(str);
					break;
//				case 10:
//					newRegData.setErrorElementLocator(str);
//					break;
				default:
					break;
				}
			}
			// System.out.println();
			// System.out.println(newRegData);
			lst.add(newRegData);
		}
		try {
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCellValue(int row, int col, String fileName, String value) {
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		XSSFWorkbook workbook = null;
		try {
			inStream = new FileInputStream(fileName);
			workbook = new XSSFWorkbook(inStream);
			workbook.getSheetAt(0).getRow(row).getCell(col).setCellValue(value);
			outStream = new FileOutputStream(fileName);
			workbook.write(outStream);
			outStream.close();
			inStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
