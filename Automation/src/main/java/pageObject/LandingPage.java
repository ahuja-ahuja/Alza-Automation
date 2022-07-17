package pageObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

	
	public WebDriver driver;
	
	public By leftMenuItems = By.xpath("//*[@id='tpf']/li/a");
    public By logIn = By.xpath("//*[@id='lblLogin']");
	public By userName = By.xpath("//*[@id='userName']");
	public By password = By.xpath("//*[@id='password']");
	public By logIn_popup_button = By.xpath("//*[@id='btnLogin']");
	public By cookiespopUp = By.xpath("//*[contains(@class,'cookies-info-accept')]");
	public By popUpClose = By.xpath("(//*[@class=\"close\"])[2]");
	public By searchBox = By.xpath("//*[@id='edtSearch']");
	public By searchButton = By.xpath("//*[@id='btnSearch']");
	public By errorMsg = By.xpath("//*[text()='Invalid username or password']");
	public By logout = By.xpath("//*[@id='lblSignOut']");
	
	public List<String> leftMenuExpectedValue = Arrays.asList("Price Hits", "Computers and Laptops","Phones, Smartwatches, Tablets" , "Gaming and Entertainment","TV, Photo, Audio & Video","Major and Small Appliances","Household Supplies","Hobby and Garden","Beauty",
			"Toys, for Kids and Babies","Drugstore","Pet Supplies","Sport and Outdoors","Clothing, Shoes, Accessories","Car & Moto","Office Supplies and Stationery","Books","Food and Alcohol","Gift Vouchers","Health");

	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getUsername() {
		
		return driver.findElement(userName);
	}
	
	public WebElement getPassword() {
		
		return driver.findElement(password);
	}
	
	public WebElement getLoginbutton() {
		
		return driver.findElement(logIn);
	}
	
    public WebElement getLoginbutton_popUp() {
		
		return driver.findElement(logIn_popup_button);
	}
	
	public ArrayList<String> getMenuItems() {
		ArrayList<String> actualValue = new ArrayList<String>();
		List<WebElement> ls = driver.findElements(leftMenuItems);
		for(WebElement value : ls) {
			String text = value.getText();
			System.out.println(text);
			actualValue.add(text);
		}
		return actualValue;
	}

	
}
