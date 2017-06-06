package com.mediamath.mediamathtesting;

import org.testng.annotations.Test;

import junit.framework.Assert;
import mediamathtesting.BlogPage;
import mediamathtesting.DeveloperPage;
import mediamathtesting.HomePage;

import org.testng.annotations.BeforeClass;

import java.text.ParseException;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class MediaMathTest {
	WebDriver driver;
	private HomePage homePage;
	private DeveloperPage developerPage;
	private BlogPage blogPage;
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// maximize Chrome window
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
  
	@Test
	public void testMediaMathNavigation() {
		homePage = new HomePage(driver);
		developerPage = new DeveloperPage(driver);
		blogPage = new BlogPage(driver);
		
		homePage.navigateToMediaMath();
		homePage.clickMenuButton();
		homePage.clickDeveloperOption();
		
		// switch to a new tab		
		Set<String> tabHandles = driver.getWindowHandles();
	    int numberOfTabs = tabHandles.size();
	    int newTabIndex = numberOfTabs-1;
	    driver.switchTo().window(tabHandles.toArray()[newTabIndex].toString());
		
	    // verify user is on developer page
	    developerPage.waitForDeveloperPageToLoad();
		String developerPageURL = developerPage.getPageURL();
		Assert.assertTrue("not on developer page", developerPageURL.contains("developer.mediamath"));

		// click blog link
		developerPage.clickBlog();
		
		// switch to a new tab
		tabHandles = driver.getWindowHandles();
	    numberOfTabs = tabHandles.size();
	    newTabIndex = numberOfTabs-1;
	    driver.switchTo().window(tabHandles.toArray()[newTabIndex].toString());
	    
	    // verify user is on blog page
	    String blogPageURL = blogPage.getPageURL();
		Assert.assertTrue("not on blog page", blogPageURL.contains("devblog.mediamath"));
	}
	
	@Test
	public void testDevBlog() throws ParseException {
		blogPage = new BlogPage(driver);
		
		blogPage.navigateToBlogPage();
		// verify posts are sorted by latest date
		Assert.assertTrue("blog is not sorted by date", blogPage.verifyBlogSorting());
		
		// verify at least one result is returned for a valid search query
		Assert.assertTrue("no results showed up in search", blogPage.verifySearch() >=1);
		
		// click on the second read more link and verify it opens the desired blog
		String expectedPostTitle = blogPage.getPostTitleInSearchResults(1); 
		blogPage.clickReadMoreLink(1);
		String actualPostTitle = blogPage.getPostTitleOfClickedResult();
		Assert.assertEquals("wrong blog opened", expectedPostTitle, actualPostTitle);
	}

}
