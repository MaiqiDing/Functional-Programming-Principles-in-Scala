val test = 2 :: 1 :: Nil
test.head
test.tail.tail

test.tail.tail.isEmpty

//test.tail.tail.head //java.util.NoSuchElementException: head of empty list
def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List() => List(x)
  case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
}

/**
  * insertion sort
  * @param xs The list to be sorted
  * @return List
  */
def isort(xs: List[Int]): List[Int] = xs match {
  case List() => List()
  case y :: ys => insert(y, isort(ys))
}

isort(List(7,2,4,9))