import week03.Rational
import week03._
import week03.{Rational, Hello}

println("Welcome to the Scala worksheet")
//  new IntSet //not working, can't new an abstract class
val t1 = new NonEmpty(3, new Empty, new Empty)
val t2 = t1 incl 4
println(t2.toString)

new Rational(1,2)



//IntSet is an abstract class
//can contain members missing implementation
abstract class IntSet {
  def incl(x: Int): IntSet

  def contains(x: Int): Boolean

  def union(other: IntSet): IntSet
}

//binary tree: Empty and NonEmpty(include 2 sub-tree)
class Empty extends IntSet {
  def contains(x: Int): Boolean = false

  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)

  override def toString: String = "."

  def union(other: IntSet): IntSet = other
}

//persistent data structure
class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  override def toString: String = "{" + left + elem + right + "}"

  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem
}
