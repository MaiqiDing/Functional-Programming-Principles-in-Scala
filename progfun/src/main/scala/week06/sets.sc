// sets are unordered
val fruit = Set("apple", "banana", "pear")
val s = (1 to 6).toSet

s map (_ + 2) // Set(5, 6, 7, 3, 8, 4)
fruit filter (_.startsWith("app")) // Set(apple)
s.nonEmpty // true

s map (_ % 2) // Set(0, 1), do not have duplicate elements

s contains 5 // true, contain is the fundamental operation on set

// example: N-Queen problem
def queens(n: Int): Set[List[Int]] = {
  def placeQueens(k: Int): Set[List[Int]] = {
    if (k == 0) Set(List())
    else
      for {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if isSafe(col, queens)
      } yield col :: queens
  }

  def isSafe(col: Int, queens: List[Int]): Boolean = {
    val row = queens.length
    val queensWithRow = (row - 1 to 0 by -1) zip queens
    queensWithRow forall {
      case (r, c) => col != c && math.abs(col - c) != row - r // not same col or diagonal
    }
  }

  placeQueens(n)
}

def show(queens: List[Int]) = {
  val lines =
    for (col <- queens.reverse)
      yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
      "\n" + (lines mkString "\n")
}

queens(5) map show
queens(5).size