package com.greenmoonsoftware.eightpuzzle.BoardStateTests;

import com.greenmoonsoftware.eightpuzzle.ApplicationState;

import junit.framework.TestCase;

public abstract class TestBase extends TestCase {
  private static final String EIGHT_PUZZLE = "1 2 3 4 5 6 7 8 0";
  
  protected void setup8() {
    ApplicationState.instance.initialize(3,3,EIGHT_PUZZLE);
  }
}