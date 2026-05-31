package com.ganeshkithana.part2oop

object Objects {

  object MySingleton { // type + the only instance of this type
    val aFiled: Int = 45
    def aMethod(x: Int): Int = x + 1
  }

  val theSingleton: MySingleton.type = MySingleton
  val anotherSingleton: MySingleton.type = MySingleton
  val isSameSingleton: Boolean = theSingleton == anotherSingleton // true
  // Objects can have fields an methods
  val theSingletonField: Int = MySingleton.aFiled
  val theSingletonMethod: Int = MySingleton.aMethod(11)

  class Person(name: String) {
    def sayHi(): String = s"Hi, my name is $name"
  }

  // companions = class + object with the same name in the same file
  object Person { // companion object
    // can access each other's private fields and methods
    val N_EYES = 2
    def canFly: Boolean = false
  }

  // methods and fields in classes are used for instance-dependent functionality
  val mary = new Person("Mary")
  val mary_v2 = new Person("Mary")
  val maryGreeting: String = mary.sayHi()
  mary == mary

  // methods and fields in object are used for instance-independent functionality - "static
  val humanCanFly: Boolean = Person.canFly
  val nEyesHuman: Int = Person.N_EYES

  // equality
  // 1. equality of reference (Usually defined as == ) - points to same reference in memory location
  val sameMary: Boolean = mary eq mary_v2 // false, different instance
  val sameSingleton: Boolean = MySingleton eq MySingleton // true
  // 2. equality of "sameness" - in Java defined as .equals
  val sameMary_v2: Boolean = mary equals mary_v2 // false
  val sameMary_v3: Boolean = mary == mary_v2 // same as equals - false
  val sameSingleton_v2: Boolean = MySingleton == MySingleton // true

  // object can extend classes
  object BigFoot extends Person("Big Foot")

  // scala application = object + def main(args: Array[String]): Unit
  /**
   * public class Objects {
   *  public static void main(String[] args){.....}
   *  }
   * */
  def main(args: Array[String]): Unit = {
    println(isSameSingleton)
    println(sameMary)
    println(sameSingleton)
    println(sameMary_v2)
    println(sameMary_v3)
    println(sameSingleton_v2)
  }
}
