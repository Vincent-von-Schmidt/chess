package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Pawn
import hwr.oop.group4.chess.core.utils.Color
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.mpp.start
import org.assertj.core.api.Assertions.assertThat

class MovePawnTest : AnnotationSpec() {

  private lateinit var board: Board

  @Test
  fun `pawn black moves from d5 to d4`() {
    // Given
    val fen = FEN("8/8/8/3p4/8/8/8/8", Color.BLACK, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.FOUR)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(Pawn(Color.BLACK))
  }

  @Test
  fun `pawn white moves from d5 to d6`() {
    // Given
    val fen = FEN("8/8/8/3P4/8/8/8/8", Color.WHITE, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.SIX)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(Pawn(Color.WHITE))
  }

  @Test
  fun `white pawn moves 2 tiles on the first move`() {
    // Given
    val fen = FEN("8/8/8/8/8/8/P7/8", Color.WHITE, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val startLocation = Location(File.A, Rank.TWO)
    val endLocation = Location(File.A, Rank.FOUR)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(Pawn(Color.WHITE))
  }
}
