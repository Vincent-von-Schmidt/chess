package hwr.oop.group4.chess.core.location

import hwr.oop.group4.chess.core.location.File

data class Location ( val file: File, val rank: Int) {
    val description = "${file}${rank}"
}
