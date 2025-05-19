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

  JUMP_TOP_LEFT_LEFT({ it.top?.left?.left }),
  JUMP_TOP_TOP_LEFT({ it.top?.top?.left }),
  JUMP_TOP_TOP_RIGHT({ it.top?.top?.right }),
  JUMP_TOP_RIGHT_RIGHT({ it.top?.right?.right }),
  JUMP_BOTTOM_RIGHT_RIGHT({ it.bottom?.right?.right }),
  JUMP_BOTTOM_BOTTOM_RIGHT({ it.bottom?.bottom?.right }),
  JUMP_BOTTOM_BOTTOM_LEFT({ it.bottom?.bottom?.left }),
  JUMP_BOTTOM_LEFT_LEFT({ it.bottom?.left?.left });

}
