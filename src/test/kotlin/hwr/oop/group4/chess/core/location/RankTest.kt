package hwr.oop.group4.chess.core.location

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class RankTest : AnnotationSpec() {


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

  @Test
  fun `all next ranks from ONE are correctly read`() {
    // Given
    var rank: Rank? = Rank.ONE
    val ranks = mutableListOf<Rank?>()

    // When
    while (rank != null) {
      ranks.add(rank)
      rank = rank.next()
    }

    // Then
    assertThat(ranks).containsExactly(
      Rank.ONE,
     Rank.TWO,
     Rank.THREE,
     Rank.FOUR,
     Rank.FIVE,
     Rank.SIX,
     Rank.SEVEN,
     Rank.EIGHT
    )
  }
}
