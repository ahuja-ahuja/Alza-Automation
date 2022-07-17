package pageObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage {

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver driver;
	
	public By buyButton = By.xpath("(//*[@id='boxes']/div//*[text()='Buy'][1])");
	
	public WebElement getBuyButton() {
	
		return driver.findElement(buyButton);
	
	}
	


	
}
