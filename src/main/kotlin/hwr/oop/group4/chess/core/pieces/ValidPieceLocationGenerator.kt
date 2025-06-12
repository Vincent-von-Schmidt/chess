package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.KnightJump

object ValidPieceLocationGenerator {

  fun searchAllowedLocations(
    from: Location,
    board: BoardView,
    directions: List<Direction>,
    maxSteps: Int,
  ): List<Location> {
    val possibleLocations = mutableListOf<Location>()

    for (direction in directions) {
      var current = from
      var steps = 0

      while (steps < maxSteps) {
        val field = board.getField(current)
        // use of direction constructor, that gives the next field after direction back
        val nextField = direction.move(field) ?: break
        current = nextField.location
        possibleLocations.add(current)

        if (nextField.piece != null) {
          break
        }
        steps++
      }
    }
    return possibleLocations
  }

  fun searchJumpLocations(
    from: Location,
    board: BoardView,
    knightJumps: List<KnightJump>,
  ): List<Location> {
    val fromField = board.getField(from)
    val possibleLocations = mutableListOf<Location>()

    for (jump in knightJumps) {
      val targetField = jump.move(fromField)
      val targetLocation = targetField?.location
      if (targetLocation != null) {
        possibleLocations.add(targetLocation)
      }
    }
    return possibleLocations
  }
}