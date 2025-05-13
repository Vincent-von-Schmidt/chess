package core.location

enum class File {
    A, B, C, D, E, F, G, H;

    fun next(): File? {
        val values = File.values()
        val nextOrdinal = this.ordinal + 1
        return if (nextOrdinal < values.size) values[nextOrdinal] else null
    }
}