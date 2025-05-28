package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.*
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveCaptureLegalTest : AnnotationSpec() {

  private lateinit var board: Board

  @BeforeEach
  fun setup() {
    board = Board(EMPTY_BOARD)
  }

  @Test
  fun `pawn white captures black queen`() {
    // Given
    val whitePawn = WhitePawn(Color.WHITE)
    val queen = Queen(Color.BLACK)
    val startLocation = Location(File.D, Rank.THREE)
    val endLocation = Location(File.E, Rank.FOUR)
    board.setPieceToField(startLocation, whitePawn)
    board.setPieceToField(endLocation, queen)

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
  fun `knight white captures black king`() {
    // Given
    val knight = Knight(Color.WHITE)
    val king = King(Color.BLACK)
    val startLocation = Location(File.D, Rank.THREE)
    val endLocation = Location(File.F, Rank.FOUR)
    board.setPieceToField(startLocation, knight)
    board.setPieceToField(endLocation, king)

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
  fun `king white captures black rook`() {
    // Given
    val rook = Rook(Color.BLACK)
    val king = King(Color.WHITE)
    val startLocation = Location(File.D, Rank.THREE)
    val endLocation = Location(File.E, Rank.FOUR)
    board.setPieceToField(startLocation, king)
    board.setPieceToField(endLocation, rook)

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
  fun `queen white captures black rook`() {
    // Given
    val rook = Rook(Color.BLACK)
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.G, Rank.EIGHT)
    board.setPieceToField(startLocation, queen)
    board.setPieceToField(endLocation, rook)

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
  fun `bishop white captures black rook`() {
    // Given
    val rook = Rook(Color.BLACK)
    val bishop = Bishop(Color.WHITE)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.G, Rank.EIGHT)
    board.setPieceToField(startLocation, bishop)
    board.setPieceToField(endLocation, rook)

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
  fun `rook white captures black bishop`() {
    // Given
    val bishop = Bishop(Color.BLACK)
    val rook = Rook(Color.WHITE)
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.ONE)
    board.setPieceToField(startLocation, rook)
    board.setPieceToField(endLocation, bishop)

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
