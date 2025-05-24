package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.*
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class MoveLegalTest : AnnotationSpec() {

  // TODO("Refactor this class into seperated classes")

  @Test
  fun `pawn black moves from d5 to d4`() {
    // Given
    val game = Game(TEST_NUMBER)
    val pawn = Pawn(Color.BLACK)
    val startLocation = Location(File.D, 5)
    val endLocation = Location(File.D, 4) // legal move
    game.board.setPieceToField(startLocation, pawn)

    // When
    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()
    game.movePiece(move)

    // Then
    assertThat(game.board.getField(startLocation).piece).isNull()
    assertThat(game.board.getField(endLocation).piece).isEqualTo(pawn)
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

    // Then
    assertThat(game.board.getField(startLocation).piece).isNull()
    assertThat(game.board.getField(endLocation).piece).isEqualTo(pawn)
  }

  @Test
  fun `king moves from e1 to d1, d2, e2, f2, f1`() {
    // Given
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, 1)
    val endLocations = listOf(
      Location(File.D, 1),  // left
      Location(File.D, 2),  // top left
      Location(File.E, 2),  // top
      Location(File.F, 2),  // top right
      Location(File.F, 1),  // right
    )

    for (endLocation in endLocations) {
      val game = Game(TEST_NUMBER)
      game.board.setPieceToField(startLocation, king)

      // When
      val move = Move(startLocation, endLocation)
      game.movePiece(move)

      // Then
      assertThat(game.board.getField(startLocation).piece).isNull()
      assertThat(game.board.getField(endLocation).piece).isEqualTo(king)
      assertThat(game.board.getField(endLocation).piece?.color).isEqualTo(Color.WHITE)
    }
  }

  @Test
  fun `knight moves from d4 to b5, c6, e6, f5, b3, c2, e2, f3`() {
    // Given
    val game = Game(TEST_NUMBER)
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, 4)
    val endLocations = listOf(
      Location(File.B, 5),  // up 1, left 2
      Location(File.C, 6),  // up 2, left 1
      Location(File.E, 6),  // up 2, right 1
      Location(File.F, 5),  // up 1, right 2
      Location(File.B, 3),  // down 1, left 2
      Location(File.C, 2),  // down 2, left 1
      Location(File.E, 2),  // down 2, right 1
      Location(File.F, 3),  // down 1, right 2
    )

    for (endLocation in endLocations) {
      game.board.setPieceToField(startLocation, knight)

      // When
      val move = Move(startLocation, endLocation)
      game.turn.switchTurn()
      game.movePiece(move)

      // Then
      assertThat(game.board.getField(startLocation).piece).isNull()
      assertThat(game.board.getField(endLocation).piece).isEqualTo(knight)
      assertThat(game.board.getField(endLocation).piece?.color).isEqualTo(Color.BLACK)
    }
  }

  @Test
  fun `queen moves from d1 to c1, a1, e1, h1, d2, d8, c2, a4, e2, h5`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, 1)
    val endLocations = listOf(
      Location(File.C, 1),  // directly left
      Location(File.A, 1),  // further left
      Location(File.E, 1),  // directly right
      Location(File.H, 1),  // further right
      Location(File.D, 2),  // directly above
      Location(File.D, 8),  // further above
      Location(File.A, 4),  // further diagonal left
      Location(File.E, 2),  // directly diagonal right
      Location(File.H, 5),  // further diagonal right
    )

    for (endLocation in endLocations) {
      val game = Game(TEST_NUMBER)
      game.board.setPieceToField(startLocation, queen)

      // When
      val move = Move(startLocation, endLocation)
      game.board.movePiece(move)

      // Then
      assertThat(game.board.getField(startLocation).piece).isNull()
      assertThat(game.board.getField(endLocation).piece).isEqualTo(queen)
      assertThat(game.board.getField(endLocation).piece?.color).isEqualTo(Color.WHITE)
    }
  }

  @Test
  fun `bishop moves from c8 to b7, a6, d7, h3`() {
    // Given
    val game = Game(TEST_NUMBER)
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, 8)
    val endLocations = listOf(
      Location(File.B, 7),  // directly bottom-left diagonal
      Location(File.H, 3),  // further bottom-right diagonal
    )

    for (endLocation in endLocations) {
      game.board.setPieceToField(startLocation, bishop)
      // When
      val move = Move(startLocation, endLocation)
      game.turn.switchTurn()
      game.movePiece(move)

      // Then
      assertThat(game.board.getField(startLocation).piece).isNull()
      assertThat(game.board.getField(endLocation).piece).isEqualTo(bishop)
      assertThat(game.board.getField(endLocation).piece?.color).isEqualTo(Color.BLACK)
    }
  }

  @Test
  fun `rook moves from h1 to g1, a1, h2, h8`() {
    // Given
    val rook = Rook(Color.WHITE)
    val startLocation = Location(File.H, 1)
    val endLocations = listOf(
      Location(File.G, 1),  // directly left
      Location(File.H, 8),  // further above
    )

    for (endLocation in endLocations) {
      val game = Game(TEST_NUMBER)
      game.board.setPieceToField(startLocation, rook)

      // When
      val move = Move(startLocation, endLocation)
      game.movePiece(move)

      // Then
      assertThat(game.board.getField(startLocation).piece).isNull()
      assertThat(game.board.getField(endLocation).piece).isEqualTo(rook)
      assertThat(game.board.getField(endLocation).piece?.color).isEqualTo(Color.WHITE)
    }
  }
}
