package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.fen.ParserFEN
import hwr.oop.group4.chess.core.game.GameState
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import java.io.File as JavaFile

class SaveEntryTest : AnnotationSpec() {
  @Test
  fun `check if getScores returns not equal to 0`() {
    // Given
    val fen: FEN = ParserFEN.parseStringToFen("3b4/8/1p6/p2k4/PP4Kp/8/8/4B3 b - - 0 1")

    // That
    val entry: SaveEntry = SaveEntry(fen, 2, 2, GameState.NORMAL)

    // Then
    assertThat(entry.getBlackScore()).isEqualTo(2)
    assertThat(entry.getWhiteScore()).isEqualTo(2)
  }
}
