package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class BoardTest : AnnotationSpec() {

  private lateinit var board: Board

  @BeforeEach
  fun setUp() {
    board = BoardFactory.generateBoardFromFen(EMPTY_BOARD)
  }

  @Test
  fun `field d4 is accessible via field map`() {
    // Given
    val location = Location(File.D, Rank.FOUR)

    // When
    val d4Location = board.getField(location).location.description

    // Then
    assertThat(d4Location).isEqualTo("D4")
  }

  @Test
  fun `board with no fen given loads correctly`() {
    // Given
    val board = BoardFactory.generateBoardFromFen(STARTING_POSITION)
    val e1Location = Location(File.E, Rank.ONE)
    val g7Location = Location(File.G, Rank.SEVEN)

    // When
    val pieceAtLocationE1 = board.getPiece(e1Location)?.getDescription()
    val pieceAtLocationG7 = board.getPiece(g7Location)?.getDescription()

    // Then
    assertThat(pieceAtLocationE1).isEqualTo("WHITE King")
    assertThat(pieceAtLocationG7).isEqualTo("BLACK Pawn")
  }

  @Test
  fun `board with empty_fen given is empty`() {
    // Given
    val board = BoardFactory.generateBoardFromFen(EMPTY_BOARD)
    val e1Location = Location(File.E, Rank.ONE)
    val g7Location = Location(File.G, Rank.SEVEN)

    // When
    val pieceAtLocationE1 = board.getPiece(e1Location)?.getDescription()
    val pieceAtLocationG7 = board.getPiece(g7Location)?.getDescription()

    // Then
    assertThat(pieceAtLocationE1).isNull()
    assertThat(pieceAtLocationG7).isNull()
  }
}