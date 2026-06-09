package com.ganeshkithana.part2oop

object CaseClasses {

  // lightweight data structures
  case class Person(name: String, age: Int) {
    // do some other stuff
  }

  // 1 - class args are now fields
  val daniel = new Person("Daniel", 99)
  val danielsAge = daniel.age

  // 2 - toString, equal and hashCode
  val danielToString = daniel.toString // Person(Daniel,99)
  val danielDuped = new Person("Daniel", 99)
  val isSameDaniel = daniel == danielDuped  // true:- because both object contains the same values
  val isSameDanielMemory = daniel.eq(danielDuped) // false: both object have different memory

  // 3 - utility methods
  val danielYounger = daniel.copy(age = 78) // new Person("Daniel", 78)

  // 4 - Case Classes have companion objects
  val thePersonSingleton = Person
  val daniel_v2 = Person("Daniel", 99) // "constructor" -> equivalent to Person.apply("Daniel", 99)

  // 5 - CCs are serializable : converting objects to bytes for storage/transfer, and back again.
  // use-case: Akka

  // 6 - CCs have extractor patterns for PATTERN MATCHING

  // can't create CCs with no arg lists
  /*
    case class CCWithNoArgs {
      // some code
    }

    val ccna = new CCWithNoArgs
    val ccna_v2 = new CCWithNoArgs // all instances would be equal!
  */

  case object UnitedKingdom {
    // fields and methods
    def name: String = "The UK of GB and NI"
  }

  case class CCWithArgListNoArgs[A]() // legal, mainly used in the context of generics

  /**
   * Exercise: use case classes for LList
   * */

  def main(args: Array[String]): Unit = {
    // without case class the below println will return `com.ganeshkithana.part2oop.CaseClasses$Person@7a0ac6e3`
    println(daniel) // Output: Person(Daniel,99)
    println(isSameDaniel)
    println(isSameDanielMemory)
  }
}
