package hwr.oop.group4.chess.core.utils

import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.pieces.*

object StringParser {
  fun parseLocationFromString(input: String): Location {
    if (input.length != 2) throw InvalidLocationException("Invalid location format: must be exactly 2 characters")

    val fileChar = input[0].uppercaseChar()
    val rankChar = input[1]

    val file = try {
      File.valueOf(fileChar.toString())
    } catch (e: IllegalArgumentException) {
      throw InvalidLocationException("Invalid file character: $fileChar")
    }

    val rank =
      Rank.values().firstOrNull { it.number.toString() == rankChar.toString() }
        ?: throw InvalidLocationException("Invalid rank character: $rankChar")

    return Location(file, rank)
  }

  fun parsePromotionPieceFromString(inputString: String): Piece {
    // all colors white as default (will be changed along the way
    return when (inputString.lowercase()) {
      "queen" -> Queen(Color.WHITE)
      "knight" -> Knight(Color.WHITE)
      "rook" -> Rook(Color.WHITE)
      "bishop" -> Bishop(Color.WHITE)
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
      else -> throw IllegalPieceException(char)
    }
  }

  fun parsePieceCharFromPiece(piece: Piece): Char {
    val isUppercase: Boolean = piece.color == Color.WHITE
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