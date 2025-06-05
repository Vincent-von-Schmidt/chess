package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class FieldIteratorTest: AnnotationSpec(){

  val board = BoardFactory.generateBoardWithPieces(EMPTY_BOARD) // TODO i give it board and not BoardView?

  @Test
  fun `iterator starts at A8`() {
    val iterator = FieldIterator(board)
    val first = iterator.next()
    assertThat(first.location).isEqualTo(Location(File.A, Rank.EIGHT))
  }

  @Test
  fun `iterator steps through files correctly within rank`() {
    val iterator = FieldIterator(board)
    val locs = listOf(iterator.next(), iterator.next(), iterator.next()).map { it.location }

    assertThat(locs).containsExactly(
      Location(File.A, Rank.EIGHT),
      Location(File.B, Rank.EIGHT),
      Location(File.C, Rank.EIGHT)
    )
  }

  @Test
  fun `iterator covers all 64 fields`() {
    val iterator = FieldIterator(board)
    val fields = mutableListOf<Field>()

    while (iterator.hasNext()) {
      println()
      fields.add(iterator.next())
    }

    assertThat(fields).hasSize(64)
  }

  @Test
  fun `iterator ends at H1`() {
    val iterator = FieldIterator(board)
    var lastField = iterator.next()
    while (iterator.hasNext()) {
      lastField = iterator.next()
    }
    lastField = iterator.next() // last field when hasNext was false
    assertThat(lastField.location).isEqualTo(Location(File.H, Rank.ONE))
  }
}