package com.nttdata.stepsdefinitions;

import com.nttdata.steps.AddCarSteps;
import com.nttdata.steps.LoginSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;

public class LoginStepsDef {

    private WebDriver driver;

    private AddCarSteps addCarSteps(WebDriver driver){
        return new AddCarSteps(driver);
    }

    @Given("estoy en la página de la tienda")
    public void estoyEnLaPáginaDeLaTienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/pe/");
        screenShot();
    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String usuario, String password) {
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.clickIniciarSesion();
        loginSteps.typeUser(usuario);
        loginSteps.typePassword(password);
        loginSteps.login();
        loginSteps.validaAutentication();
        screenShot();
    }

    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {
        AddCarSteps addCarSteps = new AddCarSteps(driver);
        addCarSteps.selectCategory(categoria);
        addCarSteps.selectSubcategory(subcategoria);
    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int cantidad) {
        AddCarSteps addCarSteps = new AddCarSteps(driver);
        try {
            Thread.sleep(5000);
            addCarSteps.clickItem1();
            addCarSteps.addCantidad(cantidad);
            Thread.sleep(5000);
            screenShot();
            addCarSteps.clickButtonAddCarrito();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {
        String getTitlePopup =  addCarSteps(driver).getTitlePopup();
        String expectedTitle = "Producto añadido correctamente a su carrito de compra";
        //prueba: validamos el título del popup
        Assertions.assertTrue(getTitlePopup.contains(expectedTitle));
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        AddCarSteps addCarSteps = new AddCarSteps(driver);
        addCarSteps.validarValorCalculado();
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        AddCarSteps addCarSteps = new AddCarSteps(driver);
        addCarSteps.finalizoCompra();
    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        String getTitleCarrito =  addCarSteps(driver).getTitleCarrito();
        String expectedTitle = "CARRITO";
        //prueba: validamos el título del carrito
        Assertions.assertEquals(expectedTitle, getTitleCarrito);

    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        AddCarSteps addCarSteps = new AddCarSteps(driver);
        try {
            Thread.sleep(5000);
            addCarSteps.validarValorCalculadoFinal();
            Thread.sleep(5000);
            screenShot();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
