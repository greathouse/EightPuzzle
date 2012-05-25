package com.greenmoonsoftware.eightpuzzle.BoardStateTests;

import java.util.Set;

import com.greenmoonsoftware.eightpuzzle.BoardState;


public class GetNextAvailableStates8Tests extends TestBase {

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
    setup8();
    run("0 1 2 3 4 5 6 7 8", new String[]{"1 0 2 3 4 5 6 7 8", "3 1 2 0 4 5 6 7 8"});
  }
  
  public void testTopMiddle() {
    setup8();
    run("1 0 2 3 4 5 6 7 8", new String[]{"0 1 2 3 4 5 6 7 8", "1 4 2 3 0 5 6 7 8", "1 2 0 3 4 5 6 7 8"});
  }
  
  public void testTopRight() {
    setup8();
    run("1 2 0 3 4 5 6 7 8", new String[]{"1 0 2 3 4 5 6 7 8", "1 2 5 3 4 0 6 7 8"});
  }
  
  public void testMiddleLeft() {
    setup8();
    run("1 2 3 0 4 5 6 7 8", new String[]{"0 2 3 1 4 5 6 7 8", "1 2 3 4 0 5 6 7 8", "1 2 3 6 4 5 0 7 8"});
  }
  
  public void testCenter() {
    setup8();
    run("1 2 3 4 0 5 6 7 8", new String[]{"1 0 3 4 2 5 6 7 8", "1 2 3 0 4 5 6 7 8", "1 2 3 4 5 0 6 7 8", "1 2 3 4 7 5 6 0 8"});
  }
  
  public void testMiddleRight() {
    setup8();
    run("1 2 3 4 5 0 6 7 8", new String[]{"1 2 0 4 5 3 6 7 8", "1 2 3 4 0 5 6 7 8", "1 2 3 4 5 8 6 7 0"});
  }
  
  public void testBottomLeft() {
    setup8();
    run("1 2 3 4 5 6 0 7 8", new String[]{"1 2 3 0 5 6 4 7 8", "1 2 3 4 5 6 7 0 8"});
  }
  
  public void testBottomCenter() {
    setup8();
    run("1 2 3 4 5 6 7 0 8", new String[]{"1 2 3 4 0 6 7 5 8", "1 2 3 4 5 6 0 7 8", "1 2 3 4 5 6 7 8 0"});
  }
  
  public void testBottomRight() {
    setup8();
    run("1 2 3 4 5 6 7 8 0", new String[]{"1 2 3 4 5 0 7 8 6", "1 2 3 4 5 6 7 0 8"});
  }
}
