# MediaMath testing
You need to have the Eclipse Maven plugin installed on your machine. Once installed, import the project to Eclipse and right click on pom.xml
file and select "Run as > Maven test" 
- If you are running the tests on Windows, then you need to change the path in MediaMathTest.java (L29) and you would also need to add the chromedriver.exe file to src/test/resources. The change would be like this:
System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\chromedriver.exe");
