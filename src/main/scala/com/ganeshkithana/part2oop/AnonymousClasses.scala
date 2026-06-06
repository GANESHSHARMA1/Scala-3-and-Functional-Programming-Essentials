package com.ganeshkithana.part2oop

import com.ganeshkithana.part2oop.AnonymousClasses.someAnimal

object AnonymousClasses {

  abstract class Animal {
    def eat(): Unit
  }

  // classes used for just one instance are boilerplate-y
  class someAnimal extends Animal {
    override def eat(): Unit = println("I'm a weired animal.")
  }

  val someAnimal: someAnimal = new someAnimal // someAnimal class is redundant as it's used by 1-2 times

  val someAnimal_v2: Animal = new Animal { // anonymous class
    override def eat(): Unit = println("I'm a weired animal.")
  }

  /**
   * equivalent with:
   *
   * class AnonymousClasses.AnonClass$ extends Animal {
   *    override def eat(): Unit = println("I'm a weired animal.")
   * }
   *
   * val someAnimal_v2 = AnonymousClasses.AnonClass$
   * */

  // works for classes (abstract or not) + traits
  class Person(name: String) {
    def sayHi(): Unit = println(s"Hi! my name is $name")
  }

  val jim: Person = new Person("Jim") {
    override def sayHi(): Unit = println("My Name is JIM")
  }

  def main(args: Array[String]): Unit = {
    someAnimal.eat()
    someAnimal_v2.eat()
  }
}
