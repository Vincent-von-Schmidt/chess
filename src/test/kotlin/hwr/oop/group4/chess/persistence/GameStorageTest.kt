package hwr.oop.group4.chess.persistence

import hwr.oop.group4.chess.persistence.GameStorage.GameDoesNotExistException
import hwr.oop.group4.chess.persistence.GameStorage.GameAlreadyExistsException
import hwr.oop.group4.chess.core.utils.Constants.GAMES_FILE_TEST
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import hwr.oop.group4.chess.persistence.GameStorage

import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.*

class GameStorageTest : AnnotationSpec() {

    private val storage = GameStorage()
    private val file = java.io.File(GAMES_FILE_TEST)

    @BeforeEach
    fun setup() {
        file.deleteRecursively()
    }

    @AfterEach
    fun tearDown() {
        file.deleteRecursively()
    }

    @Test
    fun `games folder does not exist`() {
        // Given
        val id = TEST_NUMBER
        val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"

        // When
        storage.saveGame(id, fen)

        // Then
        assertThat(file.exists()).isTrue
    }

    @Test
    fun `creating a game`() {
        //given
        val id = TEST_NUMBER
        val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
        //when
        storage.saveGame(id, fen)
        //then
        assertThat(file.readLines()).contains("$id;$fen")
    }

    @Test
    fun `creating a game with existing ID`() {
        //given
        val id = TEST_NUMBER
        val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
        //when
        storage.saveGame(id, fen)
        //then
        assertThatThrownBy { storage.saveGame(id, fen) }
            .isInstanceOf(GameAlreadyExistsException::class.java)
            .hasMessage("Game with ID $id already exists.")
    }

    @Test
    fun `loading game that doesn't exist`() {
        //given
        val id = TEST_NUMBER

        //when

        //then
        assertThatThrownBy { storage.loadGame(id) }
            .isInstanceOf(GameDoesNotExistException::class.java)
            .hasMessage("Game with ID $id does not exist.")
    }

    @Test
    fun `loading game that exists`() {
        //given
        val id = TEST_NUMBER
        val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
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
    }

    @Test
    fun `deleting a game`() {
        //given
        val id = TEST_NUMBER
        val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
        storage.saveGame(id, fen)
        //when
        storage.deleteGame(id)
        //then
        assertThatThrownBy { storage.loadGame(id) }
            .isInstanceOf(GameDoesNotExistException::class.java)
            .hasMessage("Game with ID $id does not exist.")
    }

    @Test
    fun `create two games and load the first`() {
        // Given
        val id1 = TEST_NUMBER
        val fen1 = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"
        val id2 = TEST_NUMBER + 1
        val fen2 = "Rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2"

        // When
        storage.saveGame(id1, fen1)
        storage.saveGame(id2, fen2)
        val output = storage.loadGame(id1)

        // Then
        assertThat(output).isEqualTo(
        """
        r n b q k b n r
        p p p p p p p p
                       
                       
                       
                       
        P P P P P P P P
        R N B Q K B N R
        """.trimIndent())
    }

}
