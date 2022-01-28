package demo23;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class dependsonmethods 
{
	WebDriver driver;
   @BeforeSuite
   public void openbrowser()
   {
	   System.setProperty("webdriver.chrome.driver","C:\\Users\\91909\\Downloads\\updated chrome driver\\chromedriver.exe");
	   driver=new ChromeDriver();
	   
   }
   @BeforeTest
   public void openurl()
   {
	  driver.get("https://www.mercurytravels.co.in/");
	  
   }
   @BeforeTest
   public void windowmaximize() throws InterruptedException
   {
	   driver.manage().window().maximize();
	   Thread.sleep(2000);
	   
   }
   @BeforeMethod
   public void deletecookies()
   {
	   driver.manage().deleteAllCookies();
   }
   @Test
   public void logintest() throws InterruptedException
   {
	   Actions act=new Actions(driver);
	   act.moveToElement(driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[2]"))).build().perform();
	   driver.findElement(By.xpath("(//a[@data-toggle='modal'])[3]")).click();
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//input[@id='sign_user_email']")).sendKeys("tandalemahesh0144@gmail.com");
	   driver.findElement(By.xpath("//input[@id='sign_user_password']")).sendKeys("Amu@4321");
	   driver.findElement(By.xpath("//button[@class='btn btn-lg btn-primary modal-btn ajax-submit'][normalize-space()='Log in']")).click();
	   Thread.sleep(2000);
			   
   }
   @Test(dependsOnMethods= {"logintest"})
   public void logouttest() throws InterruptedException
   {
	   Actions act=new Actions(driver);
	   act.moveToElement(driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[4]"))).build().perform();
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("(//a[contains(text(),'Log Out')])[2]")).click();
	   Thread.sleep(2000);
			   
   }
   @AfterMethod
   public void screenshot() throws IOException
   {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(src, new File("C:\\Users\\91909\\eclipse-workspace\\TestNg\\screenshot"));
	   
   }
   @AfterClass
	public void deleteallcookies()
	{
		System.out.println("dlt cookies");
	}
	
	
	@AfterTest
	public void closedbconnection()
	{
		
		System.out.println("close db connection");
	}
	
	
	
	@AfterSuite
	public void closebrowser()
	{
		System.out.println("close browser");
		driver.close();
	}
   
}
