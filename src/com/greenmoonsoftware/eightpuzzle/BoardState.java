package com.greenmoonsoftware.eightpuzzle;

import java.util.ArrayList;
import java.util.List;

public class BoardState {
  private String state;
  private char[][] chars;
  private BoardState parentState;
  private List<BoardState> nextStates;
  private int cost = -1;
  
  public BoardState(String state, BoardState parent) {
    this.state = state;
    this.parentState = parent;
    char[][] c = {
        {state.charAt(0), state.charAt(1), state.charAt(2)},
        {state.charAt(3), state.charAt(4), state.charAt(5)},
        {state.charAt(6), state.charAt(7), state.charAt(8)}
    };
    chars = c;
    calculateCost();
  }
  
  public String getState() {
    return state;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (false == (obj instanceof BoardState)) {
      return false;
    }
    BoardState that = (BoardState)obj;
    return state.equals(that.state);
  }
  
  @Override
  public int hashCode() {
    return state.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    return sb.append(state.substring(0, 3)).append("\n")
      .append(state.substring(3,6)).append("\n")
      .append(state.substring(6))
      .toString();
  }
  
  public List<BoardState> getNextAvailableStates() {
    if (nextStates != null) {
      return nextStates;
    }
    nextStates = new ArrayList<BoardState>();
    int blankIndex = state.indexOf('0');
    switch(blankIndex) {
    case 0:
      addState(1, blankIndex);
      addState(3, blankIndex);
      break;
    case 1:
      addState(0,blankIndex);
      addState(2,blankIndex);
      addState(4,blankIndex);
    case 2:
      addState(1,blankIndex);
      addState(5,blankIndex);
    case 3:
      addState(0,blankIndex);
      addState(4,blankIndex);
      addState(6,blankIndex);
    case 4:
      addState(1,blankIndex);
      addState(3,blankIndex);
      addState(5,blankIndex);
      addState(7,blankIndex);
    case 5:
      addState(2,blankIndex);
      addState(4,blankIndex);
      addState(8,blankIndex);
    case 6:
      addState(3,blankIndex);
      addState(7,blankIndex);
    case 7:
      addState(6,blankIndex);
      addState(4,blankIndex);
      addState(8,blankIndex);
    case 8:
      addState(5,blankIndex);
      addState(7,blankIndex);
    }
    return nextStates;
  }
  
  private void addState(int from, int to) {
    StringBuilder sb = new StringBuilder(state);
    sb.setCharAt(to, state.charAt(from));
    sb.setCharAt(from, '0');
    nextStates.add(new BoardState(sb.toString(), this));
  }
  
  public int calculateCost() {
    if (cost >= 0) {
      return cost;
    }
    int cost = 0;
    for (int x=0; x<3; x++) {
      for (int y=0; y<3; y++) {
        char c = chars[x][y];
        switch(c) {
        case '1': //position 1:1
          cost += Math.abs(x+1-1); 
          cost += Math.abs(y+1-1);
          break;
        case '2':
          cost += Math.abs(x+1-1);
          cost += Math.abs(y+1-2);
          break;
        case '3':
          cost += Math.abs(x+1-1);
          cost += Math.abs(y+1-3);
          break;
        case '4':
          cost += Math.abs(x+1-2);
          cost += Math.abs(y+1-1);
          break;
        case '5':
          cost += Math.abs(x+1-2);
          cost += Math.abs(y+1-2);
          break;
        case '6':
          cost += Math.abs(x+1-2);
          cost += Math.abs(y+1-3);
          break;
        case '7':
          cost += Math.abs(x+1-3);
          cost += Math.abs(y+1-1);
          break;
        case '8':
          cost += Math.abs(x+1-3);
          cost += Math.abs(y+1-2);
          break;
        }
      }
    }
    return cost;
  }
}
