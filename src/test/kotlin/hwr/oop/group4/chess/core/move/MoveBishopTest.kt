package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Bishop
import hwr.oop.group4.chess.core.utils.Color
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveBishopTest : AnnotationSpec() {

  val fen = FEN(
    piecePlacement = "2b5/8/8/8/8/8/8/8",
    activeColor = Color.BLACK,
    castle = "-",
    enPassant = "-",
    halfMoves = 0,
    fullMoves = 1
  )

  @Test
  fun `bishop moves from c8 to b7`() {
    // Given
    val board = BoardFactory.generateBoardFromFen(fen)
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, Rank.EIGHT)
    val endLocation = Location(File.B, Rank.SEVEN)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)

    // Then
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }

  @Test
  fun `bishop moves from c8 to a6`() {
    // Given
    val board = BoardFactory.generateBoardFromFen(fen)
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, Rank.EIGHT)
    val endLocation = Location(File.A, Rank.SIX)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }

  @Test
  fun `bishop moves from c8 to d7`() {
    // Given
    val board = BoardFactory.generateBoardFromFen(fen)
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, Rank.EIGHT)
    val endLocation = Location(File.D, Rank.SEVEN)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }

  @Test
  fun `bishop moves from c8 to h3`() {
    // Given
    val board = BoardFactory.generateBoardFromFen(fen)
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, Rank.EIGHT)
    val endLocation = Location(File.H, Rank.THREE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }
}