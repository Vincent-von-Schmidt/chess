package hwr.oop.group4.chess.core.location

enum class File {
  A, B, C, D, E, F, G, H;

  fun next(): File? = File.values().getOrNull(this.ordinal + 1)
  fun previous(): File? = File.values().getOrNull(this.ordinal - 1)

}