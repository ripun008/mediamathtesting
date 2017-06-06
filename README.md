# MediaMath testing
You need to have the Eclipse Maven plugin installed on your machine. Once installed, import the project to Eclipse and right click on pom.xml
file and select "Run as > Maven test" 
- I am assuming that you will run these tests on Chrome on a Mac :)
- If you are running the tests on Windows, then you need to change the path in MediaMathTest.java (L29) and you would also need to add the chromedriver.exe file to src/test/resources. The change would be like this:
System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\chromedriver.exe");

# Follow Up Questions

Bugs
- After clicking on any category or tag, there is no way to reset the clicked category or tag. 

Rolling out new Functionality
- It would be best to do it in small chunks i.e. have some feature work done on a branch and then it out there and fix any bugs that are found. Make sure that you have adequate test coverage for all the new features. Once all of this is done, then push those changes to your production environment. Keep on iterating over this until all the desired changes have been implemented and rolled out.
