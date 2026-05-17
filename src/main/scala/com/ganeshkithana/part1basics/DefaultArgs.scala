package com.ganeshkithana.part1basics

import scala.annotation.tailrec

object DefaultArgs {

  @tailrec
  def sumUntilTailRec(x: Int, acc: Int = 0): Int =
    if (x <= 0) acc
    else sumUntilTailRec(x - 1, acc + x)

  val sumUntil50: Int = sumUntilTailRec(50) // default value is passed automatically

  // when you use a function most of the time with the same value = default argument
  def savePicture(dirPath: String, name: String, format: String = "jpeg", width: Int = 1980, height: Int = 1080): Unit =
    println(s"Saving picture $name at $dirPath in $format.")

  def main(args: Array[String]): Unit = {
    // default arguments are injected
    savePicture("user/ganesh/downloads", "myPhoto")
    // pass explicit different values for default args
    savePicture("user/ganesh/downloads", "myPhoto", "png")
    // savePicture("user/ganesh/downloads", "myPhoto", 980, 1020) // It will give error as it can't skip format value in this way

    // pass value after the default arguments
    savePicture("user/ganesh/downloads", "myPhoto", width = 980, height = 1020)
    savePicture("user/ganesh/downloads", "myPhoto", height = 1020, width = 980) // In this way, we can change the order/sequence of parameters
  }
}
