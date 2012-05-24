package com.greenmoonsoftware.eightpuzzle;

import java.util.HashMap;
import java.util.Map;

public final class ApplicationState {
  public static final ApplicationState instance = new ApplicationState();
  private int dimensionX;
  private int dimensionY;
  private String solutionState;
  private Map<String, Coordinates> solutionMap = new HashMap<String, Coordinates>();
  private ApplicationState() {}
  
  public final void initialize(int dimX, int dimY, String solution) {
    dimensionX = dimX;
    dimensionY = dimY;
    solutionState = solution;
    String[] solutionValues = solutionState.split(" ");
    if (solutionValues.length != dimX*dimY) {
      throw new IllegalArgumentException("Solution values ["+solutionValues.length+"] do not match dimension size ["+dimX*dimY+"]");
    }
    int spotCounter = 0;
    for (int x=0; x<dimensionX; x++) {
      for (int y=0; y<dimensionY; y++) {
        solutionMap.put(solutionValues[spotCounter], new Coordinates(x,y));
        spotCounter++;
      }
    }
  }
  
  public int getDimensionX() {
    return dimensionX;
  }
  
  public int getDimensionY() {
    return dimensionY;
  }
  
  public int getXForValue(String val) {
    return solutionMap.get(val).x;
  }
  
  public int getYForValue(String val) {
    return solutionMap.get(val).y;
  }
  
  public String getSolutionState() {
    return solutionState;
  }
  
  private static class Coordinates {
    final int x;
    final int y;
    private Coordinates(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
