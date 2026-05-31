package com.ganeshkithana.practice

import scala.annotation.tailrec

// singly Linked List
// [1, 2, 3] = [1] -> [2] -> [3] -> |
abstract class LList {
  def head: Int
  def tail: LList
  def isEmpty: Boolean
  def add(ele: Int): LList = new Cons(ele, this)
}

class Empty extends LList {
  override def head: Int = throw new NoSuchElementException
  override def tail: LList = throw new NoSuchElementException
  override def isEmpty: Boolean = true

  override def toString: String = "[]"
}

class Cons(override val head: Int, override val tail: LList) extends LList {
  override def isEmpty: Boolean = false
  override def toString: String = {
    @tailrec
    def concatenateElements(remainder: LList, acc: String): String =
      if(remainder.isEmpty) acc
      else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")

    s"[${concatenateElements(this.tail, s"$head")}]"
  }
}

object LListTest {
  def main(args: Array[String]): Unit = {
    val empty = new Empty
    println(empty.isEmpty)
    println(empty)

    val first3Num = new Cons(1, new Cons(2, new Cons(3, empty)))
    println(first3Num)

    val first3Num_v2 = empty.add(1).add(2).add(3)
    println(first3Num_v2)
    println(first3Num_v2.head)
    println(first3Num_v2.tail)
    println(first3Num_v2.isEmpty)
  }
}
