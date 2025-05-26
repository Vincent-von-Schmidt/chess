package hwr.oop.group4.chess.core.location

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class FileTest : AnnotationSpec() {

  @Test
  fun `next file of a is b`() {
    val file = File.A
    val nexFile = file.next()
    assertThat(nexFile).isEqualTo(File.B)
  }

  @Test
  fun `previous file of b is a`() {
    val file = File.B
    val previousFile = file.previous()
    assertThat(previousFile).isEqualTo(File.A)
  }
}