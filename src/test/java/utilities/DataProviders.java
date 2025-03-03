package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	// DataProvider 1
	@DataProvider(name="loginData")
	public String[][] getData() throws IOException{
		String path = ".\\testData\\testData.xlsx";  // taking xlfile from test data 
		ExcelUtility xlutil = new ExcelUtility(path);  // creating object of excelutility file to access methods
		int totalRows = xlutil.getRowCount("sheet1");  // getting row count
		int totalCols = xlutil.getCellCount("sheet1", 1);  // getting col count
		
		String loginData [][] = new String[totalRows][totalCols]; // creating multi-dimentional array to store data
		
		for(int i=1;i<=totalRows;i++) {  // i is starting from 1 because first row is header
			for (int j=0;j<totalCols;j++) 
			{ 
				loginData[i-1][j] = xlutil.getCellData("sheet1", i, j); 
			}
		}
		
		return loginData;
	}
	
	// DataProvider 2
	//DataProvider 3

}
