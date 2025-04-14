package hwr.oop.board

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class BoardTest : AnnotationSpec() {

    @Test
    fun `board should initialize with 64 fields`() {
        val board = Board()
        val fields = board.getAllFields()

        assertThat(fields).hasSize(64)
    }
}