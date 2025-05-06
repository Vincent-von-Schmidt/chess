package group4.chess.board
import group4.chess.location.*

fun main() {
    val board = Board()
    // println("${board.root} -> ${board.root.location.file}${board.root.location.rank}")
    // println("${board.root.right} -> ${board.root.right?.location?.file}${board.root.right?.location?.rank}")
    // println("${board.root.right?.left} -> ${board.root.right?.left?.location?.file}${board.root.right?.left?.location?.rank}")
    // println("${board.root.bottom} -> ${board.root.bottom?.location?.file}${board.root.bottom?.location?.rank}")
    // println("${board.root.top} -> ${board.root.top?.location?.file}${board.root.top?.location?.rank}")
    // println("${board.root.right?.bottom} -> ${board.root.right?.bottom?.location?.file}${board.root.right?.bottom?.location?.rank}")

    var tmp: Field = board.root
    for (i in 0..7) {
        println("${tmp.location.file}${tmp.location.rank}")
        println("${tmp.bottom?.location?.file}${tmp.bottom?.location?.rank}")
        println("------")

        if (i < 7)
            tmp = tmp.right!!
    }
}

class Board {
    val root: Field = generateFields()

    private fun generateRank(rankId: Int, lastRank: Field?): Field {
        var newField = Field(Location(rankId, File.values()[0]))
        val root: Field = newField
        var lastRank: Field? = lastRank
        for (file in 2..8) {
            newField.right = Field(Location(rankId, File.values()[file-1]))
            newField.right!!.left = newField

            if (lastRank != null) {
                newField.top = lastRank
                newField.top!!.bottom = newField
                lastRank = newField.right!!
            }

            newField = newField.right!!
        }
        return root
    }

    private fun generateFields(): Field {
        var firstRank: Field? = null
        var lastRank: Field? = null

        for (y in 0..7) {
            lastRank = generateRank(y, lastRank)

            if (y == 0) {
                firstRank = lastRank
            }
        }

        return firstRank!!
    }
}