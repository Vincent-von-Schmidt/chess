package hwr.oop.group4.chess.core.utils

import hwr.oop.group4.chess.core.board.InvalidPromotionException
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.*

object StringParser {
  fun parseLocationFromString(input: String): Location {
    if (input.length != 2) throw InvalidLocationException("Invalid location format: must be exactly 2 characters") // TODO rewrite the exception

    val fileChar = input[0].uppercaseChar()
    val rankChar = input[1]

    val file = try {
      File.valueOf(fileChar.toString())
    } catch (e: IllegalArgumentException) {
      throw InvalidLocationException("Invalid file character: $fileChar")
    }

    val rank =
      Rank.entries.firstOrNull { it.number.toString() == rankChar.toString() } // TODO weird entries shenanigans
        ?: throw InvalidLocationException("Invalid rank character: $rankChar")

    return Location(file, rank)
  }

  fun parsePromotionPieceFromString(inputString: String, color: Color): Piece {
    // all colors white as default (will be changed along the way
    return when (inputString.lowercase()) {
      "queen" -> Queen(color)
      "knight" -> Knight(color)
      "rook" -> Rook(color)
      "bishop" -> Bishop(color)
      else -> throw InvalidPromotionException()
    }
  }

  fun parsePieceFromChar(char: Char): Piece {
    val color = if (char.isUpperCase()) Color.WHITE else Color.BLACK
    return when (char) {
      'b', 'B' -> Bishop(color)
      'k', 'K' -> King(color)
      'q', 'Q' -> Queen(color)
      'n', 'N' -> Knight(color)
      'r', 'R' -> Rook(color)
      'p', 'P' -> Pawn(color)
      else -> throw InvalidPieceException(char)
    }
  }

  fun parsePieceCharFromPiece(piece: Piece): Char {
    val isUppercase: Boolean = piece.getColor() == Color.WHITE
    val char = when (piece) {
      is Bishop -> 'b'
      is King -> 'k'
      is Queen -> 'q'
      is Knight -> 'n'
      is Rook -> 'r'
      is Pawn -> 'p'
    }
    return if (isUppercase) char.uppercaseChar() else char
  }
}