package com.ganeshkithana.part1basics

object StringOps {

  val aString: String = "Hello, I'm learning Scala"

  // String functions
  val secondChar: Char = aString.charAt(2)
  val firstWord: String = aString.substring(0, 5) // "Hello"
  val words: Array[String] = aString.split(" ") // Array("Hello,", "I'm", "learning", "Scala")
  val startsWithHello: Boolean = aString.startsWith("Hello") // true
  val allUnderscore: String = aString.replaceAll(" ", "_")
  val allLowercase: String = aString.toLowerCase()
  val allUppercase: String = aString.toUpperCase()
  val len: Int = aString.length

  // other functions
  val reversed: String = aString.reverse
  val aBunchOfChars: String = aString.take(10)

  // parse to numeric
  val aNum: String = "2"
  val number: Int = aNum.toInt

  // string s-interpolation
  val name: String = "Ganesh"
  val age: Int = 23
  val greeting: String = "Hello! I'm" + name + "and I am" + age + "years old"
  val greeting_v2: String = s"Hello! I'm $name and I am $age years old"
  val greeting_v3: String = s"Hello! I'm $name and I will be turning ${age + 1} years old"

  // string f-interpolation
  val speed: Float = 1.2f
  val myth: String = f"$name can eat $speed%2.2f burger per minute."

  // string raw-interpolation
  val escape: String = raw"This is a \n newline"

  def main(args: Array[String]): Unit = {
    println(secondChar)
    println(firstWord)
    println(startsWithHello)
    println(allUnderscore)

    println(myth)

    println(escape)
  }
}
