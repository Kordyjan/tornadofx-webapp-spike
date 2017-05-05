package spike.backend.data

interface DataAccess<T> {
    val list: Map<Int, T>

    fun get(id: Int): T?

    fun update(id: Int, value: T)

    fun insert(value: T)

    fun remove(id: Int)
}