package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Queen
import hwr.oop.group4.chess.core.utils.Color
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveQueenTest : AnnotationSpec() {

  private lateinit var board: Board
  private val queen = Queen(Color.WHITE)
  private val fen = FEN("8/8/8/8/8/8/8/3Q4", Color.WHITE, "-", "-", 0, 1)
  val startLocation = Location(File.D, Rank.ONE)

  @Test
  fun `queen moves from d1 to c1`() {
    // Given
    val endLocation = Location(File.C, Rank.ONE)
    board = BoardFactory.generateBoardFromFen(fen)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to a1`() {
    // Given
    val endLocation = Location(File.A, Rank.ONE)
    board = BoardFactory.generateBoardFromFen(fen)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to e1`() {
    // Given
    val endLocation = Location(File.E, Rank.ONE)
    board = BoardFactory.generateBoardFromFen(fen)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to h1`() {
    // Given
    val endLocation = Location(File.H, Rank.ONE)
    board = BoardFactory.generateBoardFromFen(fen)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to d2`() {
    // Given
    val endLocation = Location(File.D, Rank.TWO)
    board = BoardFactory.generateBoardFromFen(fen)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to d8`() {
    // Given
    val endLocation = Location(File.D, Rank.EIGHT)
    board = BoardFactory.generateBoardFromFen(fen)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to a4`() {
    // Given
    val endLocation = Location(File.A, Rank.FOUR)
    board = BoardFactory.generateBoardFromFen(fen)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to e2`() {
    // Given
    val endLocation = Location(File.E, Rank.TWO)
    board = BoardFactory.generateBoardFromFen(fen)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to h5`() {
    // Given
    val endLocation = Location(File.H, Rank.FIVE)
    board = BoardFactory.generateBoardFromFen(fen)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to c2`() {
    // Given
    val endLocation = Location(File.C, Rank.TWO)
    board = BoardFactory.generateBoardFromFen(fen)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }
}