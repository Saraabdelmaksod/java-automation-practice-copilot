package test.testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class FirstTest {

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

    @Test
    public void validateThatPageTitleIsCorrect(){

        driver.get("https://duckduckgo.com");
        //softAssert for continue the test even if the first assert fails
        softAssert.assertTrue(driver.getTitle().contains("Google"), "Title is not as expected");

    }

    @Test
    public void validateThatPageTitleIsCorrect2(){

        driver.get("https://duckduckgo.com");
        //softAssert for continue the test even if the first assert fails
        softAssert.assertEquals("Google", driver.getTitle(), "Title is not as expected");

    }

    @Test
    public void validateDuckLogoIsDisplayed(){
        driver.get("https://duckduckgo.com");
        logoStatus= driver.findElement(By.xpath("(//a[@title='Learn about DuckDuckGo']/img)[2]")).isDisplayed();
        Assert.assertTrue(logoStatus);
    }
    @Test
    public void validateFirstLinkIsSeleniumDocumentation(){

        driver.get("https://duckduckgo.com");
        driver.findElement(By.id("searchbox_input")).sendKeys("Selenium WebDriver");
        driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
        String seleniumWebDriverDocLink= driver.findElement(By.xpath("//a[@data-testid='result-title-a']")).getDomAttribute("href");
        Assert.assertEquals(seleniumWebDriverDocLink, "https://www.selenium.dev/documentation/webdriver/");
    }

    @Test
    public void validateCheckboxesAreChecked(){
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
        softAssert.assertTrue(driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).isSelected());
        softAssert.assertTrue(driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).isSelected());

    }
    @Test
    public void validateCorrectCountryIsDisplayed(){
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        List<WebElement> rows=driver.findElements(By.xpath("//table[@id='customers']//tr"));
        for (int i = 2; i <= rows.size(); i++) {
            String country=driver.findElement(By.xpath("//table[@id='customers']//tr["+i+"]/td[3]")).getText();
            if(country.equals("Austria")){
                String Company=driver.findElement(By.xpath("//table[@id='customers']//tr["+i+"]/td[1]")).getText();
                softAssert.assertEquals(Company, "Ernst Handel");
            }
        }

    }

    @Test
    public void validateFileUpload(){
        driver.get("http://the-internet.herokuapp.com/upload");
        driver.findElement(By.id("file-upload")).sendKeys("D:\\Atomation\\java-automation-practice\\test.jpg");
        driver.findElement(By.id("file-submit")).click();
        softAssert.assertTrue(driver.findElement(By.id("uploaded-files")).getText().contains("test.jpg"));

    }

    @Test
    public void validateThatDragAndDropWorkingFine(){
        driver.navigate().to("https://jqueryui.com/resources/demos/droppable/default.html");
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
        softAssert.assertEquals(target.getText(), "Dropped!");


    }
    @AfterMethod
    public void closeBrowser(){
       driver.quit();
    }
}
