package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Queen
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveQueenTest : AnnotationSpec() {

  private lateinit var board: Board

  @BeforeEach
  fun setup() {
    board = Board(EMPTY_BOARD)
  }

  @Test
  fun `queen moves from d1 to c1`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.C, Rank.ONE)
    board.setPieceToField(startLocation, queen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to a1`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.A, Rank.ONE)
    board.setPieceToField(startLocation, queen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to e1`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.E, Rank.ONE)
    board.setPieceToField(startLocation, queen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to h1`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.H, Rank.ONE)
    board.setPieceToField(startLocation, queen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to d2`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.D, Rank.TWO)
    board.setPieceToField(startLocation, queen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to d8`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.D, Rank.EIGHT)
    board.setPieceToField(startLocation, queen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to a4`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.A, Rank.FOUR)
    board.setPieceToField(startLocation, queen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to e2`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.E, Rank.TWO)
    board.setPieceToField(startLocation, queen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to h5`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.H, Rank.FIVE)
    board.setPieceToField(startLocation, queen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to c2`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.C, Rank.TWO)
    board.setPieceToField(startLocation, queen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }
}