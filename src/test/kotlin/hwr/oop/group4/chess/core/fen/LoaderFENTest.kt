package hwr.oop.group4.chess.core.fen

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Color

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

class LoaderFENTest : AnnotationSpec() {

     @Test
     fun `pieces from fen placed correctly`() {
         val board = Board()
         val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"

         val loaderFEN = LoaderFEN()
         val readerFEN = ReaderFEN(fen)

         val fieldA1 = board.getField(Location(File.A, 1))
         val fieldH1 = board.getField(Location(File.H, 1))
         val fieldA8 = board.getField(Location(File.A, 8))
         val fieldD8 = board.getField(Location(File.D, 8))
         val fieldB2 = board.getField(Location(File.B, 2))
         val fieldG4 = board.getField(Location(File.G, 4))

         loaderFEN.placePieces(readerFEN, board)

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
     }

     @Test
     fun `piece placement throws an error on wrong fen`() {
         val board = Board()
         val badFen = "rnbqkbnr/zppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"

         val fenReader = ReaderFEN(badFen)
         val fenLoader = LoaderFEN()

         assertThatThrownBy {
             fenLoader.placePieces(fenReader, board)
         }.hasMessageContaining("Unknown char: z")
     }
}
