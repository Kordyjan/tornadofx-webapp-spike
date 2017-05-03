package spike.spark.backend

import org.slf4j.LoggerFactory
import spark.Spark.*
import spike.common.Person
import spike.common.toJson

fun main(args: Array<String>) {
    val log = LoggerFactory.getLogger("server")

    staticFiles.location("/runtime-data/images")

    path("/api") {
        before("/*") { req, _ ->
            log.info("api call: ${req.pathInfo()}")
        }

        get("/lis") { _, _ ->
            Person("Spock", 100).toJson()
        }
    }
}