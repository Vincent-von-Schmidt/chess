package hwr.oop.group4.chess.core.location

enum class File {
  A, B, C, D, E, F, G, H;

  fun next() =
    when (this) {
      A -> B
      B -> C
      C -> D
      D -> E
      E -> F
      F -> G
      G -> H
      H -> null
    }

  fun previous() =
    when (this) {
      H -> G
      G -> F
      F -> E
      E -> D
      D -> C
      C -> B
      B -> A
      A -> null
    }
}