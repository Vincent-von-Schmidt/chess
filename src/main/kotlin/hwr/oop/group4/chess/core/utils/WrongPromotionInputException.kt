package hwr.oop.group4.chess.core.utils

class WrongPromotionInputException : Exception( // TODO combine with NoPromoteChoiceException
  """
  Valid Promotions are...
  ...Queen, Rook, Bishop, Knight.  
  """.trimIndent()
)