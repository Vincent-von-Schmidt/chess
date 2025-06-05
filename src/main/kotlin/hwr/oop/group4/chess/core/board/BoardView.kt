package hwr.oop.group4.chess.core.board

import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.pieces.Piece

interface BoardView {
  fun getPiece(location: Location): Piece?
  fun getAllPieces(): Map<Location, Piece>
}