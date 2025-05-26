package hwr.oop.group4.chess.core.location

data class Location(val file: File, val rank: Rank) {
  val description = "${file}${rank.number}"
}