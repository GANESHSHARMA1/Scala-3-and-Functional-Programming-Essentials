package com.ganeshkithana.part2oop

object Exceptions {

  val aString: String = null
  // aString.length crashes with a NPE

  // 1 - throw exceptions
  // val aWeirdValue: Int = throw new NullPointerException  // returns Nothing

  // type Throwable
  //    Error, e.g. SOError (Stack Overflow), OOMError (Out of memory)
  //    Exception, e.g. NPException, NSEException (No Such Element), ...

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 43


  val potentialFail: Int = try {
    // code that might fail
    getInt(true) // an Int
  } catch{
    // most specific exception first
    case e: RuntimeException => 45 // an Int
    case e: NullPointerException => 35
    // ..
  } finally { // optional
    // executed no matter what
    // closing resources
    // Unit here
  }

  // custom exceptions
  class MyException extends RuntimeException {
    // fields or methods
    override def getMessage: String = "MY EXCEPTION"
  }

  val myException = new MyException

  /**
   * Exercises:
   *
   * 1. Crash with SOError
   * 2. Crash with OOMError
   * 3. Find an element matching a predicate in LList
   */

  def soCrash(): Unit = {
    def infinite(): Int = 1 + infinite()

    infinite()
  }

  def oomCrash(): Unit = {
    def bigString(n: Int, acc: String): String =
      if(n == 0) acc
      else bigString(n - 1, acc + acc)

    bigString(54663434, "Scala")
  }

  def main(args: Array[String]): Unit = {
    // println(aString.length)
    println(potentialFail)

    // val throwingMyException = throw myException

    // soCrash()

    oomCrash()
  }
}
