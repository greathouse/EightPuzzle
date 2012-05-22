package com.greenmoonsoftware.eightpuzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardState {
  private String state;
  private BoardState parentState;
  private List<BoardState> nextStates;
  
  public BoardState(String state, BoardState parent) {
    this.state = state;
    this.parentState = parent;
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
}
