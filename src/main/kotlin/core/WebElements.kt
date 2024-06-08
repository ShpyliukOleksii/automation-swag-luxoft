package core

import org.openqa.selenium.WebElement
import org.slf4j.LoggerFactory
import org.testng.Assert

abstract class WebElements {
    abstract val pageTitle: String
    abstract val pageLink: String

    private val logger = LoggerFactory.getLogger(WebElement::class.java)

    fun debug(message: String) {
        logger.debug("${if (pageTitle.isBlank() || pageTitle == "{text}") this::class.simpleName else pageTitle} -> $message")
    }

    fun reportError(message: String) {
        Assert.assertTrue(
            false,
            "${if (pageTitle.isBlank() || pageTitle == "{text}") this::class.simpleName else pageTitle} -> $message"
        )
    }
}