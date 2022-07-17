package pageObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductAddedPage {

	
	public WebDriver driver;
	
	public By productAdded = By.xpath("//a[contains(text(),'Product Added to Cart')]");
	public By basketAmount = By.xpath("//*[@id='price']");
	public By processCheckoutButton = By.xpath("//*[@id='varBToBasketButton']");
	
	
	public ProductAddedPage(WebDriver driver) {
		this.driver = driver;
	}

	
	

	
}
