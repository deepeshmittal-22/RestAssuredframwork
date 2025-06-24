package api.utilites;

import java.io.IOException;

import org.testng.annotations.DataProvider;



public class DataProviders {
	
	//DataProvider
	
		//@DataProvider (name="LoginData")
		@DataProvider (name="Data")
		public String[][] getData() throws IOException
		{
			String path=System.getProperty("user.dir")+"\\testData\\userdata.xlsx"; // taking xl file from testdata
			XLUtility xlutil= new XLUtility(path); //creating an object for XLUtility
			
			int totalrows=xlutil.getRowCount("sheet1");
			int totalcols=xlutil.getCellCount("sheet1", 1); 
			
			String apidata[][]= new String [totalrows][totalcols]; //created for two dimension array which can store
			
			for(int i=1;i<=totalrows;i++) // i  read the data from xl storing in two dimnsional array
				
			{
				for(int j=0;j<totalcols;j++) // i is rows and j is col
					
				{
					apidata[i-1][j] = xlutil.getCellData("sheet1", i, j);  //1.0
					
				}
				
				
			}
			return apidata; // returning two dimension array
			
			
		}
		
		
				@DataProvider (name="UserNames")
				public String[] getUserNamesData() throws IOException
				{
					String path=System.getProperty("user.dir")+"\\testData\\userdata.xlsx"; // taking xl file from testdata
					XLUtility xlutil= new XLUtility(path); //creating an object for XLUtility
					
					int totalrows=xlutil.getRowCount("sheet1");
					int totalcols=xlutil.getCellCount("sheet1", 1); 
					
					String apidata[]= new String [totalrows]; 
					
					for(int i=1;i<=totalrows;i++) 
						
					{
						 
					apidata[i-1] = xlutil.getCellData("sheet1", i, 1);  //1.0
							
						
					}
					return apidata; 
					
				
				}

}
