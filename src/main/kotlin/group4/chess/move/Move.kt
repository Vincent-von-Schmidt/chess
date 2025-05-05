package group4.chess.move

import group4.chess.board.Location
import group4.chess.pieces.Piece

class Move (
    val startLocation: Location,
    val endLocation: Location,
    val toMovePiece: Piece
)