package com.nttdata.page;

import org.openqa.selenium.By;

public class AddCarPage {


    public static By cantidad = By.id("quantity_wanted");
    public static By btnAddCarrito = By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]");

    public static By popupConfirmation = By.xpath("//*[@id=\"blockcart-modal\"]/div/div");
    public static By productsTitle = By.id("myModalLabel");

    public  static By pricePopup = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[1]/div/div[2]/p");
    public  static By quantityPopup = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[1]/div/div[2]/span[3]");
    public  static By totalPopup = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/p[4]/span[2]");

    public static By btnFinalizarCompra = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a");

    public static By carritoTitle = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[1]/h1");

    public static By priceCar = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[2]/div[2]/div[2]/span");
    public static By quantityCar = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[1]/div/input");
    public static By totalCar = By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[1]/div[2]/div[2]/span[2]");
}
