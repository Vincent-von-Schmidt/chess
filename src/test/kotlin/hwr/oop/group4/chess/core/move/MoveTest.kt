package core.move

import core.board.Board
import core.Game
import core.location.File
import core.location.Location
import core.pieces.*

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class MoveTest: AnnotationSpec() {

    lateinit var board: Board
    @BeforeEach
    fun setUp() {
        board = Board()
    }

    @Test
    fun `empty field is immovable`() {
        val startLocation = Location(File.A, 2)
        val endLocation = Location(File.H, 7)
        val pawn = Pawn(Color.WHITE)

        val move = Move(startLocation, endLocation, pawn)

        assertThatThrownBy {
            Game(board).movePiece(move)
        }.hasMessageContaining("A2 does not contain a WHITE Pawn")
    }

    @Test
    fun `field with wrong piece is immovable`() {
        val pawn = Pawn(Color.WHITE)
        val queen = Queen(Color.WHITE)
        val startLocation = Location(File.A, 2)
        val endLocation = Location(File.H, 7)

        board.setPieceToField(startLocation, queen)

        val move = Move(startLocation, endLocation, pawn)

        assertThatThrownBy {
            Game(board).movePiece(move)
        }.hasMessageContaining("A2 does not contain a WHITE Pawn")
    }

    @Test
    fun `pawn black throw on move from d5 to d6`() {
        val pawn = Pawn(Color.BLACK)

        val startLocation = Location(File.D, 5)
        val endLocation = Location(File.D, 6) // illegal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)
        assertThatThrownBy {
            Game(board).movePiece(move)
        }.hasMessageContaining("BLACK Pawn can not be moved to D6")
    }

    @Test
    fun `pawn black throw on move from d5 to h8`() {
        val pawn = Pawn(Color.BLACK)

        val startLocation = Location(File.D, 5)
        val endLocation = Location(File.H, 8) // illegal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)
        assertThatThrownBy {
            Game(board).movePiece(move)
        }.hasMessageContaining("BLACK Pawn can not be moved to H8")
    }

    @Test
    fun `pawn black moves from d5 to d4`() {
        val pawn = Pawn(Color.BLACK)

        val startLocation = Location(File.D, 5)
        val endLocation = Location(File.D, 4) // legal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)
        Game(board).movePiece(move)

        assertThat(board.getField(startLocation).piece).isNull()
        assertThat(board.getField(endLocation).piece).isEqualTo(pawn)
    }

    @Test
    fun `pawn white throw on move from d5 to d4`() {
        val pawn = Pawn(Color.WHITE)

        val startLocation = Location(File.D, 5)
        val endLocation = Location(File.D, 4) // illegal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)

        assertThatThrownBy {
            Game(board).movePiece(move)
        }.hasMessageContaining("WHITE Pawn can not be moved to D4")
    }

    @Test
    fun `pawn white throw on move from d5 to h8`() {
        val pawn = Pawn(Color.WHITE)

        val startLocation = Location(File.D, 5)
        val endLocation = Location(File.H, 8) // illegal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)

        assertThatThrownBy {
            Game(board).movePiece(move)
        }.hasMessageContaining("WHITE Pawn can not be moved to H8")
    }

    @Test
    fun `pawn white moves from d5 to d6`() {
        val pawn = Pawn(Color.WHITE)

        val startLocation = Location(File.D, 5)
        val endLocation = Location(File.D, 6) // legal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)

        Game(board).movePiece(move)

        assertThat(board.getField(startLocation).piece).isNull()
        assertThat(board.getField(endLocation).piece).isEqualTo(pawn)
    }

    @Test
    fun `king moves from e1 to d1, d2, e2, f2, f1`() {
        val king = King(Color.WHITE)
        val startLocation = Location(File.E, 1)

        val endLocations = listOf(
            Location(File.D, 1),  // left
            Location(File.D, 2),  // top left
            Location(File.E, 2),  // top
            Location(File.F, 2),  // top right
            Location(File.F, 1),  // right
        )

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, king)
            val move = Move(startLocation, endLocation, king)
            Game(board).movePiece(move)

            assertThat(board.getField(startLocation).piece).isNull()
            assertThat(board.getField(endLocation).piece).isEqualTo(king)
            assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.WHITE)
        }
    }

    @Test
    fun `king throw on move from e1 to h8`() {
        val king = King(Color.WHITE)

        val startLocation = Location(File.E, 1)
        val endLocation = Location(File.H, 8) // illegal move

        board.setPieceToField(startLocation, king)
        val move = Move(startLocation, endLocation, king)

        assertThatThrownBy {
            Game(board).movePiece(move)
        }.hasMessageContaining("WHITE King can not be moved to H8")
    }

    @Test

    fun `knight moves from d4 to b5, c6, e6, f5, b3, c2, e2, f3`() {
        val knight = Knight(Color.BLACK)
        val startLocation = Location(File.D, 4)

        val endLocations = listOf(
            Location(File.B, 5),  // up 1, left 2
            Location(File.C, 6),  // up 2, left 1
            Location(File.E, 6),  // up 2, right 1
            Location(File.F, 5),  // up 1, right 2
            Location(File.B, 3),  // down 1, left 2
            Location(File.C, 2),  // down 2, left 1
            Location(File.E, 2),  // down 2, right 1
            Location(File.F, 3),  // down 1, right 2
            )

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, knight)
            val move = Move(startLocation, endLocation, knight)
            Game(board).movePiece(move)

            assertThat(board.getField(startLocation).piece).isNull()
            assertThat(board.getField(endLocation).piece).isEqualTo(knight)
            assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.BLACK)
        }
    }

    @Test
    fun `knight throw on move from d4 to h8`() {
        val knight = Knight(Color.BLACK)

        val startLocation = Location(File.D, 4)
        val endLocation = Location(File.H, 8) // illegal move

        board.setPieceToField(startLocation, knight)
        val move = Move(startLocation, endLocation, knight)

        assertThatThrownBy {
            Game(board).movePiece(move)
        }.hasMessageContaining("BLACK Knight can not be moved to H8")
    }

    @Test
    fun `queen moves from d1 to c1, a1, e1, h1, d2, d8, c2, a4, e2, h5`(){
        val queen = Queen(Color.WHITE)
        val startLocation = Location(File.D, 1)

        val endLocations = listOf(
            Location(File.C, 1),  // directly left
            Location(File.A, 1),  // further left
            Location(File.E, 1),  // directly right
            Location(File.H, 1),  // further right
            Location(File.D, 2),  // directly above
            Location(File.D, 8),  // further above
            Location(File.A, 4),  // further diagonal left
            Location(File.E, 2),  // directly diagonal right
            Location(File.H, 5),  // further diagonal right
        )

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, queen)
            val move = Move(startLocation, endLocation, queen)
            board.movePiece(move)

            assertThat(board.getField(startLocation).piece).isNull()
            assertThat(board.getField(endLocation).piece).isEqualTo(queen)
            assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.WHITE)
        }
    }

    @Test
    fun `queen throw on move from d4 to h1`() {
        val queen = Queen(Color.WHITE)

        val startLocation = Location(File.D, 4)
        val endLocation = Location(File.H, 1) // illegal move

        board.setPieceToField(startLocation, queen)
        val move = Move(startLocation, endLocation, queen)

        assertThatThrownBy {
            Game(board).movePiece(move)
        }.hasMessageContaining("WHITE Queen can not be moved to H1")
    }

    @Test
    fun `bishop moves from c8 to b7, a6, d7, h3`(){
        val bishop = Bishop(Color.BLACK)
        val startLocation = Location(File.C, 8)

        val endLocations = listOf(
            Location(File.B, 7),  // directly bottom-left diagonal
            Location(File.A, 6),  // further bottom-left diagonal
            Location(File.D, 7),  // directly bottom-right diagonal
            Location(File.H, 3),  // further bottom-right diagonal
        )

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, bishop)
            val move = Move(startLocation, endLocation, bishop)
            Game(board).movePiece(move)

            assertThat(board.getField(startLocation).piece).isNull()
            assertThat(board.getField(endLocation).piece).isEqualTo(bishop)
            assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.BLACK)
        }
    }
    @Test
    fun `bishop throw on move from d4 to h1`() {
        val bishop = Bishop(Color.BLACK)

        val startLocation = Location(File.D, 4)
        val endLocation = Location(File.H, 1) // illegal move

        board.setPieceToField(startLocation, bishop)
        val move = Move(startLocation, endLocation, bishop)

        assertThatThrownBy {
            Game(board).movePiece(move)
        }.hasMessageContaining("BLACK Bishop can not be moved to H1")
    }

    @Test
    fun `rook moves from h1 to g1, a1, h2, h8`(){
        val rook = Rook(Color.WHITE)
        val startLocation = Location(File.H, 1)

        val endLocations = listOf(
            Location(File.G, 1),  // directly left
            Location(File.A, 1),  // further left
            Location(File.H, 2),  // directly above
            Location(File.H, 8),  // further above
        )

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, rook)
            val move = Move(startLocation, endLocation, rook)
            Game(board).movePiece(move)

            assertThat(board.getField(startLocation).piece).isNull()
            assertThat(board.getField(endLocation).piece).isEqualTo(rook)
            assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.WHITE)
        }
    }

    @Test
    fun `rook throw on move from d4 to h8`() {
        val rook = Rook(Color.BLACK)

        val startLocation = Location(File.D, 4)
        val endLocation = Location(File.H, 8) // illegal move

        board.setPieceToField(startLocation, rook)
        val move = Move(startLocation, endLocation, rook)

        assertThatThrownBy {
            Game(board).movePiece(move)
        }.hasMessageContaining("BLACK Rook can not be moved to H8")
    }
}
