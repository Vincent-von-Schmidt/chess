package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.King
import hwr.oop.group4.chess.core.utils.Color
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveKingTest : AnnotationSpec() {

  private lateinit var board: Board

  @Test
  fun `king moves from e1 to d1`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/8/4K3", Color.WHITE, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.D, Rank.ONE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to d2`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/8/4K3", Color.WHITE, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.D, Rank.TWO)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to e2`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/8/4K3", Color.WHITE, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.E, Rank.TWO)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to f2`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/8/4K3", Color.WHITE, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.F, Rank.TWO)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to f1`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/8/4K3", Color.WHITE, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.F, Rank.ONE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }
}