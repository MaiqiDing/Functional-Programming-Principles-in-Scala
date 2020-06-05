// Vector: construct log32(N) subclass of Seq(like List)
val nums = Vector(1, 2, 3, 4)

// create new Vector(immutable)
// : always points to the sequence
0 +: nums
nums :+ 5

// Array and String support the same operations as Seq and
// can implicitly be converted to sequences where needed (but not subclass of Seq)
val xs = Array(1, 2, 3, 44)
xs map (x => x * 2)

val str = "Hello World"
str filter (c => c.isUpper)
str filter (_.isLower)
str exists (_.isUpper)
str forall (_.isLetter)


// Range is another kind of sequence
// to: inclusive until: exclusive
val r = 1 until 5 // 1, 2, 3, 4
val s = 1 to 5 // 1, 2, 3, 4, 5
1 to 10 by 3 // 1, 4, 7, 10
6 to 1 by -2 // 6, 4, 2
val pairs = List(1, 2, 3) zip str // List((1,H), (2,e), (3,l))
pairs.unzip // (List(1, 2, 3),List(H, e, l))

str flatMap (c => List('.', c)) //.H.e.l.l.o. .W.o.r.l.d

xs.sum // 50
xs.max // 44

//def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
//  (xs zip ys).map(xy => xy._1 * xy._2).sum
//def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
//  (xs zip ys).map(x => x match {case (x, y) => x * y}).sum
def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
  (xs zip ys).map { case (x, y) => x * y }.sum

scalarProduct(Vector(1, 2, 3), Vector(1, 2, 1))

// value conciseness over efficiency
def isPrime(n: Int): Boolean = (2 until n) forall (d => n % d != 0)
