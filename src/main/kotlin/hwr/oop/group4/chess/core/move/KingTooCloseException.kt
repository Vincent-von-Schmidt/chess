package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.utils.Color

class KingTooCloseException(kingColor : Color, location: Location) : Exception(
  "${kingColor.toString()} King of the opponent is too close to move to ${location.description}"
)