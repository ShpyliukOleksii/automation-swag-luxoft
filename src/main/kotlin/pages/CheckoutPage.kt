package pages

import com.example.core.Driver
import core.BasePage
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.testng.Assert

class CheckoutPage(driver: Driver) : BasePage(driver)  {
    override var pageTitle = ""
    override var pageLink = "https://www.saucedemo.com/"

    private val productsLabel = "//span[contains(@class,'title') and contains(text(), 'Products')]"
    private val burgerButton = "//button[contains(text(), 'Open Menu')]"
    private val logoutText = "//a[contains(text(), 'Logout')]"
    private val resetAppButton = "//a[contains(text(), 'Reset App State')]"
    private val checkOutButton = "//a[@class='shopping_cart_link']"
    private val COButtonOnCOPage ="//button[@id='checkout']"
    private val continueButton = "//input[@id='continue']"
    private val finishButton = "//button[@id='finish']"

    private val backpackText = "//div[contains(text(), 'Sauce Labs Backpack')]"
    private val bikelightText = "//div[contains(text(), 'Sauce Labs Bike Light')]"
    private val bTShirtText = "//div[contains(text(), 'Sauce Labs Bolt T-Shirt')]"
    private val fleeceJacketText = "//div[contains(text(), 'Sauce Labs Fleece Jacket')]"
    private val onesieText = "//div[contains(text(), 'Sauce Labs Onesie')]"
    private val redTShirtText = "//div[contains(text(), 'Test.allTheThings() T-Shirt (Red)')]"

    private val addBackpack = "//button[@id='add-to-cart-sauce-labs-backpack']"
    private val addBikelight = "//button[@id='add-to-cart-sauce-labs-bike-light']"
    private val addBTShirt = "//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"
    private val addFleeceJacket = "add-to-cart-sauce-labs-fleece-jacket']"
    private val addOnesie = "//button[@id='add-to-cart-sauce-labs-onesie']"
    private val addRedTShirt = "//button[@id='add-to-cart-test.allthethings()-t-shirt-(red)']"

    init {
        pageTitle = driver.driver.title
    }


    fun fillCheckoutForm(firstName: String, lastName: String, postalCode: String) {
        driver.findElement(By.id("first-name")).sendKeys(firstName)
        driver.findElement(By.id("last-name")).sendKeys(lastName)
        driver.findElement(By.id("postal-code")).sendKeys(postalCode)
    }
    fun clickContinueButton(){
        click(continueButton)
    }

    fun clickFinishButton(){
        click(finishButton)
    }

    fun logout() {
        click(burgerButton)
        click(resetAppButton)
        click(logoutText)
    }

    fun checkoutAuthenticityVerification(){

        var beforeCheckoutChecker = " "
        var afterCheckoutChecker = " "
        if(!isElementPresent(backpackText)){
            reportError("Unable to find backpack text on product page")
        }
        else{
            beforeCheckoutChecker = backpackText
        }
        click(addBackpack)
        click(checkOutButton)
        if(!isElementPresent(backpackText)){
            reportError("Unable to find backpack text on checkout page")
        }
        else{
            afterCheckoutChecker = backpackText
        }
        if(beforeCheckoutChecker != afterCheckoutChecker){
            reportError("Something went wrong at checkout - wrong item was added")
        }

    }

    fun checkoutMultipleVerification(){
        val productNameXpaths = listOf(
            backpackText,
            bikelightText,
            bTShirtText,
            fleeceJacketText,
            onesieText,
            redTShirtText
        )

        val productNames: MutableList<String> = mutableListOf()
        for (xpath in productNameXpaths) {
            val productNameElement: WebElement = driver.findElement(By.xpath(xpath))
            val productName: String = productNameElement.text
            productNames.add(productName)
        }

        for (i in 1..6) {
            driver.findElement(By.xpath("//div[@class='inventory_item'][${i}]//button")).click()
        }

        driver.findElement(By.id("shopping_cart_container")).click()


        val cartProductNames: MutableList<String> = mutableListOf()
        for (i in 1..6) {
            val cartProductNameElement: WebElement = driver.findElement(By.xpath("//div[@class='cart_item'][${i}]//div[@class='inventory_item_name']"))
            val cartProductName: String = cartProductNameElement.text
            cartProductNames.add(cartProductName)
        }

        Assert.assertEquals(cartProductNames, productNames)
    }

    fun checkoutProceed(){
        click(COButtonOnCOPage)

    }



}
