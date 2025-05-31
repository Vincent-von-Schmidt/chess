package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.King
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveKingTest : AnnotationSpec() {

  private lateinit var board: Board

  @BeforeEach
  fun setup() {
    board = Board(EMPTY_BOARD)
  }

  @Test
  fun `king moves from e1 to d1`() {
    // Given
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.D, Rank.ONE)
    board.setPieceToField(startLocation, king)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to d2`() {
    // Given
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.D, Rank.TWO)
    board.setPieceToField(startLocation, king)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to e2`() {
    // Given
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.E, Rank.TWO)
    board.setPieceToField(startLocation, king)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to f2`() {
    // Given
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.F, Rank.TWO)
    board.setPieceToField(startLocation, king)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `king moves from e1 to f1`() {
    // Given
    val king = King(Color.WHITE)
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.F, Rank.ONE)
    board.setPieceToField(startLocation, king)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }
}