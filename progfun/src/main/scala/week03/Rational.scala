package week03

class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be nonzero")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  private val g = gcd(x, y)

  def numer = x / g

  def denom = y / g


  //add can be replaced by '+' symbols
  def add(that: Rational) =
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)

  override def toString = numer + "/" + denom

  //  def neg: Rational = new Rational(-numer, denom)
  def unary_- : Rational = new Rational(-numer, denom)

  def sub(that: Rational) = add( - that)

  def less(that: Rational) = this.numer * that.denom < that.numer * this.denom

  def max(that: Rational) = if (this.less(that)) that else this
}
