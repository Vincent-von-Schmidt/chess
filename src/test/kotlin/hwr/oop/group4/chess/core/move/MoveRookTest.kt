package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Rook
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveRookTest : AnnotationSpec() {

  private lateinit var board: Board

  @BeforeEach
  fun setup() {
    board = Board(EMPTY_BOARD)
  }

  @Test
  fun `rook moves from h1 to g1`() {
    // Given
    val rook = Rook(Color.WHITE)
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.G, Rank.ONE)
    board.setPieceToField(startLocation, rook)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(rook)
  }

  @Test
  fun `rook moves from h1 to a1`() {
    // Given
    val rook = Rook(Color.WHITE)
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.A, Rank.ONE)
    board.setPieceToField(startLocation, rook)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(rook)
  }

  @Test
  fun `rook moves from h1 to h2`() {
    // Given
    val rook = Rook(Color.WHITE)
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.H, Rank.TWO)
    board.setPieceToField(startLocation, rook)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(rook)
  }

  @Test
  fun `rook moves from h1 to h8`() {
    // Given
    val rook = Rook(Color.WHITE)
    val startLocation = Location(File.H, Rank.ONE)
    val endLocation = Location(File.H, Rank.EIGHT)
    board.setPieceToField(startLocation, rook)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(rook)
  }
}