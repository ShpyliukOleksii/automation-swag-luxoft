package test

import BaseTest
import com.example.core.Driver
import pages.LoginPage
import utils.PropertyFileReader
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterMethod
import org.testng.annotations.Test
import pages.ProductsPage
import pages.CheckoutPage


class CheckoutPageTest : BaseTest() {
    private var browserDriver: Driver = Driver()
    private var loginPage = LoginPage(browserDriver)
    private var productsPage = ProductsPage(browserDriver)
    private var checkoutPage = CheckoutPage(browserDriver)
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
    fun Test1_Correct_Item_In_Checkout() {
        val credentials = PropertyFileReader.getCredentials("userS")
        loginPage.login(credentials)
        checkoutPage.checkoutAuthenticityVerification()
    }

    @Test
    fun Test2_Multiple_items_in_Checkout() {
        val credentials = PropertyFileReader.getCredentials("userS")
        loginPage.login(credentials)
        checkoutPage.checkoutMultipleVerification()
    }

    @Test
    fun Test3_Correct_Checkout_Process() {
        val credentials = PropertyFileReader.getCredentials("userS")
        loginPage.login(credentials)
        checkoutPage.checkoutAuthenticityVerification()
        checkoutPage.checkoutProceed()
        checkoutPage.fillCheckoutForm("John", "Doe", "29000")
        checkoutPage.clickContinueButton()
        checkoutPage.clickFinishButton()
        if (!checkoutPage.isElementPresent("//img[@class='pony_express']")) {
            checkoutPage.reportError("Something went wrong at final checkout stage")
        }


    }


}