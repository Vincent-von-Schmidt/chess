package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class FieldIteratorTest : AnnotationSpec() {

  val board =
    BoardFactory.generateBoardFromFen(EMPTY_BOARD)

  @Test
  fun `iterator starts at A8`() {
    // Given
    val iterator = FieldIterator(board)
    val expectedLocation = Location(File.A, Rank.EIGHT)

    // When
    val first = iterator.next().location

    // Then
    assertThat(first).isEqualTo(expectedLocation)
  }

  @Test
  fun `iterator steps through files correctly within rank`() {
    // Given
    val iterator = FieldIterator(board)

    // When
    val locations = listOf(
      iterator.next(),
      iterator.next(),
      iterator.next()
    ).map { it.location }

    // Then
    assertThat(locations).containsExactly(
      Location(File.A, Rank.EIGHT),
      Location(File.B, Rank.EIGHT),
      Location(File.C, Rank.EIGHT)
    )
  }

  @Test
  fun `iterator covers all 64 fields`() {

    // Given
    val iterator = FieldIterator(board)
    val fields = mutableListOf<Field>()

    // When
    while (iterator.hasNext()) {
      fields.add(iterator.next())
    }

    // Then
    assertThat(fields).hasSize(64)
  }

  @Test
  fun `iterator ends at H1`() {
    // Given
    val iterator = FieldIterator(board)
    val expectedLocation = Location(File.H, Rank.ONE)

    // When
    while (iterator.hasNext()) {
      iterator.next()
    }
    val lastField  = iterator.next().location // field after H1 when hasNext was false

    // Then
    assertThat(lastField).isEqualTo(expectedLocation)
  }
}