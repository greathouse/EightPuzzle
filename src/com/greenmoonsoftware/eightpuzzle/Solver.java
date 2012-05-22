package com.greenmoonsoftware.eightpuzzle;


public class Solver {
  private final String puzzleLayout;
  
  public Solver(String puzzleLayout) {
    this.puzzleLayout = puzzleLayout; 
  }
  
  public Result solve() {
    return new Result(0,1);
  }
}
