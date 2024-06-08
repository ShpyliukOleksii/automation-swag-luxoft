import allureListeners.AllureTestListener
import utils.PropertyFileReader
import org.testng.annotations.BeforeClass
import org.testng.annotations.Listeners

@Listeners(AllureTestListener::class)
open class BaseTest {
    @BeforeClass
    fun init() {
        PropertyFileReader.loadProperties()
    }
}