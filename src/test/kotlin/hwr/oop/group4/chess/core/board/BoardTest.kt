package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location

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
    fun `rank length is 8`() {
        // TODO: problem -> if upper end of interval greater than 8, no code will be executed and so cant be checked by a test

        var length: Int = 0
        val location = Location(File.A, 2)
        var current: Field? = this.board.getField(location)

        while (current?.bottom != null) {
            length++
            println("$length -> ${current.location.file}${current.location.rank}")
            current = current.right
        }

        assertThat(length).isEqualTo(8)
    }

  @Test
  fun `root pointer is pointing to A1`() {
    val rootLocation = Location(File.A, 1)
    val firstField: Field = this.board.getField(rootLocation)

    assertThat(firstField.location).isEqualTo(rootLocation)
  }
}
