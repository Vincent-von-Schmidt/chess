package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.*
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveLegalTest : AnnotationSpec() {

  // TODO("Refactor this class into seperated classes")

  @Test
  fun `pawn black moves from d5 to d4`() {
    // Given
    val game = Game(TEST_NUMBER)
    val pawn = Pawn(Color.BLACK)
    val startLocation = Location(File.D, 5)
    val endLocation = Location(File.D, 4)
    game.board.setPieceToField(startLocation, pawn)

    // When
    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(pawn)
  }

  @Test
  fun `pawn white moves from d5 to d6`() {
    // Given
    val game = Game(TEST_NUMBER)
    val pawn = Pawn(Color.WHITE)
    val startLocation = Location(File.D, 5)
    val endLocation = Location(File.D, 6) // legal move
    game.board.setPieceToField(startLocation, pawn)

    // When
    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(pawn)
  }

  @Test
  fun `king moves from e1 to d1`() {
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, 1)
    val endLocation = Location(File.D, 1)
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    game.board.setPieceToField(startLocation, king)

    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to d2`() {
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, 1)
    val endLocation = Location(File.D, 2)
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    game.board.setPieceToField(startLocation, king)

    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to e2`() {
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, 1)
    val endLocation = Location(File.E, 2)
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    game.board.setPieceToField(startLocation, king)

    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to f2`() {
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, 1)
    val endLocation = Location(File.F, 2)
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    game.board.setPieceToField(startLocation, king)

    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to f1`() {
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, 1)
    val endLocation = Location(File.F, 1)
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    game.board.setPieceToField(startLocation, king)

    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }


  @Test
  fun `knight moves from d4 to b5`() {
    val game = Game(TEST_NUMBER)
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, 4)
    val endLocation = Location(File.B, 5)
    game.board.setPieceToField(startLocation, knight)

    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to c6`() {
    val game = Game(TEST_NUMBER)
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, 4)
    val endLocation = Location(File.C, 6)
    game.board.setPieceToField(startLocation, knight)

    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to e6`() {
    val game = Game(TEST_NUMBER)
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, 4)
    val endLocation = Location(File.E, 6)
    game.board.setPieceToField(startLocation, knight)

    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to f5`() {
    val game = Game(TEST_NUMBER)
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, 4)
    val endLocation = Location(File.F, 5)
    game.board.setPieceToField(startLocation, knight)

    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to b3`() {
    val game = Game(TEST_NUMBER)
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, 4)
    val endLocation = Location(File.B, 3)
    game.board.setPieceToField(startLocation, knight)

    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to c2`() {
    val game = Game(TEST_NUMBER)
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, 4)
    val endLocation = Location(File.C, 2)
    game.board.setPieceToField(startLocation, knight)

    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to e2`() {
    val game = Game(TEST_NUMBER)
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, 4)
    val endLocation = Location(File.E, 2)
    game.board.setPieceToField(startLocation, knight)

    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to f3`() {
    val game = Game(TEST_NUMBER)
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, 4)
    val endLocation = Location(File.F, 3)
    game.board.setPieceToField(startLocation, knight)

    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `queen moves from d1 to c1`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, 1)
    val endLocation = Location(File.C, 1)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to a1`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, 1)
    val endLocation = Location(File.A, 1)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to e1`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, 1)
    val endLocation = Location(File.E, 1)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to h1`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, 1)
    val endLocation = Location(File.H, 1)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to d2`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, 1)
    val endLocation = Location(File.D, 2)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to d8`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, 1)
    val endLocation = Location(File.D, 8)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to a4`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, 1)
    val endLocation = Location(File.A, 4)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to e2`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, 1)
    val endLocation = Location(File.E, 2)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to h5`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, 1)
    val endLocation = Location(File.H, 5)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to c2`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, 1)
    val endLocation = Location(File.C, 2)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `bishop moves from c8 to b7`() {
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, 8)
    val endLocation = Location(File.B, 7)
    game.board.setPieceToField(startLocation, bishop)

    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }

  @Test
  fun `bishop moves from c8 to a6`() {
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, 8)
    val endLocation = Location(File.A, 6)
    game.board.setPieceToField(startLocation, bishop)

    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }

  @Test
  fun `bishop moves from c8 to d7`() {
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, 8)
    val endLocation = Location(File.D, 7)
    game.board.setPieceToField(startLocation, bishop)

    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }

  @Test
  fun `bishop moves from c8 to h3`() {
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, 8)
    val endLocation = Location(File.H, 3)
    game.board.setPieceToField(startLocation, bishop)

    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }

  @Test
  fun `rook moves from h1 to g1`() {
    val rook = Rook(Color.WHITE)
    val startLocation = Location(File.H, 1)
    val endLocation = Location(File.G, 1)
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    game.board.setPieceToField(startLocation, rook)

    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(rook)
  }

  @Test
  fun `rook moves from h1 to a1`() {
    val rook = Rook(Color.WHITE)
    val startLocation = Location(File.H, 1)
    val endLocation = Location(File.A, 1)
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    game.board.setPieceToField(startLocation, rook)

    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(rook)
  }

  @Test
  fun `rook moves from h1 to h2`() {
    val rook = Rook(Color.WHITE)
    val startLocation = Location(File.H, 1)
    val endLocation = Location(File.H, 2)
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    game.board.setPieceToField(startLocation, rook)

    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(rook)
  }

  @Test
  fun `rook moves from h1 to h8`() {
    val rook = Rook(Color.WHITE)
    val startLocation = Location(File.H, 1)
    val endLocation = Location(File.H, 8)
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    game.board.setPieceToField(startLocation, rook)

    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(rook)
  }
}
