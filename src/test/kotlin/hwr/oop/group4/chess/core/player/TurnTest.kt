package hwr.oop.group4.chess.core.player

import hwr.oop.group4.chess.core.board.Board
import io.kotest.core.spec.style.AnnotationSpec

class MoveTest : AnnotationSpec() {

  private lateinit var board: Board

  @BeforeEach
  fun setUp() {
    board = Board()
  }
}