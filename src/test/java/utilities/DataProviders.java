package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [] [] getData() throws IOException{
		
		String path=".\\testData\\Opencart_LoginData.xlsx";  //taking xl file from testData"
		
		ExcelUtility xlutil=new ExcelUtility(path);  //creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("sheet1");
		int totalcols=xlutil.getCellcount("sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols]; //creating for two dimensions array which can stored
		
		for(int i=1; i<=totalrows; i++)  //1
		{
			for(int j=0; j<totalcols; j++)		//0   i is rows j is col
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1",i,j); //1,0
			}
		}
		return logindata; //returning two dimension array
	}
			
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


