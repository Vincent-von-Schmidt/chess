package group4.chess.board

import group4.chess.FenLoader
import group4.chess.pieces.*
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class FenLoaderTest : AnnotationSpec() {

    @Test
    fun `fen read correctly`() {
        val board = Board()
        val fenLoader = FenLoader()
        val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"

        val fieldA1 = board.getField(Location('a', 1))
        val fieldH1 = board.getField(Location('h', 1))
        val fieldA8 = board.getField(Location('a', 8))
        val fieldD8 = board.getField(Location('d', 8))
        val fieldB2 = board.getField(Location('b', 2))
        val fieldG4 = board.getField(Location('g', 4))

        fenLoader.placePieces(fen, board)

        assertThat(fieldA1.piece).isInstanceOf(Rook::class.java)
        assertThat(fieldA1.piece?.color).isEqualTo(Color.WHITE)

        assertThat(fieldH1.piece).isInstanceOf(Rook::class.java)
        assertThat(fieldH1.piece?.color).isEqualTo(Color.WHITE)

        assertThat(fieldA8.piece).isInstanceOf(Rook::class.java)
        assertThat(fieldA8.piece?.color).isEqualTo(Color.BLACK)

        assertThat(fieldD8.piece).isInstanceOf(Queen::class.java)
        assertThat(fieldD8.piece?.color).isEqualTo(Color.BLACK)

        assertThat(fieldB2.piece).isInstanceOf(Pawn::class.java)
        assertThat(fieldB2.piece?.color).isEqualTo(Color.WHITE)

        assertThat(fieldG4.piece).isEqualTo(null)
    }

    @Test
    fun `fen read-error on wrong fen`() {
        val board = Board()
        val fenLoader = FenLoader()
        val badFen = "rnbqkbnr/zppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"

        assertThatThrownBy {
            fenLoader.placePieces(badFen, board)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Unbekanntes Zeichen: z")
    }
}