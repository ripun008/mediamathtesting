package mediamathtesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeveloperPage extends BasePage {

	public DeveloperPage(WebDriver driver) {
		super(driver);
	}
	
	private static final String developerPageContentsLocator = "#content";
	private static final String blogLocator = "#local .first>a";
	
	public void waitForDeveloperPageToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(developerPageContentsLocator)));
	}
	
	public void clickBlog() {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
		WebElement blogLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(blogLocator)));
		blogLink.click();
	}

}
