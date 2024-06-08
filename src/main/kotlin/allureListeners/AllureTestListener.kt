package allureListeners
import io.qameta.allure.listener.StepLifecycleListener
import io.qameta.allure.model.StepResult
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.testng.ITestResult
import org.testng.TestListenerAdapter

class AllureTestListener : TestListenerAdapter(), StepLifecycleListener {
    override fun onTestStart(result: ITestResult) {
        LOGGER.debug("Test class: " + result.testClass.name, "Test STARTED: " + result.name)
    }


    override fun onTestSuccess(result: ITestResult) {
        LOGGER.debug("Test class: " + result.testClass.name, "Test SUCCESS: " + result.name)
    }

    override fun onTestFailure(result: ITestResult) {

        LOGGER.debug("Test class: " + result.testClass.name, "Test FAILED: " + result.name)

        if (result.throwable != null) {
            result.throwable.printStackTrace()
        }
    }

    override fun onTestSkipped(result: ITestResult) {
        LOGGER.debug("Test class: " + result.testClass.name, "Test SKIPPED: " + result.name)
        super.onTestSkipped(result)
    }


    override fun beforeStepStart(result: StepResult) {
        LOGGER.debug("STEP START: {}", result.getName())
    }

    override fun afterStepStop(result: StepResult) {
        LOGGER.debug("STEP DONE: {}\n", result.getName())
    }

    companion object {
        var LOGGER: Logger = LoggerFactory.getLogger(AllureTestListener::class.java.simpleName)
    }
}