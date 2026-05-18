package com.ganeshkithana.part2oop

object OOBasics {

  // classes
  class Person(val name: String = "John Doe", age: Int = 0) { // constructor signature
    // fields
    val allCaps: String = name.toUpperCase()

    // methods
    def greet(name: String): String =
      s"${this.name} says: Hi, $name"

    // signature differs
    // OVERLOADING
    def greet(): String =
      s"Hi, everyone, my name is $name"

    // aux constructor: Usually we don't use in Scala. Instead, we can pass the default parameter
//    def this(name: String) =
//      this(name, 0)
//
//    def this() =
//      this("John Doe")
  }

  val aPerson: Person = new Person("John", 24)
  val john: String = aPerson.name // class parameter != field
  val johnSaysHiToDaniel: String = aPerson.greet("Daniel")
  val johnSaysHi: String = aPerson.greet()

  val genericPerson: Person = new Person()

  def main(args: Array[String]): Unit = {
    println(johnSaysHiToDaniel)
    println(johnSaysHi)
  }
}
