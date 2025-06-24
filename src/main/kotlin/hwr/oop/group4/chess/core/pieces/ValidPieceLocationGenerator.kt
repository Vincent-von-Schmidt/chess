package hwr.oop.group4.chess.core.pieces

import hwr.oop.group4.chess.core.board.BoardView
import hwr.oop.group4.chess.core.move.NoPieceException
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.move.Direction
import hwr.oop.group4.chess.core.move.KnightJump

object ValidPieceLocationGenerator {

    fun calculatePossibleLocationsToMove(
    from: Location,
    board: BoardView,
    directions: List<Direction>,
    capture: Boolean,
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

        if (nextField.getPiece() != null) {
          val movingPieceColor = board.getField(from).getPiece()?.getColor() ?: throw NoPieceException(from)
          val occupyingPieceColor = nextField.getPiece()!!.getColor()
          if (capture &&  movingPieceColor != occupyingPieceColor){
            possibleLocations.add(currentLocation)
          }
          break
        }
        possibleLocations.add(currentLocation)
        steps++
      }
    }
    return possibleLocations
  }

  fun calculatePossibleLocationsToJump(
    from: Location,
    board: BoardView,
    knightJumps: List<KnightJump>,
    capture: Boolean
  ): List<Location> {
    val fromField = board.getField(from)
    val possibleLocations = mutableListOf<Location>()

    for (jump in knightJumps) {
      val targetField = jump.moveFrom(fromField)?: continue
      val targetLocation = targetField.location
      if (targetField.getPiece() != null) {

        val movingPieceColor = board.getField(from).getPiece()?.getColor() ?: throw NoPieceException(from)
        val occupyingPieceColor = targetField.getPiece()!!.getColor()
        if (capture &&  movingPieceColor != occupyingPieceColor) {
          possibleLocations.add(targetLocation)
        }
        continue
      }

      possibleLocations.add(targetLocation)
    }
    return possibleLocations
  }
}