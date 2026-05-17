package com.ganeshkithana.part1basics

object CBNvsCBV {

  // CBV = call by value: arguments are evaluated before function invocation
  def aFunction(arg: Int): Int = arg + 1 // 79 + 1

  val aComputation: Int = aFunction(45 + 34)

  // CBN = Call by Name: arguments are passed LITERALLY as expression
  def aByNameFunction(arg: => Int): Int = arg + 1 // 45 + 34 + 1

  val anotherComputation: Int = aByNameFunction(45 + 34)

  // CBV v/s CBN

  def printTwiceByValue(num: Long): Unit = {
    println("num by value: " + num)
    println("num by value: " + num)
  }

  /**
   * 1. delayed evaluation of arguments
   * 2. arguments are evaluated every time
   * */
  def printTwiceByName(num: => Long): Unit = {
    println("num by name: " + num)
    println("num by name: " + num)
  }

  def infinite(): Int = 1 + infinite()

  def printFirst(x: Int, y: => Int): Unit = println(x)

  def main(args: Array[String]): Unit = {
    println(aComputation)
    println(anotherComputation)

    // difference
    println(printTwiceByValue(System.nanoTime()))
    println(printTwiceByName(System.nanoTime()))

    // println(infinite()) // stack overflow
    // println(printFirst(infinite(), 34)) // stack overflow: infinite() function is evaluated before passed
    println(printFirst(34, infinite())) // prints 34: infinite() method is never evaluated as it's values is never used
  }
}
