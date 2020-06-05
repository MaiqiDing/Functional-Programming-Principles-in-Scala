// Vector: each level has 32 pointer
// construct: log32(N)
// subclass of Seq(like List)
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
val r: Range = 1 until 5 // 1, 2, 3, 4
val s: Range = 1 to 5 // 1, 2, 3, 4, 5
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

// 1 <= j < i < n, find if any i+j is prime number
// generate pair (i, j)
val n = 7
val xss = (1 until n) map (i => (1 until i) map (j => (i, j))) // Vector of Vectors because range is also a Seq
(xss foldRight Seq[(Int, Int)]()) (_ ++ _) // combine all sub-seq in one Vector
xss.flatten // equivalence

// useful law
//xs flatMap f = (xs map f).flatten
(1 until n) flatMap (i => (1 until i) map (j => (i, j)))

// filter
(1 until n) flatMap (i => (1 until i) map (j => (i, j))) filter
  (pair => isPrime(pair._1 + pair._2))

// for-expression
// for (s) yield e
// s contains:
// generator: p <- e
// filter: if f, where f is a boolean expression
case class Person(name: String, age: Int)

var persons = List(Person("a", 12), Person("b", 22), Person("c", 24))
for (p <- persons if p.age > 20) yield p.name // person is List/Vector then return List/Vector(b,c)

persons filter (p => p.age > 20) map (p => p.name)

// for {s} can also be used and then generator and filter can be written on multi-lines without semicolons
for {
  i <- 1 until n
  j <- 1 until i
  if isPrime(i + j)
} yield (i, j)

def scalarProduct1(xs: Vector[Double], ys: Vector[Double]): Double =
  (for((x,y) <- xs zip ys) yield x*y).sum

scalarProduct1(Vector(1,2,3), Vector(1,2,3))