package excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	// Reads data from Excel and returns as list of lists
	public static List<List<String>> dataFromExcel(String filePath, int sheetNo)
 {
	    List<List<String>> credentials = null;
	    
	    try {
	    	
	    	//Initializing list to store Excel data
	        credentials = new ArrayList<>();
	        FileInputStream file = new FileInputStream(new File(filePath));
	        Workbook workbook = new XSSFWorkbook(file);
	        Sheet sheet = workbook.getSheetAt(sheetNo);
	        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
	            Row row = sheet.getRow(i);
	            List<String> rowData = new ArrayList<>();
	            if (row != null) {
	                for (int j = 0; j < row.getLastCellNum(); j++) {
	                    Cell cell = row.getCell(j);
	                    if (cell != null) {
	                       try {
	                    	   
	                    	   //Try to read the cell as string
	                    	   rowData.add(cell.getStringCellValue());
	                    	   
	                       }catch (Exception e) {
							rowData.add(String.valueOf((int)cell.getNumericCellValue()));
						}
	                    } else {
	                        rowData.add("");
	                    }
	                }
	            }
	            credentials.add(rowData);
	        }
	        workbook.close();
	    } catch (Exception e) {
	        System.out.println("Exception in dataFromExcel method: " + e);
	    }
	    return credentials;
	}

}
