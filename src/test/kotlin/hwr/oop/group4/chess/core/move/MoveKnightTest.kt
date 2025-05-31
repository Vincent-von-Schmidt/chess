package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Knight
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveKnightTest : AnnotationSpec() {

  private lateinit var board: Board

  @BeforeEach
  fun setup() {
    board = Board(EMPTY_BOARD)
  }

  @Test
  fun `knight moves from d4 to b5`() {
    // Given
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.B, Rank.FIVE)
    board.setPieceToField(startLocation, knight)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to c6`() {
    // Given
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.C, Rank.SIX)
    board.setPieceToField(startLocation, knight)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to e6`() {
    // Given
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.E, Rank.SIX)
    board.setPieceToField(startLocation, knight)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to f5`() {
    // Given
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.F, Rank.FIVE)
    board.setPieceToField(startLocation, knight)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to b3`() {
    // Given
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.B, Rank.THREE)
    board.setPieceToField(startLocation, knight)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to c2`() {
    // Given
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.C, Rank.TWO)
    board.setPieceToField(startLocation, knight)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to e2`() {
    // Given
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.E, Rank.TWO)
    board.setPieceToField(startLocation, knight)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }

  @Test
  fun `knight moves from d4 to f3`() {
    // Given
    val knight = Knight(Color.BLACK)
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.F, Rank.THREE)
    board.setPieceToField(startLocation, knight)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(knight)
  }
}