package core.location

data class Location (val file: File, val rank: Int) {
    val description = "${file}${rank}"
}
