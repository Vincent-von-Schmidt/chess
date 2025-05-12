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

        assertThat(board.getField(startLocation).piece).isNull()
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
        }.hasMessageContaining("a2 does not contain")
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
        }.hasMessageContaining("a2 does not contain a WHITE Pawn")
    }

    @Test
    fun `pawn black throw on move from d5 to d6`() {
        val board = Board()
        val pawn = Pawn(Color.BLACK)

        val startLocation = Location('d', 5)
        val endLocation = Location('d', 6) // illegal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)
        assertThatThrownBy {
            board.movePiece(move)
        }.hasMessageContaining("BLACK Pawn can not be moved to d6")
    }

    @Test
    fun `pawn black throw on move from d5 to h8`() {
        val board = Board()
        val pawn = Pawn(Color.BLACK)

        val startLocation = Location('d', 5)
        val endLocation = Location('h', 8) // illegal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)
        assertThatThrownBy {
            board.movePiece(move)
        }.hasMessageContaining("BLACK Pawn can not be moved to h8")
    }

    @Test
    fun `pawn black moves from d5 to d4`() {
        val board = Board()
        val pawn = Pawn(Color.BLACK)

        val startLocation = Location('d', 5)
        val endLocation = Location('d', 4) // legal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)
        board.movePiece(move)

        assertThat(board.getField(startLocation).piece).isNull()
        assertThat(board.getField(endLocation).piece).isEqualTo(pawn)
    }

    @Test
    fun `pawn white throw on move from d5 to d4`() {
        val board = Board()
        val pawn = Pawn(Color.WHITE)

        val startLocation = Location('d', 5)
        val endLocation = Location('d', 4) // illegal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)

        assertThatThrownBy {
            board.movePiece(move)
        }.hasMessageContaining("WHITE Pawn can not be moved to d4")
    }

    @Test
    fun `pawn white throw on move from d5 to h8`() {
        val board = Board()
        val pawn = Pawn(Color.WHITE)

        val startLocation = Location('d', 5)
        val endLocation = Location('h', 8) // illegal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)

        assertThatThrownBy {
            board.movePiece(move)
        }.hasMessageContaining("WHITE Pawn can not be moved to h8")
    }

    @Test
    fun `pawn white moves from d5 to d6`() {
        val board = Board()
        val pawn = Pawn(Color.WHITE)

        val startLocation = Location('d', 5)
        val endLocation = Location('d', 6) // legal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)

        board.movePiece(move)

        assertThat(board.getField(startLocation).piece).isNull()
        assertThat(board.getField(endLocation).piece).isEqualTo(pawn)
    }

    @Test
    fun `king moves from e1 to d1, d2, e2, f2, f1`() {
        val board = Board()
        val king = King(Color.WHITE)
        val startLocation = Location('e', 1)

        val endLocations = listOf(
            Location('d', 1),  // left
            Location('d', 2),  // top left
            Location('e', 2),  // top
            Location('f', 2),  // top right
            Location('f', 1),  // right
        )

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, king)
            val move = Move(startLocation, endLocation, king)
            board.movePiece(move)

            assertThat(board.getField(startLocation).piece).isNull()
            assertThat(board.getField(endLocation).piece).isEqualTo(king)
            assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.WHITE)
        }
    }

    @Test
    fun `king throw on move from e1 to h8`() {
        val board = Board()
        val king = King(Color.WHITE)

        val startLocation = Location('e', 1)
        val endLocation = Location('h', 8) // illegal move

        board.setPieceToField(startLocation, king)
        val move = Move(startLocation, endLocation, king)

        assertThatThrownBy {
            board.movePiece(move)
        }.hasMessageContaining("WHITE King can not be moved to h8")
    }

    @Test

    fun `knight moves from d4 to b5, c6, e6, f5, b3, c2, e2, f3`() {
        val board = Board()
        val knight = Knight(Color.BLACK)
        val startLocation = Location('d', 4)

        val endLocations = listOf(
            Location('b', 5),  // up 1, left 2
            Location('c', 6),  // up 2, left 1
            Location('e', 6),  // up 2, right 1
            Location('f', 5),  // up 1, right 2
            Location('b', 3),  // down 1, left 2
            Location('c', 2),  // down 2, left 1
            Location('e', 2),  // down 2, right 1
            Location('f', 3),  // down 1, right 2
            )

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, knight)
            val move = Move(startLocation, endLocation, knight)
            board.movePiece(move)

            assertThat(board.getField(startLocation).piece).isNull()
            assertThat(board.getField(endLocation).piece).isEqualTo(knight)
            assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.BLACK)
        }
    }

    @Test
    fun `knight throw on move from d4 to h8`() {
        val board = Board()
        val knight = Knight(Color.BLACK)

        val startLocation = Location('d', 4)
        val endLocation = Location('h', 8) // illegal move

        board.setPieceToField(startLocation, knight)
        val move = Move(startLocation, endLocation, knight)

        assertThatThrownBy {
            board.movePiece(move)
        }.hasMessageContaining("BLACK Knight can not be moved to h8")
    }

    @Test
    fun `queen moves correctly`(){
        val board = Board()
        val queen = Queen(Color.WHITE)
        val startLocation = Location('d', 1)

        val endLocations = listOf(
            Location('c', 1),  // directly left
            Location('a', 1),  // further left
            Location('e', 1),  // directly right
            Location('h', 1),  // further right
            Location('d', 2),  // directly above
            Location('d', 8),  // further above
            Location('c', 2),  // directly diagonal left
            Location('a', 4),  // further diagonal left
            Location('e', 2),  // directly diagonal right
            Location('h', 5),  // further diagonal right
        )
        val endlocationc3 = Location('c', 3) // illegal move

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
    fun `queen throw on move from d4 to h8`() {
        val board = Board()
            val queen = Queen(Color.WHITE)

        val startLocation = Location('d', 4)
        val endLocation = Location('h', 8) // illegal move

        board.setPieceToField(startLocation, queen)
        val move = Move(startLocation, endLocation, queen)

        assertThatThrownBy {
            board.movePiece(move)
        }.hasMessageContaining("WHITE Queen can not be moved to h8")
    }

    @Test
    fun `bishop moves correctly`(){
        val board = Board()
        val bishop = Bishop(Color.BLACK)
        val startLocation = Location('c', 8)

        val endLocations = listOf(
            Location('b', 7),  // directly bottom-left diagonal
            Location('a', 6),  // further bottom-left diagonal
            Location('d', 7),  // directly bottom-right diagonal
            Location('h', 3),  // further bottom-right diagonal
        )
        val endlocationc5 = Location('c', 5) // illegal diagonal move

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
    fun `bishop throw on move from d4 to h8`() {
        val board = Board()
        val bishop = Bishop(Color.BLACK)

        val startLocation = Location('d', 4)
        val endLocation = Location('h', 8) // illegal move

        board.setPieceToField(startLocation, bishop)
        val move = Move(startLocation, endLocation, bishop)

        assertThatThrownBy {
            board.movePiece(move)
        }.hasMessageContaining("BLACK Bishop can not be moved to h8")
    }

    @Test
    fun `rook moves correctly`(){
        val board = Board()
        val rook = Rook(Color.WHITE)
        val startLocation = Location('h', 1)

        val endLocations = listOf(
            Location('g', 1),  // directly left
            Location('a', 1),  // further left
            Location('h', 2),  // directly above
            Location('h', 8),  // further above
        )
        val endlocationg2 = Location('g', 2) // illegal diagonal move

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

        @Test
        fun `rook throw on move from d4 to h8`() {
            val board = Board()
            val rook = Rook(Color.BLACK)

            val startLocation = Location('d', 4)
            val endLocation = Location('h', 8) // illegal move

            board.setPieceToField(startLocation, rook)
            val move = Move(startLocation, endLocation, rook)

            assertThatThrownBy {
                board.movePiece(move)
            }.hasMessageContaining("BLACK Rook can not be moved to h8")

        }
}