package hwr.oop.group4.chess

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Move
import hwr.oop.group4.chess.core.pieces.Pawn
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class GameTest : AnnotationSpec() {

  @Test
  fun `init game`() {
    // Given
    val id = TEST_NUMBER

    // When
    val game = Game(id)

    // Then
    assertThat(game.id).isEqualTo(id)
    assertThat(game.fen).isEqualTo(STARTING_POSITION)
  }

  @Test
  fun `game board to string`() {
    // Given
    val game = Game(TEST_NUMBER)

    // When
    val boardString = game.boardToString()

    // Then
    assertThat(boardString).isEqualTo("r n b q k b n r \np p p p p p p p \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \nP P P P P P P P \nR N B Q K B N R \n")
  }

  @Test
  fun `board to string after move`() {
    // Given
    val game = Game(TEST_NUMBER)
    val move = Move(Location(File.E, 2), Location(File.E, 3))
    game.movePiece(move)

    // When
    val boardStringAfterMove = game.boardToString()

    // Then
    assertThat(boardStringAfterMove).isEqualTo("r n b q k b n r \np p p p p p p p \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \n- - - - P - - - \nP P P P - P P P \nR N B Q K B N R \n")
  }

  @Test
  fun `game move piece`() {
    // Given
    val game = Game(TEST_NUMBER)
    val move = Move(Location(File.E, 2), Location(File.E, 3))

    // When
    game.movePiece(move)

    // Then
    assertThat(game.fen).isNotEqualTo(STARTING_POSITION)
    assertThat(game.board.getField(Location(File.E, 2)).piece).isNull()
    assertThat(
      game.board.getField(
        Location(
          File.E,
          3
        )
      ).piece
    ).isInstanceOf(Pawn::class.java)
  }

  @Test
  fun `both players make turns`() {
    // Given
    val game = Game(TEST_NUMBER)
    val moveWhite = Move(Location(File.E, 2), Location(File.E, 3))
    val moveBlack = Move(Location(File.E, 7), Location(File.E, 6))

    // When
    game.movePiece(moveWhite)
    game.movePiece(moveBlack)

    // Then
    assertThat(game.boardToString()).isEqualTo("r n b q k b n r \np p p p - p p p \n- - - - p - - - \n- - - - - - - - \n- - - - - - - - \n- - - - P - - - \nP P P P - P P P \nR N B Q K B N R \n")
  }

}