package hwr.oop.group4.chess.core.game

import hwr.oop.group4.chess.core.utils.Color

class GameWinningException(winningColor: Color) :
  Exception("Player $winningColor won the game!")