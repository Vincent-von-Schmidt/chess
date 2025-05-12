package hwr.oop.group4.chess.board

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Color
import hwr.oop.group4.chess.core.pieces.King
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class BoardTest : AnnotationSpec() {

//     @Test
//     fun `board initializes with 64 fields`() {
//         val board = Board()
//         val fields = board.getAllFields()
//
//         assertThat(fields).hasSize(64)
//     }
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