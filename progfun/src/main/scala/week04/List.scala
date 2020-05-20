package week04

/**
  * generic list
  * sub typing: List has Cons and Nil
  * @tparam T type parameter
  */
trait List[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  // def f(xs: List[NonEmpty], x: Empty) = xs prepend x //result type is List[IntSet]
  def prepend[U >: T](elem: U): List[U] = new Cons(elem, this)
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty: Boolean = false
}

//class Nil[T] extends List[T] {
//  def isEmpty = true
//  def head: Nothing = throw new NoSuchElementException("Nil.head")
//  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
//}

 object Nil extends List[Nothing] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

// function is also object
//object List {
//  //List(1, 2) = List.apply(1, 2)
//  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons[T](x2, new Nil[T]))
//
//  def apply[T]() = new Nil
//
////  def main(args: Array[String]): Unit = {
////    val a = List(2,3)
////    println(a)
////  }
//}
object test {
  val x: List[String] = Nil // make List[+T] Nothing is subtype of String
//  def f(xs: List[NonEmpty], x: Empty) = xs prepend x
}
