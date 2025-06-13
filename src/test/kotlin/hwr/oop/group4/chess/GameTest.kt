package hwr.oop.group4.chess

import hwr.oop.group4.chess.core.game.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.MoveDesired
import hwr.oop.group4.chess.core.pieces.Pawn
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import hwr.oop.group4.chess.persistence.GameStorage
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
    val boardString = game.boardToAscii()

    // Then
    assertThat(boardString).isEqualTo("r n b q k b n r \np p p p p p p p \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \nP P P P P P P P \nR N B Q K B N R \n")
  }

  @Test
  fun `board to string after move`() {
    // Given
    val game = Game(TEST_NUMBER)
    val moveDesired = MoveDesired(Location(File.E, Rank.TWO), Location(File.E, Rank.THREE))
    game.movePiece(moveDesired, promoteTo = null)

    // When
    val boardStringAfterMove = game.boardToAscii()

    // Then
    assertThat(boardStringAfterMove).isEqualTo("r n b q k b n r \np p p p p p p p \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \n- - - - P - - - \nP P P P - P P P \nR N B Q K B N R \n")
  }

  @Test
  fun `game move piece`() {
    GameStorage.deleteGame(Game(TEST_NUMBER))
    // Given
    val game = Game(TEST_NUMBER)
    val moveDesired = MoveDesired(Location(File.E, Rank.TWO), Location(File.E, Rank.THREE))

    // When
    game.movePiece(moveDesired)

    // Then
    assertThat(game.fen).isNotEqualTo(STARTING_POSITION)
    assertThat(game.board.getField(Location(File.E, Rank.TWO)).piece).isNull()
    assertThat(
      game.board.getField(
        Location(
          File.E,
          Rank.THREE
        )
      ).piece
    ).isEqualTo(Pawn(Color.WHITE))
  }

  @Test
  fun `both players make turns`() {
    GameStorage.deleteGame(Game(TEST_NUMBER))
    // Given
    val game = Game(TEST_NUMBER)
    val moveDesiredWhite =
      MoveDesired(Location(File.E, Rank.TWO), Location(File.E, Rank.THREE))
    val moveDesiredBlack =
      MoveDesired(Location(File.E, Rank.SEVEN), Location(File.E, Rank.SIX))

    // When
    game.movePiece(moveDesiredWhite, promoteTo = null)
    game.movePiece(moveDesiredBlack, promoteTo = null)

    // Then
    assertThat(game.boardToAscii()).isEqualTo("r n b q k b n r \np p p p - p p p \n- - - - p - - - \n- - - - - - - - \n- - - - - - - - \n- - - - P - - - \nP P P P - P P P \nR N B Q K B N R \n")
  }
}