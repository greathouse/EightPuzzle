package com.greenmoonsoftware.eightpuzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class Solver {
  private final BoardState initialState;
  private final String solutionState = "123456780";
  private int movesForSolution = -1;
  
  private PriorityQueue<BoardState> openStates = new PriorityQueue<BoardState>(1000, new Comparator<BoardState>() {
    @Override
    public int compare(BoardState o1, BoardState o2) {
      return  Integer.valueOf(o1.calculateCost()).compareTo(Integer.valueOf(o2.calculateCost()));
    }
  });
  private List<BoardState> closedStates = new ArrayList<BoardState>();
  
  private int calculations = 0;
  
  public Solver(String initialState) {
    this.initialState = new BoardState(initialState, null);
  }
  
  public Result solve() {
    openStates.add(initialState);
    BoardState currentState = null;
    boolean foundSolution = false;
    
    while(openStates.size() > 0) {
//      System.out.println(openStates.size());
      calculations++;
      currentState = openStates.poll();
//      System.out.println(currentState);
      closedStates.add(currentState);
      if (solutionState.equals(currentState.getState())) {
        System.out.println("Found solution");
        foundSolution = true;
        break;
      }
      
      List<BoardState> nextStates = currentState.getNextAvailableStates();
      nextStates.removeAll(closedStates);
//      System.out.println("Adding "+nextStates.size()+" states to be evaluated...");
      openStates.addAll(nextStates);
    }
    
    calculateMoves(currentState);
    return new Result(movesForSolution, calculations, currentState);
  }
  
  private void calculateMoves(BoardState state) {
    if (state.getParentState() != null) {
      movesForSolution++;
      calculateMoves(state.getParentState());
    }
  }
  
  public static void main(String[] args) {
    Result r = new Solver("573681420").solve();
    System.out.println("Evaluated States: "+r.getCalculations());
    System.out.println("Moves: "+r.getMoves());
    System.out.println("Done!");
  }
}
