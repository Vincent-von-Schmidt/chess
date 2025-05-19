package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.*
import hwr.oop.group4.chess.core.player.Player
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class MoveTest : AnnotationSpec() {

    private lateinit var board: Board
    private lateinit var players: List<Player>

    @BeforeEach
    fun setUp() {
        board = Board()
        players = listOf(Player(1, Color.WHITE), Player(2, Color.BLACK))
    }

    @Test
    fun `empty field is immovable`() {
        val startLocation = Location(File.A, 2)
        val endLocation = Location(File.H, 7)
        val pawn = Pawn(Color.WHITE)

        val move = Move(startLocation, endLocation, pawn)

        assertThatThrownBy {
            Game(board, players).movePiece(move)
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
            Game(board, players).movePiece(move)
        }.hasMessageContaining("A2 does not contain a WHITE Pawn")
    }

    @Test
    fun `white player cant move black pawn`() {
        val pawn = Pawn(Color.BLACK)
        val startLocation = Location(File.A, 2)
        val endLocation = Location(File.A, 1)

        board.setPieceToField(startLocation, pawn)

        val move = Move(startLocation, endLocation, pawn)

        assertThatThrownBy {
            Game(board, players).movePiece(move)
        }.hasMessageContaining("You can not move a BLACK piece")
    }

    @Test
    fun `throw on white pawn move to white queen`() {
        val pawn = Pawn(Color.WHITE)
        val queen = Queen(Color.WHITE)
        val startLocation = Location(File.D, 3)
        val endLocation = Location(File.D, 4)

        board.setPieceToField(startLocation, pawn)
        board.setPieceToField(endLocation, queen)

        val move = Move(startLocation, endLocation, pawn)

        assertThatThrownBy {
            Game(board, players).movePiece(move)
        }.hasMessageContaining("D4 is already occupied with WHITE Queen")
    }

    @Test
    fun `throw on white pawn move to black queen`() {
        val pawn = Pawn(Color.WHITE)
        val queen = Queen(Color.BLACK)
        val startLocation = Location(File.D, 3)
        val endLocation = Location(File.D, 4)

        board.setPieceToField(startLocation, pawn)
        board.setPieceToField(endLocation, queen)

        val move = Move(startLocation, endLocation, pawn)

        assertThatThrownBy {
            Game(board, players).movePiece(move)
        }.hasMessageContaining("D4 is already occupied with BLACK Queen, do you want to capture?")
    }

    @Test
    fun `pawn black throw on wrong capture white queen`() {
        val pawn = Pawn(Color.BLACK)
        val queen = Queen(Color.WHITE)
        val startLocation = Location(File.D, 3)
        val endLocation = Location(File.D, 4)

        board.setPieceToField(startLocation, pawn)
        board.setPieceToField(endLocation, queen)
        val game = Game(board, players)
        game.turn.switchTurn()

        val move = Move(startLocation, endLocation, pawn, true)

        assertThatThrownBy {
            game.movePiece(move)
        }.hasMessageContaining("BLACK Pawn can not capture WHITE Queen at D4")
    }

    @Test
    fun `pawn white captures black queen`() {
        val pawn = Pawn(Color.WHITE)
        val queen = Queen(Color.BLACK)
        val startLocation = Location(File.D, 3)
        val endLocation = Location(File.E, 4)

        board.setPieceToField(startLocation, pawn)
        board.setPieceToField(endLocation, queen)

        val move = Move(startLocation, endLocation, pawn, true)
        Game(board, players).movePiece(move)

        assertThat(board.getField(startLocation).piece).isNull()
        assertThat(board.getField(endLocation).piece).isEqualTo(pawn)
    }

    @Test
    fun `knight white captures black king`() {
        val knight = Knight(Color.WHITE)
        val king = King(Color.BLACK)
        val startLocation = Location(File.D, 3)
        val endLocation = Location(File.F, 4)

        board.setPieceToField(startLocation, knight)
        board.setPieceToField(endLocation, king)

        val move = Move(startLocation, endLocation, knight, true)
        Game(board, players).movePiece(move)

        assertThat(board.getField(startLocation).piece).isNull()
        assertThat(board.getField(endLocation).piece).isEqualTo(knight)
    }

    @Test
    fun `pawn black throw on move from d5 to d6`() {
        val pawn = Pawn(Color.BLACK)

        val startLocation = Location(File.D, 5)
        val endLocation = Location(File.D, 6) // illegal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)
        val game = Game(board, players)
        game.turn.switchTurn()

        assertThatThrownBy {
            game.movePiece(move)
        }.hasMessageContaining("BLACK Pawn can not be moved to D6")
    }

    @Test
    fun `pawn black throw on move from d5 to h8`() {
        val pawn = Pawn(Color.BLACK)

        val startLocation = Location(File.D, 5)
        val endLocation = Location(File.H, 8) // illegal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)
        val game = Game(board, players)
        game.turn.switchTurn()

        assertThatThrownBy {
            game.movePiece(move)
        }.hasMessageContaining("BLACK Pawn can not be moved to H8")
    }

    @Test
    fun `pawn black moves from d5 to d4`() {
        val pawn = Pawn(Color.BLACK)

        val startLocation = Location(File.D, 5)
        val endLocation = Location(File.D, 4) // legal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)
        val game = Game(board, players)
        game.turn.switchTurn()
        game.movePiece(move)

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
            Game(board, players).movePiece(move)
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
            Game(board, players).movePiece(move)
        }.hasMessageContaining("WHITE Pawn can not be moved to H8")
    }

    @Test
    fun `pawn white moves from d5 to d6`() {
        val pawn = Pawn(Color.WHITE)

        val startLocation = Location(File.D, 5)
        val endLocation = Location(File.D, 6) // legal move

        board.setPieceToField(startLocation, pawn)
        val move = Move(startLocation, endLocation, pawn)

        Game(board, players).movePiece(move)

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
            Game(board, players).movePiece(move)

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
            Game(board, players).movePiece(move)
        }.hasMessageContaining("WHITE King can not be moved to H8")
    }

    @Test
    fun `king white captures black rook`() {
        val rook = Rook(Color.BLACK)
        val king = King(Color.WHITE)
        val startLocation = Location(File.D, 3)
        val endLocation = Location(File.E, 4)

        board.setPieceToField(startLocation, king)
        board.setPieceToField(endLocation, rook)

        val move = Move(startLocation, endLocation, king, true)
        Game(board, players).movePiece(move)

        assertThat(board.getField(startLocation).piece).isNull()
        assertThat(board.getField(endLocation).piece).isEqualTo(king)
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
            val game = Game(board, players)
            game.turn.switchTurn()
            game.movePiece(move)

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
        val game = Game(board, players)
        game.turn.switchTurn()

        assertThatThrownBy {
            game.movePiece(move)
        }.hasMessageContaining("BLACK Knight can not be moved to H8")
    }

    @Test
    fun `queen moves from d1 to c1, a1, e1, h1, d2, d8, c2, a4, e2, h5`() {
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
            Game(board, players).movePiece(move)
        }.hasMessageContaining("WHITE Queen can not be moved to H1")
    }

    @Test
    fun `queen white captures black rook`() {
        val rook = Rook(Color.BLACK)
        val queen = Queen(Color.WHITE)
        val startLocation = Location(File.D, 5)
        val endLocation = Location(File.G, 8)

        board.setPieceToField(startLocation, queen)
        board.setPieceToField(endLocation, rook)

        val move = Move(startLocation, endLocation, queen, true)
        Game(board, players).movePiece(move)

        assertThat(board.getField(startLocation).piece).isNull()
        assertThat(board.getField(endLocation).piece).isEqualTo(queen)
    }

    @Test
    fun `bishop moves from c8 to b7, a6, d7, h3`() {
        val bishop = Bishop(Color.BLACK)
        val startLocation = Location(File.C, 8)

        val endLocations = listOf(
            Location(File.B, 7),  // directly bottom-left diagonal
            Location(File.H, 3),  // further bottom-right diagonal
        )

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, bishop)
            val move = Move(startLocation, endLocation, bishop)
            val game = Game(board, players)
            game.turn.switchTurn()
            game.movePiece(move)

            assertThat(board.getField(startLocation).piece).isNull()
            assertThat(board.getField(endLocation).piece).isEqualTo(bishop)
            assertThat(board.getField(endLocation).piece?.color).isEqualTo(Color.BLACK)
        }
    }

    @Test
    fun `bishop throw on interrupted path move`() {
        val bishop = Bishop(Color.BLACK)
        val king = King(Color.BLACK)
        val startLocation = Location(File.C, 8)

        val occupiedLocation =
            Location(File.B, 7)  // directly bottom-left diagonal
        val interruptedLocation =
            Location(File.A, 6)  // further bottom-right diagonal, illegal


        board.setPieceToField(startLocation, bishop)
        board.setPieceToField(occupiedLocation, king)
        val move = Move(startLocation, interruptedLocation, bishop)
        val game = Game(board, players)
        game.turn.switchTurn()

        assertThatThrownBy {
            game.movePiece(move)
        }.hasMessageContaining("BLACK Bishop can not be moved to A6")
    }

    @Test
    fun `bishop throw on move from d4 to h1`() {
        val bishop = Bishop(Color.BLACK)

        val startLocation = Location(File.D, 4)
        val endLocation = Location(File.H, 1) // illegal move

        board.setPieceToField(startLocation, bishop)
        val move = Move(startLocation, endLocation, bishop)
        val game = Game(board, players)
        game.turn.switchTurn()

        assertThatThrownBy {
            game.movePiece(move)
        }.hasMessageContaining("BLACK Bishop can not be moved to H1")
    }

    @Test
    fun `bishop white captures black rook`() {
        val rook = Rook(Color.BLACK)
        val bishop = Bishop(Color.WHITE)
        val startLocation = Location(File.D, 5)
        val endLocation = Location(File.G, 8)

        board.setPieceToField(startLocation, bishop)
        board.setPieceToField(endLocation, rook)

        val move = Move(startLocation, endLocation, bishop, true)
        Game(board, players).movePiece(move)

        assertThat(board.getField(startLocation).piece).isNull()
        assertThat(board.getField(endLocation).piece).isEqualTo(bishop)
    }

    @Test
    fun `rook moves from h1 to g1, a1, h2, h8`() {
        val rook = Rook(Color.WHITE)
        val startLocation = Location(File.H, 1)

        val endLocations = listOf(
            Location(File.G, 1),  // directly left
            Location(File.H, 8),  // further above
        )

        for (endLocation in endLocations) {
            board.setPieceToField(startLocation, rook)
            val move = Move(startLocation, endLocation, rook)
            Game(board, players).movePiece(move)

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
        val game = Game(board, players)
        game.turn.switchTurn()

        assertThatThrownBy {
            game.movePiece(move)
        }.hasMessageContaining("BLACK Rook can not be moved to H8")
    }

    @Test
    fun `rook white captures black bishop`() {
        val bishop = Bishop(Color.BLACK)
        val rook = Rook(Color.WHITE)
        val startLocation = Location(File.D, 5)
        val endLocation = Location(File.D, 1)

        board.setPieceToField(startLocation, rook)
        board.setPieceToField(endLocation, bishop)

        val move = Move(startLocation, endLocation, rook, true)
        Game(board, players).movePiece(move)

        assertThat(board.getField(startLocation).piece).isNull()
        assertThat(board.getField(endLocation).piece).isEqualTo(rook)
    }
}
