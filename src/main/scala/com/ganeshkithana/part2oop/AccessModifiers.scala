package com.ganeshkithana.part2oop

object AccessModifiers {

  class Person(val name: String) {
    // no modifier = public
    def sayHi(): String = s"Hi, my name is $name"

    // Protected = access to inside same class + children classes
    protected def sayHello(): String = s"Hello, my name is $name"

    // Private = access to inside same class
    private def watchNetflix(): String = s"I'm watching my favourite series....."
  }

  class Kid(override val name: String, age: Int) extends Person(name){
    // no modifier = public
    def greetPolitely(): String = sayHello() + " I love to Play"
  }

  val aPerson = new Person("Ganesh")
  val aKid = new Kid("Sharma", 24)

  // complication
  class KidWithParents(override val name: String, momName: String, dadName: String) extends Person(name) {
    val mom = new Person(momName)
    val dad = new Person(dadName)

//    def everyoneSayHello(): String =
//      s"Hello, I'm $name, and here are my parents: " + mom.sayHello() + dad.sayHello()
  }

  def main(args: Array[String]): Unit = {
    println(aPerson.sayHi())
    // println(aPerson.sayHello())
    println(aKid.greetPolitely())
  }
}
