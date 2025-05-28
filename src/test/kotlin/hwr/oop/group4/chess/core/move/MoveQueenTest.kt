package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.Queen
import hwr.oop.group4.chess.core.utils.Color
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class MoveQueenTest : AnnotationSpec() {

  @Test
  fun `queen moves from d1 to c1`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.C, Rank.ONE)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to a1`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.A, Rank.ONE)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to e1`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.E, Rank.ONE)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to h1`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.H, Rank.ONE)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to d2`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.D, Rank.TWO)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to d8`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.D, Rank.EIGHT)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to a4`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.A, Rank.FOUR)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to e2`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.E, Rank.TWO)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to h5`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.H, Rank.FIVE)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }

  @Test
  fun `queen moves from d1 to c2`() {
    val queen = Queen(Color.WHITE)
    val startLocation = Location(File.D, Rank.ONE)
    val endLocation = Location(File.C, Rank.TWO)
    val game = Game(TEST_NUMBER)
    game.board.setPieceToField(startLocation, queen)

    val move = Move(startLocation, endLocation)
    game.board.movePiece(move)
    val pieceOnStartLocation = game.board.getField(startLocation).piece
    val pieceOnEndLocation = game.board.getField(endLocation).piece

    assertThat(pieceOnStartLocation).isNull()
    assertThat(pieceOnEndLocation).isEqualTo(queen)
  }
}