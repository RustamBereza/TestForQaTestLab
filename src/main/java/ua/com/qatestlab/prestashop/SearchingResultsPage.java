package ua.com.qatestlab.prestashop;

import org.openqa.selenium.*;

import java.util.List;

public class SearchingResultsPage {

    private WebDriver driver;

    public SearchingResultsPage(WebDriver driver){
        this.driver = driver;
    }

    private By inscription = By.xpath("//*[@id='js-product-list-top']//p");
    private By relevance = By.xpath("//a[@class='select-title']");
    private By priceReduction = By.xpath("//*[@id='js-product-list-top']/div[2]/div/div/div/a[5]");
    private By sorting = By.xpath("//*[@id='js-product-list-top']/div[2]//div/a");
    private By price = By.xpath("//*[@class='price']");
    private By discount = By.xpath("//*[@class='discount-percentage']");
    private By discountedPrice = By.xpath("//*[@class='regular-price']");

    public void startSearchingResultsPage(){
        this.checkPresenceOfInscription();
        this.checkCurrencyInProduct();
        this.setPriceReduction();
        this.checkSorting();
        this.checkExistenceElements();
        this.checkDiscountedPrices();
    }

    public void checkPresenceOfInscription(){
        if(driver.findElement(inscription).getText().equals("Товаров: 7.")){
            System.out.println("The page \"Search results\" contains the inscription \"Products: 7.\" ");
        }else{
            System.out.println("Fail!");
        }
    }

    public void checkCurrencyInProduct(){
        List<WebElement> currencyInProducts = driver.findElements(price);
        for(int i = 0; i < currencyInProducts.size(); i++) {
            String currencyInProductsText = currencyInProducts.get(i).getText();
            if (currencyInProductsText.charAt(currencyInProductsText.length() - 1) != '$') {
                System.out.println("Fail!");
                break;
            }
            if(i == currencyInProducts.size() - 1){
                System.out.println("The price of all the results shown is displayed in dollars. ");
            }
        }
    }

    public void setPriceReduction(){
        driver.findElement(relevance).click();
        driver.findElement(priceReduction).click();
        System.out.println("Set sorting from high to low. ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkSorting(){
        String sortingByPrice = driver.findElement(sorting).getText();
        if(sortingByPrice.substring(0,4).equals("Цене")){
            System.out.println("Products are sorted by price. ");
        }else{
            System.out.println("Fail!");
        }
    }

    public void checkExistenceElements(){
        try{
            driver.findElements(discount);
            driver.findElements(discountedPrice);
            driver.findElements(price);
            System.out.println("Elements of discounts and prices before and after are present. ");
        }catch (NoSuchElementException e){
            System.out.println("Fail!");
        }
    }

    private void checkDiscountedPrices() {
        List<WebElement> discounts = driver.findElements(discount);
        double[] percentageDifference = {((1.14 - 1.08) / 1.14) * 100, ((0.76 - 0.61) / 0.76) * 100};
        for(int i = 0; i < discounts.size(); i++) {
            String discountsText = discounts.get(i).getText();
            int discount = Integer.parseInt(discountsText.substring(1, discountsText.length() - 1));
            if (Math.round(percentageDifference[i]) != discount) {
                System.out.println("Fail!");
                break;
            }
            if(i == discounts.size() - 1){
                System.out.println("Checked that the prices before and after the discount correspond " +
                        "to the specified size of the discount. ");
            }
        }
    }


}