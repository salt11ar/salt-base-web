package com.example.postgres.springbootpostgresdocker;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.Math;

import org.junit.jupiter.api.Test;

public class SomeTests {

  // Math.isPrime(int) returns whether the given number is prime or not
  @Test
  public void testMin() {
    assertTrue(Integer.valueOf(Math.min(1, 2)).equals(1) );
   
 }
  
 
  
}
