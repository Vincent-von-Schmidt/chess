package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Bishop
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveBishopTest : AnnotationSpec() {

  private lateinit var board: Board

  @BeforeEach
  fun setup() {
    board = Board(EMPTY_BOARD)
  }

  @Test
  fun `bishop moves from c8 to b7`() {
    // Given
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, Rank.EIGHT)
    val endLocation = Location(File.B, Rank.SEVEN)
    board.setPieceToField(startLocation, bishop)

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
  fun `bishop moves from c8 to a6`() {
    // Given
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, Rank.EIGHT)
    val endLocation = Location(File.A, Rank.SIX)
    board.setPieceToField(startLocation, bishop)

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
  fun `bishop moves from c8 to d7`() {
    // Given
    val bishop = Bishop(Color.BLACK)
    val startLocation = Location(File.C, Rank.EIGHT)
    val endLocation = Location(File.D, Rank.SEVEN)
    board.setPieceToField(startLocation, bishop)

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
    board.setPieceToField(startLocation, bishop)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }
}