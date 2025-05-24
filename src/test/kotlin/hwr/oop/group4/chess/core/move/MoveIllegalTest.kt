package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.*
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import hwr.oop.group4.chess.persistence.GameStorage
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThatThrownBy

class MoveIllegalTest : AnnotationSpec() {

  private val storage = GameStorage()

  @Test
  fun `empty field is immovable`() {
    // Given
    val game = Game(TEST_NUMBER)
    val startLocation = Location(File.A, 2)
    val endLocation = Location(File.H, 7)

    // When
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("A2 does not contain a piece")
    storage.deleteGame(game)
  }

  @Test
  fun `field with wrong piece is immovable`() {
    // Given
    val game = Game(TEST_NUMBER)
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.A, 2)
    val endLocation = Location(File.H, 7)
    game.board.setPieceToField(startLocation, queen)

    // When
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("WHITE Queen can not be moved to H7")
  }

}
