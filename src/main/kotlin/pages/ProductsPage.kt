package pages

import com.example.core.Driver
import core.BasePage
import org.openqa.selenium.By

class ProductsPage(driver: Driver) : BasePage(driver)  {
    override var pageTitle = ""
    override var pageLink = "https://www.saucedemo.com/"

    private val productsLabel = "//span[contains(@class,'title') and contains(text(), 'Products')]"
    private val burgerButton = "//button[contains(text(), 'Open Menu')]"
    private val logoutText = "//a[contains(text(), 'Logout')]"
    private val resetAppButton = "//a[contains(text(), 'Reset App State')]"
    private val checkOutButton = "//a[@class='shopping_cart_link']"
    private val backpackText = "//div[contains(text(), 'Sauce Labs Backpack')]"
    private val addBackpack = "//button[@id='add-to-cart-sauce-labs-backpack']"
    private val addBikelight = "//button[@id='add-to-cart-sauce-labs-bike-light']"
    private val addBTShirt = "//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"
    private val addFleeceJacket = "//button[@id='add-to-cart-sauce-labs-fleece-jacket']"
    private val addOnesie = "//button[@id='add-to-cart-sauce-labs-onesie']"
    private val addRedTShirt = "//button[@id='add-to-cart-test.allthethings()-t-shirt-(red)']"


    init {
        pageTitle = driver.driver.title
    }

    fun getLoggedIn(): Boolean {
        waitUntilElementVisible(productsLabel)
        return driver.findElements(By.xpath(productsLabel))?.size == 1
    }

    fun addOneCartItem(){
        click(addBackpack)
    }

    fun addMultipleCartItem(){
        click(addBackpack)
        click(addBikelight)
        click(addOnesie)
    }

    fun logout() {
        click(burgerButton)
        click(resetAppButton)
        click(logoutText)
    }

}
