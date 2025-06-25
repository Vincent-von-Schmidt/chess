package hwr.oop.group4.chess.core.game

import hwr.oop.group4.chess.core.utils.Color

class CheckMateException(state: GameState, winningColor: Color) :
  Exception("The game ended in a ${state.toString()}. The winner is $winningColor")