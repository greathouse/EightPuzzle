package com.greenmoonsoftware.eightpuzzle;

import java.util.HashSet;
import java.util.Set;

public class BoardState {
  private String state;
  private Spot[][] spots;
  private BoardState parentState;
  private Set<BoardState> nextStates;
  private int cost = -1;
  private int openSpotX;
  private int openSpotY;
  
  public BoardState(String state, BoardState parent) {
    String[] stateValues = state.split(" ");
    this.state = state;
    this.parentState = parent;
    
    ApplicationState appState = ApplicationState.instance;
    int dimX = appState.getDimensionX();
    int dimY = appState.getDimensionY();
    
    Spot[][] c = new Spot[dimX][dimY];
    int spotCounter = 0;
    for (int x=0; x<dimX; x++) {
      for (int y=0; y<dimY; y++) {
        String value = stateValues[spotCounter];
        c[x][y] = new Spot(value, x, y, appState.getXForValue(value), appState.getYForValue(value));
        if (value.equals("0")) {
          openSpotX = x;
          openSpotY = y;
        }
        spotCounter++;
      }
    }
    spots = c;
    calculateCost();
  }
  
  public String getState() {
    return state;
  }
  
  public BoardState getParentState() {
    return parentState;
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
    return 683 * state.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int x=0; x<ApplicationState.instance.getDimensionX(); x++) {
      for (int y=0; y<ApplicationState.instance.getDimensionY(); y++) {
        sb.append(String.format("%3s",spots[x][y].getCharacter())).append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }
  
  public Set<BoardState> getNextAvailableStates() {
    if (nextStates != null) {
      return nextStates;
    }
    ApplicationState appState = ApplicationState.instance;
    nextStates = new HashSet<BoardState>();
    int top = openSpotX - 1;
    int bottom = openSpotX + 1;
    int left = openSpotY - 1;
    int right = openSpotY + 1;
    
    if (bottom < appState.getDimensionX()) {
      nextStates.add(moveOpenSpot(openSpotX, openSpotY, bottom, openSpotY));
    }
    if (right < appState.getDimensionY()) {
      nextStates.add(moveOpenSpot(openSpotX, openSpotY, openSpotX, right));
    }
    if (left >= 0) {
      nextStates.add(moveOpenSpot(openSpotX, openSpotY, openSpotX, left));
    }
    if (top >= 0) {
      nextStates.add(moveOpenSpot(openSpotX, openSpotY, top, openSpotY));
    }
    
    return nextStates;
  }
  
  private String encode(Spot[][] spots) {
    StringBuilder sb = new StringBuilder();
    for (Spot[] x : spots) {
      for (Spot y : x) {
        sb.append(y.getCharacter()).append(" ");
      }
    }
    return sb.toString().trim();
  }
  
  private Spot[][] copySpots() {
    //TODO: Maybe look at using System.arraycopy for micro-optimazation
    Spot[][] copy = new Spot[ApplicationState.instance.getDimensionX()][ApplicationState.instance.getDimensionY()];
    for (int x=0; x<ApplicationState.instance.getDimensionX(); x++) {
      for (int y=0; y<ApplicationState.instance.getDimensionY(); y++) {
        copy[x][y] = spots[x][y];
      }
    }
    return copy;
  }
  
  private BoardState moveOpenSpot(int fromX, int fromY, int toX, int toY) {
    Spot[][] copy = copySpots();
    Spot openSpot = spots[fromX][fromY];
    copy[toX][toY] = openSpot.newMove(toX, toY);
    
    Spot replaceSpot = spots[toX][toY];
    copy[fromX][fromY] = replaceSpot.newMove(fromX, fromY);
    return new BoardState(encode(copy), this);
  }
  
  public int calculateCost() {
    if (cost >= 0) {
      return cost;
    }
    ApplicationState appState = ApplicationState.instance;
    int cost = 0;
    for (int x=0; x<appState.getDimensionX(); x++) {
      for (int y=0; y<appState.getDimensionY(); y++) {
        cost += spots[x][y].calculateCost();
      }
    }
    cost += getParentCost();
    return cost;
  }
  
  private int getParentCost() {
    if (parentState != null) {
      return parentState.calculateCost();
    }
    return 0;
  }
  
  public static void main(String[] args) {
    ApplicationState.instance.initialize(3,3,"1 2 3 4 5 6 7 8 0");
    System.out.println(new BoardState("1 2 3 4 5 0 7 8 6", null).calculateCost());
  }
}
