package com.ganeshkithana.practice

import scala.annotation.tailrec

// singly Linked List
// [1, 2, 3] = [1] -> [2] -> [3] -> |
abstract class LList[A] {
  def head: A

  def tail: LList[A]

  def isEmpty: Boolean

  def add(ele: A): LList[A] = Cons[A](ele, this)

  // concatenation
  infix def ++(anotherList: LList[A]): LList[A]

  def map[B](transformer: A => B): LList[B]

  def filter(predicate: A => Boolean): LList[A]

  def withFilter(predicate: A => Boolean): LList[A] = filter(predicate)

  def flatMap[B](transformer: A => LList[B]): LList[B]

  // HOFs and curries exercises
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): LList[A]
  def zipWith[B, T](list: LList[T], zip: (A, T) => B): LList[B]
  def foldLeft[B](start: B)(operator: (B, A) => B): B
}

case class Empty[A]() extends LList[A] {
  override def head: A = throw new NoSuchElementException

  override def tail: LList[A] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def toString: String = "[]"

  override infix def ++(anotherList: LList[A]): LList[A] = anotherList

  override def map[B](transformer: A => B): LList[B] = Empty()

  override def filter(predicate: A => Boolean): LList[A] = this

  override def flatMap[B](transformer: A => LList[B]): LList[B] = Empty()

  // HOFs exercises
  override def foreach(f: A => Unit): Unit = ()

  override def sort(compare: (A, A) => Int): LList[A] = this

  override def zipWith[B, T](list: LList[T], zip: (A, T) => B): LList[B] =
    if(!list.isEmpty) throw new IllegalArgumentException("Zipping lists of nonequal length")
    else Empty()

  override def foldLeft[B](start: B)(operator: (B, A) => B): B = start
}

case class Cons[A](override val head: A, override val tail: LList[A]) extends LList[A] {
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
    Cons(head, tail ++ anotherList)

  /*
   example
   [1,2,3].map(n * 2) =
   new Cons(2, [2,3].map(n * 2)) =
   new Cons(2, new Cons(4, [3].map(n * 2))) =
   new Cons(2, new Cons(4, new Cons(6, [].map(n * 2)))) =
   new Cons(2, new Cons(4, new Cons(6, [])))) =
   [2,4,6]
  */
  override def map[B](transformer: A => B): LList[B] =
    Cons[B](transformer(head), tail.map(transformer))


  /*
    example
    [1,2,3].filter(n % 2 == 0) =
    [2,3].filter(n % 2 == 0) =
    new Cons(2, [3].filter(n % 2 == 0)) =
    new Cons(2, [].filter(n % 2 == 0)) =
    new Cons(2, []) =
    [2]
    */
  override def filter(predicate: A => Boolean): LList[A] =
    if(predicate(head)) Cons(head, tail.filter(predicate))
    else tail.filter(predicate)

  /*
    [1,2,3].flatMap(n => [n, n + 1]) =
    [1,2] ++ [2,3].flatMap(trans) =
    [1,2] ++ [2,3] ++ [3].flatMap(trans) =
    [1,2] ++ [2,3] ++ [3,4] ++ [].flatMap(trans) =
    [1,2] ++ [2,3] ++ [3,4] ++ [] =
    [1,2,2,3,3,4]
     */
  override def flatMap[B](transformer: A => LList[B]): LList[B] =
    transformer(head) ++ tail.flatMap(transformer)

  // HOFs exercises
  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def sort(compare: (A, A) => Int): LList[A] = {
    /*
    * compare = x - y
    * insert(3, [1,2,4]) =
    *   Cons(1, insert(3, [2,4])) =
    *   Cons(1, Cons(2, insert(3, [4])))
    *   Cons(1, Cons(2, Cons(3, [4]))) = [1,2,3,4]
    * */
    // insertion sort, O(n^2), stack recursive
    def insert(ele: A, sortedList: LList[A]): LList[A] =
      if(sortedList.isEmpty) Cons(ele, Empty())
      else if(compare(ele, sortedList.head) <= 0) Cons(ele, sortedList)
      else Cons(sortedList.head, insert(ele, sortedList.tail))

    val sortedTail = tail.sort(compare)
    insert(head, sortedTail)
  }

  override def zipWith[B, T](list: LList[T], zip: (A, T) => B): LList[B] =
    if(list.isEmpty) throw new IllegalArgumentException("Zipping lists of nonequal length")
    else Cons(zip(head, list.head), tail.zipWith(list.tail, zip))

  /*
  * [1,2,3,4].foldLeft(0)(x + y)
    = [2,3,4].foldLeft(1)(x + y)
    = [3,4].foldLeft(3)(x + y)
    = [4].foldLeft(6)(x + y)
    = [6].foldLeft(10)(x + y)
    = 10
  * */
  override def foldLeft[B](start: B)(operator: (B, A) => B): B =
    tail.foldLeft(operator(start, head))(operator)
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

// (replaced with function types)
//trait Predicate[A] {
//  def test(element: A): Boolean
//}
//
//class EvenPredicate extends Predicate[Int] {
//  override def test(element: Int): Boolean =
//    element % 2 == 0
//}
//
//trait Transformer[A, B] {
//  def transform(value: A): B // A => B
//}
//
//class Doubler extends Transformer[Int, Int] {
//  override def transform(value: Int): Int =
//    value * 2
//}
//
//class DoublerList extends Transformer[Int, LList[Int]] {
//  override def transform(value: Int): LList[Int] =
//    Cons(value, Cons(value + 1, Empty()))
//}
//
//val doublerList = new DoublerList().transform(5)

object LList {
  @tailrec
  def find[A](list: LList[A], predicate: A => Boolean): A = {
    if(list.isEmpty) throw new NoSuchElementException
    else if(predicate(list.head)) list.head
    else find(list.tail, predicate)
  }
}

object LListTest {
  def main(args: Array[String]): Unit = {
    val empty = Empty[Int]()
    println(empty.isEmpty)
    println(empty)

    val first3Num = Cons(1, Cons(2, Cons(3, empty)))
    println(first3Num)

    val first3Num_v2 = empty.add(1).add(2).add(3)
    println(first3Num_v2)
    println(first3Num_v2.head)
    println(first3Num_v2.tail)
    println(first3Num_v2.isEmpty)

    val someStrings = Cons[String]("Scala", Cons[String]("Java", Empty()))
    println(someStrings)

//    val evenPredicate = new ((Int) => Boolean) {
//      override def apply(element: Int): Boolean =
//        element % 2 == 0
//    }
//
//    println(evenPredicate(24))
//
//    val doubler = new Function1[Int, Int] {
//      override def apply(value: Int): Int =
//        value * 2
//    }
//
//    val doublerList = new Function1[Int, LList[Int]] {
//      override def apply(value: Int): LList[Int] =
//        Cons(value, Cons(value + 1, Empty()))
//    }

    val evenPredicate: Int => Boolean = (x: Int) => x % 2 == 0
    println(evenPredicate(24))

    val doubler: Int => Int = x => x * 2

    val doublerList: Int => LList[Int] = value => Cons(value, Cons(value + 1, Empty()))

    println(doubler(24))
    println(doublerList)

    // map testing
    val numbersDoubled = first3Num.map(doubler)
    println(numbersDoubled)

    val numbersNested = first3Num.map(doublerList)
    println(numbersNested)


    // filter testing
    val onlyEvenNumbers = first3Num.filter(evenPredicate)
    println(onlyEvenNumbers)

    // concatenation testing
    val listInBothWays = first3Num ++ first3Num_v2
    println(listInBothWays)

    // flatmap testing
    val flattenedList = first3Num.flatMap(doublerList)
    println(flattenedList)

    // find test
    println(LList.find[Int](first3Num, evenPredicate)) // 2

    // println(LList.find[Int](first3Num, new Predicate[Int] {
    //   override def test(element: Int): Boolean = element > 5
    // })) // throws a NSEException

    // HOFs exercises testing
    first3Num.foreach(println)
    println(first3Num_v2.sort((x, y) => x - y))
    val someStrings_v2 = Cons("I", Cons("Love", Cons("Scala", Empty())))
    val zippedList = first3Num.zipWith(someStrings_v2, (number, string) => s"$number-$string")
    println(zippedList)
    println(first3Num.foldLeft(0)((x, y) => x + y))
    println(first3Num.foldLeft(0)(_ + _))

    // For-Comprehensions testing
    val forComp = for {
      num <- first3Num
    } println(num)
  }
}
