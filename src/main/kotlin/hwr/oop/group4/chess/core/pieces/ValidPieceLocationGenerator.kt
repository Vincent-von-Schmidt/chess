package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.KnightJump

object ValidPieceLocationGenerator {

  fun calculatePossibleLocationsToMove(
    from: Location,
    board: BoardView,
    directions: List<Direction>,
    maxSteps: Int,
  ): List<Location> {
    val possibleLocations = mutableListOf<Location>()

    for (direction in directions) {
      var currentLocation = from
      var steps = 0

      while (steps < maxSteps) {
        val field = board.getField(currentLocation)
        // use of direction constructor, that gives the next field after direction back
        val nextField = direction.moveFrom(field) ?: break
        currentLocation = nextField.location
        possibleLocations.add(currentLocation)

        if (nextField.getPiece() != null) {
          break
        }
        steps++
      }
    }
    return possibleLocations
  }

  fun calculatePossibleLocationsToJump(
    from: Location,
    board: BoardView,
    knightJumps: List<KnightJump>,
  ): List<Location> {
    val fromField = board.getField(from)
    val possibleLocations = mutableListOf<Location>()

    for (jump in knightJumps) {
      val targetField = jump.moveFrom(fromField)
      val targetLocation = targetField?.location
      if (targetLocation != null) {
        possibleLocations.add(targetLocation)
      }
    }
    return possibleLocations
  }
}