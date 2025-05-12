package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.location.*

class Board {
    val root: Field = generateFields()

    private fun generateRank(rankId: Int, lastRank: Field?): Field {
        var currentField = Field(Location(rankId, File.values()[0])) // first Field of Rank + moving pointer to the current Field
        val root: Field = currentField // stationary pointer to first Field of Rank
        var topField: Field? = lastRank // moving pointer to the Field above the current Field

        for (file in 2..9) {

            val isTopField: Boolean = topField != null
            val isNotLastFile: Boolean = file <= 8

            if (isTopField) {
                currentField.top = topField
                topField!!.bottom = currentField
            }

            // new field on the right of current
            if (isNotLastFile) {
                val neoField = Field(Location(rankId, File.values()[file-1]))
                currentField.right = neoField
                neoField.left = currentField
                currentField = neoField
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
