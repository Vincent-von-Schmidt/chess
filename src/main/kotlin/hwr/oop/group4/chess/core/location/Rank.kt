package hwr.oop.group4.chess.core.location

enum class Rank(val number: Int) {
  ONE(1), TWO(2), THREE(3), FOUR(4),
  FIVE(5), SIX(6), SEVEN(7), EIGHT(8);

  fun next() =
    when (this) {
      ONE -> TWO
      TWO -> THREE
      THREE -> FOUR
      FOUR -> FIVE
      FIVE -> SIX
      SIX -> SEVEN
      SEVEN -> EIGHT
      EIGHT -> null
    }

  fun previous() =
    when (this) {
      EIGHT -> SEVEN
      SEVEN -> SIX
      SIX -> FIVE
      FIVE -> FOUR
      FOUR -> THREE
      THREE -> TWO
      TWO -> ONE
      ONE -> null
    }
}