package com.greenmoonsoftware.eightpuzzle;

public class Result {
  private int moves;
  private int calculations;
  private BoardState finalState;
  private boolean foundSolution;
  
  public Result(int moves, int calculations, BoardState finalState, boolean foundSolution) {
    this.moves = moves;
    this.calculations = calculations;
    this.finalState = finalState;
    this.foundSolution = foundSolution;
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
  
  public boolean isFoundSolution() {
    return foundSolution;
  }
}
