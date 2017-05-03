package spike.spark.backend

import spark.Spark.*

fun main(args: Array<String>) {
    get("/") { _, _ ->
        "WORKING!!!"
    }
}