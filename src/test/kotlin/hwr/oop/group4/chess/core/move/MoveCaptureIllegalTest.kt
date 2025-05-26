package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Color
import hwr.oop.group4.chess.core.pieces.Pawn
import hwr.oop.group4.chess.core.pieces.Queen
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThatThrownBy

class MoveCaptureIllegalTest : AnnotationSpec() {

  @Test
  fun `pawn black throw on wrong capture white queen`() {
    // Given
    val game = Game(TEST_NUMBER)
    val pawn = Pawn(Color.BLACK)
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.THREE)
    val endLocation = Location(File.D, Rank.FOUR)
    game.board.setPieceToField(startLocation, pawn)
    game.board.setPieceToField(endLocation, queen)
    game.turn.switchTurn()

    // When
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("BLACK Pawn can not be moved to D4")
  }
}
