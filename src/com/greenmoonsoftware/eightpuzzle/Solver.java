package com.greenmoonsoftware.eightpuzzle;

import java.util.ArrayList;
import java.util.List;


public class Solver {
  private final BoardState initialState;
  private final String solutionState = "123456780";
  private List<BoardState> openStates = new ArrayList<BoardState>();
  private List<BoardState> closedStates = new ArrayList<BoardState>();
  
  private int calculations = 0;
  
  public Solver(String initialState) {
    this.initialState = new BoardState(initialState, null);
  }
  
  public Result solve() {
    openStates.add(initialState);
    BoardState currentState = null;
    
    while(openStates.size() > 0) {
      System.out.println(openStates.size());
      calculations++;
      currentState = openStates.remove(0);
//      System.out.println(currentState);
      closedStates.add(currentState);
      if (solutionState.equals(currentState.getState())) {
        System.out.println("Found solution");
      }
      
      List<BoardState> nextStates = currentState.getNextAvailableStates();
      nextStates.removeAll(closedStates);
//      System.out.println("Adding "+nextStates.size()+" states to be evaluated...");
      openStates.addAll(nextStates);
    }
    
    return new Result(0, calculations, currentState);
  }
  
  public static void main(String[] args) {
    Result r = new Solver("123450786").solve();
    System.out.println(r.getFinalState().toString());
    System.out.println("Done!");
  }
}
