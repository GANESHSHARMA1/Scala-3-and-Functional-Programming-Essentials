package com.ganeshkithana.part2oop

import scala.language.postfixOps

object MethodNotation {

  class Person(val name: String, val age: Int, favoriteMovie: String) {
    def like(movie: String): Boolean =
      movie == favoriteMovie

    infix def isLikes(movie: String): Boolean =
      movie == favoriteMovie

    infix def +(person: Person): String =
      s"${this.name} is hanging out with ${person.name}"

    infix def +(nickname: String): Person =
      new Person(s"$name ($nickname)", age, favoriteMovie)

    def !!(programmingLanguage: String): String = // ?, !, >>, <<, <+>
      s"This $programmingLanguage is Awesome!"

    // prefix position
    // unary ops: -, +, ~, !
    def unary_- : String =
      s"$name's alter ego"

    def unary_+ : Person =
      new Person(name, age + 1, favoriteMovie)

      // postfix position
    def isAlive: Boolean = true

    def apply(): String =
      s"Hi! my name is $name and I'm very happy."

    def apply(n: Int): String =
      s"$name watched $favoriteMovie $n times"
  }

  val mary = new Person("Mary", 34, "Inception")
  val john = new Person("John", 36, "Fight Club")

  val negativeOne = -1

  /**
   * Exercises:
   * - a + operator on the Person class that returns a person with a nickname
   *    mary + "the rockstar" => new Person("Mary the rockstar", _, _)
   *
   * - a UNARY + operator that increases the person's age
   *    +mary => new Person(_, _, age + 1)
   *
   * - an apply method with an int arg
   *    mary.apply(2) => "Mary watched Inception 2 times"
   * */

  def main(args: Array[String]): Unit = {
    println(mary.like("Avengers"))

    println(mary.isLikes("Fight Club"))
    // infix notation - for methods with ONE argument
    println(mary isLikes "Fight Club") // identical

    //    "operator" = plain method
    println(mary + john)
    println(mary.+(john)) // identical
    println(2 + 3)
    println(2.+(3)) // same
    println(mary.!!("Scala"))
    //    println(mary !! "Scala")


    // prefix position
    println(-mary)

    // postfix position
    println(mary.isAlive)
    println(mary isAlive) // discouraged

    // apply is special
    println(mary.apply())
    println(mary())

    // Exercises
    val maryWithNickname = mary + "the rockstar"
    println(maryWithNickname.name)

    val maryOlder = +mary
    println(maryOlder.age)

    println(mary(10))
  }
}
