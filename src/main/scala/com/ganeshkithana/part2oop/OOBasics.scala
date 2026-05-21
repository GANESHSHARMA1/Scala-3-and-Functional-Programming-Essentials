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


    val johnWille = new Writer("John", "Wille", 1730)
    val jamesWille = new Writer("James", "Wille", 1830)

    val novel = new Novel("Great Achirvements", 1764, johnWille)
    val newEdition = novel.copy(1781)

    println(johnWille.fullName)
    println(novel.authorAge)

    println(novel.isWrittenBy(jamesWille))
    println(novel.isWrittenBy(johnWille))
    println(newEdition.authorAge)

    val counter = new Counter()
    counter.print() // 0
    counter.increment().print() // 1
    counter.increment() // 1
    counter.print() // 0
    counter.increment(5).print()
  }
}

/**
 * Exercise: imagine we're creating a backend for a book publishing house.
 * create a Novel and a Writer class.
 *
 * Writer: firstname, surname, year
 * - method fullname
 *
 * Novel: name, year of release, author
 * - authorAge
 * - isWrittenBy(author
 * -copy (new year of release) = new instance of Noval
 * */

class Writer(firstName: String, lastName: String, val yearOfBirth: Int) {
  def fullName: String = s"$firstName $lastName"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge: Int = this.yearOfRelease - author.yearOfBirth
  def isWrittenBy(author: Writer): Boolean = this.author == author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}


/**
 * Exercise #2: an immutable counter class
 * - constructed with an initial count
 * - increment/decrement => New instance of counter
 * - increment(n)/decrement(n) => NEW instance of counter
 * - print()
 *
 * Benefits:
 * + well un distributed environments
 * + easier to read and understand code
 * */


class Counter(count: Int = 0) {
  def increment(): Counter =
    new Counter(count + 1)

  def decrement(): Counter =
    new Counter(count - 1)

  def increment(n: Int): Counter = {
    if (n <= 0) this
    else increment().increment(n - 1) // stack overflow error for large number
  }

  def decrement(n: Int): Counter = {
    if (n <= 0) this
    else decrement().decrement(n - 1)
  }

  def print(): Unit =
    println(s"Current count: $count")
}