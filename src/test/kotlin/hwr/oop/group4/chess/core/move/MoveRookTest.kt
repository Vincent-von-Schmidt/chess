package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Rook
import hwr.oop.group4.chess.core.utils.Color
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveRookTest : AnnotationSpec() {

  private lateinit var board: Board

  @Test
  fun `rook moves from h1 to g1`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/8/7R", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.G, Rank.ONE)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(Rook(Color.WHITE))
  }

  @Test
  fun `rook moves from h1 to a1`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/8/7R", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.A, Rank.ONE)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(Rook(Color.WHITE))
  }

  @Test
  fun `rook moves from h1 to h2`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/8/7R", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.H, Rank.TWO)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(Rook(Color.WHITE))
  }

  @Test
  fun `rook moves from h1 to h8`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/8/7R", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.H, Rank.EIGHT)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(Rook(Color.WHITE))
  }
}