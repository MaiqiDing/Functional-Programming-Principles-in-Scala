package recfun

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /**
    * Exercise 1
    *
    * (0,2)=1 (1,2)=2 (1,3)=3
    */
  def pascal(c: Int, r: Int): Int =
    if (c == 0 || r == c) 1 else pascal(c, r - 1) + pascal(c - 1, r - 1)

  /**
    * Exercise 2
    *
    * chars.isEmpty: Boolean returns whether a list is empty
    * chars.head: Char returns the first element of the list
    * chars.tail: List[Char] returns the list without the first element
    *
    * Hint: you can define an inner function if you need to pass extra parameters to your function.
    *
    * Testing: You can use the toList method to convert from a String to aList[Char]: e.g. "(just an) example".toList.
    */
  def balance(chars: List[Char]): Boolean = {

    def helper(chars: List[Char], score: Int): Int =
      if (chars.isEmpty || score < 0) {
        score
      }
      else {
        if (chars.head == '(') {
          helper(chars.tail, score + 1)
        } else if (chars.head == ')') {
          helper(chars.tail, score - 1)
        } else {
          helper(chars.tail, score)
        }
      }

    helper(chars, 0) == 0
  }


  /**
    * Exercise 3
    *
    * Hint: Think of the degenerate cases.
    * How many ways can you give change for 0 CHF(swiss money)?
    * How many ways can you give change for >0 CHF, if you have no coins?
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (money < 0 || coins.isEmpty) 0
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}
