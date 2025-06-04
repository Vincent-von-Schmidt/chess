package hwr.oop.group4.chess.cli

import hwr.oop.group4.chess.persistence.GameStorage

fun main(args: Array<String>) {
  """
    
    Possible commands: 
    chess new_game <id>
    chess game show <id>
    chess on <id> move <from> to <to>
  
  """.trimIndent()

  val fileSystemPersistenceAdapter = GameStorage()
  val cli = Cli()
  cli.handle(
    args.toList(),
    fileSystemPersistenceAdapter,
    fileSystemPersistenceAdapter
  )
}
