import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
public class GoogleSearch {
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test;
    WebDriver driver;

    @BeforeTest
    public void setUp(){
        htmlReporter = new ExtentHtmlReporter("extent.html");
        // create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }
    @Test
    public void searchTest() {
        test = extent.createTest("Google Search", "Search On JAVA and Select Java Compiler");
        System.setProperty("webdriver.chrome.driver", "C://Selenium//chromedriver_win32//chromedriver.exe");
        driver = new ChromeDriver();
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
                Assert.assertEquals(itemlist,"java compiler");
                break;
            }
        }
    }
    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
        extent.flush();
    }
}
