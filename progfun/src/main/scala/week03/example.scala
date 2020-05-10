package week03

//class Rational(x: Int, y: Int) {
//  def numer = x
//  def denom = y
//}

object example extends App {
  val x = new Rational(1, 3)
  val y = new Rational(5, 7)
  val z = new Rational(3, 2)

  println(x.sub(y).sub(z))

  println(x)
  assert(x.denom >= 0)
  println(x.denom)
  println(x.add(new Rational(1, 3)))
  println(x.less(y))
  println(x.max(y))

//  val strange = new Rational(1,0)
//  strange.add(strange)


  val three = new Rational(3+5)
  println(three add x)
}
