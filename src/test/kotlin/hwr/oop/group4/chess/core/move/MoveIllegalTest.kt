package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.board.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.utils.Color
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThatThrownBy

class MoveIllegalTest : AnnotationSpec() {

  private lateinit var board: Board

  @Test
  fun `empty field is immovable`() {
    val fen = FEN("8/8/8/8/8/8/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    // Given
    val startLocation = Location(File.A, Rank.TWO)
    val endLocation = Location(File.H, Rank.SEVEN)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("A2 does not contain a piece")
  }

  @Test
  fun `queen throw on illegal move`() {
    val fen = FEN("8/8/8/8/8/8/Q7/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    // Given
    val startLocation = Location(File.A, Rank.TWO)
    val endLocation = Location(File.H, Rank.SEVEN)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("WHITE Queen can not be moved to H7")
  }

  @Test
  fun `throw on white pawn move to occupied file with white queen`() {
    val fen = FEN("8/8/8/8/3Q4/3P4/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    // Given
    val startLocation = Location(File.D, Rank.THREE)
    val endLocation = Location(File.D, Rank.FOUR)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("WHITE Pawn can not be moved to D4")
  }

  @Test
  fun `throw on white pawn capture to occupied file with white queen`() {
    val fen = FEN("8/8/8/8/4Q3/3P4/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    // Given
    val startLocation = Location(File.D, Rank.THREE)
    val endLocation = Location(File.E, Rank.FOUR)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("WHITE Pawn can not be moved to E4")
  }

  @Test
  fun `pawn black throw on move from d5 to d6`() {
    val fen = FEN("8/8/8/3p4/8/8/8/8", Color.BLACK, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    // Given
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.SIX)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("BLACK Pawn can not be moved to D6")
  }

  @Test
  fun `pawn black throw on move from d5 to h8`() {
    val fen = FEN("8/8/8/3p4/8/8/8/8", Color.BLACK, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    // Given
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.H, Rank.EIGHT)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("BLACK Pawn can not be moved to H8")
  }

  @Test
  fun `pawn white throw on move from d5 to d4`() {
    val fen = FEN("8/8/8/3P4/8/8/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    // Given
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.D, Rank.FOUR)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("WHITE Pawn can not be moved to D4")
  }

  @Test
  fun `pawn white throw on move from d5 to h8`() {
    val fen = FEN("8/8/8/3P4/8/8/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    // Given
    val startLocation = Location(File.D, Rank.FIVE)
    val endLocation = Location(File.H, Rank.EIGHT)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("WHITE Pawn can not be moved to H8")
  }

  @Test
  fun `king throw on move from e1 to h8`() {
    val fen = FEN("8/8/8/8/8/8/8/4K3", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    // Given
    val startLocation = Location(File.E, Rank.ONE)
    val endLocation = Location(File.H, Rank.EIGHT)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("WHITE King can not be moved to H8")
  }

  @Test
  fun `knight throw on move from d4 to h8`() {
    val fen = FEN("8/8/8/8/3n4/8/8/8", Color.BLACK, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    // Given
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.H, Rank.EIGHT)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("BLACK Knight can not be moved to H8")
  }

  @Test
  fun `queen throw on move from d4 to h1`() {
    val fen = FEN("8/8/8/8/3Q4/8/8/7q", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    // Given
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.H, Rank.ONE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("WHITE Queen can not be moved to H1")
  }

  @Test
  fun `bishop throw on interrupted path move`() {
    val fen = FEN("2b5/1k6/8/8/8/8/8/8", Color.BLACK, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    // Given
    val startLocation = Location(File.C, Rank.EIGHT)
    val interruptedLocation = Location(File.A, Rank.SIX)

    // When
    val moveDesired = MoveDesired(startLocation, interruptedLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("BLACK Bishop can not be moved to A6")
  }

  @Test
  fun `bishop throw on move from d4 to h1`() {
    val fen = FEN("8/8/8/8/3b4/8/8/8", Color.BLACK, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    // Given
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.H, Rank.ONE)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("BLACK Bishop can not be moved to H1")
  }

  @Test
  fun `rook throw on move from d4 to h8`() {
    val fen = FEN("8/8/8/8/3r4/8/8/8", Color.BLACK, "-", "-", 0, 1)
    board = BoardFactory.generateBoardFromFen(fen)
    // Given
    val startLocation = Location(File.D, Rank.FOUR)
    val endLocation = Location(File.H, Rank.EIGHT)

    // When
    val moveDesired = MoveDesired(startLocation, endLocation)

    // Then
    assertThatThrownBy {
      board.movePiece(moveDesired, fen.activeColor)
    }.hasMessage("BLACK Rook can not be moved to H8")
  }
}
