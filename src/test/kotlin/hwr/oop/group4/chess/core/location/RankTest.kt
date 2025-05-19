package hwr.oop.group4.chess.core.location

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class RankTest : AnnotationSpec() {

    @Test
    fun `next rank of 1 is 2`() {
        val rank = Rank.ONE
        assertThat(rank.next()).isEqualTo(Rank.TWO)
    }

    @Test
    fun `next rank of 8 is null`() {
        val rank = Rank.EIGHT
        assertThat(rank.next()).isNull()
    }

    @Test
    fun `previous rank of 8 is 7`() {
        val rank = Rank.EIGHT
        assertThat(rank.previous()).isEqualTo(Rank.SEVEN)
    }
}
