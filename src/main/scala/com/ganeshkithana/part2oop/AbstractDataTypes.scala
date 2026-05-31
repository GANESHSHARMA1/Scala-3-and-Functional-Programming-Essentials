package com.ganeshkithana.part2oop

object AbstractDataTypes {

  // A class that can have members without an implementation
  abstract class Animal {
    val creatureType: String // abstract
    def eat(): Unit

    // non-abstract fields/methods allowed
    def preferredFood: String = "Anything" // "accessor methods"
  }

  // abstract class can't be initialized
  // val anAnimal: Animal = new Animal()

  // non-abstract classes must implement the abstract fields and methods
  class Dog extends Animal {

    override val creatureType: String = "Dog"

    override def eat(): Unit = println("Dog eats bone")

    // overriding is legal for everything
    override val preferredFood: String = "bones" // here accessor method is overridden as field - It's only possible if method doesn't have any parameter/arguments
  }

  val aDog = new Dog()

  abstract class Cat extends Animal {
    def sayHi(): String = "Hi, I'm a Cat"
  }

  // val aCat = new Cat() // Cat is abstract; it cannot be instantiated


  // traits:- A datatype that describe behavior -- Same as Interface in JAVA
  trait Carnivore { // Scala 3 - traits can have constructor args
    // Both abstract and non-abstract members are allowed
    def eat(animal: Animal): Unit
    def run(animal: Animal): String = s"$animal is running"
  }

  class TRex extends Carnivore{
    override def eat(animal: Animal): Unit = println(s"I'm T-Rex. I eat animals")
  }

  /** Abstract class v/s traits */
  // practical difference
  /** Abstract class: A class can inherits only a single class - one class inheritance
   * Traits: multiple traits inheritance - multiple traits can be inherited */
  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "I'm a Crocodile"

    override def eat(): Unit = println("I eat animal")

    override def eat(animal: Animal): Unit = println(s"Hi, I'm eating ${animal}...")
  }

  /**
   * philosophical difference - abstract class v/s traits
   * - abstract classes are THINGS
   * - traits are BEHAVIORS
   * */

  /**
   * Any
   *    - AnyRef
   *        : All classes we write extends 'AnyRef' By default
   *          scala.Null (the null reference)
   *        : Java.lang.Object - String, List, Set, .. so on
   *    - AnyVal
   *        : All Primitive dataTypes: Int, Boolean, Char, ... so on
   *
   *
   *       scala.Nothing - An proper replacement for any type
   * */

  class MyThing extends AnyRef // here 'extends AnyRef' will be added by default by compiler. We don't need to add it manually

  val aNonExistentAnimal: Animal = null
  val anInt: Int = throw new NullPointerException // scala.Nothing

  def main(args: Array[String]): Unit = {

  }
}
