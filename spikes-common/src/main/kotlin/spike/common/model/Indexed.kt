package spike.common.model

data class Indexed<T>(val id: Int, val value: T) {
    constructor(e: Map.Entry<Int, T>): this(e.key, e.value)
}