package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.utils.Color

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class LoaderFENTest : AnnotationSpec() {

  @Test
  fun `pieces from fen placed correctly`() {
    val board = Board()
    val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"

    val piecePlacement = ReaderFEN(fen).piecePlacement

    val fieldA1 = board.getField(Location(File.A, Rank.ONE))
    val fieldH1 = board.getField(Location(File.H, Rank.ONE))
    val fieldA8 = board.getField(Location(File.A, Rank.EIGHT))
    val fieldD8 = board.getField(Location(File.D, Rank.EIGHT))
    val fieldB2 = board.getField(Location(File.B, Rank.TWO))
    val fieldG4 = board.getField(Location(File.G, Rank.FOUR))
    val fieldG1 = board.getField(Location(File.G, Rank.ONE))

    LoaderFEN.placePieces(piecePlacement, board)

    assertThat(fieldA1.piece?.name).isEqualTo("Rook")
    assertThat(fieldA1.piece?.color).isEqualTo(Color.WHITE)

    assertThat(fieldH1.piece?.name).isEqualTo("Rook")
    assertThat(fieldH1.piece?.color).isEqualTo(Color.WHITE)

    assertThat(fieldA8.piece?.name).isEqualTo("Rook")
    assertThat(fieldA8.piece?.color).isEqualTo(Color.BLACK)

    assertThat(fieldD8.piece?.name).isEqualTo("Queen")
    assertThat(fieldD8.piece?.color).isEqualTo(Color.BLACK)

    assertThat(fieldB2.piece?.name).isEqualTo("Pawn")
    assertThat(fieldB2.piece?.color).isEqualTo(Color.WHITE)
    assertThat(fieldG4.piece).isNull()
    assertThat(fieldG1.piece?.name).isEqualTo("Knight")
    assertThat(fieldG1.piece?.color).isEqualTo(Color.WHITE)
  }

  @Test
  fun `piece placement throws an error on wrong fen`() {
    val board = Board()
    val badFen = "rnbqkbnr/zppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"

    val piecePlacement = ReaderFEN(badFen).piecePlacement

    assertThatThrownBy {
      LoaderFEN.placePieces(piecePlacement, board)
    }.hasMessage("The piece placement [rnbqkbnr, zppppppp, 8, 8, 8, 8, PPPPPPPP, RNBQKBNR] is invalid.")
  }
}
