package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.Location

class NoFieldException(location: Location) : Exception(
  "No field at ${location.description}"
)