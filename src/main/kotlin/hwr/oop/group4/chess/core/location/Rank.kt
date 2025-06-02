package hwr.oop.group4.chess.core.location

// TODO: ordinal

enum class Rank(val number: Int) {
  ONE(1), TWO(2), THREE(3), FOUR(4),
  FIVE(5), SIX(6), SEVEN(7), EIGHT(8);

  fun next(): Rank? = values().getOrNull(this.ordinal + 1)
  fun previous(): Rank? = values().getOrNull(this.ordinal - 1)

  // TODO use this to cycle through files
}