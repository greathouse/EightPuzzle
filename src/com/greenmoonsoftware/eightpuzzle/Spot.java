package com.greenmoonsoftware.eightpuzzle;

public class Spot {
  private int currentX;
  private int currentY;
  private int solutionX;
  private int solutionY;
  private int cost = -1;
  
  private String character;

  public Spot(String character, int currentX, int currentY, int solutionX, int solutionY) {
    this.character = character;
    this.currentX = currentX;
    this.currentY = currentY;
    this.solutionX = solutionX;
    this.solutionY = solutionY;
  }
  
  public Spot newMove(int newX, int newY) {
    return new Spot(this.character, newX, newY, solutionX, solutionY);
  }

  public int getCurrentX() {
    return currentX;
  }

  public int getCurrentY() {
    return currentY;
  }

  public int getSolutionX() {
    return solutionX;
  }

  public int getSolutionY() {
    return solutionY;
  }

  public String getCharacter() {
    return character;
  }
  
  public int calculateCost() {
    if (cost >= 0) {
      return cost;
    }
    if (character.equals("0")) {
      cost = 0;
      return cost;
    }
    cost = Math.abs(currentX - solutionX);
    cost += Math.abs(currentY - solutionY);
    return cost;
  }
}
