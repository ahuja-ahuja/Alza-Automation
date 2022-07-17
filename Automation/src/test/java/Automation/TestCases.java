package Automation;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.Base;
import org.openqa.selenium.JavascriptExecutor;
import pageObject.CheckoutPage;
import pageObject.LandingPage;
import pageObject.ProductAddedPage;
import pageObject.SearchResultPage;


public class TestCases extends Base{
	
	 public WebDriverWait wait;
	 public static Logger log =LogManager.getLogger(TestCases.class.getName());
	 public WebDriver driver;
	
	@BeforeMethod
	public void initialize() throws IOException
	{
	     driver =initializeDriver();
	     log.info("Driver is initialize");
	     
	     driver.get(prop.getProperty("url"));
	     driver.manage().window().maximize();
	     log.info("Navigating to Home Page");
	     
		 }
	
	
	@Test (description = "Verify Invalid Login")
	public void Case1_InvalidLogin() throws IOException, InterruptedException {
		
		LandingPage lp = new LandingPage(driver);
		wait =  new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(lp.cookiespopUp).click();
		driver.findElement(lp.popUpClose).click();
		lp.getLoginbutton().click();
		
		log.info("Login popup appeared");
		
		driver.switchTo().frame("loginIframe");
		wait.until(ExpectedConditions.visibilityOfElementLocated(lp.userName));
		lp.getUsername().sendKeys(prop.getProperty("username"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(lp.password));
		lp.getPassword().sendKeys(prop.getProperty("wrongpassword"));
		lp.getLoginbutton_popUp().click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(lp.errorMsg));
		
		log.info("Error msg displyed successfully");
		
	}
	
	@Test (description = "Verify Valid Login")
	public void Case2_ValidLogin() throws IOException, InterruptedException {
		
		
		LandingPage lp = new LandingPage(driver);
		wait =  new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(lp.cookiespopUp).click();
		driver.findElement(lp.popUpClose).click();
		lp.getLoginbutton().click();
		log.info("Login popup appeared");
		driver.switchTo().frame("loginIframe");
		wait.until(ExpectedConditions.visibilityOfElementLocated(lp.userName));
		lp.getUsername().sendKeys(prop.getProperty("username"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(lp.password));
		lp.getPassword().sendKeys(prop.getProperty("correctpassword"));
		lp.getLoginbutton_popUp().click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(lp.logout));
		log.info("User is able to login successfully");	
		
	}
	
	@Test(description = "Verify Home Page Left Hand Side Menu Options")
	public void Case3_VerifyLeftHandSideMenu() throws IOException, InterruptedException {

		LandingPage lp = new LandingPage(driver);
		wait =  new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(lp.cookiespopUp).click();
		driver.findElement(lp.popUpClose).click();
		List<String> actualLeftMenuValue =  lp.getMenuItems();
		Assert.assertEquals(actualLeftMenuValue, lp.leftMenuExpectedValue);
	
		
	}
	
	@Test (description = "Verify Product is Successfully added in the Cart")
	public void Case4_addSearchItemInCart() throws IOException, InterruptedException {

		LandingPage lp = new LandingPage(driver);
		wait =  new WebDriverWait(driver, Duration.ofSeconds(40));
		driver.findElement(lp.cookiespopUp).click();
		driver.findElement(lp.popUpClose).click();
		driver.findElement(lp.searchBox).sendKeys("iphone 12");
		Thread.sleep(10);
		driver.findElement(lp.searchButton).click();
		Thread.sleep(10);
		SearchResultPage sp = new SearchResultPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", sp.getBuyButton());
		wait.until(ExpectedConditions.visibilityOfElementLocated(sp.buyButton));
		driver.findElement(sp.buyButton).click();
		
		ProductAddedPage pa = new ProductAddedPage(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(pa.processCheckoutButton));
		
		log.info("Product has been addedd successfully");
		driver.findElement(pa.processCheckoutButton).click();
		String basketPrice = driver.findElement(pa.basketAmount).getText().replaceAll("[^\\d.]", "");
		Assert.assertTrue(Integer.parseInt(basketPrice)>0);
		CheckoutPage cp = new CheckoutPage(driver);
		String ProdDes = cp.returnAddedProductDescription();
		System.out.println("Product "+ ProdDes + "has been successfully added into the Cart");
		System.out.println("Product Added Price is " + basketPrice +" kc");
		
		
	}
	
	@AfterMethod
	public void teardown()
	{
		
//		driver.close();
	}
}
