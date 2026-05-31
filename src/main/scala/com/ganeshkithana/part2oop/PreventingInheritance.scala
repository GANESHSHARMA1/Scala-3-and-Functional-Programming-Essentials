package com.ganeshkithana.part2oop

object PreventingInheritance {

  class Person(name: String) {
    def enjoyLife(): Int = 34

    final def enjoyBirthday(): Int = 35
  }

  class Adult(name: String) extends Person(name) {
    override def enjoyLife(): Int = 33

    // It cannot override final member method enjoyBirthday in class Person
    // override def enjoyBirthday(): Int = 34
  }

  final class Animal // Cannot be inherited
  // class Cat extends Animal // Illegal inheritance from final class 'Animal'

  // sealing a type hierarchy = inheritance only permitted inside this file
  sealed class Guitar(nStrings: Int)
  class ElectricGuitar(nStrings: Int) extends Guitar(nStrings)
  class AcousticGuitar extends Guitar(6)

  // no modifier for class = "not encouraging" inheritance
  // mot mandatory, but good practice
  open class ExtensibleGuitar(nString: Int) // open = specially marked for extension

  def main(args: Array[String]): Unit = {

  }
}
