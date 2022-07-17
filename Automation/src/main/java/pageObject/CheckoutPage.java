package pageObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

	
	public WebDriver driver;
	
	public By productDescription = By.xpath("//a[@class='mainItem']");
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String returnAddedProductDescription() {
		List<WebElement> des = driver.findElements(productDescription);
		String description = null;
		for(WebElement value : des) {
			 description = value.getText() + System.lineSeparator();
		}
		return description;
	}
	
	

	
}
