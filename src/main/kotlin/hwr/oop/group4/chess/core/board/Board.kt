package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.move.Move

class Board {
    val root: Field = generateFields() // root ist A1

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

    private fun generateRank(rankId: Int, lastRank: Field?): Field {
        var currentField = Field(Location(File.values()[0], rankId)) // first Field of Rank + moving pointer to the current Field
        val root: Field = currentField // stationary pointer to first Field of Rank
        var topField: Field? = lastRank // moving pointer to the Field above the current Field

        for (file in 1..8) {

            val isTopField: Boolean = topField != null
            val isNotLastFile: Boolean = file < 8

            if (isTopField) {
                currentField.top = topField
                topField!!.bottom = currentField
            }

            // new field on the right of current
            if (isNotLastFile) {
                val neoField = Field(Location(File.values()[file],rankId ))
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

    fun getField(location: Location): Field {
        var current: Field? = root

        while(current!!.location.rank < location.rank) {
            current = current.bottom ?: throw IllegalArgumentException("Invalid rank ${location.rank}")
        }
        while (current!!.location.file.ordinal < location.file.ordinal) {
            current = current.right ?: throw IllegalArgumentException("Invalid file ${location.file}")
        }
        return current
    }

    fun nextField(location: Location): Field { // next means go one right if possible, else switch rank 1 down
        val fileIndex = location.file.ordinal
        val rank = location.rank
        if (location.file == File.H && location.rank == 1) return getField( //last field
            Location(File.H, 1)
        )

        return if (fileIndex < File.values().lastIndex) {
            getField(Location(File.values()[fileIndex + 1], rank))   // next file, same rank
        } else if (rank > 1) {
            getField(Location(File.A, rank - 1))  // beginning of the next rank, down
        } else {
            throw IllegalArgumentException("No next field available from ${location.description}")
        }
    }

    private fun removePieceFromField(location: Location) {
        getField(location).piece = null
    }

    fun setPieceToField(location: Location, piece: Piece) {
        getField(location).piece = piece
    }

    fun movePiece(move: Move) {
        setPieceToField(move.endLocation, move.movingPiece)
        removePieceFromField(move.startLocation)
    }
}
