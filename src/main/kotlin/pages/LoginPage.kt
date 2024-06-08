package pages

import core.BasePage
import com.example.core.Driver
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import java.time.Duration

class LoginPage(driver: Driver) : BasePage(driver) {
    override var pageTitle = "Swag Labs"
    override var pageLink = "https://www.saucedemo.com/"

    private val emailTextBox = "//input[@placeholder='Username']"
    private val passwordTextBox = "//input[@id='password']"
    private val loginButton = "//input[@name='login-button']"
    private val burgerButton = "//button[contains(text(), 'Open Menu')]"
    private val logoutText = "//a[contains(text(), 'Logout')]"

    init {
        driver.driver.get(pageLink)
    }

    fun setEmail(email: String) {
        waitUntilElementVisible(emailTextBox)
        fillInputField(emailTextBox, email)
    }

    fun setPassword(password: String) {
        fillInputField(passwordTextBox, password)
    }

    fun clickSignIn() {
        click(loginButton)
    }

    fun login(username: String) {
        setEmail(username)
        setPassword("secret_sauce")
        clickSignIn()
        WebDriverWait(driver, Duration.ofMillis(5000))
    }

    fun logout() {
        click(burgerButton)
        click(logoutText)
    }

    fun closePage() {
        commonDriver.quitDriver()
    }

    fun verifyLogout(url: String) {
        Assert.assertEquals(url, driver.currentUrl)
    }

    fun getTitle(): String? {
        return getElement(pageTitle)?.text?.trim()
    }
}