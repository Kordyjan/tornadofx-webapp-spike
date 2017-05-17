package spike.backend.data

import com.fasterxml.jackson.annotation.JsonProperty
import spike.common.model.Person
import spike.common.read
import spike.common.toJson
import java.io.File
import kotlin.concurrent.thread

class DataBase private constructor(@JsonProperty val persons: Table<Person> = Table()) {
    lateinit private var file: File

    fun save() {
        thread {
            file.parentFile.mkdirs()
            file.writeText(this.toJson())
        }
    }

    companion object {
        operator fun invoke(path: String): DataBase {
            val file = File(path)
            val dataBase: DataBase? = try {
                file.readText().read<DataBase>()
            } catch (e: Exception) {
                null
            }
            return (dataBase ?: DataBase()).also { it.file = file }
        }
    }
}
