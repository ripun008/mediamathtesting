package mediamathtesting;

import org.openqa.selenium.WebDriver;

public class BasePage {
	
	public static final int DEFAULT_TIMEOUT = 15; // in seconds
	
	public WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getPageURL() {
		return driver.getCurrentUrl();
	}

}
