package hwr.oop

class Game (
    val id: Int,
) {

    init {
        GameStorage.saveGame(this)
    }
}