package com.ganeshkithana.practice

import scala.annotation.tailrec

// singly Linked List
// [1, 2, 3] = [1] -> [2] -> [3] -> |
abstract class LList[A] {
  def head: A

  def tail: LList[A]

  def isEmpty: Boolean

  def add(ele: A): LList[A] = new Cons[A](ele, this)

  // concatenation
  infix def ++(anotherList: LList[A]): LList[A]

  def map[B](transformer: Transformer[A, B]): LList[B]

  def filter(predicate: Predicate[A]): LList[A]

  def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B]
}

class Empty[A] extends LList[A] {
  override def head: A = throw new NoSuchElementException

  override def tail: LList[A] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def toString: String = "[]"

  override infix def ++(anotherList: LList[A]): LList[A] = anotherList

  override def map[B](transformer: Transformer[A, B]): LList[B] = new Empty[B]

  override def filter(predicate: Predicate[A]): LList[A] = this

  override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] = new Empty[B]
}

class Cons[A](override val head: A, override val tail: LList[A]) extends LList[A] {
  override def isEmpty: Boolean = false

  override def toString: String = {
    @tailrec
    def concatenateElements(remainder: LList[A], acc: String): String =
      if (remainder.isEmpty) acc
      else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")

    s"[${concatenateElements(this.tail, s"$head")}]"
  }

  /*
    example
    [1,2,3] ++ [4,5,6]
    new Cons(1, [2,3] ++ [4,5,6]) =
    new Cons(1, new Cons(2, [3] ++ [4,5,6])) =
    new Cons(1, new Cons(2, new Cons(3, [] ++ [4,5,6]))) =
    new Cons(1, new Cons(2, new Cons(3, [4,5,6]))) =
    [1,2,3,4,5,6]
  */
  override infix def ++(anotherList: LList[A]): LList[A] =
    new Cons(head, tail ++ anotherList)

  /*
   example
   [1,2,3].map(n * 2) =
   new Cons(2, [2,3].map(n * 2)) =
   new Cons(2, new Cons(4, [3].map(n * 2))) =
   new Cons(2, new Cons(4, new Cons(6, [].map(n * 2)))) =
   new Cons(2, new Cons(4, new Cons(6, [])))) =
   [2,4,6]
  */
  override def map[B](transformer: Transformer[A, B]): LList[B] =
    new Cons[B](transformer.transform(head), tail.map(transformer))


  /*
    example
    [1,2,3].filter(n % 2 == 0) =
    [2,3].filter(n % 2 == 0) =
    new Cons(2, [3].filter(n % 2 == 0)) =
    new Cons(2, [].filter(n % 2 == 0)) =
    new Cons(2, []) =
    [2]
    */
  override def filter(predicate: Predicate[A]): LList[A] =
    if(predicate.test(head)) new Cons(head, tail.filter(predicate))
    else tail.filter(predicate)

  /*
    [1,2,3].flatMap(n => [n, n + 1]) =
    [1,2] ++ [2,3].flatMap(trans) =
    [1,2] ++ [2,3] ++ [3].flatMap(trans) =
    [1,2] ++ [2,3] ++ [3,4] ++ [].flatMap(trans) =
    [1,2] ++ [2,3] ++ [3,4] ++ [] =
    [1,2,2,3,3,4]
     */
  override def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B] =
    transformer.transform(head) ++ tail.flatMap(transformer)
}

/**
 * {{{
 *Exercise: LList extension
 *
 *1.  Generic trait Predicate[A] with a little method test(T) => Boolean
 *2.  Generic trait Transformer[A, B] with a method transform(A) => B
 *3.  LList:
 *- map(transformer: Transformer[A, B]) => LList[B]
 *- filter(predicate: Predicate[A]) => LList[A]
 *- flatMap(transformer from A to LList[B]) => LList[B]
 *
 *class EvenPredicate extends Predicate[Int]
 *class StringToIntTransformer extends Transformer[String, Int]
 *
 *[1,2,3].map(n * 2) = [2,4,6]
 *[1,2,3,4].filter(n % 2 == 0) = [2,4]
 *[1,2,3].flatMap(n => [n, n+1]) => [1,2, 2,3, 3,4]
 * }}}
 */


trait Predicate[A] {
  def test(element: A): Boolean
}

class EvenPredicate extends Predicate[Int] {
  override def test(element: Int): Boolean =
    element % 2 == 0
}

trait Transformer[A, B] {
  def transform(value: A): B
}

class Doubler extends Transformer[Int, Int] {
  override def transform(value: Int): Int =
    value * 2
}

class DoublerList extends Transformer[Int, LList[Int]] {
  override def transform(value: Int): LList[Int] =
    new Cons(value, new Cons(value + 1, new Empty))
}

val doublerList = new DoublerList().transform(5)

object LListTest {
  def main(args: Array[String]): Unit = {
    val empty = new Empty[Int]
    println(empty.isEmpty)
    println(empty)

    val first3Num = new Cons(1, new Cons(2, new Cons(3, empty)))
    println(first3Num)

    val first3Num_v2 = empty.add(1).add(2).add(3)
    println(first3Num_v2)
    println(first3Num_v2.head)
    println(first3Num_v2.tail)
    println(first3Num_v2.isEmpty)

    val someStrings = new Cons[String]("Scala", new Cons[String]("Java", new Empty[String]))
    println(someStrings)

    val evenPredicate = new Predicate[Int] {
      override def test(element: Int): Boolean =
        element % 2 == 0
    }

    println(evenPredicate.test(24))

    val doubler = new Transformer[Int, Int] {
      override def transform(value: Int): Int =
        value * 2
    }

    println(doubler.transform(24))
    println(doublerList)

    // map testing
    val numbersDoubled = first3Num.map(doubler)
    println(numbersDoubled)

    val numbersNested = first3Num.map(new DoublerList)
    println(numbersNested)


    // filter testing
    val onlyEvenNumbers = first3Num.filter(evenPredicate)
    println(onlyEvenNumbers)

    // concatenation testing
    val listInBothWays = first3Num ++ first3Num_v2
    println(listInBothWays)

    // flatmap testing
    val flattenedList = first3Num.flatMap(new DoublerList)
    println(flattenedList)
  }
}
