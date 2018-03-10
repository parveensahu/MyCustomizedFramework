package DriverScript;

import GenericLibrary.HtmlReport;
import GenericLibrary.ScenarioDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Driverscript {
	public static int failcnt=0;
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest logger; 
	public static String extentReport;
	
	public static ScenarioDriver sc=new ScenarioDriver();
	
	public static String vAppName,vAppUrl,vTestDataFile,vScenarioFile,vBrowser,vDBName,vDBUser,vDBPwd,vReportName;
	public static String vModuleName,vModPriority,vTCName,vDescription;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Xls_Reader xr=new Xls_Reader(System.getProperty("user.dir")+"/src/DriverFile/AppDriver.xlsx");
		//Xls_Reader xr=new Xls_Reader("/Users/apple/eclipse-workspace/AQH Framework/src/DriverFile/AppDriver.xlsx");
        int approws=xr.getRowCount("Applications");
        for(int i=2;i<=approws;i++)
        {
        	String vAppRun=xr.getCellData("Applications", "Run", i).trim();
        	if(vAppRun.equalsIgnoreCase("ON"))
        	{
        		vAppName=xr.getCellData("Applications", "ApplicationName", i).trim();
        		vAppUrl=xr.getCellData("Applications", "ApplicationURL", i).trim();
        		vTestDataFile=xr.getCellData("Applications", "TestDataFile", i).trim();
        		vScenarioFile=xr.getCellData("Applications", "ScenarioFile", i).trim();
        		vBrowser=xr.getCellData("Applications", "Browser", i).trim();
        		vDBName=xr.getCellData("Applications", "DBName", i).trim();
        		vDBUser=xr.getCellData("Applications", "DBUser", i).trim();
        		vDBPwd=xr.getCellData("Applications", "DBPwd", i).trim();
        		int vSync=Integer.parseInt(xr.getCellData("Applications", "Sync", i).trim());
        		vReportName=xr.getCellData("Applications", "ReportName", i).trim();
        	    System.out.println("========Application Name="+vAppName);
        	    
        	    Xls_Reader xrtd=new Xls_Reader(System.getProperty("user.dir")+"\\src\\TestData\\"+vTestDataFile+".xlsx");
        	    Xls_Reader xrsc=new Xls_Reader(System.getProperty("user.dir")+"\\src\\scenario\\"+vScenarioFile+".xlsx");
        	    report=setupResult();
        	    if(vBrowser.equals("Firefox"))
        	    {
        	    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\browsers\\geckodriver.exe");
        	        driver=new FirefoxDriver();
        	    }
        	    else if(vBrowser.equals("IE"))
        	    {
        	    	System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\browsers\\IEDriverServer.exe");
        	        driver=new InternetExplorerDriver();
        	    }
        	    else 
        	    {
        	    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\browsers\\chromedriver.exe");
        	        driver=new ChromeDriver();
        	    }
        	    driver.manage().timeouts().implicitlyWait(vSync, TimeUnit.SECONDS);
        	    	
        	    int modrows=xr.getRowCount(vAppName);
        	    for(int j=2;j<=modrows;j++)
        	    {
        	    	String vModRun=xr.getCellData(vAppName, "Run", j).trim();
        	    	if(vModRun.equalsIgnoreCase("ON"))
        	    	{
        	    		vModuleName=xr.getCellData(vAppName, "ModuleName", j).trim();
                		vModPriority=xr.getCellData(vAppName, "Priority", j).trim();
                		System.out.println("-------"+vModuleName);
                		
                		int scrows=xrsc.getRowCount(vModuleName);
                		int tdrows=xrtd.getRowCount(vModuleName);
                		for(int k=2;k<=tdrows;k++)
                 	    {
                			String vTDRun=xrtd.getCellData(vModuleName, "Run", k).trim(); 
                			if(vTDRun.equalsIgnoreCase("ON"))
                	    	{
                				failcnt=0;
                				vTCName=xrtd.getCellData(vModuleName, "TCName", k).trim();
                        		vDescription=xrtd.getCellData(vModuleName, "Description", k).trim();
                        		logger=report.startTest(vTCName);
                        		System.out.println(">>"+vTCName);
                        		int flag=0;
                        		int rownum=0;
                        		for(int m=2;m<=scrows;m++)
                         	    {
                        			String vCommandtext=xrsc.getCellData(vModuleName, "Command", m).trim();
                        			if(vCommandtext.equals(vTCName))
                        			{
                        				flag=1;
                        				rownum=m;
                        			}
                        			if((flag==1) && (m>rownum))
                        			{
                        				if(vCommandtext.contains("_TC"))
                        				{
                        					break;
                        				}
                        				else
                        				{
                        					String keyword=vCommandtext;
                        					System.out.println(keyword);
                        					sc.keywordDriver(keyword,xrsc,vModuleName,m,xrtd,k);
                        					
                        					
                        				}
                        			}
                        			
                         	    }
                        		if(failcnt>0)
								{
                        			HtmlReport.ExcelResult("FAILED");	
								}
								else
								{
									HtmlReport.ExcelResult("PASSED");	
								}
                        		report.endTest(logger);
                        		
                        		
                	    	}
                 	    }
                		
        	    	}
        	    }
        	    report.flush();
        	    //file:////src/Results/vTigerRegression2018_01_21_11_09_10.html
        		driver.navigate().to("file:///"+extentReport);
        	}
        } 
        
        
	}
	
	public static ExtentReports setupResult()
	{					
		extentReport=HtmlReport.GetReportName(vReportName);
		System.out.println(extentReport);
		report=new ExtentReports(extentReport);
		return report;
	}

}
