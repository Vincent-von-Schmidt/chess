package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Rook
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveRookTest : AnnotationSpec() {

  @Test
  fun `rook moves from h1 to g1`() {
    val rook = Rook(Color.WHITE)
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.G, Rank.ONE)
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
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.A, Rank.ONE)
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
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.H, Rank.TWO)
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
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.H, Rank.EIGHT)
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