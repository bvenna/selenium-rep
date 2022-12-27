import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
public class GoogleSearch {
    @Test
    public void searchTest() {
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
                break;
            }

        }
        driver.quit();
    }
}
