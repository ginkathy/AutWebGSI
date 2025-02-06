package com.nttdata.steps;

import com.nttdata.page.AddCarPage;
import com.nttdata.page.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddCarSteps {

    private WebDriver driver;

    //contrsuctor
    public AddCarSteps(WebDriver driver){
        this.driver = driver;
    }

    public void selectCategory(String category){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(., '" + category + "')]")));

        WebElement categoryMenuElement = this.driver.findElement(By.xpath("//a[contains(., '" + category + "')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(categoryMenuElement).perform();
    }

    public void selectSubcategory(String subcategory){
        WebElement categoryMenuElement = this.driver.findElement(By.xpath("//a[contains(., '" + subcategory + "')]"));
        categoryMenuElement.click();
    }

    public void clickItem1(){
        List<WebElement> item = this.driver.findElements(LoginPage.items);
        item.get(0).click();
    }

    public void addCantidad(int cantidad){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AddCarPage.cantidad));
        WebElement quantityInputElement = driver.findElement(AddCarPage.cantidad);
        quantityInputElement.sendKeys(Keys.CONTROL + "a");
        quantityInputElement.sendKeys(Keys.BACK_SPACE);
        quantityInputElement.sendKeys(String.valueOf(cantidad));
    }

    public void clickButtonAddCarrito(){
        this.driver.findElement(AddCarPage.btnAddCarrito).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AddCarPage.popupConfirmation));

    }

    public String getTitlePopup() {
        return this.driver.findElement(AddCarPage.productsTitle).getText();
    }

    public void validarValorCalculado() {
        String pricePopup = this.driver.findElement(AddCarPage.pricePopup).getText();
        String priceOnly = pricePopup.replaceAll("[^\\d.]", "");
        double priceObt = Double.parseDouble(priceOnly);

        String quantityPopup = this.driver.findElement(AddCarPage.quantityPopup).findElement(By.tagName("strong")).getText();
        double quantityObt = Double.parseDouble(quantityPopup);

        double totalCalculado = priceObt * quantityObt;

        String totalPopup = this.driver.findElement(AddCarPage.totalPopup).getText();
        String totalOnly = totalPopup.replaceAll("[^\\d.]", "");
        double totalObt = Double.parseDouble(totalOnly);

        Assertions.assertEquals(totalObt, totalCalculado);
    }

    public void finalizoCompra() {
        this.driver.findElement(AddCarPage.btnFinalizarCompra).click();
    }

    public String getTitleCarrito() {
        return this.driver.findElement(AddCarPage.carritoTitle).getText();
    }

    public void validarValorCalculadoFinal() {
        String priceCar = this.driver.findElement(AddCarPage.priceCar).getText();
        String priceOnly = priceCar.replaceAll("[^\\d.]", "");
        double priceObt = Double.parseDouble(priceOnly);

        String quantityCar = this.driver.findElement(AddCarPage.quantityCar).getAttribute("value");
        double quantityObt = Double.parseDouble(quantityCar);

        double totalCalculado = priceObt * quantityObt;

        String totalCar = this.driver.findElement(AddCarPage.totalCar).getText();
        String totalOnly = totalCar.replaceAll("[^\\d.]", "");
        double totalObt = Double.parseDouble(totalOnly);

        Assertions.assertEquals(totalObt, totalCalculado);
    }
}
