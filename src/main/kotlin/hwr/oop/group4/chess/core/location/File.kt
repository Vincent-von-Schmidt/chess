package hwr.oop.group4.chess.core.location

enum class File {
    A, B, C, D, E, F, G, H;

    fun next(): File? {
        return File.values().getOrNull(this.ordinal + 1)
    } // TODO use this to cycle through files
}