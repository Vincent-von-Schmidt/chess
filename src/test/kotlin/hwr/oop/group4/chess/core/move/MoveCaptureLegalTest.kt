package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.utils.Color
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveCaptureLegalTest : AnnotationSpec() {

  private lateinit var board: Board

  @Test
  fun `pawn white captures black queen`() {
    // Given
    val fen = FEN("8/8/8/8/4q3/3P4/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    val pawn = board.getField(Location(File.D, Rank.THREE)).piece
    val startLocation = Location(File.D, Rank.THREE)
    val endLocation = Location(File.E, Rank.FOUR)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(pawn)
  }

  @Test
  fun `king white captures black rook`() {
    // Given
    val fen = FEN("8/8/8/8/4r3/3K4/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    val king = board.getField(Location(File.D, Rank.THREE)).piece
    val startLocation = Location(File.D, Rank.THREE)
    val endLocation = Location(File.E, Rank.FOUR)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(king)
  }

  @Test
  fun `queen white captures black rook`() {
    // Given
    val fen = FEN("6r1/8/8/3Q4/8/8/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    val queen = board.getField(Location(File.D, Rank.FIVE)).piece
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.G, Rank.EIGHT)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `bishop white captures black rook`() {
    // Given
    val fen = FEN("6r1/8/8/3B4/8/8/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    val bishop = board.getField(Location(File.D, Rank.FIVE)).piece
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.G, Rank.EIGHT)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(bishop)
  }

  @Test
  fun `rook white captures black bishop`() {
    // Given
    val fen = FEN("8/8/8/3R4/8/8/8/3b4", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    val rook = board.getField(Location(File.D, Rank.FIVE)).piece
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.ONE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)
    board.movePiece(moveDesired, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(rook)
  }
}
