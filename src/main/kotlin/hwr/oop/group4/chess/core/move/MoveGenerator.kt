package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Board
import hwr.oop.group4.chess.core.location.Location

class MoveGenerator {

  fun searchAllowedLocations(
    from: Location,
    board: Board,
    directions: List<Direction>,
    maxSteps: Int = 8,
  ): List<Location> {
    val possibleLocations = mutableListOf<Location>()

    for (direction in directions) {
      var current = from
      for (step in 1..maxSteps) {
        val field = board.getField(current)
        val nextField = direction.move(field) ?: break
        if (nextField.piece != null) { //interrupt path if piece in the way
          current = nextField.location
          possibleLocations.add(current)
          break
        }
        current = nextField.location
        possibleLocations.add(current)
      }
    }
    return possibleLocations
  }

  fun searchJumpLocations(
    from: Location,
    board: Board,
    knightJumps: List<KnightJump>,
  ): List<Location> {
    val possibleLocations = mutableListOf<Location>()
    for (jump in knightJumps) {
      var current = from

      val field = board.getField(current)
      val nextField = jump.move(field) ?: continue
      current = nextField.location
      possibleLocations.add(current)
    }
    return possibleLocations
  }
}