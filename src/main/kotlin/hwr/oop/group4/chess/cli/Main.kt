package cli

import persistence.GameStorage

fun main(args: Array<String>) {
  """
    
    Possible commands: 
    chess new_game <id>
    chess game show <id>
    chess on <id> move <from> to <to>
  
  """.trimIndent()

  val fileSystemPersistenceAdapter = GameStorage()
  val cli = Cli(
    loadGamePort = fileSystemPersistenceAdapter,
    saveGamePort = fileSystemPersistenceAdapter,
  )
  cli.handle(args.toList(), fileSystemPersistenceAdapter, fileSystemPersistenceAdapter)
}
