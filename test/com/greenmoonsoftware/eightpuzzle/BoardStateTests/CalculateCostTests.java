package com.greenmoonsoftware.eightpuzzle.BoardStateTests;


import com.greenmoonsoftware.eightpuzzle.BoardState;

public class CalculateCostTests extends TestBase {
  private void run(String state, int expectedCost) {
    BoardState s = new BoardState(state, null);
    assertEquals(expectedCost, s.calculateCost());
  }
  
  public void testCanary() {
    setup8();
    run("1 2 3 4 5 6 7 8 0", 0);
  }
  
  public void testSimple() {
    setup8();
    run("1 2 3 4 5 0 7 8 6", 1);
  }
  
  public void testComplex() {
    setup8();
    run("0 1 2 3 4 5 6 7 8", 12);
  }
}
