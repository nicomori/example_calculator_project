package com.demo.nicolas.mori.example_calculator_project;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.nicolas.mori.objects.ApartmentsDetails;

import io.appium.java_client.android.AndroidDriver;

/**
 * This Class is an abstract class, and created for make the interaction with the class ParentPage.
 */
public abstract class DSL {

	public WebDriver driver;

	private static final SimpleDateFormat horaActual = new SimpleDateFormat("HH");
	private static final SimpleDateFormat minutosActual = new SimpleDateFormat("mm");
	private static final SimpleDateFormat segundosActual = new SimpleDateFormat("ss");
	private static final SimpleDateFormat dayActual = new SimpleDateFormat("dd");
	private static final SimpleDateFormat monthActual = new SimpleDateFormat("MM");
	private static final SimpleDateFormat yearActual = new SimpleDateFormat("yy");

	public DSL(WebDriver driver2) {
		this.driver = driver2;
	}

	// ############### GENERIC METHODS SECTIONS ######################
	/**
	 * This method make a generic click in a elements with some text.
	 * 
	 * @param String
	 *            with some exclusive Class
	 * @param String
	 *            with the text of the element
	 */
	protected boolean genericVerifyIfAnExclusiveClassAppearWithAnText(String eclusiveClass, String stringInTheElement) {
		System.out.println("Starting to verify is some text is displayed with this exclusive class: \"" + eclusiveClass
				+ "\"" + ", and if contains this text: \"" + stringInTheElement + "\"");
		try {
			driver.findElement(By.xpath(
					String.format("//*[@class='%s']//*[contains(text(),'%s')]", eclusiveClass, stringInTheElement)))
					.isDisplayed();
			return true;
		} catch (Exception e) {
			driver.findElement(By.xpath(
					String.format("//*[@class='%s']//*[contains(text(),'%s')]", eclusiveClass, stringInTheElement)))
					.isDisplayed();
			return true;
		}
	}
	
	/**
	 * this method make a mouse over from a web element to other locator, and make
	 * click in the second element.
	 *
	 * @param first web element locator
	 * @param second web element locator
	 */
	protected void mouseOverAndClick(By firstLocator, By secondLocator) {
		waitForAnExplicitElement(firstLocator);
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(firstLocator);
		action.moveToElement(we).moveToElement(driver.findElement(secondLocator)).click().build().perform();
	}

	/**
	 * This method make a generic click in a button element with some text.
	 * 
	 * @param String
	 *            with the text of the element
	 */
	protected void genericClickByButtonContainsSomeText(String stringInTheElement) {
		System.out.println("Making a generic click in a web element with the text \"" + stringInTheElement + "\"");
		driver.findElement(By.xpath(String.format("(//button[contains(text(),'%s')])[1]", stringInTheElement))).click();
	}

	/**
	 * This method make a generic click in a elements with some text.
	 * 
	 * @param String
	 *            with some exclusive Class
	 * @param List
	 *            of String with the text of the element
	 */
	protected boolean genericVerifyIfAnExclusiveClassAppearWithAListOfText(String eclusiveClass,
			List<List<String>> listOfStringsToVerify) {
		for (List<String> dataTableToVerify : listOfStringsToVerify) {
			System.out.println("Verifing: " + dataTableToVerify.iterator().next());
			if (!genericVerifyIfAnExclusiveClassAppearWithAnText(eclusiveClass, dataTableToVerify.iterator().next())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method make a generic click in a elements with some text.
	 * 
	 * @param String
	 *            with the text of the element
	 */
	protected void genericValidatorOfLinksText2ndGeneration(String originalUrl) {
		System.out.println("Starting to validate the Generic links of 2nd generation");
		ArrayList<String> listadoDeIndicesDeployedTrue = new ArrayList<String>();

		accessToAURL(originalUrl);
		for (int i = 0; i < driver.findElements(By.xpath("//*[@href and text()[string-length() > 0]]")).size(); i++) {
			if (verifyIfisDisplayedX2(By.xpath("(//*[@href and text()[string-length() > 0]])[" + i + "]"))) {
				if (getTextByLocator(By.xpath("(//*[@href and text()[string-length() > 0]])[" + i + "]"))
						.length() > 1) {
					listadoDeIndicesDeployedTrue.add(i + "");
				}
			}
		}

		for (int i = 0; i < listadoDeIndicesDeployedTrue.size(); i++) {
			System.out.println("Strting to make click in the link number: " + i);
			clickJSx2(By.xpath(
					"(//*[@href and text()[string-length() > 0]])[" + listadoDeIndicesDeployedTrue.get(i) + "]"));
			System.out.println("Starting to verify if all is working after make the click");
			System.out.println("Starting to go, to the original url with this url: " + originalUrl);
			accessToAURL(originalUrl);

		}

	}

	/**
	 * This method make a generic click in a elements with some text.
	 * 
	 * @param String
	 *            with the text of the element
	 */
	protected void genericClickByContainsSomeText(String stringInTheElement) {
		System.out.println("Making a generic click in a web element with the text \"" + stringInTheElement + "\"");
		driver.findElement(By.xpath(String.format("//*[contains(text(),'%s')]", stringInTheElement))).click();
	}

	/**
	 * This method make a generic click in a elements with some text, and some specific index.
	 * 
	 * @param String
	 *            with the text of the element
	 * @param Int
	 *            with the index of the element
	 */
	protected void genericClickByContainsSomeTextAndWithSomeSpecificIndex(String stringInTheElement, String index) {
		System.out.println("Making a generic click in a web element with the text \"" + stringInTheElement + "\"");
		clickJSx2(By.xpath(String.format("(//*[contains(text(),'%s')])['%s']", stringInTheElement, index)));
	}

	/**
	 * This method make a generic click with a text.
	 * 
	 * @param String
	 *            with the text of the element
	 */
	protected void genericClickByText(String stringInTheElement) {
		System.out.println("Making a generic click in a web element with the text \"" + stringInTheElement + "\"");
		driver.findElement(By.xpath(String.format("(//*[contains(text(),'%s')])[1]", stringInTheElement))).click();
	}

	/**
	 * This method try to find if some text element is displayed in the web
	 * 
	 * @param String
	 *            with the text of the element to find
	 * @return boolean condition
	 */
	protected boolean genericVerifyIfSomeTextExist(String stringToVerify) {
		System.out.println("Starting to verify if this text is displayed \"" + stringToVerify + "\"");
		try {
			driver.findElement(By.xpath(String.format("//*[contains(text(),'%s')]", stringToVerify))).isDisplayed();
			return true;
		} catch (Exception e) {
			driver.findElement(By.xpath(String.format("//*[contains(text(),'%s')]", stringToVerify))).isDisplayed();
			return true;
		}
	}

	/**
	 * This method try to find if some text element appear in the url
	 * 
	 * @param String
	 *            with the text of the element to find in the URL
	 * @return boolean condition
	 */
	protected boolean genericVerifyIfSomeTextAppearInTheURL(String stringToVerify) {
		System.out.println("Starting to verify if this text: \"" + stringToVerify
				+ "\" appear in the URL, with this content: " + driver.getCurrentUrl());
		if (driver.getCurrentUrl().contains(stringToVerify)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method try to find if some text element appear in the title
	 * 
	 * @param String
	 *            with the text of the element to find in the TITLE
	 * @return boolean condition
	 */
	protected boolean genericVerifyIfSomeTextAppearInTheTitle(String stringToVerify) {
		System.out.println("Starting to verify if this text: \"" + stringToVerify
				+ "\" appear in the TITLE, with this content: " + driver.getTitle());
		if (driver.getTitle().contains(stringToVerify)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method verify if some element appear correctly
	 * 
	 * @param element
	 *            By with the locator to find.
	 * @return boolean condition
	 */
	protected boolean genericVerifyIfSomeElementExist(By locator) {
		System.out.println("Starting to verify if some element appear correctly");
		try {
			driver.findElement(locator).isDisplayed();
			return true;
		} catch (Exception e) {
			driver.findElement(locator).isDisplayed();
			return true;
		}
	}

	/**
	 * This method try to find some text in all the Alternative tags of the web
	 * 
	 * @param String
	 *            with the text of the element to find
	 * @return boolean condition
	 */
	protected boolean genericVerifyIfSomeTextExistInsideOfTheAlternativeTag(String stringToVerify) {
		System.out.println("Starting to verify if this text \"" + stringToVerify + "\" appear in some Alternative tag");
		try {
			driver.findElement(By.xpath(String.format("//*[contains(@alt,'%s')]", stringToVerify))).isDisplayed();
			return true;
		} catch (Exception e) {
			driver.findElement(By.xpath(String.format("//*[contains(@alt,'%s')]", stringToVerify))).isDisplayed();
			return true;
		}
	}

	/**
	 * This method try to find if some text element is displayed in some locator
	 * 
	 * @param String
	 *            with the text of the element to find
	 * @param locator
	 * @return boolean condition
	 */
	protected boolean genericVerifyIfSomeTextExistInSomeLocator(By locator, String stringToVerify) {
		System.out.println("Starting to verify if this text is displayed \"" + stringToVerify + "\" in some locator");
		if (driver.findElement(locator).getText().contains(stringToVerify)) {
			return true;
		} else {
			return false;
		}
	}

	// ############### STANDART SECTIONS ######################

//	/**
//	 * This method make click!
//	 * 
//	 * @param locator
//	 */
//	protected void verifyIfAElement(By locator) {
//		System.out.println("Making click in a web element");
//		driver.findElement(locator).click();
//	}
	
	
	/**
	 * This method make click!
	 * 
	 * @param locator
	 */
	protected void click(By locator) {
		System.out.println("Making click in a web element");
		driver.findElement(locator).click();
	}

	/**
	 * This method make a double click
	 * 
	 * @param locator
	 */
	protected void clickX2(By locator) {
		System.out.println("Starting to make double click");
		try {
			driver.findElement(locator).click();
		} catch (Exception e) {
			System.out.println(e);
			driver.findElement(locator).click();
		}
	}

	/**
	 * this method retrive the attribute of some web element.
	 * 
	 * @param locator
	 *            web element to find
	 * @param string
	 *            with the attribute to find in the web element
	 * 
	 * @return String with the string content in the attribute.
	 */
	protected String getAttributeFromLocator(By locator, String attribute) {
		System.out.println("Starting to find this attribute: " + attribute);
		try {
			String contentOfAttribute = driver.findElement(locator).getAttribute(attribute);
			return contentOfAttribute;
		} catch (Exception e) {
			System.out.println("The WebElement dont have this attribute: "+attribute+", we return null");
			return null;
		}
	}
	
	
	
	

	/**
	 * This method make click!
	 * 
	 */
	protected void clickBrowserButtonBack() {
		System.out.println("Making click in browser button back");
		driver.navigate().back();
	}

	/**
	 * this method make clear to some locator with some content
	 * 
	 * @param locator
	 *            to make clear
	 */
	protected void clearField(By locator) {
		System.out.println("Starting to clear a field.");
		driver.findElement(locator).clear();
	}

	/**
	 * this method verify if some locator is enable.
	 * 
	 * @param locator
	 * @return boolean if some locator is enable
	 */
	protected boolean isEnable(By locator) {
		System.out.println("Starting to verify if some web element is enable.");
		return driver.findElement(locator).isEnabled();
	}

	/**
	 * this method make a javascript click
	 *
	 * @param locator
	 */
	protected void clickJS(By locator) {
		System.out.println("Starting to make a javascript click");
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	/**
	 * this method make a double javascript click
	 * 
	 * @param locator
	 */
	protected void clickJSx2(By locator) {
		System.out.println("Starting to make a javascript click X2");

		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		try {
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			executor.executeScript("arguments[0].click();", element);
		}
	}

	/**
	 * this method send Keys to a web element.
	 * 
	 * @param locator
	 * @param string
	 *            to send
	 */
	protected void sendKeysToLocator(By locator, Keys keysToSend) {
		System.out.println("Starting to send some Keys to a webelement");
		driver.findElement(locator).sendKeys(keysToSend);
	}

	/**
	 * this method send string to a web element.
	 * 
	 * @param locator
	 * @param string
	 *            to send
	 */
	protected void sendStringToLocator(By locator, String stringToSend) {
		System.out.println("Starting to send this String " + stringToSend + " to a webelement");
		driver.findElement(locator).sendKeys(stringToSend);
	}

	/**
	 * This method select options from a list.
	 * 
	 * @param locator,
	 *            string to send
	 */
	protected void selectOptionByString(By locator, String identifier) {
		Select dropdown = new Select(driver.findElement(locator));
		dropdown.selectByVisibleText(identifier);
	}

	/**
	 * This method navigate back
	 */
	protected void buttonBackDevice() {
		driver.navigate().back();
	}

	/**
	 * this method verify if a method is displayed per 2.
	 * 
	 * @return boolean
	 */
	protected boolean verifyIfisDisplayedX2(By locator) {
		System.out.println("Starting to verify if a webelement is displayed");
		try {
			driver.findElement(locator).isDisplayed();
			return true;
		} catch (Exception e) {
			try {
				driver.findElement(locator).isDisplayed();
				return true;
			} catch (Exception f) {
				return false;
			}
		}
	}

	/**
	 * this method verify if a method is displayed.
	 * 
	 * @return boolean
	 */
	protected boolean verifyIfisDisplayed(By locator) {
		System.out.println("Starting to verify if a webelement is displayed");
		try {
			driver.findElement(locator).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * this method verify a string contain inside the string "0.00"
	 * 
	 * @return boolean
	 */
	protected boolean verifyIfSomeStringContainsZeroDotZeroZeroValueInside(String valueToVerify) {
		System.out.println("Starting to verify if this String: " + valueToVerify + " ,contain inside the value 0.00");
		if (valueToVerify.contains("0.00")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * this method return the string valur from some locator
	 * 
	 * @param locator
	 * @return string with the gettext content
	 */
	protected String getTextByLocator(By locator) {
		System.out.println("Starting to get the element text inside of some locator.");
		return driver.findElement(locator).getText();
	}

	/**
	 * This method verify the status of a jenkins job
	 * 
	 * @param int
	 *            with the index of the task in the jenkins menu.
	 * @return String with status of the bola
	 */
	protected String verifyBolaStatusIsFailed(int index) {
		System.out.println("Starting to verify the status of a task in jenkins.");
		return driver.findElement(By.xpath("(//tr[contains(@id,'job_')]//td[1][" + "contains(@data,'0') or " // fail
				+ "contains(@data,'4') or " // pass
				+ "contains(@data,'5') or " // executing pass
				+ "contains(@data,'1') or " // executing fail
				+ "contains(@data,'10') or " + "contains(@data,'8')" // disable
				+ "])[" + index + "]")).getAttribute("data");
	}

	/**
	 * this method return the name of a taskin the jenkins menu.
	 * 
	 * @param int
	 *            with the index of the task
	 * @return String with the name of the task.
	 */
	protected String getTextNameOfTheTask(int index) {
		System.out.println("Starting to get the name of the task of jenkins with the index " + index);
		return driver.findElement(By.xpath("(//tr//td[3]//a[1])[" + index + "]")).getText();
	}

	/**
	 * this method refresh the browser
	 */
	protected void refreshBrowser() {
		System.out.println("Starting to refresh the browser");
		driver.navigate().refresh();
	}

	/**
	 * this method retrive the time average of a task in jenkins.
	 * 
	 * @return String with the average of time of a task.
	 */
	protected String getAverageTimeLastExecution(int index) {
		System.out.println("Starting to get the average time of the task with the index " + index);
		return driver.findElement(By.xpath("(//tr//td[6])[" + index + "]")).getAttribute("data");
	}

	/**
	 * This method make click in the scheduler of a task.
	 * 
	 * @param task
	 *            name
	 */
	protected void pressClickInTheSchedulerOfTheTask(String task) {
		System.out.println("Waiting for the element with the name " + task + " appear");
		@SuppressWarnings("unused")
		WebElement myDynamicElement = (new WebDriverWait(driver, 40)).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(@href,'" + task + "')]//img[contains(@alt,'Schedule')]")));
		System.out.println("Making click in the element " + task);
		driver.findElement(By.xpath("//*[contains(@href,'" + task + "')]//img[contains(@alt,'Schedule')]")).click();
	}

	/**
	 * this method wait for a exclusive elemente deployed
	 * 
	 * @param locator
	 */
	protected void waitForAnExplicitElement(By locator) {
		System.out.println("Starting to wait for a exclusive element");
		@SuppressWarnings("unused")
		WebElement myDynamicElement = (new WebDriverWait(driver, 20))
				.until(ExpectedConditions.presenceOfElementLocated(locator));
		waitSleepingTheTread(200);
	}

	/**
	 * this method wait a long time for a exclusive elemente deployed
	 * 
	 * @param locator
	 */
	protected void waitLongForAnExplicitElement(By locator) {
		System.out.println("Starting to wait for a exclusive element");
		@SuppressWarnings("unused")
		WebElement myDynamicElement = (new WebDriverWait(driver, 20))
				.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * This method count the elements in a list of web elements.
	 *
	 * @param locator
	 * @return int
	 */
	protected Integer countElements(By locator) {
		System.out.println("Starting to verify the numbers of elements");
		int count = driver.findElements(locator).size();
		return count;
	}

	/**
	 * this method make a tread sleep
	 * 
	 * @param int
	 *            with miliseconds to sleep.
	 */
	protected void waitSleepingTheTread(int milisecondsToSleep) {
		try {
			System.out.println("Starting to sleep the thread " + milisecondsToSleep);
			Thread.sleep(milisecondsToSleep);
		} catch (Exception e) {
			System.out.println("Appear a problem at the moment to wait we can see this error:");
			System.out.println(e);
		}
	}

	/**
	 * this method will give back the first item of a dropdown menu
	 *
	 * @param locator
	 * @return string
	 */
	protected String getFirstDropDownItem(By locator) {
		System.out.println("Starting to verify the first item in the dropdown menu");
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		WebElement selectedOption = select.getFirstSelectedOption();
		String firstElement = selectedOption.getText();
		return firstElement;
	}

	/**
	 * This method get the text in some locator
	 * 
	 * @param locator
	 */
	protected String getTextOfALocator(By locator) {
		System.out.println("Starting to get the text in some web element.");
		return driver.findElement(locator).getText();
	}

	/**
	 * This method store in a list the text content of some list of web element with objects
	 * ApartmentsDetails
	 * 
	 * @param locator
	 *            with a web element list.
	 * @return List of ApartmentsDetails
	 */
	protected List<ApartmentsDetails> storeDataInAList(String locator) {
		System.out.println("Starting to store in a list all the apartments details.");
		List<ApartmentsDetails> apartmentList = new ArrayList<ApartmentsDetails>();
		ApartmentsDetails apartamento = new ApartmentsDetails();

		for (int i = 0; i < driver.findElements(By.xpath(locator)).size(); i++) {
			apartamento.setTitle(driver.findElements(By.xpath(locator)).get(i).getText());
			apartmentList.add(apartamento);
		}
		return apartmentList;
	}

	/**
	 * this method return the current url.
	 * 
	 * @return string with the current url
	 */
	protected String getUrl() {
		System.out.println("Starting to get the current URL.");
		return driver.getCurrentUrl();
	}

	/**
	 * This method return the current hour.
	 * 
	 * @return String with the current hour.
	 */
	protected String getActualHour() {
		System.out.println("Starting to return the current hour");
		Date date = new Date();
		return horaActual.format(date);
	}

	/**
	 * This method return the current minutes.
	 * 
	 * @return String with current minutes.
	 */
	protected String getActualMinutes() {
		System.out.println("Starting to return the current ");
		Date date = new Date();
		return minutosActual.format(date);
	}

	/**
	 * This method return the current seconds.
	 * 
	 * @return String with the current seconds.
	 */
	protected String getActualSeconds() {
		System.out.println("Starting to return the current ");
		Date date = new Date();
		return segundosActual.format(date);
	}

	/**
	 * This method return the current day.
	 * 
	 * @return String with the current day.
	 */
	protected String getActualDay() {
		System.out.println("Starting to return the current day");
		Date date = new Date();
		return dayActual.format(date);
	}

	/**
	 * This method return the current month.
	 * 
	 * @return String with the current month.
	 */
	protected String getActualMonth() {
		System.out.println("Starting to return the current month.");
		Date date = new Date();
		return monthActual.format(date);
	}

	/**
	 * This method return the current year.
	 * 
	 * @return String with the current year.
	 */
	protected String getActualYear() {
		System.out.println("Starting to return the current year");
		Date date = new Date();
		return yearActual.format(date);
	}

	/**
	 * This method can make the download of a image.
	 * 
	 * @param locatorOfTheImage
	 * @param scenarioName
	 * @throws IOException
	 */
	protected void donwloadImage(By locatorOfTheImage, String scenarioName) throws IOException {
		System.out.println("Starting to get the data of the image");
		String s = driver.findElement(locatorOfTheImage).getAttribute("src");
		URL url = new URL(s);
		System.out.println("Starting to generate the buffer for the image");
		BufferedImage bufImgOne = ImageIO.read(url);
		System.out.println("Starting to write the image");
		ImageIO.write(bufImgOne, "png", new File("imagesFailedScenarios/" + scenarioName + ".png"));
		System.out.println("Image download completed for the scenario " + scenarioName);
	}

	/**
	 * this method go to some url
	 * 
	 * @param url
	 */
	protected void accessToAURL(String url) {
		System.out.println("Starting to go to this url: " + url);
		driver.get(url);
	}

	/**
	 * This method make scroll down.
	 * 
	 * @param locator
	 */
	protected void scrollDown() {
		System.out.println("make scroll down.");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,20000)", "");
		waitSleepingTheTread(1500);
	}
	
	/**
	 * This method make scroll up.
	 * 
	 * @param locator
	 */
	protected void scrollUp() {
		System.out.println("make scroll down.");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(5000,0)", "");
		waitSleepingTheTread(1500);
	}

	/**
	 * this method make scroll down until find a text displayed.
	 * 
	 * @param textToFind
	 *            this is the text to find and make click.
	 */
	protected void scrollDownUntilFindAText(String textToFind) {
		boolean flag = false;

		Dimension dimensions = driver.manage().window().getSize();

		Double screenHeightStart = dimensions.getHeight() * 0.4;
		Double screenHeightEnd = dimensions.getHeight() * 0.2;

		int scrollStart = screenHeightStart.intValue();
		System.out.println("s=" + scrollStart);

		int scrollEnd = screenHeightEnd.intValue();

		flag = false;

		while (flag == false) {

			try {
				if (driver.findElement(By.xpath("//*[contains(text(),'" + textToFind + "')]")).isDisplayed()) {
					flag = true;
				} else {
					System.out.println("make scroll down.");
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,20000)", "");
					waitSleepingTheTread(1500);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		// while (flag == false) {
		// try {
		// driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'" + textToFind +
		// "')]")).click();
		//
		// flag = true;
		// } catch (Exception e) {
		// ((AndroidDriver<?>) driver).swipe(0, scrollStart, 0, scrollEnd, 500);
		// }
		// }
	}

	/**
	 * This method make scroll down until the buttom of the website.
	 */
	protected void scrollDownToTheBottom() {
		System.out.println("make scroll down to the buttom.");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,20000)", "");
		waitSleepingTheTread(1500);
	}

	/**
	 * this method verify if some element contain a String inside.
	 * 
	 * @param locator
	 * @boolean true in case the locator contain the string in the parameter
	 */
	protected boolean verifyIfSomeLocatorContainsSomeString(By locator, String someStringToValidate) {
		System.out.println("verify if some element contains this string: " + someStringToValidate);
		if (driver.findElement(locator).getText().contains(someStringToValidate)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * this method make stop in the load of the web.
	 */
	protected void stopBrowserLoad() {
		System.out.println("Starting to make stop in the load of some page.");
		driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("return window.stop");
	}

	/**
	 * this method verify if some locator is displayed correctly
	 * 
	 * @param Locator
	 *            to verify if this is desplayed correctly
	 */
	protected boolean verifyIfAWebelementIsDeployed(By locator) {
		return driver.findElement(locator).isDisplayed();
	}

	/**
	 * this method move the focus to the second tab and after close this tab and return the focus in
	 * the first one.
	 */
	protected void tabCloseSecondTabAndSwitchToTheFirst() {
		System.out.println("Starting to move to the second tab and close");
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		driver.close();
		System.out.println("Second tab closed, and back to the first");
		driver.switchTo().window(tabs2.get(0));
	}

	/**
	 * this method get all the string content in some web elements and return a list.
	 *
	 * @param locator
	 *
	 * @return list with all the strings
	 */
	protected List<String> getNamesFromAListOfNamesAndReturnAList(By locator) {
		System.out.println("Starting to get all the names in the list.");
		List<String> listOfStrings = new ArrayList<String>();
		for (int i = 0; i < driver.findElements(locator).size(); i++) {
			listOfStrings.add(driver.findElements(locator).get(i).getText());
		}
		
		
		System.out.println("ddddddddddddddddd");
		for(String pepe:listOfStrings) {
			System.out.println(pepe);
		}
		System.out.println("ddddddddddddddddd");
		
		return listOfStrings;
	}
	
	/**
	 * this method wait for a exclusive elemente deployed
	 * 
	 * @param locator
	 */
	protected Map<String,String> integrate2ListInAHashMap(String keys,String values) {
		System.out.println("Starting to get all the elements of a list and return in a map");
		List<WebElement> listOfWebelementsKeys = driver.findElements(By.xpath(keys));
		List<WebElement> listOfWebelementsValues = driver.findElements(By.xpath(values));
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		for (int i = 0; i < listOfWebelementsKeys.size(); i++) {
			map.put(listOfWebelementsKeys.get(i).getText(), listOfWebelementsValues.get(i).getText());			
		}
		return map;
	}

	// ############### MOBILE SECTIONS ######################

	/**
	 * this method get the text in some locator with the attribute name
	 * 
	 * @return String
	 */
	protected String getTextInContentDescriptionByLocator(By locator) {
		return ((AndroidDriver<?>) driver).findElement(locator).getAttribute("name");

	}

	/**
	 * this method make scroll down in a mobile Native App until a text appear, and after all that
	 * make click in the text.
	 * 
	 * @param textToFind
	 *            this is the text to find and make click.
	 */
	protected void scrollDownUntilFindAStringAndMakeClick(String textToFind) {
		boolean flag = false;

		Dimension dimensions = driver.manage().window().getSize();

		Double screenHeightStart = dimensions.getHeight() * 0.4;
		Double screenHeightEnd = dimensions.getHeight() * 0.2;

		int scrollStart = screenHeightStart.intValue();
		System.out.println("s=" + scrollStart);

		int scrollEnd = screenHeightEnd.intValue();

		flag = false;

		while (flag == false) {
			try {
				driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'" + textToFind + "')]")).click();

				flag = true;
			} catch (Exception e) {
				((AndroidDriver<?>) driver).swipe(0, scrollStart, 0, scrollEnd, 500);
			}
		}
	}

	/**
	 * this method is for make a refresh in the mobile native apps.
	 * 
	 */
	protected void refreshSwipingToDown() {
		Dimension dimensions = driver.manage().window().getSize();

		Double screenHeightStart = dimensions.getHeight() * 0.2;
		Double screenHeightEnd = dimensions.getHeight() * 0.5;

		int scrollStart = screenHeightStart.intValue();
		int scrollEnd = screenHeightEnd.intValue();

		((AndroidDriver<?>) driver).swipe(0, scrollStart, 0, scrollEnd, 200);

	}

	/**
	 * this method is for make a refresh in the mobile native apps.
	 * 
	 */
	protected void scrollDownDevice() {
		System.out.println("Preparing the scroll down.");

		Dimension dimensions = driver.manage().window().getSize();

		Double screenHeightStart = dimensions.getHeight() * 0.6;
		Double screenHeightEnd = dimensions.getHeight() * 0.4;

		int scrollStart = screenHeightStart.intValue();

		int scrollEnd = screenHeightEnd.intValue();

		System.out.println("Starting to make the scroll down");
		((AndroidDriver<?>) driver).swipe(0, scrollStart, 0, scrollEnd, 500);
		System.out.println("Scroll down completed");
	}

	/**
	 * This method make press the button back in the mobile device.
	 */
	protected void buttonBackMobile() {
		System.out.println("make click in the button back in some mobile device.");
		((AndroidDriver<?>) driver).navigate().back();
	}

	// ############### ANNOTATION METHOD SECTIONS ######################

	/**
	 * The Annotation WebElementLocator return
	 * 
	 * @author nicolasmori
	 */
	@Retention(RetentionPolicy.RUNTIME)
	public @interface WebElementLocator {
		String androidElementType();

		String androidElementValue();

		String iosElementType();

		String iosElementValue();
	}

	private boolean flag = false;
	private int methodNumber = 0;
	private Method[] methods;
	Method method;

	protected By getLocator(String methodName) {

		methods = getClass().getMethods();
		method = methods[0];
		flag = false;
		System.out.println("\n");

		do {
			if (method.getName() == methodName) {
				flag = true;
			} else {
				methodNumber++;
				method = methods[methodNumber];
				System.out.println("Trying to find the Method Name: \"" + methodName + "\" \n change the method to "
						+ methodNumber + ". Current method finded: " + method.getName());
			}
		} while (flag == false);

		System.out.println("\n");

		WebElementLocator annotation = method.getAnnotation(WebElementLocator.class);

		if (System.getProperty("testingType").equals("android")) {
			System.out.println("returning element android");
			if (annotation.androidElementType().equals("id")) {
				return By.id(annotation.androidElementValue());
			}
			if (annotation.androidElementType().equals("xpath")) {
				return By.xpath(annotation.androidElementValue());
			}
		} else {
			System.out.println("returning elemenet IOS");
			if (annotation.iosElementType().equals("id")) {
				return By.id(annotation.iosElementValue());
			}
			if (annotation.iosElementType().equals("xpath")) {
				return By.xpath(annotation.iosElementValue());
			}
		}
		System.out.println("Returning null value at the moment to retrive the method: " + methodName);
		return null;
	}

}