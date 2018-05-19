package com.demo.nicolas.mori.example_calculator_project;

import java.net.URL;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.demo.nicolas.mori.page.object.androidnative.HomePage;
import com.demo.nicolas.mori.page.object.mobile.LoginPage;
import com.demo.nicolas.mori.page.object.mobile.ToolBar;
import com.demo.nicolas.mori.page.object.web.MessagePage;
import com.demo.nicolas.mori.page.object.web.WG_DashboardPage;
import com.demo.nicolas.mori.page.object.web.WG_ResultsDetailPage;
import com.demo.nicolas.mori.page.object.web.WG_ResultsPage;
import com.demo.nicolas.mori.steps.StepsHelper;
import com.demo.nicolas.mori.util.SelectorBrowser;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * This Class is created for make a helper for all the pages, and all the parts of the a native app,
 * or for execute all the drivers. And for create all the objects of pages, or objects created in
 * all the pages.
 * 
 */

public class ParentScenario extends StepsHelper {

	protected static WebDriver driver;

	protected ToolBar toolBar;
	protected LoginPage loginPage;
	protected HomePage homePage;

	protected static WG_ResultsPage wg_ResultsPage;
	protected static WG_ResultsDetailPage wg_ResultsDetailPage;
	protected static WG_DashboardPage wg_DashboardPage;
	protected static MessagePage messagePage;

	protected static HomePage homePageNativeWG;

	/**
	 * this method create the object driver for Android.
	 * 
	 * @param uuid
	 *            of the device to use.
	 */
	public void startAndroid(String uuid, String appPackage) {

		DesiredCapabilities cap = new DesiredCapabilities();
		// cap.setCapability(MobileCapabilityType.DEVICE_NAME, uuid);
		cap.setCapability(MobileCapabilityType.APP_PACKAGE, appPackage);
		// cap.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.wggesucht.android.WG_Gesucht");
		// cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
		cap.setCapability(MobileCapabilityType.APP,
				"/Users/nico/Documents/github/example_demo_nicolasmori/wggesucht-android.apk");

		//
		cap.setCapability("noReset", true);
		cap.setCapability("deviceName", "8575525242395141");
		// cap.setCapability("fullReset", true);
		// cap.setCapability("fastReset", true);
		cap.setCapability("appWaitActivity", "com.wggesucht.android.WG_Gesucht");

		try {
			driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		} catch (Exception e) {
			System.out.println("Exeption at the moment to generate the driver = " + e);
		}

		homePageNativeWG = new HomePage(driver);
		toolBar = new ToolBar(driver);
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);

	}

	public void startBrowser() {
		System.out.println("Starting driver for Browser Chrome");
		driver = SelectorBrowser.createDriverWithoutCapabilities("chrome", driver);

		wg_ResultsPage = new WG_ResultsPage(driver);
		wg_ResultsDetailPage = new WG_ResultsDetailPage(driver);
		wg_DashboardPage = new WG_DashboardPage(driver);
		messagePage = new MessagePage(driver);

	}

	public void startBrowserSpecial(boolean mobile) {
		System.out.println("Starting driver for Browser Chrome Special");

		if (mobile) {
			driver = SelectorBrowser.createDriverWithoutCapabilities("chromeEmulationMobile", driver);
		} else {
			driver = SelectorBrowser.createDriverWithoutCapabilities("Chrome", driver);
		}

	}

	protected void navigateTo(String url) {

		System.out.println("gggggggggggggggg");

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		driver.close();
		driver.switchTo().window(tabs2.get(0));

		System.out.println("hhhhhhhhhhhhhh");

		// try {
		// Thread.sleep(5000);
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		driver.navigate().to(url);

		// try {
		// Thread.sleep(5000);
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		System.out.println("bbbbbbbbbbbbbbb");
	}

	public void closeDriver() {
		driver.quit();
	}

}
