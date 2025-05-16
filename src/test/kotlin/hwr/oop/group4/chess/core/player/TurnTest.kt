package hwr.oop.group4.chess.core.player

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.pieces.*

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class MoveTest: AnnotationSpec() {

    private lateinit var board: Board
    @BeforeEach
    fun setUp() {
        board = Board()
    }

    @Test
    fun `throw on no white player`() {
        val players = listOf(Player(2, Color.BLACK))

        assertThatThrownBy {
            Turn(players)
        }.hasMessageContaining("Missing WHITE player")
    }

    @Test
    fun `throw on no black player`() {
        val players = listOf(Player(1, Color.WHITE))

        assertThatThrownBy {
            Turn(players)
        }.hasMessageContaining("Missing BLACK player")
    }
}