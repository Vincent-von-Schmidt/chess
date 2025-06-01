package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.BlackPawn
import hwr.oop.group4.chess.core.pieces.WhitePawn
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MovePawnTest : AnnotationSpec() {

  private lateinit var board: Board

  @BeforeEach
  fun setup() {
    board = Board(EMPTY_BOARD)
  }

  @Test
  fun `pawn black moves from d5 to d4`() {
    // Given
    val blackPawn = BlackPawn()
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.FOUR)
    board.setPieceToField(startLocation, blackPawn)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(blackPawn)
  }

  @Test
  fun `pawn white moves from d5 to d6`() {
    // Given
    val whitePawn = WhitePawn()
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.SIX) // legal move
    board.setPieceToField(startLocation, whitePawn)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(whitePawn)
  }

  @Test
  fun `white pawn moves 2 tiles on the first move`() {
    // Given
    val whitePawn = WhitePawn()
    val startLocation = Location(File.A, Rank.TWO)
    val endLocation = Location(File.A, Rank.FOUR)
    board.setPieceToField(startLocation, whitePawn)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(whitePawn)
  }
}
