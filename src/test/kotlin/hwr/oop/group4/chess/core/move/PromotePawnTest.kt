package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Bishop
import hwr.oop.group4.chess.core.pieces.King
import hwr.oop.group4.chess.core.pieces.Rook
import hwr.oop.group4.chess.core.utils.Color
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class PromotePawnTest : AnnotationSpec() {

  private lateinit var board: Board

  @Test
  fun `pawn black promotes from d2 to d1 to Bishop`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/3p4/8", Color.BLACK, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.D, Rank.TWO)
    val endLocation = Location(File.D, Rank.ONE)

    // When
    val promoteToPiece = Bishop(Color.BLACK)
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor, promoteToPiece)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation =
      board.getPiece(endLocation) // TODO change everywhere to getPiece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(promoteToPiece)
  }

  @Test
  fun `pawn black throw on promote from d2 to d1 to white bishop`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/3p4/8", Color.BLACK, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.D, Rank.TWO)
    val endLocation = Location(File.D, Rank.ONE)

    // When
    val promoteToPiece = Bishop(Color.WHITE)
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(move, fen.activeColor, promoteToPiece)
    }.hasMessage("BLACK Pawn can not promote to WHITE Bishop")
  }

  @Test
  fun `pawn white promotes from d7 to d8 to Rook`() {
    // Given
    val fen = FEN("8/3P4/8/8/8/8/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.D, Rank.SEVEN)
    val endLocation = Location(File.D, Rank.EIGHT) // legal move

    // When
    val promoteToPiece = Rook(Color.WHITE)
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor, promoteToPiece)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(promoteToPiece)
  }

  @Test
  fun `pawn white throw on on reaching Rank without piece to promote to`() {
    // Given
    val fen = FEN("8/3P4/8/8/8/8/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.D, Rank.SEVEN)
    val endLocation = Location(File.D, Rank.EIGHT)

    // When
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(move, fen.activeColor)
    }.hasMessage("WHITE Pawn has no Piece to promote to")
  }

  @Test
  fun `pawn white throw on on reaching Rank with wrong piece to promote to selected`() {
    // Given
    val fen = FEN("8/3P4/8/8/8/8/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.D, Rank.SEVEN)
    val endLocation = Location(File.D, Rank.EIGHT)

    // When
    val promoteToPiece = King(Color.WHITE)
    val move = Move(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(move, fen.activeColor, promoteToPiece)
    }.hasMessage("WHITE Pawn can not promote to WHITE King")
  }
}
