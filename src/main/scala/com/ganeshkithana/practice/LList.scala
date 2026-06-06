package com.ganeshkithana.practice

import scala.annotation.tailrec

// singly Linked List
// [1, 2, 3] = [1] -> [2] -> [3] -> |
abstract class LList[T] {
  def head: T
  def tail: LList[T]
  def isEmpty: Boolean
  def add(ele: T): LList[T] = new Cons[T](ele, this)
}

class Empty[T] extends LList[T] {
  override def head: T = throw new NoSuchElementException
  override def tail: LList[T] = throw new NoSuchElementException
  override def isEmpty: Boolean = true

  override def toString: String = "[]"
}

class Cons[T](override val head: T, override val tail: LList[T]) extends LList[T] {
  override def isEmpty: Boolean = false
  override def toString: String = {
    @tailrec
    def concatenateElements(remainder: LList[T], acc: String): String =
      if(remainder.isEmpty) acc
      else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")

    s"[${concatenateElements(this.tail, s"$head")}]"
  }
}

/**
 Exercise: LList extension

 1.  Generic trait Predicate[T] with a little method test(T) => Boolean
 2.  Generic trait Transformer[A, B] with a method transform(A) => B
 3.  LList:
 - map(transformer: Transformer[A, B]) => LList[B]
 - filter(predicate: Predicate[A]) => LList[A]
 - flatMap(transformer from A to LList[B]) => LList[B]

 class EvenPredicate extends Predicate[Int]
 class StringToIntTransformer extends Transformer[String, Int]

 [1,2,3].map(n * 2) = [2,4,6]
 [1,2,3,4].filter(n % 2 == 0) = [2,4]
 [1,2,3].flatMap(n => [n, n+1]) => [1,2, 2,3, 3,4]
 */

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
  }
}
