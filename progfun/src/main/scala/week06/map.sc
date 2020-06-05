val romanNumerals = Map("I" -> 1, "V" -> 5, "X" -> 10)
val capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern")

capitalOfCountry("US") // Washington

//capitalOfCountry("andorra") // java.util.NoSuchElementException: key not found: andorra

// Option type
//trait Option[+A]
//case class Some[+A](value: A) extends Option[A]
//object None extends Option[Nothing]

capitalOfCountry get "andorra" // Option[String] = None
capitalOfCountry get "US" // Option[String] = Some(Washington)

def showCapital(country: String) = capitalOfCountry.get(country) match {
  case Some(capital) => capital;
  case None => "missing data"
}

showCapital("US") // Washington
showCapital("andorra") // missing data

val fruit = List("apple", "pear", "orange", "pineapple")
fruit.sortWith(_.length < _.length) // List(pear, apple, orange, pineapple)
fruit.sorted //by letter order, List(apple, orange, pear, pineapple)

// SQL queries, groupBy and orderBy

fruit groupBy (_.head) // Map(p -> List(pear, pineapple), a -> List(apple), o -> List(orange))

// polynomial
// example x^3 - 2x + 5
//Map(0 -> 5, 1 -> -2, 3 -> 1)

class Poly(terms0: Map[Int, Double]) {
  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  val terms = terms0 withDefaultValue 0.0
  //  def + (other: Poly) = new Poly(terms ++ (other.terms map adjust))
  ////  def adjust(term: (Int, Double)): (Int, Double) = {
  ////    val (exp, coeff) = term
  ////    terms get exp match {
  ////      case Some(coeff1) => exp -> (coeff + coeff1)
  ////      case None => exp -> coeff
  ////    }
  //  def adjust(term: (Int, Double)): (Int, Double) = {
  //    val (exp, coeff) = term
  //    exp -> (coeff + terms(exp)) // simplify adjust by using defaultValue
  //  }

  //another version of + using foldLeft, more efficient
  def +(other: Poly) =
    new Poly((other.terms foldLeft terms) (addTerm))

  def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
    val (exp, coeff) = term
    terms + (exp->(coeff + terms(exp)))
  }

  override def toString =
    (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
}

//val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
//val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0))

val p1 = new Poly(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)
val p2 = new Poly(0 -> 3.0, 3 -> 7.0)

p1 + p2

// Default value
val cap1 = capitalOfCountry withDefaultValue "<unknown>"
cap1("andorra") // <unknown>