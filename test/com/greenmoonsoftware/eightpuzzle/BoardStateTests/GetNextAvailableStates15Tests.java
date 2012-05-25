package com.greenmoonsoftware.eightpuzzle.BoardStateTests;

import java.util.Set;

import com.greenmoonsoftware.eightpuzzle.BoardState;


public class GetNextAvailableStates15Tests extends TestBase {

  private void run(String state, String[] expectedStates) {
    Set<BoardState> actual = new BoardState(state, null).getNextAvailableStates();
    assertEquals(expectedStates.length, actual.size());
    
    int foundCount = 0;
    StringBuilder actualStates = new StringBuilder("[");
    for (BoardState a : actual) {
      actualStates.append(a.getState()+"] [");
      for (String e : expectedStates) {
        if (e.equals(a.getState())) {
          foundCount++;
        }
      }
    }
    actualStates.append("]");
    assertEquals("Could not find all of the expected states. Actual Contains: "+actualStates.toString(), expectedStates.length, foundCount);
  }
  
  public void testTopLeft() {
    setup15();
    run("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15", new String[]{"1 0 2 3 4 5 6 7 8 9 10 11 12 13 14 15", "4 1 2 3 0 5 6 7 8 9 10 11 12 13 14 15"});
  }
  
  public void testTopLeftMiddle() {
    setup15();
    run("1 0 2 3 4 5 6 7 8 9 10 11 12 13 14 15", new String[]{"0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15", "1 5 2 3 4 0 6 7 8 9 10 11 12 13 14 15", "1 2 0 3 4 5 6 7 8 9 10 11 12 13 14 15"});
  }
  
  public void test2ndLeftMiddle() {
    setup15();
    run("1 2 3 4 5 0 6 7 8 9 10 11 12 13 14 15", new String[]{"1 0 3 4 5 2 6 7 8 9 10 11 12 13 14 15", "1 2 3 4 0 5 6 7 8 9 10 11 12 13 14 15", "1 2 3 4 5 6 0 7 8 9 10 11 12 13 14 15", "1 2 3 4 5 9 6 7 8 0 10 11 12 13 14 15"});
  }
  
  public void test3rdRightMiddle() {
    setup15();
    run("1 2 3 4 5 6 7 8 9 10 0 11 12 13 14 15", new String[]{"1 2 3 4 5 6 0 8 9 10 7 11 12 13 14 15", "1 2 3 4 5 6 7 8 9 0 10 11 12 13 14 15", "1 2 3 4 5 6 7 8 9 10 11 0 12 13 14 15", "1 2 3 4 5 6 7 8 9 10 14 11 12 13 0 15"});
  }
}
