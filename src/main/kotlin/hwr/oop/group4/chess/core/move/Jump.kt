package hwr.oop.group4.chess.core.move

import hwr.oop.group4.chess.core.board.Field

enum class Jump(val move: (Field) -> Field?) {
    TOP_LEFT_LEFT({ it.top?.left?.left }),
    TOP_TOP_LEFT({ it.top?.top?.left }),
    TOP_TOP_RIGHT({ it.top?.top?.right }),
    TOP_RIGHT_RIGHT({ it.top?.right?.right }),
    BOTTOM_RIGHT_RIGHT({ it.bottom?.right?.right }),
    BOTTOM_BOTTOM_RIGHT({ it.bottom?.bottom?.right }),
    BOTTOM_BOTTOM_LEFT({ it.bottom?.bottom?.left }),
    BOTTOM_LEFT_LEFT({ it.bottom?.left?.left });
}
