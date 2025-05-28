package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.King
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveKingTest : AnnotationSpec() {

  @Test
  fun `king moves from e1 to d1`() {
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.D, Rank.ONE)
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
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.D, Rank.TWO)
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
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.E, Rank.TWO)
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
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.F, Rank.TWO)
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
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.F, Rank.ONE)
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    game.board.setPieceToField(startLocation, king)

    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }
}