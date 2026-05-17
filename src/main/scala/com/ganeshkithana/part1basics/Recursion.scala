package com.ganeshkithana.part1basics

import scala.annotation.tailrec

object Recursion {

  // "repetition" = recursion
  def sumUntil(n: Int): Int =
    if (n <= 0) 0
    else n + sumUntil(n - 1)

  def sumUntil_v2(n: Int): Int = {
    @tailrec
    def sumUntilTailRec(x: Int, acc: Int): Int =
      if (x <= 0) acc
      else sumUntilTailRec(x - 1, acc + x) // TAIL recursion = recursion call occurs LAST in its code path
    // no further stack frames necessary = no more risk of Stack Overflow

    sumUntilTailRec(n, 0)
  }

  def sumNumbersBetween(a: Int, b: Int): Int =
    if (a > b) 0
    else a + sumNumbersBetween(a + 1, b)

  def sumNumbersBetween_v2(a: Int, b: Int): Int =
    @tailrec
    def sumTailRec(x: Int, limit: Int, acc: Int): Int =
      if (x > limit) acc
      else sumTailRec(x + 1, limit, acc + x)

    sumTailRec(a, b, 0)


  /** Exercises:
   * 1. Concatenate a String n times
   * 2. Fibonacci function, tail recursive
   * 3. isPrime function, tail recursive
   * */

  // 1
  def concatString(str: String, n: Int): String = {
    @tailrec
    def concatTailRec(limit: Int, acc: String): String =
      if (limit <= 0) acc
      else concatTailRec(limit - 1, acc + str + " ")

    concatTailRec(n, "")
  }

  // 2
  def fibonacciNumber(n: Int): Int = {
    @tailrec
    def fiboTailRec(x: Int, prev: Int, last: Int): Int =
      if(x >= n) last
      else fiboTailRec(x + 1, last, last + prev)

    if(n <= 2) 1
    else fiboTailRec(2, 1, 1)
  }

  // 3
  def isPrimeNumberFunction(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else if(n % t == 0) false
      else isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  def main(args: Array[String]): Unit = {
    println(sumUntil(10))
    // println(sumUntil(394000)) // Stack overflow error due to recursion call

    println(sumUntil_v2(394000))
    println(sumNumbersBetween(0, 10))
    println(sumNumbersBetween_v2(0, 394000))

    // Exercise:
    println(concatString("Scala", 5))
    println(fibonacciNumber(7))
    println(isPrimeNumberFunction(41))
  }
}
