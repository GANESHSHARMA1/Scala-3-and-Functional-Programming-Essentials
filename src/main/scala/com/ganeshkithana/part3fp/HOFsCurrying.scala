package com.ganeshkithana.part3fp

import scala.annotation.tailrec

object HOFsCurrying {

  // higher order functions (HOFs)
  val aHof: (Int, (Int => Int)) => Int = (x, func) => x + 1
  val anotherHof: Int => (Int => Int) = x => y => y + 2 * x

  // quick exercise
  val superfunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) =
    (x, func) => (y => y + x)

  // example: map, flatmap, filter

  // more example
  // f(f(f...(f(x)))
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if(n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne: Int => Int = x => x + 1
  val tenThousand = nTimes(plusOne, 10000, 0)

  /**
   * ntv2(po, 3) =
   * (x: Int) => ntv2(po, 2)(po(x)) = po(po(po(x)))
   *
   * ntv2(po, 2) =
   * (x: Int) => ntv2(po, 1)(po(x)) = po(po(x))
   *
   * ntv2(po, 1) =
   * (x: Int) => ntv2(po, 0)(po(x)) = po(x)
   *
   * ntv2(po, 0) = (x: Int) => x
   * */
  def nTimes_v2(f: Int => Int, n: Int): Int => Int =
    if(n <= 0) (x: Int) => x
    else (x: Int) => nTimes_v2(f, n - 1)(f(x))

  val plusHundred = nTimes_v2(plusOne, 100) // po(po(po(.... 100 calls -> risks SO if the args is too long
  val oneHundred = plusHundred(0)

  // currying = HOFs returning function instances
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  val add3: Int => Int = superAdder(3)
  val invokeSuperAdder = superAdder(100) // 103

  // curried methods = methods with multiple args list
  def curriedFormatter(fmt: String)(x: Double): String = fmt.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f") // (x: Double) => "%4.2f".format(x)
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f") // (x: Double) => "%10.8f".format(x)

  /**
   * 1. LList exercises
   *    - foreach(A => Unit)
   *      [1,2,3].foreach(x => println(x))
   *
   *    - sort((A, A) => Int) // -1 if a > b, 1 if a < b, 0 if a == b
   *      [3,2,4,1].sort((x, y) => x - y) = [1,2,3,4]
   *      (hint: use insertion sort)
   *
   *    - zipWith(LList[A], (A, A) => B): LList[B]
   *      [1,2,3].zipWith([4,5,6], x * y) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]
   *
   *    - foldLeft[B](start: B)((A, B) => B): B
   *      [1,2,3,4].foldLeft[Int](0)(x + y) = 10
   *      0 + 1 = 1
   *      1 + 2 = 3
   *      3 + 3 = 6
   *      6 + 4 = 10
   *
   * 2.
   *    - toCurry(f: (Int, Int) => Int): Int => Int => Int
   *    - fromCurry(f: (Int => Int => Int)): (Int, Int) => Int
   *
   * 3.
   *    - compose(f,g) => x => f(g(x))
   *    - andThen(f,g) => x => g(f(x))
   * */

  // 2.
  def toCurry(f: (Int, Int) => Int) : Int => Int => Int =
    x => y => f(x, y)

  def toCurry_v2[A, B, C](f: (A, B) => C): A => B => C =
    x => y => f(x, y)

  val superAdder_v2 = toCurry(_ + _) // same as superAdder
  val superAdder_v3 = toCurry_v2[Int, Int, Int](_ + _) // identical

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
    (x, y) => f(x)(y)

  def fromCurry_v2[A, B, C](f: (A => B => C)): (A, B) => C =
    (x, y) => f(x)(y)

  val simpleAdder = fromCurry(superAdder)
  val simpleAdder_v2 = fromCurry_v2[Int, Int, Int](superAdder)

  // 3.
  def compose(f: Int => Int, g: Int => Int): Int => Int =
    x => f(g(x))

  def compose_v2[A, B, C](f: B => C, g: A => B): A => C =
    x => f(g(x))

  val incrementer = (x: Int) => x + 1
  val doubler = (x: Int) => 2 * x
  val composeApplication = compose(incrementer, doubler)
  val composeApplication_v2 = compose_v2(incrementer, doubler)

  def andThen(f: Int => Int, g: Int => Int): Int => Int =
    x => g(f(x))

  def andThen_v2[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  val andThenApplication = andThen(incrementer, doubler)
  val andThenApplication_v2 = andThen_v2(incrementer, doubler)

  def main(args: Array[String]): Unit = {
    println(tenThousand)
    println(oneHundred)
    println(standardFormat(Math.PI))
    println(preciseFormat(Math.PI))
    println(simpleAdder(2, 68)) // 70
    println(composeApplication(12)) // 25 = (12 * 2) + 1
    println(composeApplication_v2(12)) // 25 = (12 * 2) + 1
    println(andThenApplication(12)) // 26 = (12 + 1) * 2
    println(andThenApplication_v2(12)) // 26 = (12 + 1) * 2
  }
}
