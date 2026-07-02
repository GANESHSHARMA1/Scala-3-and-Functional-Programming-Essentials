package com.ganeshkithana.part3fp

import scala.util.Random

object LinearCollections {

  // Seq = well-defined ordering + indexing
  def testSeq(): Unit = {
    val aSequence: Seq[Int] = Seq(4, 3, 2, 1)
    // main API : index an element
    val thirdElement = aSequence.apply(2) // element 3
    // map/flatmap/filter/for
    val anIncrementedSeq = aSequence.map(_ + 1) // [5, 4, 3, 2]
    val aFlatMappedSeq = aSequence.flatMap(x => Seq(x, x + 1)) // [4, 5, 3, 4, 2, 3, 1, 2]
    val aFilteredSeq = aSequence.filter(_ % 2 == 0) // [4, 2]
    // other methods
    val reversed = aSequence.reverse
    val concatenation = aSequence ++ Seq(5, 6, 7, 8)
    val sortedSeq = aSequence.sorted // [1, 2, 3, 4]
    val sum = aSequence.foldLeft(0)(_ + _) // 10
    val sum_v2 = aSequence.sum // identical
    val stringRep = aSequence.mkString("[", ",", "]")

    println(aSequence)
    println(reversed)
    println(concatenation)
    println(sortedSeq)
    println(anIncrementedSeq)
    println(aFlatMappedSeq)
    println(aFilteredSeq)
    println(sum)
    println(sum_v2)
    println(stringRep)
  }

  def testLists(): Unit = {
    val aList = List(1, 2, 3, 4)
    // same API as Seq
    // other methods
    val firstElement = aList.head
    val restElements = aList.tail
    // appending and prepending
    val aBiggerList = 0 +: aList :+ 5
    val prepending = 0 :: aList // :: equivalent to Cons in our LList
    // utility methods
    val scalaX5 = List.fill(5)("Scala")

    println(scalaX5)
  }

  // ranges
  def testRanges(): Unit = {
    val aRange = 1 to 10
    val aNonInclusiveRange = 1 until 10 // 10 not included
    // same API as Seq
    (1 to 10).foreach(_ => println("Scala"))
    println(aRange.mkString("(", "->", ")"))
  }

  // arrays
  def testArrays(): Unit = {
    val anArray = Array(1, 2, 3, 4) // int[] on the jvm
    // most Seq APIs
    // Arrays are not Seqs
    val aSequence: Seq[Int] = anArray.toIndexedSeq
    // arrays are mutable
    anArray.update(2, 30) // no new array is allocated
  }

  // vectors = fast Seqs for a large amount of data
  def testVectors(): Unit = {
    val aVector: Vector[Int] = Vector(1, 2, 3, 4, 5)
    // the same Seq APIs
  }

  def smallBenchmark(): Unit = {
    val maxRun = 1000
    val maxCapacity = 1000000

    def getWriteTime(collection: Seq[Int]): Double = {
      val random = new Random()
      val times = for {
        i <- 1 to maxRun
      } yield {
        val index = random.nextInt(maxCapacity)
        val element = random.nextInt()

        val currentTime = System.nanoTime()
        val updatedCollection = collection.updated(index, element)
        System.nanoTime() - currentTime
      }

      // compute average
      times.sum * 1.0 / maxRun
    }

    val numberedList = (1 to maxCapacity).toList
    val numberedVector = (1 to maxCapacity).toVector

    println(getWriteTime(numberedList))
    println(getWriteTime(numberedVector))
  }

  // sets
  def testSets(): Unit = {
    val aSet = Set(1, 2, 3, 4, 5, 4) // unique element only --> no ordering guaranteed
    // equal + hashCode = hash set
    // main API: if in the Set
    val contains3 = aSet.contains(3) // true
    val contains3_v2 = aSet.apply(3) // same: true
    // adding/removing
    val aBiggerSet = aSet + 4 // [1, 2, 3, 4]
    val aSmallerSet = aSet - 4 // [1, 2, 3, 5]
    //concatenation == union
    val anotherSet = Set(4, 5, 6, 7, 8)
    val muchBiggerSet = aSet.union(anotherSet)
    val muchBiggerSet_v2 = aSet ++ anotherSet // same
    val muchBiggerSet_v3 = aSet | anotherSet // same
    // difference
    val aDiffSet = aSet.diff(anotherSet)
    val aDiffSet_v2 = aSet -- anotherSet // same
    // intersection = common
    val anIntersection = aSet.intersect(anotherSet)
    val anIntersection_v2 = aSet & anotherSet // same
  }

  def main(args: Array[String]): Unit = {
    testSeq()
    testLists()
    testRanges()
//    smallBenchmark()
  }
}
