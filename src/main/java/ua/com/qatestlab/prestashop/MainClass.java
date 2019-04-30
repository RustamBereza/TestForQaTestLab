package ua.com.qatestlab.prestashop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MainClass {

    static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://prestashop-automation.qatestlab.com.ua/ru/");

        MainPage mainPage = new MainPage(driver);
        SearchingResultsPage searchingResultsPage = new SearchingResultsPage(driver);
        mainPage.startMainPage();
        searchingResultsPage.startSearchingResultsPage();
    }
}
