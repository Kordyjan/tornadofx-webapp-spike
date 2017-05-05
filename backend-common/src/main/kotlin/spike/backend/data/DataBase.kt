package spike.backend.data

import com.fasterxml.jackson.annotation.JsonIgnore
import spike.common.model.Person
import spike.common.read
import spike.common.toJson
import java.io.File
import kotlin.concurrent.thread

class DataBase private constructor(val persons: Table<Person>) {
    lateinit private var file: File

    fun save() {
        thread {
            file.createNewFile()
            file.writeText(this.toJson())
        }
    }

    companion object {
        operator fun invoke(path: String): DataBase {
            val file = File(path)
            val dataBase: DataBase? = try {
                file.readText().takeUnless { it.isBlank() }?.read<DataBase>()
            } catch (e: Exception) {
                null
            }
            return (dataBase ?: DataBase(Table(mutableListOf()))).also { it.file = file }
        }
    }
}
