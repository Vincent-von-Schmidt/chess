package group4.chess.board
import group4.chess.location.*

fun main() {
    val board = Board()
}

class Board {
    val root: Field = generateFields()

    private fun generateRank(rankId: Int, lastRank: Field?): Field {
        var currentFiled = Field(Location(rankId, File.values()[0])) // first Field of Rank + moving pointer to the current Field
        val root: Field = currentFiled // stationary pointer to first Field of Rank
        var topField: Field? = lastRank // moving pointer to the Field above the current Field

        for (file in 2..9) {

            val isTopField: Boolean = topField != null
            val isNotLastFile: Boolean = file <= 8

            if (isTopField) {
                currentFiled.top = topField
                topField!!.bottom = currentFiled
            }

            // new field on the right of current
            if (isNotLastFile) {
                val neoField = Field(Location(rankId, File.values()[file-1]))
                currentFiled.right = neoField
                neoField.left = currentFiled
                currentFiled = neoField
            }

            if (isTopField && isNotLastFile) {
                topField = topField!!.right!!
            }
        }
        return root
    }

    private fun generateFields(): Field {
        var firstRank: Field? = null
        var lastRank: Field? = null

        for (rank in 1..8) {
            lastRank = generateRank(rank, lastRank)

            if (rank == 1) {
                firstRank = lastRank
            }
        }

        return firstRank!!
    }
}