package mediamathtesting;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class BlogPage extends BasePage {

	public BlogPage(WebDriver driver) {
		super(driver);
	}
	
	private static final String blogPageURL = "http://devblog.mediamath.com/";
	private static final String postsContainer = "#content .post";
	private static final String datesLocator = ".date";
	private static final String searchFieldLocator = "s";
	private static final String submitSearchLocator = "searchsubmit";
	private static final String postsLocator = ".post>h3>a";
	private static final String readMoreLocator = ".more";
	private static final String postTitleLocator = ".wysiwyg>h3";
	
	public void navigateToBlogPage() {
		driver.navigate().to(blogPageURL);
		waitForBlogPageToLoad();
	}
	
	private void waitForBlogPageToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(postsContainer)));
	}
	
	public boolean verifyBlogSorting() throws ParseException {
		List<WebElement> dates = driver.findElements(By.cssSelector(datesLocator));
		List<String> allDates = new ArrayList<>();
		for(WebElement date : dates) {
			allDates.add(date.getText().replaceAll("//", "").trim());
		}
		
		return isReverseSorted(allDates);
	}
	
	public int verifySearch() {
		String searchQuery = "web components";
		searchForText(searchQuery);
		return getResultsCount();
	}
	
	public String getPostTitleInSearchResults(int index) {
		String title = null;
		if(index < 0) {
			Assert.fail("invalid index supplied");
		} else {
			title = driver.findElements(By.cssSelector(postsLocator)).get(index).getText().trim();
		}
		return title;
	}
	
	public String getPostTitleOfClickedResult() {
		return driver.findElement(By.cssSelector(postTitleLocator)).getText().trim();
	}
	
	/**
	 * clicks on a particular read more link for a post
	 * @param index - 0 based
	 */
	public void clickReadMoreLink(int index) {
		if(index < 0) {
			Assert.fail("wrong index supplied");
		} else {
			driver.findElements(By.cssSelector(readMoreLocator)).get(index).click();
		}
	}
	
	private void searchForText(String text) {
		WebElement searchField = driver.findElement(By.id(searchFieldLocator));
		searchField.clear();
		searchField.click();
		searchField.sendKeys(text);
		driver.findElement(By.id(submitSearchLocator)).click();
	}
	
	private int getResultsCount() {
		return driver.findElements(By.cssSelector(postsLocator)).size();
	}
	
	private boolean isReverseSorted(List<String> dates) {
		boolean isReverseSorted = false;
		for(int i =1; i<dates.size(); i++) {
			if (dates.get(i-1).compareTo(dates.get(i)) > 0) {
				isReverseSorted = true;
			} 
		}
		return isReverseSorted;
	}

}
