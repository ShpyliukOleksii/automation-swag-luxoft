package test

import BaseTest
import com.example.core.Driver
import pages.LoginPage
import utils.PropertyFileReader
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterMethod
import org.testng.annotations.Test
import pages.ProductsPage


class ProductPageTest : BaseTest() {
    private var browserDriver: Driver = Driver()
    private var loginPage = LoginPage(browserDriver)
    private var productsPage = ProductsPage(browserDriver)
    private var url = browserDriver.driver.currentUrl

    @AfterClass
    fun tearDown() {
        loginPage.closePage()
    }

    @AfterMethod
    fun logout() {
        productsPage.logout()
    }

    @Test
    fun Test1_Ensure_Pics_Present_StandardUser() {
        val credentials = PropertyFileReader.getCredentials("userS")
        loginPage.login(credentials)

        if (!productsPage.isElementPresent("//img[contains(@class, 'inventory_item_img') and contains(@src, '/static/media/sauce-backpack-1200x1500.0a0b85a3.jpg')]")
            && !productsPage.isElementPresent("//img[contains(@class, 'inventory_item_img') and contains(@src, '/static/media/bike-light-1200x1500.37c843b0.jpg')]")
            && !productsPage.isElementPresent("//img[contains(@class, 'inventory_item_img') and contains(@src, '/static/media/bolt-shirt-1200x1500.c2599ac5.jpg')]")
            && !productsPage.isElementPresent("//img[contains(@class, 'inventory_item_img') and contains(@src, '/static/media/sauce-pullover-1200x1500.51d7ffaf.jpg')]")
            && !productsPage.isElementPresent("//img[contains(@class, 'inventory_item_img') and contains(@src, '/static/media/red-onesie-1200x1500.2ec615b2.jpg')]")
            && !productsPage.isElementPresent("//img[contains(@class, 'inventory_item_img') and contains(@src, '/static/media/red-tatt-1200x1500.30dadef4.jpg')]")
        ) {
            productsPage.reportError("1 or more pictures are not present")
        }
    }

    @Test
    fun Test2_Ensure_Pics_Present_VisualUser() {
        val credentials = PropertyFileReader.getCredentials("userV")
        loginPage.login(credentials)

        if (!productsPage.isElementPresent("//img[contains(@class, 'inventory_item_img') and contains(@src, '/static/media/sl-404.168b1cce.jpg')]")
            && !productsPage.isElementPresent("//img[contains(@class, 'inventory_item_img') and contains(@src, '/static/media/bike-light-1200x1500.37c843b0.jpg')]")
            && !productsPage.isElementPresent("//img[contains(@class, 'inventory_item_img') and contains(@src, '/static/media/bolt-shirt-1200x1500.c2599ac5.jpg')]")
            && !productsPage.isElementPresent("//img[contains(@class, 'inventory_item_img') and contains(@src, '/static/media/sauce-pullover-1200x1500.51d7ffaf.jpg')]")
            && !productsPage.isElementPresent("//img[contains(@class, 'inventory_item_img') and contains(@src, '/static/media/red-onesie-1200x1500.2ec615b2.jpg')]")
            && !productsPage.isElementPresent("//img[contains(@class, 'inventory_item_img') and contains(@src, '/static/media/red-tatt-1200x1500.30dadef4.jpg')]")
        ) {
            productsPage.reportError("1 or more pictures are not present")
        }

    }

    @Test
    fun Test3_Add_Single_Item_To_Cart_ProductPage() {
        val credentials = PropertyFileReader.getCredentials("userS")
        val oneItemCheckoutBadgeText = "1"
        loginPage.login(credentials)
        productsPage.addOneCartItem()
        if (!productsPage.isElementPresent("//span[contains(@class, 'shopping_cart_badge') and contains (text(),'$oneItemCheckoutBadgeText')]")) {
            productsPage.reportError("Item wasn't added to Cart")
        }

    }

    @Test
    fun Test4_Add_Multiple_Item_To_Cart_ProductPage() {
        val credentials = PropertyFileReader.getCredentials("userS")
        val TItemCheckoutBadgeText = "3"
        loginPage.login(credentials)
        productsPage.addMultipleCartItem()
        if (!productsPage.isElementPresent("//span[contains(@class, 'shopping_cart_badge') and contains (text(),'$TItemCheckoutBadgeText')]")) {
            productsPage.reportError("Item wasn't added to Cart")
        }
    }
}




