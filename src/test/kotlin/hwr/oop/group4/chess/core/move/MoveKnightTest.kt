package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Knight
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveKnightTest : AnnotationSpec() {

  @Test
  fun `knight moves from d4 to b5`() {
    val game = Game(TEST_NUMBER)
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.B, Rank.FIVE)
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
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.C, Rank.SIX)
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
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.E, Rank.SIX)
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
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.F, Rank.FIVE)
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
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.B, Rank.THREE)
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
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.C, Rank.TWO)
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
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.E, Rank.TWO)
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
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.F, Rank.THREE)
    game.board.setPieceToField(startLocation, knight)

    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }
}