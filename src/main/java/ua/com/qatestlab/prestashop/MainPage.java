package ua.com.qatestlab.prestashop;

import org.openqa.selenium.*;

import java.util.List;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    private By currencyUahInTheCap = By.xpath("//*[text()='UAH ₴']");
    private By currencyDollInTheCap = By.xpath("//*[text()='USD $']");
    private By currencyInProduct = By.xpath("//*[@class='products']//span[@class='price']");
    private By search = By.xpath("//*[@name='s']");
    private By searchButton = By.xpath("//*[text()='\uE8B6']");

    public void startMainPage(){
        this.comparisonCurrency();
        this.setUSD();
        this.searchWordDress();
    }

    public void comparisonCurrency() {
        String currencyUahInTheCapText = driver.findElement(currencyUahInTheCap).getText();
        List<WebElement> currencyInProducts = driver.findElements(currencyInProduct);
        for(int i = 0; i <= currencyInProducts.size() - 1; i++) {
            String currencyInProductsText = currencyInProducts.get(i).getText();
            if (currencyUahInTheCapText.charAt(currencyUahInTheCapText.length() - 1)
                    != currencyInProductsText.charAt(currencyInProductsText.length() - 1)) {
                System.out.println("Fail!");
                break;
            }
        }
        System.out.println("Price of products in the \"Featured Products\" specified in " +
                "in accordance with the established currency in the website header. ");
    }

    public void setUSD(){
        driver.findElement(currencyUahInTheCap).click();
        driver.findElement(currencyDollInTheCap).click();
        System.out.println("Set the price display in USD. ");
    }

    public void searchWordDress(){
        driver.findElement(search).sendKeys("dress");
        driver.findElement(searchButton).submit();
        System.out.println("Searched by word \"dress\". ");
    }
}