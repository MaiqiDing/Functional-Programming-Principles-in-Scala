val nums = List(2, -4, 5, 7, 1)
val fruit = List("apples", "oranges", "pears", "banana")

nums filter (x => x > 0)
nums filterNot (x => x > 0)
nums partition (x => x > 0) // same as (xs filter p, xs filterNot p)

nums takeWhile (x => x > 0) // longest prefix return (2)
nums dropWhile (x => x > 0) // return (-4, 5, 7, 1)
nums span (x => x > 0) // same as (xs takeWhile p, xs dropWhile p)

val data = List("a", "a", "a", "b", "c", "c", "a")

// return consecutive duplicates of list elements into sub lists.
def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    val (first, rest) = xs span (elem => elem == x)
    first :: pack(rest)
}

pack(data) // return List(List(a, a, a), List(b), List(c, c), List(a))


def encode[T](xs: List[T]): List[(T, Int)] =
  pack(xs) map (ys => (ys.head, ys.length))


encode(data) // return List((a,3), (b,1), (c,2), (a,1))

/** Reduction of Lists **/
//def sum(xs: List[Int]): Int = xs match {
//  case Nil => 0
//  case y :: ys => y + sum(ys)
//}
//
//def product(xs: List[Int]): Int = xs match {
//  case Nil => 1
//  case y :: ys => y * product(ys)
//}

/** abstract the pattern **/
//use reduceLeft(non-empty list)
//def sum(xs: List[Int]) = (0 :: xs) reduceLeft ((x, y) => x + y)
//def sum(xs: List[Int]) = (0 :: xs) reduceLeft (_ + _)

//def product(xs: List[Int]) = (1 :: xs) reduceLeft ((x, y) => x * y)
//def product(xs: List[Int]) = (1 :: xs) reduceLeft (_ * _)

//use foldLeft
//can be applied to empty-list
//require an accumulator z(zero)
def sum(xs: List[Int]) = (xs foldLeft 0) (_ + _)
def product(xs: List[Int]) = (xs foldLeft 1) (_ * _)

sum(List(3, 4, 5))
product(List(4, 5, 6))

/** foldLeft and reduceLeft can be implemented in class List as **/
//abstract class List[T] {
//  def reduceLeft(op: (T, T) => T): T = this match {
//    case Nil => throw new Error("Nil.reduceLeft")
//    case x :: xs => (xs foldLeft x) (op)
//  }
//
//  def foldLeft[U](z: U)(op: (U, T) => U): U = this match {
//    case Nil => z
//    case x :: xs => (xs foldLeft op(z, x)) (op)
//  }
//}

/** foldRight and reduceRight can be implemented in class List as **/
// start from the tail
//def reduceRight(op: (T, T) => T): T = this match {
//  case Nil => throw new Error("Nil.reduceRight")
//  case x :: Nil => x
//  case x :: xs => op(x, xs.reduceRight(op))
//}
//
//def foldLeft[U](z: U)(op: (U, T) => U): U = this match {
//  case Nil => z
//  case x :: xs => op(x, (xs foldRight z, x) (op))
//}

def sum1(xs: List[Int]) = (xs foldRight 0) (_ + _)
sum1(List(3, 4, 5, 6))

// Note:
// operators are associative => foldLeft and foldRight are equivalent
// example
def concat[T](xs: List[T], ys: List[T]): List[T] =
  (xs foldRight ys) (_ :: _)
// foldRight cannot be replaced by foldLeft
// reason: type not matching, if we foldLeft, for each x1, x2 ... we
// apply the :: method but x1 is simply Int not List

concat(List(1,2,4), List(2,5,8))


//def mapFun[T, U](xs: List[T], f: T => U): List[U] =
//  (xs foldRight List[U]())( ??? )
//
//def lengthFun[T](xs: List[T]): Int =
//  (xs foldRight 0)( ??? )

def concat1[T](xs:List[T], ys:List[T]):List[T] = xs match {
  case List() => ys
  case x::xs1 => x :: concat1(xs1,ys)
}

concat1(List(1,2,4), List(2,5,8))
