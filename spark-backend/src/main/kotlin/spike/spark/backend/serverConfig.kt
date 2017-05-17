package spike.spark.backend

import org.slf4j.LoggerFactory
import spark.Spark.*
import spike.backend.data.DataBase
import spike.common.model.Indexed
import spike.common.read
import spike.common.toJson

fun main(args: Array<String>) {
    val log = LoggerFactory.getLogger("server")
    val dataBase = DataBase(args[0])

    staticFiles.location("/images")
    port(args[1].toInt())

    path("/api") {
        before("/*") { req, _ ->
            log.info("api call: ${req.pathInfo()}")
        }

        get("/users") { _, _ ->
            dataBase.persons.list.map(::Indexed).toJson()
        }

        post("/users") { req, _ ->
            try {
                dataBase.persons.insert(req.body().read())
            } catch (e: Exception) {
                halt(400)
            }
            true
        }
    }
}