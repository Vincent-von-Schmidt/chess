package group4.chess

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions.*

class GameStorageTest : AnnotationSpec() {
    // TODO: add mutation tests for game storage

    private val storage = GameStorage()

    @Test
    fun `creating a game`() {
        //given
        val id = 1000000
        val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
        storage.deleteGame(id)
        val file = java.io.File("gamesTest.txt")
        //when
        storage.saveGame(id, fen)
        //then
        assertThat(file.readLines()).contains("$id;$fen")
        storage.deleteGame(1000000)
    }

    @Test
    fun `creating a game with existing ID`() {
        //given
        val id = 1000000
        val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
        storage.deleteGame(id)
        //when
        storage.saveGame(id, fen)
        //then
        assertThatThrownBy { storage.saveGame(id, fen) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Game already exists.")
        storage.deleteGame(id)
    }

    @Test
    fun `loading game that doesn't exist`() {
        //given
        val id = 1000000
        storage.deleteGame(id)
        //when
        //then
        assertThatThrownBy { storage.loadGame(id) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Game not found.")
        storage.deleteGame(id)
    }

    @Test
    fun `loading game that exists`() {
        //given
        val id = 1000000
        val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
        storage.deleteGame(id)
        storage.saveGame(id, fen)
        //when
        val output = storage.loadGame(id)
        //then
        assertThat(output).isEqualTo(
        """
        r n b q k b n r
        p p p p p p p p
                       
                       
                       
                       
        P P P P P P P P
        R N B Q K B N R
        """.trimIndent())
        storage.deleteGame(id)
    }

    @Test
    fun `deleting a game`() {
        //given
        val id = 1000000
        val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
        storage.deleteGame(id)
        storage.saveGame(id, fen)
        //when
        storage.deleteGame(id)
        //then
        assertThatThrownBy { storage.loadGame(id) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Game not found.")
    }

}