package com.ganeshkithana.part1basics

object Expressions {

  // expressions are structures that can be evaluated to a value
  val meaningOfLife: Int = 40 + 2

  // mathematical expressions: +, -, *, /, bitwise |, &, ~, ^, <<, >>, >>>
  val mathExpression: Int = 2 + 3 * 4

  // comparison expression: <, <=, >, >=, ==, !=
  val equalityTest: Boolean = 1 == 2

  // boolean expression: !, ||, &&
  val nonEqualityTest: Boolean = !equalityTest

  // instructions vs expressions:
  // expressions are evaluated, instructions are executed
  // we think in terms of expressions

  // ifs are expressions
  val aCondition: Boolean = true
  val anIfExpression: Any = if (aCondition) 54 else "55"

  // code blocks -> Type will be same of last expression
  val aCodeBlock: Int = {
    // local values
    val localValue = 78
    //expressions..

    // last expression = value of the block
    /*"return*/localValue + 54
  }

  // everything is an expression

  /**Exercise*/
  val someValue = {
    2 < 3
  }// boolean: True

  val someAnotherValue = {
    if(someValue) 244 else 543
    45
  }// Int: bcs of last statement-> 45

  val yetAnotherValue = println("Scala") //Unit type: Print statement -> Scala  '()'
  val theUnit: Unit = ()

  def main(args: Array[String]): Unit = {
    println(meaningOfLife)
    println(anIfExpression)

    /**Exercise*/
    println(someValue) // true
    println(someAnotherValue) // 45
    println(yetAnotherValue) // Scala ()
    println(println("Hello"))
  }
}
