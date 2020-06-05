val fruit = List("apples", "oranges", "pears")
val nums = List(1, 2, 3)
val diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
val empty = List()

def removeAt[T](n: Int, xs: List[T]) = (xs take n) ::: (xs drop n + 1)

removeAt(1, diag3)
removeAt(0, empty)


// Higher-Order List Functions

//abstract class List[T] {
//
//  def map[U](f: T => U): List[U] = this match {
//    case Nil => this
//    case x :: xs => f(x) :: xs.map(f)
//  }
//  def filter(p: T => Boolean): List[T] = this match {
//    case Nil => this
//    case x :: xs => if (p(x)) x :: xs.filter(p) else xs.filter(p)
//}
//}


def squareList(xs: List[Int]): List[Int] = xs match {
  case Nil => Nil
  case y :: ys => y * y :: squareList(ys)
}

// using map
//def squareList(xs: List[Int]): List[Int] =
//  xs map (x => x * x)

// return the positive elements
def posElem(xs: List[Int]): List[Int] = xs match {
  case Nil => Nil
  case y :: ys => if (y > 0) y :: posElem(ys) else posElem(ys)
}

// using filter
//def posElem(xs: List[Int]): List[Int] =
//  xs filter (x => x > 0)