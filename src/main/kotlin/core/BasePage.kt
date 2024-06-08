package core

import com.example.core.Driver
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

abstract class BasePage(_driver: Driver) : WebElements() {
    protected var commonDriver: Driver = _driver
    protected var driver: WebDriver = commonDriver.driver

    fun waitUntilElementVisible(element: String) {
        val wait = WebDriverWait(driver, Duration.ofMillis(1000))
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)))
    }

    fun click(xpath: String) {
        driver.findElement(By.xpath(xpath))?.click()
    }

    fun getElement(element: String): WebElement? {
        waitUntilElementVisible(element)
        return driver.findElement(By.xpath(element))
    }

    fun getElements(elements: String): List<WebElement>? {
        waitUntilElementVisible(elements)
        return driver.findElements(By.xpath(elements))
    }

    fun clearInputField(field: String) {
        waitUntilElementVisible(field)
        driver.findElement(By.xpath(field))?.clear()
    }

    fun fillInputField(xpath: String, text: String) {
        clearInputField(xpath)
        driver.findElement(By.xpath(xpath))?.sendKeys(text)
    }

    fun isElementPresent(element: String): Boolean {
        val isPresent = driver.findElements(By.xpath(element)).size > 0
        if (!isPresent) {
            this.reportError("Element is not present")
        }
        return isPresent
    }

    fun findElement(element: String): WebElement? {
        return driver.findElement(By.xpath(element))
    }

    fun findElements(element: String): List<WebElement> {
        return driver.findElements(By.xpath(element))
    }

}