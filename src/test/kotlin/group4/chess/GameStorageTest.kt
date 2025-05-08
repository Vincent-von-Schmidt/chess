package group4.chess

import group4.chess.GameStorage.createGame
import group4.chess.GameStorage.deleteGame
import group4.chess.GameStorage.loadGame
import group4.chess.cli.main
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions.*

class GameStorageTest : AnnotationSpec() {
    // TODO: add all tests for game storage

    @Test
    fun `creating a game`() {
        //when
        val id = 1000000
        val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
        deleteGame(id)
        val file = java.io.File("gamesTest.txt")
        //then
        createGame(id, fen)
        //assert
        assertThat(file.readLines()).contains("$id;$fen")
        deleteGame(1000000)
    }

    @Test
    fun `creating a game with existing ID`() {
        //given
        val id = 1000000
        val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
        deleteGame(id)
        //when
        createGame(id, fen)
        //then
        assertThatThrownBy { createGame(id, fen) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Game already exists.")
        deleteGame(id)
    }

    @Test
    fun `loading game that doesn't exist`() {
        //given
        val id = 1000000
        deleteGame(id)
        //when
        //then
        assertThatThrownBy { loadGame(id) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Game not found.")
        deleteGame(id)
    }

    @Ignore
    @Test
    fun `loading game that exists`() {
        //given
        val id = 1000000
        val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
        deleteGame(id)
        createGame(id, fen)
        //when
        val output = captureStandardOut {
            loadGame(id)
        }
        //then
        assertThat(output).isEqualTo(
        """
        r n b q k b n r
        p p p p p p p p
                       
                       
                       
                       
        P P P P P P P P
        R N B Q K B N R
        """.trimIndent())
        deleteGame(id)
    }

    @Test
    fun `deleting a game`() {
        //given
        val id = 1000000
        val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
        deleteGame(id)
        createGame(id, fen)
        //when
        deleteGame(id)
        //then
        assertThatThrownBy { loadGame(id) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Game not found.")
    }

}