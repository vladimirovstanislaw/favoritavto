package parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rows.FourPointsRow;

public class FourPointsParser {
	private String filenameFrom = null;
	private HashMap<String, FourPointsRow> asIsMap;

	public FourPointsParser() {
		super();
		asIsMap = new HashMap<String, FourPointsRow>();
	}

	public void setFilenameFrom(String fileName) {
		this.filenameFrom = fileName;
	}

	public HashMap<String, FourPointsRow> Parse() throws IOException {
		File myFile = new File(filenameFrom);

		FileInputStream fis = new FileInputStream(myFile);

		// Finds the workbook instance for XLSX file
		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

		// Return first sheet from the XLSX workbook
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = mySheet.iterator();
		int countAllRows = 0;
		// Traversing over each row of XLSX file

		// Traversing over each row of XLSX file
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// System.out.println(row.getCell(0) + "; " + row.getCell(1) + "; " +
			// row.getCell(2));

			if (row == null) {
				continue;
			}
			if (row.getCell(0).toString().equals("CAI")) {
				continue;
			}

			FourPointsRow tmpRow = new FourPointsRow();
			tmpRow.setManufacturerCode(row.getCell(0).toString());
			tmpRow.setPriceRRC(row.getCell(19).toString());
			tmpRow.setPriceRoznica(row.getCell(18).toString());
			tmpRow.setLeftOver(row.getCell(17).toString());
			tmpRow.setName(row.getCell(2).toString() + " " + row.getCell(3).toString() + " "
					+ (row.getCell(4).toString().substring(0, row.getCell(4).toString().length() - 2)) + "/"
					+ (row.getCell(5).toString().substring(0, row.getCell(5).toString().length() - 2)) + " "
					+ row.getCell(6).toString() + " " + row.getCell(7).toString() + " " + row.getCell(8).toString()
					+ " " + row.getCell(9).toString());

			asIsMap.put(tmpRow.getManufacturerCode(), tmpRow);
			countAllRows++;

		}

		System.out.println("The number of entrys rows = " + countAllRows);
		myWorkBook.close();

		return asIsMap;
	}

}
