package hwr.oop.group4.chess.core.location

import hwr.oop.group4.chess.core.board.FieldIterator
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class FileTest : AnnotationSpec() {

  @Test
  fun `next file of h is null`() {
    val file = File.H
    val nexFile = file.next()
    assertThat(nexFile).isNull()
  }

  @Test
  fun `previous file of b is a`() {
    val file = File.B
    val previousFile = file.previous()
    assertThat(previousFile).isEqualTo(File.A)
  }

  @Test
  fun `previous file of a is null`() {
    val file = File.A
    val previousFile = file.previous()
    assertThat(previousFile).isNull()
  }

  @Test
  fun `all previous files from H are correctly read`() {
    // Given
    var file: File? = File.H
    val files = mutableListOf<File?>()

    // When
    while (file != null) {
      files.add(file)
      file = file.previous()
    }

    // Then
    assertThat(files).containsExactly(
      File.H,
      File.G,
      File.F,
      File.E,
      File.D,
      File.C,
      File.B,
      File.A
    )
  }
}