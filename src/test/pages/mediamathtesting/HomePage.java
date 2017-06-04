package mediamathtesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	private static final String MEDIAMATH_URL = "http://www.mediamath.com";
	private static final String middleBlockLocator = ".middle-block";
	private static final String menuButtonLocator = ".menu-btn";
	private static final String linksLocator = "ul.sublinks>li>a";
	
	
	
	/**
	 * navigate to media math home page. 
	 * waits for page to load.
	 */
	public void navigateToMediaMath() {
		driver.navigate().to(MEDIAMATH_URL);
		waitForMiddleBlockToLoad();
	}
	
	private void waitForMiddleBlockToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(middleBlockLocator))); 
	}
	
	public void clickMenuButton() {
		driver.findElement(By.cssSelector(menuButtonLocator)).click();
	}
	
	public void clickDeveloperOption() {
		WebElement developerBlogLink = driver.findElements(By.cssSelector(linksLocator)).get(5);
		developerBlogLink.click();
	}

}
