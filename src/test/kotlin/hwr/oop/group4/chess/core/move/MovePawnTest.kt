package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Pawn
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MovePawnTest : AnnotationSpec() {

  @Test
  fun `pawn black moves from d5 to d4`() {
    // Given
    val game = Game(TEST_NUMBER)
    val pawn = Pawn(Color.BLACK)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.FOUR)
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
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.SIX) // legal move
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
}
