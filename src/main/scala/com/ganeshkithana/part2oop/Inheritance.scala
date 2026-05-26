package com.ganeshkithana.part2oop

object Inheritance { // Inheritance: Is-A type relation

  class Animal {
    val creatureType = "wild"
    def eat(): Unit = println("Animal")
  }

  class Cat extends Animal {
    def crunch(): Unit = {
      eat()
      println("crunch crunch")
    }
  }

  val cat = new Cat

  class Person(val name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)  // must specify super-constructor

  // overriding
  class Dog extends Animal {
    override val creatureType: String = "domestic"

    override def eat(): Unit = println("I like this bone")

    // popular overridable method
    override def toString: String = "a dog"
  }

  val dog = new Dog

  // subtype polymorphism
  val dog1: Animal = new Dog
  dog1.eat() // the most specific method will be called

  // overloading vs overriding
  class Crocodile extends Animal {
    override val creatureType: String = "very wild"

    override def eat(): Unit = println("I can eat anything")

    // overloading: multiple methods with the same name, different signatures
    // different signature = different argument list ( different number of args + different arg types)
    // + different return type(option)
    def eat(animal: Animal): Unit = println(s"I'm eating this $animal")

    /**
     * Exercise: Which method of these are correct overloading
     *
     * def eat(dog: Dog): Unit = println("eating a dog") -> correct
     * def eat(person: Person): Unit = println(s"I'm eating a human with name ${person.name}") -> Correct
     * def eat(person: Person, dog: Dog): Unit = println("I'm eating a human AND the dog") -> Correct
     * def eat(): Int = 45  -> wrong
     * def eat(dog: Dog, person: Person): Unit = println("I'm eating a human AND the dog") -> wrong
     * */
  }

  def main(args: Array[String]): Unit = {
    cat.eat()
    cat.crunch()
    dog.eat()
    println(dog) // println(dog.toString) -> a dog
    println(dog1)

    dog1.eat()
  }
}
