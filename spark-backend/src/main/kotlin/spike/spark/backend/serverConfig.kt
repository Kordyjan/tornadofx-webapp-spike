package spike.spark.backend

import org.slf4j.LoggerFactory
import spark.Spark.*
import spike.backend.data.DataBase
import spike.common.model.Person
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
            dataBase.persons.list.map { it.toPair() }.toJson()
        }

        get("/create") { _, _ ->
            dataBase.persons.apply { insert(Person("Szkocik", 100)) }
                    .apply { insert(Person("Spock", 200)) }
            dataBase.save()
        }
    }
}