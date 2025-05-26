package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.*
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThatThrownBy

class MoveIllegalTest : AnnotationSpec() {

  @Test
  fun `empty field is immovable`() {
    // Given
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    val startLocation = Location(File.A, Rank.TWO)
    val endLocation = Location(File.H, Rank.SEVEN)

    // When
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("A2 does not contain a piece")
  }

  @Test
  fun `queen throw on illegal move`() {
    // Given
    val game = Game(TEST_NUMBER)
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.A, Rank.TWO)
    val endLocation = Location(File.H, Rank.SEVEN)
    game.board.setPieceToField(startLocation, queen)

    // When
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("WHITE Queen can not be moved to H7")
  }

  @Test
  fun `throw on white pawn move to occupied fild with white queen`() {
    // Given
    val game = Game(TEST_NUMBER)
    val pawn = Pawn(Color.WHITE)
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.THREE)
    val endLocation = Location(File.D, Rank.FOUR)
    game.board.setPieceToField(startLocation, pawn)
    game.board.setPieceToField(endLocation, queen)

    // When
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("D4 is already occupied with WHITE Queen")
  }

  @Test
  fun `pawn black throw on move from d5 to d6`() {
    // Given
    val game = Game(TEST_NUMBER)
    val pawn = Pawn(Color.BLACK)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.SIX) // illegal move
    game.board.setPieceToField(startLocation, pawn)

    // When
    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("BLACK Pawn can not be moved to D6")
  }

  @Test
  fun `pawn black throw on move from d5 to h8`() {
    // Given
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    val pawn = Pawn(Color.BLACK)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.H, Rank.EIGHT) // illegal move
    game.board.setPieceToField(startLocation, pawn)

    // When
    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("BLACK Pawn can not be moved to H8")
  }

  @Test
  fun `pawn white throw on move from d5 to d4`() {
    // Given
    val game = Game(TEST_NUMBER)
    val pawn = Pawn(Color.WHITE)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.FOUR) // illegal move
    game.board.setPieceToField(startLocation, pawn)

    // When
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("WHITE Pawn can not be moved to D4")
  }

  @Test
  fun `pawn white throw on move from d5 to h8`() {
    // Given
    val game = Game(TEST_NUMBER)
    val pawn = Pawn(Color.WHITE)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.H, Rank.EIGHT) // illegal move
    game.board.setPieceToField(startLocation, pawn)

    // When
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("WHITE Pawn can not be moved to H8")
  }

  @Test
  fun `king throw on move from e1 to h8`() {
    // Given
    val game = Game(TEST_NUMBER)
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.H, Rank.EIGHT) // illegal move
    game.board.setPieceToField(startLocation, king)

    // When
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("WHITE King can not be moved to H8")
  }

  @Test
  fun `knight throw on move from d4 to h8`() {
    // Given
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.H, Rank.EIGHT) // illegal move
    game.board.setPieceToField(startLocation, knight)

    // When
    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("BLACK Knight can not be moved to H8")
  }

  @Test
  fun `queen throw on move from d4 to h1`() {
    // Given
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.H, Rank.ONE) // illegal move
    game.board.setPieceToField(startLocation, queen)

    // When
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("WHITE Queen can not be moved to H1")
  }

  @Test
  fun `bishop throw on interrupted path move`() {
    // Given
    val game = Game(TEST_NUMBER)
    val bishop = Bishop(Color.BLACK)
    val king = King(Color.BLACK)
    val startLocation = Location(File.C, Rank.EIGHT)
    val occupiedLocation = Location(File.B, Rank.SEVEN)  // directly bottom-left diagonal
    val interruptedLocation =
      Location(File.A, Rank.SIX)  // further bottom-right diagonal, illegal
    game.board.setPieceToField(startLocation, bishop)
    game.board.setPieceToField(occupiedLocation, king)

    // When
    val move = Move(startLocation, interruptedLocation)
    game.turn.switchTurn()

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("BLACK Bishop can not be moved to A6")
  }

  @Test
  fun `bishop throw on move from d4 to h1`() {
    // Given
    val game = Game(TEST_NUMBER)
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.H, Rank.ONE) // illegal move
    game.board.setPieceToField(startLocation, bishop)

    // When
    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("BLACK Bishop can not be moved to H1")
  }

  @Test
  fun `rook throw on move from d4 to h8`() {
    // Given
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    val rook = Rook(Color.BLACK)
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.H, Rank.EIGHT) // illegal move
    game.board.setPieceToField(startLocation, rook)

    // When
    val move = Move(startLocation, endLocation)
    game.turn.switchTurn()

    // Then
    assertThatThrownBy {
      game.movePiece(move)
    }.hasMessageContaining("BLACK Rook can not be moved to H8")
  }

}
