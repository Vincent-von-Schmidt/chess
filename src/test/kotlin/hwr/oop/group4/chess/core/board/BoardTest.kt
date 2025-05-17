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
    fun `root is always A1`() {
        // Given
        val root = board.root

        // Then
        assertThat (root.location.description).isEqualTo("A1")
    }

    @Test
     fun `randomly chosen fields are accessible via pointers`() {
        // Given
        val location1 = Location(File.D, 4)
        val location2 = Location(File.H, 1)
        val location3 = Location(File.A, 8)

        // When
        val field1 = board.getField(location1)
        val field2 = board.getField(location2)
        val field3 = board.getField(location3)

        // Then
        assertThat (field1.location.description).isEqualTo("D4")
        assertThat (field2.location.description).isEqualTo("H1")
        assertThat (field3.location.description).isEqualTo("A8")
     }

    @Test
    fun `getField() throws on invalid rank`() {
        // Given
        val invalidLocation = Location(File.A, 9)

        // Then
        assertThatThrownBy { board.getField(invalidLocation) }
            .isInstanceOf(InvalidRankException::class.java)
            .hasMessage("Invalid rank 9. Rank must be between 1 and 8.")
    }

    @Test
    fun `getField() throws if file is unreachable`() {
        // Given
        val location = Location(File.G, 1)
        val field = board.getField(location)
        field.right = null // now H1 is unreachable

        // Then
        assertThatThrownBy { board.getField(Location(File.H, 1)) }
            .isInstanceOf(InvalidFileException::class.java)
            .hasMessage("Invalid file H. File must be between A and H.")
    }

    @Test
    fun `next field is in the same rank`() {
        // Given
        val location1 = Location(File.A, 1)
        val location2 = Location(File.A, 2)
        val location3 = Location(File.A, 3)
        val location4 = Location(File.A, 4)
        val location5 = Location(File.A, 5)
        val location6 = Location(File.A, 6)
        val location7 = Location(File.A, 7)
        val location8 = Location(File.A, 8)

        // When
        val nextField1 = board.nextField(location1)
        val nextField2 = board.nextField(location2)
        val nextField3 = board.nextField(location3)
        val nextField4 = board.nextField(location4)
        val nextField5 = board.nextField(location5)
        val nextField6 = board.nextField(location6)
        val nextField7 = board.nextField(location7)
        val nextField8 = board.nextField(location8)

        // Then
        assertThat (nextField1.location.description).isEqualTo("B1")
        assertThat (nextField2.location.description).isEqualTo("B2")
        assertThat (nextField3.location.description).isEqualTo("B3")
        assertThat (nextField4.location.description).isEqualTo("B4")
        assertThat (nextField5.location.description).isEqualTo("B5")
        assertThat (nextField6.location.description).isEqualTo("B6")
        assertThat (nextField7.location.description).isEqualTo("B7")
        assertThat (nextField8.location.description).isEqualTo("B8")
    }

    @Test
    fun `next field is in the next file`() {
        // Given
        val location = Location(File.H, 2)

        // When
        val nextField = board.nextField(location)

        // Then
        assertThat (nextField.location.description).isEqualTo("A1")
    }

    @Test
    fun `next field of h1 is h1`() {
        // Given
        val location = Location(File.H, 1)

        // When
        val nextField = board.nextField(location)

        // Then
        assertThat (nextField.location.description).isEqualTo("H1")
    }
}
