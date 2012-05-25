package com.greenmoonsoftware.eightpuzzle.BoardStateTests;

import com.greenmoonsoftware.eightpuzzle.ApplicationState;

import junit.framework.TestCase;

public abstract class TestBase extends TestCase {
  private static final String EIGHT_PUZZLE = "1 2 3 4 5 6 7 8 0";
  private static final String FIFTEEN_PUZZLE = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0";
  
  protected void setup8() {
    ApplicationState.instance.initialize(3,3,EIGHT_PUZZLE);
  }
  
  protected void setup15() {
    ApplicationState.instance.initialize(4,4,FIFTEEN_PUZZLE);
  }
}