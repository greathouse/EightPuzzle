package com.greenmoonsoftware.eightpuzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;


public class Solver {
  private final BoardState initialState;
  private int movesForSolution = 0;
  
  private PriorityQueue<BoardState> openStates = new PriorityQueue<BoardState>(1000, new Comparator<BoardState>() {
    @Override
    public int compare(BoardState o1, BoardState o2) {
      return  Integer.valueOf(o1.calculateCost()).compareTo(Integer.valueOf(o2.calculateCost()));
    }
  });
  private Set<BoardState> closedStates = new HashSet<BoardState>();
  
  private int calculations = 0;
  
  public Solver(int dimX, int dimY, String initialState, String solutionState) {
    ApplicationState.instance.initialize(dimX,dimY,solutionState);
    this.initialState = new BoardState(initialState, null);
  }
  
  public Result solve() {
    openStates.add(initialState);
    BoardState currentState = null;
    boolean foundSolution = false;
    
    while(openStates.size() > 0) {
      calculations++;
      currentState = openStates.poll();
      closedStates.add(currentState);
      if (ApplicationState.instance.getSolutionState().equals(currentState.getState())) {
        System.out.println("Found solution");
        foundSolution = true;
        break;
      }
      
      Set<BoardState> nextStates = currentState.getNextAvailableStates();
      nextStates.removeAll(closedStates);
      openStates.addAll(nextStates);
    }
    
    calculateMoves(currentState);
    return new Result(movesForSolution, calculations, currentState, foundSolution);
  }
  
  private void calculateMoves(BoardState state) {
    if (state.getParentState() != null) {
      movesForSolution++;
      calculateMoves(state.getParentState());
    }
  }
  
  public static void main(String[] args) {
    Result r = new Solver(3,3,"0 1 2 3 4 5 6 7 8", "1 2 3 4 5 6 7 8 0").solve();
    if (!r.isFoundSolution()) {
      System.out.println("No solution!");
      return;
    }
    System.out.println("Evaluated States: "+r.getCalculations());
    System.out.println("Moves: "+r.getMoves());
    List<BoardState> boardStates = new ArrayList<BoardState>();
    
    BoardState s = r.getFinalState();
    while (s != null) {
      boardStates.add(0,s);
      s = s.getParentState();
    }
    for (BoardState state : boardStates) {
      System.out.println("-------------------");
      System.out.println(state.toString());
    }
    System.out.println("Done!");
  }
}
