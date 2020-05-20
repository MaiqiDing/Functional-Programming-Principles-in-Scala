package week04

/**
  * Pattern matching
  */
trait Expr {
  //like switch in C/Java
  def eval(e: Expr): Int = e match {
    case Number(n) => n
    case Sum(e1, e2) => eval(e1) + eval(e2)
  }

}

case class Number(n: Int) extends Expr

case class Sum(e1: Expr, e2: Expr) extends Expr

object Number {
  def apply(n: Int) = new Number(n)
}

object Sum {
  def apply(e1: Expr, e2: Expr): Sum = new Sum(e1, e2)
}

object exprs extends Expr {
  def show(e: Expr): String = e match {
    case Number(x) => x.toString
    case Sum(l, r) => show(l) + " + " + show(r)
  }

  def main(args: Array[String]): Unit = {
    println(show(Sum(Number(1), Number(44))))
    println(eval(Sum(Number(1), Number(2))))
  }
}