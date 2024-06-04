package Absorb.absorbTest;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import suport.retryAnalyzer;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class testCases {

	static WebDriver driver;
	SoftAssert softAssertion = new SoftAssert();

	// Before test will always execute before running any test
	@BeforeTest
	// This will setup the browser driver and properties of browser where test will
	// be running
	public void setup() {

		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "/driver/chromedriver.exe");
		driver = new ChromeDriver();
		// maximize with browser window
		driver.manage().window().maximize();
		// navigate to absorblms website
		driver.get("https://www.absorblms.com/");
	}

	@Test(priority = 0, retryAnalyzer = retryAnalyzer.class)
	public void navigate_to_careers() {

		// find xpath for career option on header
		WebElement companyOptionFromHeader = driver.findElement(By.xpath(
				"//*[@id='block-mainnavigationforabsorblmscom']/ul/li[6]/span[@class='nav-link--container is-closed']/a[contains(text(),'Company')]"));

		// verify the element is present on the screen
		assertTrue(companyOptionFromHeader.isDisplayed(), "Can not see Company option");

		// create an object of class Action and hover over company option from header
		Actions action = new Actions(driver);
		action.moveToElement(companyOptionFromHeader).build().perform();

		// find element for the sublist of all the options under company option
		WebElement companyOptionSublistFromHeader = driver
				.findElement(By.xpath("//*[@id=\"block-mainnavigationforabsorblmscom\"]/ul/li[6]/ul"));

		// verify sub-menu is displayed after hovering
		softAssertion.assertTrue(driver
				.findElement(By.xpath("//*[@id=\"block-mainnavigationforabsorblmscom\"]/ul/li[6]/ul")).isDisplayed());

		// Get the sub elements of the ul
		List<WebElement> links = companyOptionSublistFromHeader.findElements(By.tagName("li"));

		// go through the list & log each item
		for (int i = 1; i < links.size(); i++) {
			System.out.println(links.get(i).getText() + System.lineSeparator());
		}
		// locate career option under company and click on it
		WebElement careerOptionFromHeader = driver.findElement(
				By.xpath("//*[@id=\"block-mainnavigationforabsorblmscom\"]/ul/li[6]/ul/li/ul[2]/li[2]/span/a"));
		action.moveToElement(careerOptionFromHeader).click().build().perform();

		// verify user is redirected to Careers page
		softAssertion.assertEquals("Careers in eLearning - Career Opportunities | Absorb LMS Software",
				driver.getTitle());

	}

	@Test(priority = 1, retryAnalyzer = retryAnalyzer.class)
	public void find_secondlast_job_opening() {

		// find the current opening jobs element
		WebElement currentJobOpeningsTilte = driver
				.findElement(By.xpath("//*[@id='jobs']//span[contains(text(),'Current Job Openings at Absorb')]"));

		// scroll into the view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", currentJobOpeningsTilte);

		// switch to iframe "HRM Direct Career Site iFrame" to access job table
		driver.switchTo().frame("HRM Direct Career Site iFrame");

		// fine table element
		WebElement currentJobOpeningsTable = driver.findElement(By.xpath("//table[@class='reqResultTable']"));

		// take list of rows element
		List<WebElement> rowsList = currentJobOpeningsTable.findElements(By.tagName("tr"));

		// loop through each row and print the titles of the job
		for (int row = 1; row < rowsList.size(); row++) {

			// get list of all columns
			List<WebElement> Columns_row = rowsList.get(row).findElements(By.tagName("td"));

			// get the second last row
			if (row == rowsList.size() - 2) {

				// take the text for second last row
				String celtext = Columns_row.get(2).getText();
				System.out.println("Position of last second role is " + celtext);
				softAssertion.assertEquals("Manager, Small & Mid-Market Sales - Remote, Canada", celtext);
			}
		}
	}

	// Tear down will quit driver session
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
