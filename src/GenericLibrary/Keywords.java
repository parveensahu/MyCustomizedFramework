package GenericLibrary;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import DriverScript.Driverscript;

public class Keywords {
	
	
	
	public void openApp()
	{
		Driverscript.driver.get(Driverscript.vAppUrl);
		
	}
	
	public void open(String url)
	{
		Driverscript.driver.navigate().to(Driverscript.vAppUrl+"/"+url);
		Driverscript.logger.log(LogStatus.INFO, "Application launched successfully");
	}
	
	public void verifyTitle(String exp)
	{
		if(Driverscript.driver.getTitle().trim().equals(exp))
		{
			System.out.println("PASSED");
			Driverscript.logger.log(LogStatus.PASS, "Title matched successfully");
		}
		else
		{
			Driverscript.failcnt=Driverscript.failcnt+1;
			System.out.println("FAILED");
			Driverscript.logger.log(LogStatus.FAIL, "Title did not match");
		}
	}
	
	public void verifyElementPresent(WebElement elm)
	{
		boolean result=false;
		try
		{
		 result=elm.isDisplayed();
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage());
		}		
		if(result)
		{
			System.out.println("PASSED");
			Driverscript.logger.log(LogStatus.PASS, "Element is available");
		}
		else
		{
			Driverscript.failcnt=Driverscript.failcnt+1;
			System.out.println("FAILED");
			Driverscript.logger.log(LogStatus.FAIL, "Element does not exist");
		}
	}
	
	public void verifyText(WebElement elm,String value)
	{		
		if(elm.getText().equals(value))
		{
			System.out.println("PASSED");
			Driverscript.logger.log(LogStatus.PASS, "Expected text "+value+ " is matched with actual text "+elm.getText()+" successsfully");
		}
		else
		{
			Driverscript.failcnt=Driverscript.failcnt+1;
			System.out.println("FAILED");
			Driverscript.logger.log(LogStatus.FAIL, "Expected text "+value+ " did not match with actual text "+elm.getText());
		}
	}
	
	public void verifyValue(WebElement elm,String value)
	{		
		if(elm.getAttribute("value").equals(value))
		{
			System.out.println("PASSED");
			Driverscript.logger.log(LogStatus.PASS, "Expected value "+value+ " is matched with actual value "+elm.getAttribute("value")+" successsfully");
		}
		else
		{
			Driverscript.failcnt=Driverscript.failcnt+1;
			System.out.println("FAILED");
			Driverscript.logger.log(LogStatus.FAIL, "Expected value "+value+ " did not match with actual value "+elm.getAttribute("value"));
		}
	}
	
	public void type(WebElement elm,String value)
	{		
		if(elm.isDisplayed())
		{
			elm.clear();
			elm.sendKeys(value);
			System.out.println("PASSED");
			Driverscript.logger.log(LogStatus.PASS, "Text "+value+ " is  Entered successfully");
		}
		else
		{
			Driverscript.failcnt=Driverscript.failcnt+1;
			System.out.println("FAILED");
			Driverscript.logger.log(LogStatus.FAIL, "Text "+value+ " did not Enter");
		}
	}
	
	public void clickAndWait(WebElement elm)
	{		
		if(elm.isDisplayed())
		{
			elm.click();
			System.out.println("PASSED");
			Driverscript.logger.log(LogStatus.PASS, "Element is displayed successfully");
		}
		else
		{
			Driverscript.failcnt=Driverscript.failcnt+1;
			System.out.println("FAILED");
			Driverscript.logger.log(LogStatus.FAIL, "Element not found");
		}
	}
	
	public void select(WebElement elm,String value)
	{		
		if(elm.isDisplayed())
		{
			elm.sendKeys(value.replace("label=", ""));
			System.out.println("PASSED");
			Driverscript.logger.log(LogStatus.PASS, "value "+value+ " is  selected successfully");
		}
		else
		{
			Driverscript.failcnt=Driverscript.failcnt+1;
			System.out.println("FAILED");
			Driverscript.logger.log(LogStatus.FAIL, "value "+value+ " did not select");
		}
	}
	
	

}
