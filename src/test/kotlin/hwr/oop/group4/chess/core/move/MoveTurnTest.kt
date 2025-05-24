package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.*
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import hwr.oop.group4.chess.persistence.GameStorage
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class MoveTurnTest : AnnotationSpec() {

  @Test
  fun `white player cant move black pawn`() {
    // Given
    val game = Game(TEST_NUMBER)
    val pawn = Pawn(Color.BLACK)
    val startLocation = Location(File.A, 2)
    val endLocation = Location(File.A, 1)
    game.board.setPieceToField(startLocation, pawn)

    // When
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("You can not move a BLACK piece")
  }

  @Test
  fun `black player cant move white queen`() {
    // Given
    val game = Game(TEST_NUMBER)
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.A, 2)
    val endLocation = Location(File.A, 1)
    game.board.setPieceToField(startLocation, queen)
    game.turn.switchTurn()

    // When
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("You can not move a WHITE piece")
  }

}
