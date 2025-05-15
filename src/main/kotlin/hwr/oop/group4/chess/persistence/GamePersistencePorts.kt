package hwr.oop.group4.chess.persistence

interface LoadGamePort {
    fun loadGame(id: Int): String
}

interface SaveGamePort {
    fun saveGame(id: Int, fen: String)
}

interface DeleteGamePort {
    fun deleteGame(id: Int)
}
