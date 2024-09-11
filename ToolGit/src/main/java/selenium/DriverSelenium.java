package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.List;

public class DriverSelenium {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://github.com/quangbm0807");

        while (true) {
            try {
                Thread.sleep(5000);
                driver.navigate().refresh();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
