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
  val fen = FEN("8/8/8/8/8/8/8/7R", Color.WHITE, "-", "-", 0, 0)

  @Test
  fun `rook moves from h1 to g1`() {
    // Given
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.G, Rank.ONE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(Rook(Color.WHITE))
  }

  @Test
  fun `rook moves from h1 to a1`() {
    // Given
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.A, Rank.ONE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(Rook(Color.WHITE))
  }

  @Test
  fun `rook moves from h1 to h2`() {
    // Given
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.H, Rank.TWO)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(Rook(Color.WHITE))
  }

  @Test
  fun `rook moves from h1 to h8`() {
    // Given
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.H, Rank.EIGHT)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(Rook(Color.WHITE))
  }
}