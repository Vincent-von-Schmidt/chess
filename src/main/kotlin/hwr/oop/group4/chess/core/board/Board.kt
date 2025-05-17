package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece
import hwr.oop.group4.chess.core.move.Move

class Board {
    val root: Field = generateFirstFieldOfRank() // ist A1

    private fun generateFirstFieldOfRank(): Field {
        var previousRankStart: Field? = null
        var firstRankStart: Field? = null

        for (currentRank in 1..8) {
            val currentRankStart = generateRank(currentRank, previousRankStart)
            if (currentRank == 1) {
                firstRankStart = currentRankStart
            }
            previousRankStart = currentRankStart
        }
        return firstRankStart!! //A1
    }

    private fun generateRank(rank: Int, previousRankStart: Field?): Field {
        var currentField = Field(Location(File.values()[0], rank)) // first Field of Rank + moving pointer to the current Field
        val rankStart: Field = currentField // stationary pointer to first Field of Rank
        var bottomField: Field? = previousRankStart // moving pointer to the Field below the current Field

        for (file in 1..8) {
            val hasBottomField: Boolean = bottomField != null
            val isNotLastFile: Boolean = file < 8

            if (hasBottomField) {
                currentField.bottom = bottomField
                bottomField!!.top = currentField
            }

            if (isNotLastFile) { // new field on the right of current
                val newField = Field(Location(File.values()[file],rank))
                currentField.right = newField
                newField.left = currentField
                currentField = newField
            }

            if (hasBottomField && isNotLastFile) {
                bottomField = bottomField!!.right!!
            }
        }
        return rankStart
    }

    fun getField(location: Location): Field {
        var current: Field? = root

        while(current!!.location.rank < location.rank) {
            current = current.top ?: throw IllegalArgumentException("Invalid rank ${location.rank}")
        }
        while (current!!.location.file.ordinal < location.file.ordinal) {
            current = current.right ?: throw IllegalArgumentException("Invalid file ${location.file}")
        }
        return current
    }

    fun nextField(location: Location): Field { // next means go one right if possible, else switch rank 1 down
        val fileIndex = location.file.ordinal
        val rank = location.rank

        return if (fileIndex < File.values().lastIndex) {
            getField(Location(File.values()[fileIndex + 1], rank))   // next file, same rank
        } else if (rank > 1) {
            getField(Location(File.A, rank - 1))  // beginning of the next rank, down
        } else {
            getField(location) // last Field with no successor (H1) returns H1
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
