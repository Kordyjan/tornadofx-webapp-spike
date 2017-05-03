package spike.spark.backend

import spark.Spark.*
import spike.common.Person

fun main(args: Array<String>) {
    staticFiles.location("/runtime-data/images")
    get("/") { _, _ ->
        Person("Szkocik", 100)
    }
}