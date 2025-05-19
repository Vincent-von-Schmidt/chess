package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Field

enum class Direction(val move: (Field) -> Field?) {
    TOP({ it.top }),
    BOTTOM({ it.bottom }),
    RIGHT({ it.right }),
    LEFT({ it.left }),

    TOP_RIGHT({ it.top?.right }),
    TOP_LEFT({ it.top?.left }),
    BOTTOM_RIGHT({ it.bottom?.right }),
    BOTTOM_LEFT({ it.bottom?.left }),
}
