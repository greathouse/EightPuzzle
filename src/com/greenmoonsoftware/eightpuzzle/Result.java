package com.greenmoonsoftware.eightpuzzle;

public class Result {
  private final int moves;
  private final int calculations;
  
  public Result(int moves, int calculations) {
    this.moves = moves;
    this.calculations = calculations;
  }
  
  public int getCalculations() {
    return calculations;
  }
  
  public int getMoves() {
    return moves;
  }
}
