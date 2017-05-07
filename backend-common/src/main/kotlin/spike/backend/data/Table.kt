package spike.backend.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties("list")
class Table<T>(@JsonProperty("content") internal val content: MutableList<Pair<T, Boolean>> = mutableListOf()) : DataAccess<T> {

    override val list: Map<Int, T>
        get() = content.withIndex().filter { it.value.second }.map { it.index to it.value.first }.toMap()

    override fun get(id: Int): T? = content[id].takeIf { it.second }?.first

    override fun update(id: Int, value: T) {
        if (content[id].second) {
            content[id] = value to true
        } else {
            throw IllegalArgumentException("Updating already deleted element")
        }
    }

    override fun insert(value: T) {
        content.add(value to true)
    }

    override fun remove(id: Int) {
        if (content[id].second) {
            content[id] = content[id].copy(second = false)
        } else {
            throw IllegalArgumentException("Deleting already deleted element")
        }
    }
}