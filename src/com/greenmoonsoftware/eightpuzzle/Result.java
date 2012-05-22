package com.greenmoonsoftware.eightpuzzle;

public class Result {
  private int moves;
  private int calculations;
  private BoardState finalState;
  
  public Result(int moves, int calculations, BoardState finalState) {
    this.moves = moves;
    this.calculations = calculations;
  }
  
  public int getCalculations() {
    return calculations;
  }
  
  public int getMoves() {
    return moves;
  }
  
  public BoardState getFinalState() {
    return finalState;
  }
}
