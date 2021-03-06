package spike.common

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

fun Any.toJson(): String {
    return jacksonObjectMapper().writeValueAsString(this)
}

inline fun <reified T: Any> String.read(): T {
    return jacksonObjectMapper().readValue<T>(this)
}
