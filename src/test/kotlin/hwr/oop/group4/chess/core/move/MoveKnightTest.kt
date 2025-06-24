package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.utils.Color
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveKnightTest : AnnotationSpec() {

  private lateinit var board: Board

  @Test
  fun `knight moves from d4 to b5`() {
    // Given
    val fen = FEN("8/8/8/8/3n4/8/8/8", Color.BLACK, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val knight = board.getPiece(Location(File.D, Rank.FOUR))
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.B, Rank.FIVE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to c6`() {
    // Given
    val fen = FEN("8/8/8/8/3n4/8/8/8", Color.BLACK, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val knight = board.getPiece(Location(File.D, Rank.FOUR))
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.C, Rank.SIX)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to e6`() {
    // Given
    val fen = FEN("8/8/8/8/3n4/8/8/8", Color.BLACK, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val knight = board.getPiece(Location(File.D, Rank.FOUR))
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.E, Rank.SIX)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to f5`() {
    // Given
    val fen = FEN("8/8/8/8/3n4/8/8/8", Color.BLACK, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val knight = board.getPiece(Location(File.D, Rank.FOUR))
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.F, Rank.FIVE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to b3`() {
    // Given
    val fen = FEN("8/8/8/8/3n4/8/8/8", Color.BLACK, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val knight = board.getPiece(Location(File.D, Rank.FOUR))
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.B, Rank.THREE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to c2`() {
    // Given
    val fen = FEN("8/8/8/8/3n4/8/8/8", Color.BLACK, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val knight = board.getPiece(Location(File.D, Rank.FOUR))
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.C, Rank.TWO)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to e2`() {
    // Given
    val fen = FEN("8/8/8/8/3n4/8/8/8", Color.BLACK, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val knight = board.getPiece(Location(File.D, Rank.FOUR))
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.E, Rank.TWO)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to f3`() {
    // Given
    val fen = FEN("8/8/8/8/3n4/8/8/8", Color.BLACK, "-", "-", 0, 0)
    board = BoardFactory.generateBoardFromFen(fen)
    val knight = board.getPiece(Location(File.D, Rank.FOUR))
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.F, Rank.THREE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getPiece(startLocation)
    val pieceOnEndLocation = board.getPiece(endLocation)

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }
}