package hwr.oop.group4.chess.core.utils

class InvalidPromotionException : Exception(
  """
  Valid Promotions are...
  ...Queen, Rook, Bishop, Knight.  
  """.trimIndent()
)