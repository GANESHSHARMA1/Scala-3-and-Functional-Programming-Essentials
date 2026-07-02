package com.ganeshkithana.part3fp

import scala.collection.MapView

object TuplesMaps {

  // tuples = finite ordered "lists" / group of values under the same "big" value
  val aTuple: (Int, String) = (2, "Scala") // Tuple2[Int, String]
  val firstField: Int = aTuple._1
  val aCopiedTuple: (Int, String) = aTuple.copy(_1 = 22)

  // tuples of 2 elements
  val aTuple_v2: (Int, String) = 2 -> "Scala" // Identical to (2, "Scala")

  // maps: keys -> values
  val aMap = Map()

  val languageBook: Map[String, Int] = Map(
    "Java" -> 1001,
    "Scala" -> 1002,
    "Python" -> 1003
  ).withDefaultValue(-1)

  // core APIs
  val languageBookHasScala: Boolean = languageBook.contains("Scala")
  val htmlLanguageBook: Int = languageBook("HTML") // crash with an exception or return default value
  // associated with map --> key not present


  // add a pair
  val newPair: (String, Int) = "HTML" -> 1004
  val newLanguageBook: Map[String, Int] = languageBook + newPair

  // remove a key
  val languageBookWithoutHTML: Map[String, Int] = newLanguageBook - "HTML"

  // list -> map
  val linearLanguageBook: List[(String, Int)] = List(
    "Java" -> 1001,
    "Scala" -> 1002,
    "Python" -> 1003
  )

  val languageBook_v2: Map[String, Int] = linearLanguageBook.toMap

  // map -> linear collection
  val linearLanguageBook_v2: List[(String, Int)] = languageBook.toList // toSeq, toVector, toSet, toArray

  // map, flatMap, filter
  // Map("Scala" -> 1001, "ScALA" -> 1101) => Map("SCALA" -> ???)
  val aProcessedLanguageBook: Map[String, Int] = languageBook.map(pair => (pair._1.toUpperCase(), pair._2))

  // filtering keys
  val noPs: MapView[String, Int] = languageBook.view.filterKeys(!_.startsWith("P"))

  // mapping values
  val prefixNumbers: Map[String, String] = languageBook.view.mapValues(number => s"1001 - $number").toMap

  // other collections can create maps
  val names: List[String] = List("Rahul", "Ganesh", "Mohan", "Sohan", "Rohan")
  val nameGroupings: Map[Char, List[String]] = names.groupBy(name => name.charAt(0))

  def main(args: Array[String]): Unit = {
    println(languageBook)
    println(languageBookHasScala)
    println(htmlLanguageBook)

    println(nameGroupings)
  }
}
