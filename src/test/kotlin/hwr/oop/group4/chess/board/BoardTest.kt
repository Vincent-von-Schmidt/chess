package hwr.oop.group4.chess.board

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Color
import hwr.oop.group4.chess.core.pieces.King
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.engine.teamcity.Locations.location
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

//
//     @Test
//     fun `pieces placeable on fields`() {
//         val board = Board()
//         val king = King(Color.WHITE)
//         val loc = Location(x = 'e', y = 4)
//
//         board.setPieceToField(loc, king)
//
//         val field = board.getAllFields()[loc]
//         assertThat(field?.piece).isEqualTo(king)
//     }
}