package hwr.oop.group4.chess.cli

class NoCommandException : Exception(
  """
        No valid command provided. Try one of the following:
        chess new_game <id>
        chess game show <id>
        chess on <id> move <from> to <to> <promotion-title>
        """.trimIndent()
)