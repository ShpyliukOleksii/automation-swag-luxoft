package test

import BaseTest
import com.example.core.Driver
import pages.LoginPage
import utils.PropertyFileReader
import io.qameta.allure.Feature
import org.testng.annotations.AfterClass
import org.testng.annotations.Test
import pages.ProductsPage

@Feature("Login")
class LoginPageTest : BaseTest() {
    private var browserDriver: Driver = Driver()
    private var loginPage = LoginPage(browserDriver)
    private var productsPage = ProductsPage(browserDriver)
    private var url = browserDriver.driver.currentUrl

    @AfterClass
    fun tearDown() {
        loginPage.closePage()
    }

    @Test

    fun Test1_Login_Logout_StandartUser() {
        val credentials = PropertyFileReader.getCredentials("userS")
        loginPage.login(credentials)
        if (!productsPage.getLoggedIn()) {
            productsPage.reportError("User was not logged in, but should've been logged in")
        }
        productsPage.logout()
        loginPage.verifyLogout(url)
    }

    @Test
    fun Test2_Login_Logout_Locked_Out_User() {
        val error_text = "Epic sadface: Sorry, this user has been locked out."
        val credentials = PropertyFileReader.getCredentials("userLO")
        loginPage.login(credentials)
        if (!loginPage.isElementPresent("//h3[contains(@data-test, 'error') and contains(text(), '$error_text')]")) {
            loginPage.reportError("User was logged in, but should't be logged in")
        }
    }

    @Test
    fun Test3_Login_Logout_ProblemUser() {
        val credentials = PropertyFileReader.getCredentials("userP")
        loginPage.login(credentials)
        if (!productsPage.getLoggedIn()) {
            productsPage.reportError("User was not logged in, but should've been logged in")
        }
        productsPage.logout()
        loginPage.verifyLogout(url)
    }

    @Test
    fun Test4_Login_Logout_PerformanceGlitchUser() {
        val credentials = PropertyFileReader.getCredentials("userPG")
        loginPage.login(credentials)

        productsPage.waitUntilElementVisible("//span[contains(@class,'title') and contains(text(), 'Products')]")

        productsPage.logout()
        loginPage.verifyLogout(url)
    }

    @Test
    fun Test5_Login_Logout_VisualUser() {
        val imageElement = "//img[@src='/static/media/sl-404.168b1cce.jpg']"
        val credentials = PropertyFileReader.getCredentials("userV")
        loginPage.login(credentials)
        if (productsPage.findElements(imageElement).size == 0) {
            productsPage.reportError("Wrong Images present")
        }
        productsPage.logout()
        loginPage.verifyLogout(url)
    }
}