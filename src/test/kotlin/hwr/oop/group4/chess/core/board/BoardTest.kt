package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class BoardTest : AnnotationSpec() {

  private lateinit var board: Board

  @BeforeEach
  fun setUp() {
    board = BoardFactory.generateBoardFromFen(EMPTY_BOARD)
  }

  @Test
  fun `field d4 is accessible via pointers`() {
    val location = Location(File.D, Rank.FOUR)

    assertThat(board.getField(location).location.description).isEqualTo("D4")
  }

  @Test
  fun `field h1 is accessible via pointers`() {
    val location = Location(File.H, Rank.ONE)

    assertThat(board.getField(location).location.description).isEqualTo("H1")
  }

  @Test
  fun `field a8 is accessible via pointers`() {
    val location = Location(File.A, Rank.EIGHT)

    assertThat(board.getField(location).location.description).isEqualTo("A8")
  }

  @Test
  fun `board with no fen given loads correctly`() {
    // Given
    val board = BoardFactory.generateBoardFromFen()
    val location = Location(File.E, Rank.ONE)
    val pieceAtLocation = board.getField(location).piece?.description

    // Then
    assertThat(pieceAtLocation).isEqualTo("WHITE King")
  }

  @Test
  fun `board with empty_fen given is empty`() {
    // Given
    val board = BoardFactory.generateBoardFromFen(EMPTY_BOARD)
    val location = Location(File.E, Rank.ONE)
    val pieceAtLocation = board.getField(location).piece?.description

    // Then
    assertThat(pieceAtLocation).isNull()
  }
}