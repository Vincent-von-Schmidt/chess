package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.fen.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.King
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveKingTest : AnnotationSpec() {

  private lateinit var board: Board

  @Test
  fun `king moves from e1 to d1`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/8/4K3", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.D, Rank.ONE)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to d2`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/3K4/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.D, Rank.TWO)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to e2`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/4K3/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.E, Rank.TWO)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to f2`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/5K2/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.F, Rank.TWO)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to f1`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/8/4K3", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.F, Rank.ONE)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }
}