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

        val move = Move(startLocation, endLocation, pawn)
        board.movePiece(move)

        assertThat(board.getField(startLocation).piece).isEqualTo(null)
        assertThat(board.getField(endLocation).piece).isEqualTo(pawn)
        assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.WHITE)
    }

    @Test
    fun `empty field is immovable`() {
        val board = Board()
        val startLocation = Location('a', 2)
        val endLocation = Location('h', 7)
        val pawn = Pawn(Color.WHITE)

        val move = Move(startLocation, endLocation, pawn)

        assertThatThrownBy {
            board.movePiece(move)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("a2 does not contain")
    }

    @Test
    fun `field with wrong piece is immovable`() {
        val board = Board()
        val pawn = Pawn(Color.WHITE)
        val queen = Queen(Color.WHITE)
        val startLocation = Location('a', 2)
        val endLocation = Location('h', 7)

        board.setPieceToField(startLocation, queen)

        val move = Move(startLocation, endLocation, pawn)

        assertThatThrownBy {
            board.movePiece(move)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("a2 does not contain a group4.chess.pieces.Pawn")
    }
}