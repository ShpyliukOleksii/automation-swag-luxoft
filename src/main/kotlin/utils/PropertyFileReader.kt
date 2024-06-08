package utils
import java.io.FileInputStream
import java.util.*


object PropertyFileReader : KeyValueData() {
    fun loadProperties() {
        val properties = Properties()
        FileInputStream("./src/test/resources/config.properties").use { input ->
            properties.load(input)
        }
        for (key in properties.stringPropertyNames()) {
            val value = properties.getProperty(key)
            data[key] = value
        }
    }

    fun getCredentials(userAlias: String): String {
        return if (data[userAlias] == null) "" else data[userAlias]!!
    }
}