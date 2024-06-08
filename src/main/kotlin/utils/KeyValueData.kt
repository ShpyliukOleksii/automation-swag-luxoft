package utils

import org.slf4j.LoggerFactory
import kotlin.collections.HashMap

open class KeyValueData {
    private val logger = LoggerFactory.getLogger("")
    val data: HashMap<String, String> = HashMap()

    operator fun get(key: String): String {
        return data[key] ?: ""
    }

    fun replace(key: String, oldValue: String, newValue: String): Boolean {
        return data.replace(key, oldValue, newValue)
    }
}