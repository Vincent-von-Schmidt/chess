package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.*
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveCaptureLegalTest : AnnotationSpec() {

  @Test
  fun `pawn white captures black queen`() {
    // Given
    val game = Game(TEST_NUMBER)
    val pawn = Pawn(Color.WHITE)
    val queen = Queen(Color.BLACK)
    val startLocation = Location(File.D, Rank.THREE)
    val endLocation = Location(File.E, Rank.FOUR)
    game.board.setPieceToField(startLocation, pawn)
    game.board.setPieceToField(endLocation, queen)

    // When
    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(pawn)
  }

  @Test
  fun `knight white captures black king`() {
    // Given
    val game = Game(TEST_NUMBER)
    val knight = Knight(Color.WHITE)
    val king = King(Color.BLACK)
    val startLocation = Location(File.D, Rank.THREE)
    val endLocation = Location(File.F, Rank.FOUR)
    game.board.setPieceToField(startLocation, knight)
    game.board.setPieceToField(endLocation, king)

    // When
    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `king white captures black rook`() {
    // Given
    val game = Game(TEST_NUMBER)
    val rook = Rook(Color.BLACK)
    val king = King(Color.WHITE)
    val startLocation = Location(File.D, Rank.THREE)
    val endLocation = Location(File.E, Rank.FOUR)
    game.board.setPieceToField(startLocation, king)
    game.board.setPieceToField(endLocation, rook)

    // When
    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `queen white captures black rook`() {
    // Given
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    val rook = Rook(Color.BLACK)
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.G, Rank.EIGHT)
    game.board.setPieceToField(startLocation, queen)
    game.board.setPieceToField(endLocation, rook)

    // When
    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `bishop white captures black rook`() {
    // Given
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    val rook = Rook(Color.BLACK)
    val bishop = Bishop(Color.WHITE)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.G, Rank.EIGHT)
    game.board.setPieceToField(startLocation, bishop)
    game.board.setPieceToField(endLocation, rook)

    // When
    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }

  @Test
  fun `rook white captures black bishop`() {
    // Given
    val game = Game(TEST_NUMBER, fen = EMPTY_BOARD)
    val bishop = Bishop(Color.BLACK)
    val rook = Rook(Color.WHITE)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.ONE)
    game.board.setPieceToField(startLocation, rook)
    game.board.setPieceToField(endLocation, bishop)

    // When
    val move = Move(startLocation, endLocation)
    game.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(rook)
  }
}
