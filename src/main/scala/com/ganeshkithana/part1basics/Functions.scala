package com.ganeshkithana.part1basics

object Functions {

  // function: reusable piece of code that you can invoke with some argument and return a result
  def aFunction(a: String, b: Int): String =
    a + " " + b // One Expression

  // function invocation
  def aFunctionInvocation = aFunction("Scala", 34)

  def aNoArgFunction(): Int = 34
  def aParameterlessFunction: Int = 34

  // functions can be recursive
  def stringConcatenation(str: String, n: Int): String = {
    if(n == 0) ""
    else if(n == 1) str
    else str + " " + stringConcatenation(str, n - 1)
  }

  val scalax5 = stringConcatenation("Scala", 5)
  // when you need loops, use RECURSION.

  // "void" function
  def aVoidFunction(str: String): Unit =
    println(str)

  def computeDoubleStringWithSideEffect(str: String): String = {
    aVoidFunction(str) // Unit
    str + str // meaningful value
  } // discouraging side effects

  def aBigFunction(n: Int): Int = {
    // small, auxiliary functions inside
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n + 1)
  }

  /**Exercises*
   * 1. A greeting function (name, age) => "Hi my name is $name and I'm $age years old."
   * 2. Factorial function n => 1 * 2 * 3 * 4 * .. * n - 1 * n
   * 3. Fibonacci Function
   * 4. Test if a number is Prime
   */

  // 1
  def aGreetinFunction(name: String, age: Int): String =
    s"Hi my name is $name and I'm $age years old."

  // 2
  def aFactorialFunction(n: Int): Int = {
    if(n <= 0) 0
    else if(n == 1) 1
    else n * aFactorialFunction(n - 1)
  }

  // 3
  def aFibonacciFunction(n: Int): Int = {
    if(n <= 2) 1
    else aFibonacciFunction(n - 1) + aFibonacciFunction(n - 2)
  }

  // 4
  def isPrimeNumberFunction(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if(t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  def main(args: Array[String]): Unit = {
    println(scalax5)
    println(aGreetinFunction("Ganesh", 24))
    println(aFactorialFunction(5))
    println(aFibonacciFunction(7))
    println(isPrimeNumberFunction(41))
  }
}
