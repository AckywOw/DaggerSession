package com.example.book.a_1_3;

import java.util.Stack;

/**
 * Created by Jiang on 2016/11/2.
 */

public class TwoStackCalculator {

  public static void main(String[] args) {
    System.out.println(calculator("1+(2-3)"));
  }

  public static double calculator(String str) {
    System.out.println(str);
    Stack<Character> charStack = new Stack<>();
    Stack<Double> doubleStack = new Stack<>();
    char[] chars = str.replace(" ", "").toCharArray();
    for (char c : chars) {
      if (c == '(') {
        continue;
      } else if (c == '+') {
        charStack.push(c);
      } else if (c == '-') {
        charStack.push(c);
      } else if (c == ')') {
        calculatorNumbers(charStack, doubleStack);
      } else {
        doubleStack.push((double) Character.getNumericValue(c));
      }
    }
    calculatorNumbers(charStack, doubleStack);
    return doubleStack.pop();
  }

  private static void calculatorNumbers(Stack<Character> charStack, Stack<Double> doubleStack) {
    Character c1 = charStack.pop();
    Double d1 = doubleStack.pop();
    Double d2 = doubleStack.pop();
    Double d = d1;
    if (c1 == '+') {
      d = d1 + d2;
    } else if (c1 == '-') {
      d = d2 - d1;
    }
    doubleStack.push(d);
  }
}
