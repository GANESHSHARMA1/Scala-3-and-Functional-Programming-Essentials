package com.ganeshkithana.part3fp

import scala.annotation.meta.param

object MapFlatMapFilterFor {

  // standard list
  val aList = List(1, 2, 3) // [1] -> [2] -> [3] -> Nil // [1,2,3]
  val firstElement = aList.head
  val restOfElements = aList.tail

  // map
  val anIncrementedList = aList.map(_ + 1)

  // filter
  val onlyOddNumbers = aList.filter(_ % 2 != 0)

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  val aFlatMappedList = aList.flatMap(toPair) // [1,2, 2,3, 3,4]

  // Exercise: All the possible combination of all the elements of those lists, int the format - "a1 - black"
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white", "red")

  /*
  lambda = num => chars.map(char => s"$num$char)
  [1,2,3,4].flatMap(lambda) = ["1a", "1b", "1c", "1d", "2a", "2b", "2c", "2d", ...............]
  lambda(1) = chars.map(char => s"1$char") = ["1a", "1b", "1c", "1d"]
  lambda(2) = .... = ["2a", "2b", "2c", "2d"]
  lambda(3) = ....
  lambda(4) = ....
  * */
  val combinations = numbers.withFilter(_ % 2 == 0).flatMap(number => chars.flatMap(
    char => colors.map(color => s"$number$char - $color")))

  // for-comprehension - IDENTICAL to flatmap + map chains
  val combinationsFor = for {
    number <- numbers if number % 2 == 0 // generator
    char <- chars
    color <- colors
  } yield s"$number$char - $color" // an EXPRESSION

  // for-comprehension with Unit
  // if foreach

  /**
   * Exercises
   * 1. LList supports for comprehensions ? - YES
   * 2. A small collection of AT MOST ONE element - Maynbe[A]
   *  - map
   *  - flatmap
   *  - filter
   * */

  import com.ganeshkithana.practice.*
  val isSimpleNumbers: LList[Int] = Cons(1, Cons(2, Cons(3, Empty())))
  // map, flatmap, filter
  val incrementedNumbers = isSimpleNumbers.map(_ + 1)
  val filteredNumbers = isSimpleNumbers.filter(_ % 2 == 0)
  val toPairLList: Int => LList[Int] = (x: Int) => Cons(x, Cons(x + 1, Empty()))
  val flatMappedNumbers = isSimpleNumbers.flatMap(toPairLList)

  // map + flatMap = for comprehension
  val combinationNumbers = for {
    number <- isSimpleNumbers if number % 2 == 0
    char <- Cons('a', Cons('b', Cons('c', Empty())))
  } yield s"$number - $char"

  def main(args: Array[String]): Unit = {
    numbers.foreach(println)
    for {
      num <- numbers
    } println(num)
    println(combinations)
    println(combinationsFor)

    println(isSimpleNumbers)
    println(incrementedNumbers)
    println(filteredNumbers)
    println(flatMappedNumbers)
    println(combinationNumbers)
  }
}
