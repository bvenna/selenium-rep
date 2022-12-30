import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
public class GoogleSearch {
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;

    @BeforeTest
    public void setUp(){
        htmlReporter = new ExtentHtmlReporter("extent.html");
        // create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }
    @Test
    public void searchTest() {
        ExtentTest test = extent.createTest("MyFirstTest", "Google Search");
        System.setProperty("webdriver.chrome.driver", "C://Selenium//chromedriver_win32//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Java");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> list = driver.findElements(By.xpath("//li/descendant::div[@class='eIPGRd']"));
        System.out.println("Total items in the list are: " + list.size());
        for (int i =0;i< list.size();i++){
            String itemlist = list.get(i).getText();
            if (itemlist.contains("java compiler")){
                list.get(i).click();
                System.out.println("Item selected from the list is: " + itemlist);
                test.pass("Test Passed");
                break;
            }
        }
        driver.quit();
    }
    @AfterTest
    public void tearDown(){
        extent.flush();
    }
}
