package test.testPackage;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {


    RemoteWebDriver driver;
    ChromeOptions options;
    SoftAssert softAssert=new SoftAssert();
    Actions actions;
    boolean logoStatus;

    @BeforeMethod
    public void openBrowser(){

        options= new ChromeOptions();
        // options.enableBiDi().setPageLoadStrategy(PageLoadStrategy.NORMAL).addArguments("--headless", "start-maximized");
        options.enableBiDi().setPageLoadStrategy(PageLoadStrategy.NORMAL).addArguments("start-maximized");
        driver  =new ChromeDriver(options);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
