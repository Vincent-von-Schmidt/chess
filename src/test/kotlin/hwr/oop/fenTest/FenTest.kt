package hwr.oop.fenTest

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import hwr.oop.fen.Fen
import hwr.oop.fen.Color
import org.assertj.core.api.Assertions.assertThatThrownBy

class FenTest: AnnotationSpec() {

    @Test
    fun `test if the piecePlacement list is correctly created`() {
        val notation_example = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
        val fenOb = Fen(notation_example)
        assertThat(fenOb.piecePlacement).isEqualTo(listOf("rnbqkbnr", "pp1ppppp", "8", "2p5", "4P3", "8", "PPPP1PPP", "RNBQKBNR"))
    }

    @Test
    fun `check if the active color is correctly set to white`() {
        val notation_example = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
        val fenOb = Fen(notation_example)
        assertThat(fenOb.activeColor).isEqualTo(Color.WHITE)
    }

    @Test
    fun `check if the active color is correctly set to black`() {
        val notation_example = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR b KQkq c6 0 2"
        val fenOb = Fen(notation_example)
        assertThat(fenOb.activeColor).isEqualTo(Color.BLACK)
    }

    @Test
    fun `check if active color thows an error`() {
        val notation_example = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR t KQkq c6 0 2"
        assertThatThrownBy { Fen(notation_example) }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `check if castle is correctly`() {
        val notation_example = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
        val fenOb = Fen(notation_example)
        assertThat(fenOb.castle).isEqualTo("KQkq")
    }

    @Test
    fun `check if enpassant is correctly`() {
        val notation_example = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
        val fenOb = Fen(notation_example)
        assertThat(fenOb.enpassant).isEqualTo("c6")
    }

    @Test
    fun `check if halfmove is correctly`() {
        val notation_example = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
        val fenOb = Fen(notation_example)
        assertThat(fenOb.halfmoves).isEqualTo(0)
    }

    @Test
    fun `check if fullmove is correctly`() {
        val notation_example = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"
        val fenOb = Fen(notation_example)
        assertThat(fenOb.fullmoves).isEqualTo(2)
    }
}