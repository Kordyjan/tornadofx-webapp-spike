package spike.common

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun Any.toJson(): String {
    return jacksonObjectMapper().writeValueAsString(this)
}