package spike.spark.backend

import org.slf4j.LoggerFactory
import spark.Spark.*
import spike.backend.data.DataBase
import spike.common.model.Person

fun main(args: Array<String>) {
    val log = LoggerFactory.getLogger("server")

    staticFiles.location("/images")

    path("/api") {
        before("/*") { req, _ ->
            log.info("api call: ${req.pathInfo()}")
        }

        get("/create") { _, _ ->
            val dataBase = DataBase(args[0])
            dataBase.persons.insert(Person("Spock", 100))
            dataBase.persons.insert(Person("Szkocik", 200))
            dataBase.save()
        }
    }
}