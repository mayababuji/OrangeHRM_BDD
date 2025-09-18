package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    // ThreadLocal copies so each thread has its own workbook & fis
    private static ThreadLocal<Workbook> workbookThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<FileInputStream> fisThreadLocal = new ThreadLocal<>();

    // Method to open the Excel file for this thread
    public static void openExcel(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);

            fisThreadLocal.set(fis);
            workbookThreadLocal.set(workbook);
        } catch (IOException e) {
            throw new RuntimeException("Error opening Excel: " + e.getMessage(), e);
        }
    }

    // Get workbook for this thread
    private static Workbook getWorkbook() {
        Workbook workbook = workbookThreadLocal.get();
        if (workbook == null) {
            throw new IllegalStateException("Excel file not opened for this thread. Call openExcel() first.");
        }
        return workbook;
    }

    // Method to read entire sheet into List<Map<String, String>>
    public static List<Map<String, String>> getExcelData(String sheetName) {
        Workbook workbook = getWorkbook();
        Sheet sheet = workbook.getSheet(sheetName);
        List<Map<String, String>> dataList = new ArrayList<>();

        Row headerRow = sheet.getRow(0);
        int columnCount = headerRow.getLastCellNum();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from row 1 (skip headers)
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Map<String, String> dataMap = new HashMap<>();
            for (int j = 0; j < columnCount; j++) {
                Cell headerCell = headerRow.getCell(j);
                String header = headerCell.getStringCellValue();

                Cell valueCell = row.getCell(j);
                String value = (valueCell == null) ? "" : valueCell.toString();

                dataMap.put(header, value);
            }
            dataList.add(dataMap);
        }
        return dataList;
    }

    // Method to fetch row by testcase column
    public static Map<String, String> getTestData(String sheetName, String testCase) {
        List<Map<String, String>> dataList = getExcelData(sheetName);
        for (Map<String, String> data : dataList) {
            if (data.get("testcase").equalsIgnoreCase(testCase)) {
                return data;
            }
        }
        return null; // If test case not found
    }

    // Close workbook and stream for this thread
    public static void closeExcel() {
        try {
            Workbook workbook = workbookThreadLocal.get();
            FileInputStream fis = fisThreadLocal.get();

            if (workbook != null) {
                workbook.close();
            }
            if (fis != null) {
                fis.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error closing Excel: " + e.getMessage(), e);
        } finally {
            workbookThreadLocal.remove();
            fisThreadLocal.remove();
        }
    }
    
    public static Map<String,String> getemployeeDetails(String sheetName){
    	 List<Map<String, String>> excelData = ExcelReader.getExcelData(sheetName);
    	 Map<String,String> row = excelData.get(0);
    	 Map<String,String> empDetails = new HashMap<>();
    	 empDetails.put("FirstName",row.get("EmployeeFirstName"));
    	 empDetails.put("LastName", row.get("EmployeeLastName"));
    	 empDetails.put("Password", row.get("Password"));
    	 return empDetails;
    	 
    }

    // For local testing
    public static void main(String[] args) {
        ExcelReader.openExcel("/Users/maya/eclipse-workspace/OrangeHRMBDD/src/test/resources/testData/LoginData.xlsx");
//        Map<String, String> data = ExcelReader.getTestData("Login", "ValidCredentials");
//        System.out.println(data);
//        System.out.println(data.get("Username"));
//        System.out.println(data.get("Password"));
        Map<String, String> empName = ExcelReader.getemployeeDetails("employee");
System.out.println(empName.get("FirstName"));
       
        	
       
        ExcelReader.closeExcel();
    }
}
