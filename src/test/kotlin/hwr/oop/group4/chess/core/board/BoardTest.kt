package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import hwr.oop.group4.chess.core.utils.Constants.STARTING_POSITION

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class BoardTest : AnnotationSpec() {

  private lateinit var board: Board

  @BeforeEach
  fun setUp() {
    board = Board()
  }

  @Test
  fun `field d4 is accessible via pointers`() {
    val location = Location(File.D, 4)

    assertThat(board.getField(location).location.description).isEqualTo("D4")
  }

  @Test
  fun `field h1 is accessible via pointers`() {
    val location = Location(File.H, 1)

    assertThat(board.getField(location).location.description).isEqualTo("H1")
  }

  @Test
  fun `field a8 is accessible via pointers`() {
    val location = Location(File.A, 8)

    assertThat(board.getField(location).location.description).isEqualTo("A8")
  }

  @Test
  fun `getField throws on invalid rank`() {
    val invalidLocation = Location(File.A, 9)

    assertThatThrownBy {
      board.getField(invalidLocation)
    }.hasMessageContaining("No field at A9")
  }

  @Test
  fun `next field of b4 is c4`() {

    val location = Location(File.B, 4)

    assertThat(board.nextField(location).location.description).isEqualTo("C4")
  }

  @Test
  fun `next field of h2 is a1`() {
    val location = Location(File.H, 2)

    assertThat(board.nextField(location).location.description).isEqualTo("A1")
  }

  @Test
  fun `next field of h1 is h1`() {
    val location = Location(File.H, 1)

    assertThat(board.nextField(location).location.description).isEqualTo("H1")
  }

  @Test
  fun `board with no fen given loads correctly`() {
    // Given
    val board = Board()
    val location = Location(File.E, 1)
    val pieceAtLocation = board.getField(location).piece?.description

    // Then
    assertThat(pieceAtLocation).isEqualTo("WHITE King")
  }

  @Test
  fun `board with empty_fen given is empty`() {
    // Given
    val board = Board(EMPTY_BOARD)
    val location = Location(File.E, 1)
    val pieceAtLocation = board.getField(location).piece?.description

    // Then
    assertThat(pieceAtLocation).isNull()
  }


}
