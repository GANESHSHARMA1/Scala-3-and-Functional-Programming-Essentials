package com.ganeshkithana.part2oop

val meaningOfLife = 42
def computeMyLife: String = "scala"

object PackagesImports { // top - level

  // packages = "folders"

  // fully qualified name
  val aList: com.ganeshkithana.practice.LList[Int] = ??? // throws NotImplementedError

  // import
  import com.ganeshkithana.practice.LList
  val anotherList: LList[Int] = ???

  // importing under an alias
  import java.util.List as JList
  val aJavaList: JList[Int] = ???

  // import everything
  import com.ganeshkithana.practice.*
  val aPredicate: Predicate[Int] = ???

  // import multiple symbols
  import PhysicsConstants.{SPEED_OF_LIGHT, EARTH_GRAVITY}
  val c = SPEED_OF_LIGHT

  // import everything but something
  object PlayingPhysics {
    import PhysicsConstants.{PLANCK as _, *}
    // val planck = PLANCK // will not work
    val c = SPEED_OF_LIGHT
  }

  import com.ganeshkithana.part2oop.* // import the mol and computeMyLife
  val mol = meaningOfLife

  // default imports
  // scala.*, scala.Predef.*, java.lang.*

  // exports
  class PhysicsCalculator {
    import PhysicsConstants.*
    def computePhotonEnergy(wavelength: Double): Double =
      PLANCK / wavelength
  }

  object ScienceApp {
    val physicsCalculator = new PhysicsCalculator

    export physicsCalculator.computePhotonEnergy

    def computeEquivalentMass(wavelength: Double): Double =
      computePhotonEnergy(wavelength) / (SPEED_OF_LIGHT * SPEED_OF_LIGHT)
  }

  def main(args: Array[String]): Unit = {

  }
}


// usually organizing "utils" and constants in separate objects
object PhysicsConstants {
  // constants
  val SPEED_OF_LIGHT = 299792458
  val PLANCK = 6.62e-34 // scientific notation
  val EARTH_GRAVITY = 9.8
}