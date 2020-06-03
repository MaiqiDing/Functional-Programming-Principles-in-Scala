import math.Ordering
//def msort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
//def msort[T](xs: List[T])(ord: Ordering[T]): List[T] = {
def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
  val n = xs.length / 2
  if (n == 0) xs
  else {
    //    def merge(xs: List[Int], ys: List[Int]): List[Int] = xs match {
    //      case Nil => ys
    //      case x :: xs1 => ys match {
    //        case Nil => xs
    //        case y :: ys1 =>
    //          if (x < y) x :: merge(xs1, ys)
    //          else y :: merge(xs, ys1)
    //      }
    //    }
//    def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
//      case (Nil, ys) => ys
//      case (xs, Nil) => xs
//      case (x :: xs1, y :: ys1) =>
//        if (x < y) x :: merge(xs1, ys)
//        else y :: merge(xs, ys1)
//    }
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (Nil, ys) => ys
      case (xs, Nil) => xs
      case (x :: xs1, y :: ys1) =>
//        if (lt(x,y)) x :: merge(xs1, ys) // comparison is not defined to arbitrary type
        if (ord.lt(x,y)) x :: merge(xs1, ys) // comparison is not defined to arbitrary type
        else y :: merge(xs, ys1)
    }

    val (fst, snd) = xs splitAt n // pair: (List, List)
//    merge(msort(fst)(lt), msort(snd)(lt))
//    merge(msort(fst)(ord), msort(snd)(ord))
      merge(msort(fst), msort(snd))// the ord parameter in msort is filled by the compiler

  }
}


val l = List(2, 4, 6, 25, 23, 4)
//msort(l)((x: Int, y: Int)=> x<y )
//msort(l)((x, y)=> x<y ) // types are inferred
msort(l)(Ordering.Int)

val fruit = List("apples", "oranges", "pears", "banana")
//msort(fruit)((x: String, y: String) => x.compareTo(y) < 0)
msort(fruit)(Ordering.String)
msort(fruit)//can omit the ord if use the implicit parameter



val pair = ("answer", 42)
val (label, value) = pair
label+":"
value+3