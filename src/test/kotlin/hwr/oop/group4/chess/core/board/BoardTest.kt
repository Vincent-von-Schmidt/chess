package core.board

import core.location.File
import core.location.Location

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class BoardTest : AnnotationSpec() {

     @Test
     fun `field d4 is accessible via pointers`() {
         val board = Board()
         val location = Location(File.D, 4)

         assertThat (board.getField(location).location.file).isEqualTo(File.D)
         assertThat (board.getField(location).location.rank).isEqualTo(4)
     }

    @Test
    fun `field h1 is accessible via pointers`() {
        val board = Board()
        val location = Location(File.H, 1)

        assertThat (board.getField(location).location.file).isEqualTo(File.H)
        assertThat (board.getField(location).location.rank).isEqualTo(1)
    }

    @Test
    fun `field a8 is accessible via pointers`() {
        val board = Board()
        val location = Location(File.A, 8)

        assertThat (board.getField(location).location.file).isEqualTo(File.A)
        assertThat (board.getField(location).location.rank).isEqualTo(8)
    }

    @Test
    fun `next field of b4 is c4`() {
        val board = Board()
        val location = Location(File.B, 4)

        assertThat (board.nextField(location).location.file).isEqualTo(File.C)
        assertThat (board.nextField(location).location.rank).isEqualTo(4)
    }

    @Test
    fun `next field of h2 is a1`() {
        val board = Board()
        val location = Location(File.H, 2)

        assertThat (board.nextField(location).location.file).isEqualTo(File.A)
        assertThat (board.nextField(location).location.rank).isEqualTo(1)
    }

    @Test
    fun `next field of h1 is h1`() {
        val board = Board()
        val location = Location(File.H, 1)

        assertThat (board.nextField(location).location.file).isEqualTo(File.H)
        assertThat (board.nextField(location).location.rank).isEqualTo(1)
    }

}
