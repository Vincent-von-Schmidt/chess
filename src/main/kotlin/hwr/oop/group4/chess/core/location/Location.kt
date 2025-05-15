package hwr.oop.group4.chess.core.location

data class Location (val file: File, val rank: Int) {
    val description = "${file}${rank}"
}
