package com.ganeshkithana.part3fp

object WhatsAFunction {

  // FP: functions are "first-class" citizens
  // JVM

  trait MyFunction[A, B] {
    def apply(arg: A): B
  }

  val doubler = new MyFunction[Int, Int] {
    override def apply(arg: Int): Int = arg * 2
  }

  val meaningOfLife = 42
  val meaningDoubled = doubler(meaningOfLife) // doubler.apply(meaningOfLife)

  // function types
  val doublerStandard = new Function[Int, Int] {
    override def apply(arg: Int): Int = arg * 2
  }

  val meaningDoubled_v2 = doublerStandard(meaningOfLife)

  val adder = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  val anAddition = adder(2, 67)

  // (Int, String, Double, Boolean) => Int ==== Function4[Int, String, Double, Boolean, Int]
  val aThreeArgsFunction = new Function4[Int, String, Double, Boolean, Int] {
    override def apply(v1: Int, v2: String, v3: Double, v4: Boolean): Int = ???
  }

  // all functions are instances of FunctionX with apply methods

  /**
   * Exercises
   * 1. A function which takes 2 strings and concatenates them
   * 2. Replace Predicate/Transformer with the appropriate function types if necessary
   * 3. Define a function which takes an int as argument and returns ANOTHER FUNCTION as a result.
   */

  // 1
  val concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  // 2
  // yes Predicate[T] equivalent with Function1[T, Boolean] === T => Boolean
  // yes: Transformer[A, B] equivalent with Function1[A, B] === A => B

  // 3
  val superAdder = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Int => Int = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val superAdder_v2: Int => (Int => Int) = x => y => x + y

  // function values != methods

  val adder2 = superAdder(2)
  val anAddition_v2 = adder2(43) // 45
  // currying
  val anAddition_v3 = superAdder(2)(43)
  val anAddition_v4= superAdder_v2(2)(43)

  def main(args: Array[String]): Unit = {
    println(concatenator("I Love ", "Scala"))
    println(anAddition_v2)
    println(anAddition_v3)
    println(anAddition_v4)
  }
}
