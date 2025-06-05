package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.fen.BoardFactory
import hwr.oop.group4.chess.core.fen.FEN

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Queen
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.EMPTY_BOARD
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveQueenTest : AnnotationSpec() {

  private lateinit var board: Board

  @Test
  fun `queen moves from d1 to c1`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.C, Rank.ONE)
    val fen = FEN("8/8/8/8/8/8/8/2Q5", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to a1`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.A, Rank.ONE)
    val fen = FEN("8/8/8/8/8/8/8/3Q4", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to e1`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.E, Rank.ONE)
    val fen = FEN("8/8/8/8/8/8/8/3Q4", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to h1`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.H, Rank.ONE)
    val fen = FEN("8/8/8/8/8/8/8/3Q4", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to d2`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.D, Rank.TWO)
    val fen = FEN("8/8/8/8/8/8/3Q4/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to d8`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.D, Rank.EIGHT)
    val fen = FEN("3Q4/8/8/8/8/8/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to a4`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.A, Rank.FOUR)
    val fen = FEN("8/8/8/8/3Q4/8/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to e2`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.E, Rank.TWO)
    val fen = FEN("8/8/8/8/8/8/4Q3/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to h5`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.H, Rank.FIVE)
    val fen = FEN("8/8/8/5Q2/8/8/8/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to c2`() {
    // Given
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.C, Rank.TWO)
    val fen = FEN("8/8/8/8/8/8/2Q5/8", Color.WHITE, "-", "-", 0, 1)
    board = BoardFactory.generateBoardWithPieces(fen)

    // When
    val move = Move(startLocation, endLocation)
    board.movePiece(move, fen.activeColor)
    val pieceOnStartLocation = board.getField(startLocation).piece
    val pieceOnEndLocation = board.getField(endLocation).piece

    // Then
    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }
}