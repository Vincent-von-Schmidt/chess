package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.location.Location

class KingTooCloseException(location: Location) : Exception(
  "King of the opponent is too close to move to ${location.description}"
)