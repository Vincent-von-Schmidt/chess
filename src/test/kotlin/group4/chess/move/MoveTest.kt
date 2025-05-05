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

    @Test
    fun `pawn moves correctly`() {
        val board = Board()
        val blackPawn = Pawn(Color.BLACK)
        val whitePawn = Pawn(Color.WHITE)
        val startLocation = Location('d', 5)

        val endLocationd4 = Location('d', 4)  // unten
        val endLocationd6 = Location('d', 6) // oben
        val endlocationh1 = Location('h', 1) // illegaler move

        board.setPieceToField(startLocation, blackPawn)
        var move = Move(startLocation, endLocationd4, blackPawn)
        board.movePiece(move)

        assertThat(board.getField(startLocation).piece).isEqualTo(null)
        assertThat(board.getField(endLocationd4).piece).isEqualTo(blackPawn)

        board.setPieceToField(startLocation, whitePawn)
        move = Move(startLocation, endLocationd6, whitePawn)
        board.movePiece(move)

        assertThat(board.getField(startLocation).piece).isEqualTo(null)
        assertThat(board.getField(endLocationd6).piece).isEqualTo(whitePawn)

        move = Move(endLocationd6, startLocation, whitePawn)
        assertThatThrownBy {
            board.movePiece(move)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("WHITE Pawn can not be moved to d5")

        move = Move(endLocationd4, startLocation, blackPawn)
        assertThatThrownBy {
            board.movePiece(move)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("BLACK Pawn can not be moved to d5")

        move = Move(endLocationd4, endlocationh1, blackPawn)
        assertThatThrownBy {
            board.movePiece(move)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("BLACK Pawn can not be moved to h1")
    }

    @Test
    fun `king moves correctly`() {
        val board = Board()
        val king = King(Color.WHITE)
        val startLocation = Location('e', 1)
        val endlocationh1 = Location('h', 1) // illegaler move

        val endLocations = listOf(
            Location('d', 1),  // links
            Location('d', 2), // oben links
            Location('e', 2),  // oben
            Location('f', 2),   // oben rechts
            Location('f', 1),  //  rechts
        )

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, king)
            val move = Move(startLocation, endLocation, king)
            board.movePiece(move)

            assertThat(board.getField(startLocation).piece).isEqualTo(null)
            assertThat(board.getField(endLocation).piece).isEqualTo(king)
            assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.WHITE)
        }

        board.setPieceToField(startLocation, king)
        val move = Move(startLocation, endlocationh1, king)

        assertThatThrownBy {
            board.movePiece(move)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("WHITE King can not be moved to h1")
    }

    @Test

    fun `knight moves correctly`() {
        val board = Board()
        val knight = Knight(Color.BLACK)
        val startLocation = Location('d', 4)

        val endLocations = listOf(
            Location('b', 5),
            Location('c', 6),
            Location('e', 6),
            Location('f', 5),
            Location('b', 3),
            Location('c', 2),
            Location('e', 2),
            Location('f', 3),

            )
        val endlocationh1 = Location('h', 1) // illegaler move

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, knight)
            val move = Move(startLocation, endLocation, knight)
            board.movePiece(move)

            assertThat(board.getField(startLocation).piece).isEqualTo(null)
            assertThat(board.getField(endLocation).piece).isEqualTo(knight)
            assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.BLACK)
        }

        board.setPieceToField(startLocation, knight)
        val move = Move(startLocation, endlocationh1, knight)

        assertThatThrownBy {
            board.movePiece(move)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("BLACK Knight can not be moved to h1")
    }

    @Test
    fun `queen moves correctly`(){
        val board = Board()
        val queen = Queen(Color.WHITE)
        val startLocation = Location('d', 1)

        val endLocations = listOf(
            Location('c', 1),  // direkt links
            Location('a', 1), // weiter links
            Location('e', 1),  // direkt rechts
            Location('h', 1),   // weiter rechts
            Location('d', 2),  // direkt oben
            Location('d', 8), // weiter oben
            Location('c', 2),  // direkt diagonal links
            Location('a', 4), // weiter diagonal links
            Location('e', 2),  // direkt diagonal rechts
            Location('h', 5), // weiter diagonal rechts
        )
        val endlocationc3 = Location('c', 3) // illegaler move

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, queen)
            val move = Move(startLocation, endLocation, queen)
            board.movePiece(move)

            assertThat(board.getField(startLocation).piece).isEqualTo(null)
            assertThat(board.getField(endLocation).piece).isEqualTo(queen)
            assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.WHITE)
        }

        board.setPieceToField(startLocation, queen)
        val move = Move(startLocation, endlocationc3, queen)

        assertThatThrownBy {
            board.movePiece(move)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("WHITE Queen can not be moved to c3")
    }

    @Test
    fun `bishop moves correctly`(){
        val board = Board()
        val bishop = Bishop(Color.BLACK)
        val startLocation = Location('c', 8)

        val endLocations = listOf(
            Location('b', 7),  // direkt diagonal links unten
            Location('a', 6), // weiter diagonal links unten
            Location('d', 7),  // direkt diagonal rechts unten
            Location('h', 3),   // weiter diagonal rechts unten
        )
        val endlocationc5 = Location('c', 5) // illegaler diagonal move

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, bishop)
            val move = Move(startLocation, endLocation, bishop)
            board.movePiece(move)

            assertThat(board.getField(startLocation).piece).isEqualTo(null)
            assertThat(board.getField(endLocation).piece).isEqualTo(bishop)
            assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.BLACK)
        }

        board.setPieceToField(startLocation, bishop)
        val move = Move(startLocation, endlocationc5, bishop)

        assertThatThrownBy {
            board.movePiece(move)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("BLACK Bishop can not be moved to c5")
    }

    @Test
    fun `rook moves correctly`(){
        val board = Board()
        val rook = Rook(Color.WHITE)
        val startLocation = Location('h', 1)

        val endLocations = listOf(
            Location('g', 1),  // direkt links
            Location('a', 1), // weiter links
            Location('h', 2),  // direkt oben
            Location('h', 8),   // weiter oben
        )
        val endlocationg2 = Location('g', 2) // illegaler diagonal move

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, rook)
            val move = Move(startLocation, endLocation, rook)
            board.movePiece(move)

            assertThat(board.getField(startLocation).piece).isEqualTo(null)
            assertThat(board.getField(endLocation).piece).isEqualTo(rook)
            assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.WHITE)
        }

        board.setPieceToField(startLocation, rook)
        val move = Move(startLocation, endlocationg2, rook)

        assertThatThrownBy {
            board.movePiece(move)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("WHITE Rook can not be moved to g2")
    }
}