package GenericLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import DriverScript.Driverscript;
import DriverScript.Xls_Reader;

public class ScenarioDriver {
	Keywords keys=new Keywords();
	public String vTarget,value;
	
	public void keywordDriver(String keyword,Xls_Reader xrsc,String vModuleName,int m,Xls_Reader xrtd,int k)
	{
		WebElement elm;
		switch(keyword)
		{
		case "openApp":
			keys.openApp();
			break;
		case "open":
			 vTarget=xrsc.getCellData(vModuleName, "Target", m).trim();
			keys.open(vTarget);
			break;
		case "verifyTitle":
			vTarget=xrsc.getCellData(vModuleName, "Target", m).trim();
			keys.verifyTitle(vTarget);
			break;
		case "verifyElementPresent":
			vTarget=xrsc.getCellData(vModuleName, "Target", m).trim();
			elm=retriveLocators(vTarget);
			keys.verifyElementPresent(elm);
			break;
		case "verifyText":
			vTarget=xrsc.getCellData(vModuleName, "Target", m).trim();
			value=xrsc.getCellData(vModuleName, "Value", m).trim();
			elm=retriveLocators(vTarget);
			keys.verifyText(elm,value);
			break;
		case "verifyValue":
			vTarget=xrsc.getCellData(vModuleName, "Target", m).trim();
			value=xrsc.getCellData(vModuleName, "Value", m).trim();
			elm=retriveLocators(vTarget);
			keys.verifyValue(elm,value);
			break;
		case "type":
			vTarget=xrsc.getCellData(vModuleName, "Target", m).trim();
			value=ReplaceValue(xrsc.getCellData(vModuleName, "Value", m).trim(),k,xrtd,vModuleName);
			elm=retriveLocators(vTarget);
			keys.type(elm,value);
			break;
		case "clickAndWait":
			vTarget=xrsc.getCellData(vModuleName, "Target", m).trim();			
			elm=retriveLocators(vTarget);
			keys.clickAndWait(elm);
			break;
		case "select":
			vTarget=xrsc.getCellData(vModuleName, "Target", m).trim();
			value=xrsc.getCellData(vModuleName, "Value", m).trim();
			elm=retriveLocators(vTarget);
			keys.select(elm,value);
			break;
		}
		
	}
	
	
	public WebElement retriveLocators(String str)
	{
		WebElement elm=null;
		String locatortext;
		try
		{
				if(str.startsWith("css="))
				{
					locatortext=str.replace("css=", "").trim();
					elm=Driverscript.driver.findElement(By.cssSelector(locatortext));
				}
				else if(str.startsWith("name="))
				{
					locatortext=str.replace("name=", "").trim();
					elm=Driverscript.driver.findElement(By.name(locatortext));
				}
				else if(str.startsWith("id="))
				{
					locatortext=str.replace("id=", "").trim();
					elm=Driverscript.driver.findElement(By.id(locatortext));
				}
				else if(str.startsWith("link="))
				{
					locatortext=str.replace("link=", "").trim();
					elm=Driverscript.driver.findElement(By.linkText(locatortext));
				}
				else if(str.startsWith("//"))
				{
					elm=Driverscript.driver.findElement(By.xpath(str));
				}
				else 
				{
					elm=Driverscript.driver.findElement(By.xpath(str));
				}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage());
			elm=null;
		}
		return elm;
		
	}
	
	
	public String ReplaceValue(String value,int k,Xls_Reader xrtd,String vModuleName)
	{
		String Newvalue = null;
		
		if(value.startsWith("Param"))
		{
			Newvalue=getParamData(value,k,xrtd,vModuleName);
		}
		else if(value.startsWith("Fn_RandomUser"))
		{
           //Newvalue=Fn_RandomUser(value,k,xrtd,vModuleName);
			Newvalue=value;
		}
		else
		{
			Newvalue=value;
		}
		
		
		
		return Newvalue;
		
	}
	
	public String getParamData(String value,int k,Xls_Reader xrtd,String vModuleName)
	{
		String newValue=xrtd.getCellData(vModuleName, value, k);		
		return newValue;
	}

}
