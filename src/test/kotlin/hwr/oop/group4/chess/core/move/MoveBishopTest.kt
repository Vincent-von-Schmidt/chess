package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.fen.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Bishop
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.mpp.start
import net.bytebuddy.dynamic.DynamicType.Loaded
import org.assertj.core.api.Assertions.assertThat

class MoveBishopTest : AnnotationSpec() {

  @Test
  fun `bishop moves from c8 to b7`() {
    // Given
    val fen = minimalFEN("2b5/8/8/8/8/8/8/8", Color.BLACK)
    val board = BoardFactory.generateBoardWithPieces(fen)
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, Rank.EIGHT)
    val endLocation = Location(File.B, Rank.SEVEN)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)

    // Then
    val pieceOnStartLocation = board.getField(start).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }

  @Test
  fun `bishop moves from c8 to a6`() {
    // Given
    val fen = minimalFEN("2b5/8/8/8/8/8/8/8", Color.BLACK)
    val board = BoardFactory.generateBoardWithPieces(fen)
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, Rank.EIGHT)
    val endLocation = Location(File.A, Rank.SIX)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }

  @Test
  fun `bishop moves from c8 to d7`() {
    // Given
    val fen = minimalFEN("2b5/8/8/8/8/8/8/8", Color.BLACK)
    val board = BoardFactory.generateBoardWithPieces(fen)
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, Rank.EIGHT)
    val endLocation = Location(File.D, Rank.SEVEN)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }

  @Test
  fun `bishop moves from c8 to h3`() {
    // Given
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, Rank.EIGHT)
    val endLocation = Location(File.H, Rank.THREE)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }

  private fun minimalFEN(placement: String, color: Color) = FEN(
    piecePlacement = placement,
    activeColor = color,
    castle = "-",
    enPassant = "-",
    halfMoves = 0,
    fullMoves = 1
  )
}