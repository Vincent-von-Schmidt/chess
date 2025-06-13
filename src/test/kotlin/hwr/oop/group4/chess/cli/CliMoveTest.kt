package hwr.oop.group4.chess.cli

import hwr.oop.group4.chess.core.Game
import hwr.oop.group4.chess.core.fen.ParserFEN
import hwr.oop.group4.chess.core.location.File
import hwr.oop.group4.chess.core.location.Location
import hwr.oop.group4.chess.core.location.Rank
import hwr.oop.group4.chess.core.move.MoveDesired
import hwr.oop.group4.chess.core.utils.Constants.TEST_NUMBER
import hwr.oop.group4.chess.persistence.GameStorage
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.system.captureStandardOut
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy


class CliMoveTest : AnnotationSpec() {

  private val game = Game(TEST_NUMBER)

  @BeforeEach
  fun setup() {
    GameStorage.deleteGame(game)
  }

  @Test
  fun `user prompts valid move`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER.toString()))

    // When
    val outputMove = captureStandardOut {
      main(arrayOf("on", TEST_NUMBER.toString(), "move", "e2", "to", "e3"))
    }.trim()

    val outputShow = captureStandardOut {
      main(arrayOf("game", "show", TEST_NUMBER.toString()))
    }.trim()

    // Then
    assertThat(outputMove).isEqualTo("Move from E2 to E3 executed.")
    assertThat(outputShow).isEqualTo("r n b q k b n r \np p p p p p p p \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \n- - - - P - - - \nP P P P - P P P \nR N B Q K B N R \nBLACK to move.")
  }

  @Test
  fun `user prompts invalid move`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER.toString()))

    // When
    val outputMove = captureStandardOut {
      main(arrayOf("on", TEST_NUMBER.toString(), "move", "e2", "to", "e5"))
    }.trim()

    val outputShow = captureStandardOut {
      main(arrayOf("game", "show", TEST_NUMBER.toString()))
    }.trim()

    // Then
    assertThat(outputMove).isEqualTo("Invalid move from E2 to E5.")
    assertThat(outputShow).isEqualTo("r n b q k b n r \np p p p p p p p \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \n- - - - - - - - \nP P P P P P P P \nR N B Q K B N R \nWHITE to move.")
  }

  @Test
  fun `user prompts less than 6 args`() {
    // Then
    assertThatThrownBy {
      main(
        arrayOf(
          "on",
          TEST_NUMBER.toString(),
          "move",
          "e2",
          "to"
        )
      )
    }
      .hasMessage(
        """
        No valid command provided. Try one of the following:
        chess new_game <id>
        chess game show <id>
        chess on <id> move <from> to <to> <promotion-title>
        """.trimIndent()
      )
  }

  @Test
  fun `user prompts more than 7 args`() {
    // Then
    assertThatThrownBy {
      main(
        arrayOf(
          "on",
          TEST_NUMBER.toString(),
          "move",
          "e2",
          "to",
          "e4",
          "queen",
          "extra"
        )
      )
    }
      .hasMessage(
        """
        No valid command provided. Try one of the following:
        chess new_game <id>
        chess game show <id>
        chess on <id> move <from> to <to> <promotion-title>
        """.trimIndent()
      )
  }

  @Test
  fun `user prompts invalid id format`() {
    // Then
    assertThatThrownBy {
      main(
        arrayOf(
          "on",
          "invalid_id",
          "move",
          "e2",
          "to",
          "e4"
        )
      )
    }
      .hasMessage(
        """
        Error: <id> must be a valid integer!
        """.trimIndent()
      )
  }

  @Test
  fun `user prompts invalid keyword move`() {
    // Then
    assertThatThrownBy {
      main(
        arrayOf(
          "on",
          TEST_NUMBER.toString(),
          "invalid_keyword",
          "e2",
          "to",
          "e4"
        )
      )
    }
      .hasMessage(
        """
        No valid command provided. Try one of the following:
        chess new_game <id>
        chess game show <id>
        chess on <id> move <from> to <to> <promotion-title>
        """.trimIndent()
      )
  }

  @Test
  fun `user prompts invalid keyword to`() {
    // Then
    assertThatThrownBy {
      main(
        arrayOf(
          "on",
          TEST_NUMBER.toString(),
          "move",
          "e2",
          "invalid_keyword",
          "e4"
        )
      )
    }
      .hasMessage(
        """
        No valid command provided. Try one of the following:
        chess new_game <id>
        chess game show <id>
        chess on <id> move <from> to <to> <promotion-title>
        """.trimIndent()
      )
  }

  @Test
  fun `user prompts right promotion-title queen`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER.toString()))

    // When
    val outputMove = captureStandardOut {
      main(
        arrayOf(
          "on",
          TEST_NUMBER.toString(),
          "move",
          "a2",
          "to",
          "a4",
          "queen"
        )
      )
    }.trim()

    // Then
    assertThat(outputMove).isEqualTo("Move from A2 to A4 executed.")
  }

  @Test
  fun `user prompts right promotion-title rook`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER.toString()))

    // When
    val outputMove = captureStandardOut {
      main(
        arrayOf(
          "on",
          TEST_NUMBER.toString(),
          "move",
          "a2",
          "to",
          "a4",
          "rook"
        )
      )
    }.trim()

    // Then
    assertThat(outputMove).isEqualTo("Move from A2 to A4 executed.")
  }

  @Test
  fun `user prompts right promotion-title bishop`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER.toString()))

    // When
    val outputMove = captureStandardOut {
      main(
        arrayOf(
          "on",
          TEST_NUMBER.toString(),
          "move",
          "a2",
          "to",
          "a4",
          "bishop"
        )
      )
    }.trim()

    // Then
    assertThat(outputMove).isEqualTo("Move from A2 to A4 executed.")
  }

  @Test
  fun `user prompts right promotion-title knight`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER.toString()))

    // When
    val outputMove = captureStandardOut {
      main(
        arrayOf(
          "on",
          TEST_NUMBER.toString(),
          "move",
          "a2",
          "to",
          "a4",
          "knight"
        )
      )
    }.trim()

    // Then
    assertThat(outputMove).isEqualTo("Move from A2 to A4 executed.")
  }

  @Test
  fun `user prompts wrong promotion-title`() {
    // Given
    main(arrayOf("new_game", TEST_NUMBER.toString()))

    // Then
    assertThatThrownBy {
      main(
        arrayOf(
          "on",
          TEST_NUMBER.toString(),
          "move",
          "a2",
          "to",
          "a4",
          "wrong"
        )
      )
    }
      .hasMessage(
        """
    Valid Promotions are...
    ...Queen, Rook, Bishop, Knight.  
    """.trimIndent()
      )
  }

  @Test
  fun `user makes draw move`() {
    // Given
    val game = Game(
      TEST_NUMBER,
      ParserFEN.parseStringToFen("8/8/8/8/8/8/r7/R7 w - - 0 1")
    )
    GameStorage.saveGame(game)

    // When
    val a1 = Location(File.A, Rank.ONE)
    val a2 = Location(File.A, Rank.TWO)
    val b1 = Location(File.B, Rank.ONE)
    val b2 = Location(File.B, Rank.TWO)
    game.movePiece(MoveDesired(a1, b1))
    game.movePiece(MoveDesired(a2, b2))
    game.movePiece(MoveDesired(b1, a1))
    game.movePiece(MoveDesired(b2, a2))

    game.movePiece(MoveDesired(a1, b1))
    game.movePiece(MoveDesired(a2, b2))
    game.movePiece(MoveDesired(b1, a1))

    val output = captureStandardOut {
      main(arrayOf("on", TEST_NUMBER.toString(), "move", "b2", "to", "a2"))
    }.trim()

    // Then
    assertThat(output).isEqualTo("The game ended in a draw.")
  }

}