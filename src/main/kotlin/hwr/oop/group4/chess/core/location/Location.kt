package core.location

import core.location.File

data class Location ( val file: File, val rank: Int) {
    val description = "${file}${rank}"
}
