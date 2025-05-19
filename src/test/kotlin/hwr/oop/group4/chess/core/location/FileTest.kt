package hwr.oop.group4.chess.core.location

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class FileTest : AnnotationSpec() {

    @Test
    fun `next file of a is b`() {
        val file = File.A
        assertThat(file.next()).isEqualTo(File.B)
    }
}
