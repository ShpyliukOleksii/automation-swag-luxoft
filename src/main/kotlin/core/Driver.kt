package com.example.core

import org.openqa.selenium.NoSuchSessionException
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import java.lang.Boolean.FALSE
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

open class Driver {
    var driver: ChromeDriver
    var chrome_options = ChromeOptions()
    val seleniumFolderPath = Paths.get("").toAbsolutePath().toString()

    init {
        chrome_options.addArguments("--disable-geolocation")
        chrome_options.addArguments("--incognito")
        chrome_options.addArguments("--start-maximized")
        System.setProperty("webdriver.chrome.driver", "$seleniumFolderPath\\chromedriver\\chromedriver.exe")
        driver = ChromeDriver(chrome_options)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
    }

    fun quitDriver() {
        driver.quit()
    }

    fun hasQuit(): Boolean {
        var result = false
        try {
            result = (driver == null) || (driver as RemoteWebDriver).sessionId == null || driver.toString()
                .contains("(null)")
        } catch (e: Exception) {
            if (FALSE) {
                e.printStackTrace()
            }
            result = true
        }
        return result
    }

    fun hasClosed(): Boolean {
        if (hasQuit()) {
            return true
        }

        var doesSessionExist = true
        try {
            driver.windowHandles
        } catch (sessionFail: NoSuchSessionException) {
            doesSessionExist = false
        } catch (wdEx: WebDriverException) {
            val message = wdEx.message
            if (message!!.contains("no such session") || (message!!.startsWith("Tried to run command without establishing a connection"))) {
                doesSessionExist = false
            }
        }

        return !doesSessionExist
    }

}
