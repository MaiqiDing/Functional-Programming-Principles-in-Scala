val fruit = List("apples", "oranges", "pears")
val nums = List(1, 2, 3)
val diag3 = List(List(1,0,0), List(0,1,0), List(0,0,1))
val empty = List()

def removeAt[T](n: Int, xs: List[T]) = (xs take n) ::: (xs drop n+1)

removeAt(1, diag3)
removeAt(0, empty)
