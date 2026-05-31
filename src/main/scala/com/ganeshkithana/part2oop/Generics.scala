package com.ganeshkithana.part2oop

object Generics {

  // reuse code on different types
  //
  abstract class MyList[T] { // "generic" list - Java: abstract class MyList<A>
    def head: T

    def tail: MyList[T]
  }

  class Empty[T] extends MyList[T] {
    override def head: T = throw new NoSuchElementException

    override def tail: MyList[T] = throw new NoSuchElementException
  }

  class NonEmpty[T](override val head: T, override val tail: MyList[T]) extends MyList[T]

  val listOfIntegers: MyList[Int] = new NonEmpty[Int](1, new NonEmpty[Int](2, new Empty[Int]))
  val listOfString: MyList[String] = new NonEmpty[String]("Scala", new NonEmpty[String]("Java", new Empty[String]))

  val firstNumber: Int = listOfIntegers.head

  // multiple generic types : Generic type can be represented as single letter as well as a word
  trait MyMap[Key, Value]

  object MyList {
    def from2Elements[T](elem1: T, elem2: T): MyList[T] =
      new NonEmpty[T](elem1, new NonEmpty[T](elem1, new Empty[T]))
  }

  val first2Numbers: MyList[Int] = MyList.from2Elements[Int](1, 2)
  val first2Numbers_v2: MyList[String] = MyList.from2Elements[String]("Scala", "Java")

  /**
   * Exercise: genericise LList.
   * */
  
  def main(args: Array[String]): Unit = {
    println(first2Numbers)
  }
}
