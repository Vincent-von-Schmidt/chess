package group4.chess

import group4.chess.board.Board
import group4.chess.board.Location
import group4.chess.move.Move
import group4.chess.pieces.*
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class MoveTest: AnnotationSpec() {
    @Test
    fun `a piece is movable`() {
        val board = Board()
        val pawn = Pawn(Color.WHITE)
        val startLocation = Location('a', 2)
        val endLocation = Location('a', 3)

        board.setPieceToField(startLocation, pawn)

        val move = Move(board, startLocation, endLocation, pawn)
        move.movePiece()

        assertThat(board.getField(startLocation).piece).isEqualTo(null)
        assertThat(board.getField(endLocation).piece).isEqualTo(pawn)
        assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.WHITE)
    }

    @Test
    fun `pawn moves correctly`() { // zu ende schreiben
        val board = Board()
        val blackPawn = Pawn(Color.BLACK)
        val whitePawn = Pawn(Color.WHITE)
        val startLocation = Location('d', 5)

        val endLocationd4 = Location('d', 4)  // oben
        val endLocationd6 = Location('d', 6) // unten
        val endlocationh1 = Location('h', 1) // illegaler move

        board.setPieceToField(startLocation, blackPawn)
        var move = Move(board, startLocation, endLocationd4, blackPawn)
        move.movePiece()

        assertThat(board.getField(startLocation).piece).isEqualTo(null)
        assertThat(board.getField(endLocationd4).piece).isEqualTo(blackPawn)

        board.setPieceToField(startLocation, whitePawn)
        move = Move(board, startLocation, endLocationd6, whitePawn)
        move.movePiece()

        assertThat(board.getField(startLocation).piece).isEqualTo(null)
        assertThat(board.getField(endLocationd6).piece).isEqualTo(whitePawn)

        move = Move(board, endLocationd6, startLocation, whitePawn)
        assertThatThrownBy {
            move.movePiece()
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("WHITE Pawn can not be moved to d5")

        move = Move(board, endLocationd4, startLocation, blackPawn)
        assertThatThrownBy {
            move.movePiece()
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("BLACK Pawn can not be moved to d5")

        move = Move(board, endLocationd4, endlocationh1, blackPawn)
        assertThatThrownBy {
            move.movePiece()
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("BLACK Pawn can not be moved to h1")
    }

    @Test
    fun `king moves correctly`() {
        val board = Board()
        val king = King(Color.WHITE)
        val startLocation = Location('e', 1)

        val endLocations = listOf(
            Location('d', 1),  // links
            Location('d', 2), // oben links
            Location('e', 2),  // oben
            Location('f', 2),   // oben rechts
            Location('f', 1),  //  rechts
        )

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, king)
            val move = Move(board, startLocation, endLocation, king)
            move.movePiece()

            assertThat(board.getField(startLocation).piece).isEqualTo(null)
            assertThat(board.getField(endLocation).piece).isEqualTo(king)
            assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.WHITE)
        }

        board.setPieceToField(startLocation, king)
        val move = Move(board, startLocation, startLocation, king)

        assertThatThrownBy {
            move.movePiece()
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("WHITE King can not be moved to e1")
    }

    @Test
    fun `empty field is immovable`() {
        val board = Board()
        val startLocation = Location('a', 2)
        val endLocation = Location('h', 7)
        val pawn = Pawn(Color.WHITE)

        val move = Move(board, startLocation, endLocation, pawn)

        assertThatThrownBy {
            move.movePiece()
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("a2 does not contain a WHITE Pawn")
    }

    @Test
    fun `field with wrong piece is immovable`() {
        val board = Board()
        val pawn = Pawn(Color.WHITE)
        val queen = Queen(Color.WHITE)
        val startLocation = Location('a', 2)
        val endLocation = Location('h', 7)

        board.setPieceToField(startLocation, queen)

        val move = Move(board, startLocation, endLocation, pawn)

        assertThatThrownBy {
            move.movePiece()
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("a2 does not contain a WHITE Pawn")
    }
}