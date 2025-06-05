package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank

class FieldIterator(private val board: BoardView) : Iterator<Field> {
  private var currentFile: File = File.A
  private var currentRank: Rank = Rank.EIGHT
  private var hasReturnedLastField = false

  override fun hasNext(): Boolean {
    return !hasReturnedLastField
  }

  override fun next(): Field {
    val location = Location(currentFile, currentRank)
    val field = board.getField(location)

    // Check if this was the last field
    if (currentFile == File.H && currentRank == Rank.ONE) {
      hasReturnedLastField = true
    } else {
      // Advance to next location
      currentFile = currentFile.next() ?: File.A
      if (currentFile == File.A) {
        currentRank = currentRank.previous() ?: Rank.ONE
      }
    }

    return field
  }
}