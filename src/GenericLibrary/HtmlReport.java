package GenericLibrary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import DriverScript.Driverscript;
import DriverScript.Xls_Reader;



public class HtmlReport {
	
	public static String GetReportName(String ReportName)
	{		
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();		
			String TempFileName=dateFormat.format(date);
			String NewFileName1=TempFileName.replace("/","_");
			String NewFileName2=NewFileName1.replace(" ","_");
			String NewFileName=NewFileName2.replace(":","_");			
			String ReportFileName =(System.getProperty("user.dir")+"\\src\\Results\\"+ReportName+""+NewFileName+".html");
		    return ReportFileName;
		
	}
	
	
	public static void ExcelResult(String status)
	{
		Xls_Reader xres=new Xls_Reader(System.getProperty("user.dir")+"\\src\\Results\\ExcelResult.xlsx");
		int rowcount=xres.getRowCount(Driverscript.vAppName);
		xres.setCellData(Driverscript.vAppName, "ProjectName", rowcount+1, Driverscript.vAppName);
		xres.setCellData(Driverscript.vAppName, "ModuleName", rowcount+1, Driverscript.vModuleName);
		xres.setCellData(Driverscript.vAppName, "TestCaseName", rowcount+1, Driverscript.vTCName);
		xres.setCellData(Driverscript.vAppName, "TestCaseDescription", rowcount+1, Driverscript.vDescription);
		xres.setCellData(Driverscript.vAppName, "Status", rowcount+1, status);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();		
		String TempFileName=dateFormat.format(date);		
		xres.setCellData("vTiger", "DateTime", rowcount+1, TempFileName);
	}

}
