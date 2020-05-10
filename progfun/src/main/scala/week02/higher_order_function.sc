object higher_order_function {
  def sumInts(a: Int, b: Int): Int =
    if (a > b) 0 else a + sumInts(a + 1, b)

  sumInts(0, 100)

  def cube(x: Int): Int = x * x * x

  def sumCubes(a: Int, b: Int): Int =
    if (a > b) 0 else cube(a) + sumCubes(a + 1, b)

  sumCubes(0, 3)


  def fact(x: Int): Int = if (x == 0) 1 else x * fact(x - 1)

  fact(3)

  /**
    * @param f function type: Int => Int
    * @param a start
    * @param b end
    * @return sum of f(n) from a to b
    */
  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)

  sum(cube, 1, 3)

  def sumTailRecursion(f: Int => Int, a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, acc + f(a))
    }

    loop(a, 0)
  }

  sumTailRecursion(cube, 1, 3)
  sumTailRecursion(x => x * x, 3, 5)


  //multiple parameter list
  def sum1(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0
      else f(a) + sumF(a + 1, b)

    sumF
  }

  sum1(cube)(0, 3)

  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)
  }

  product(x => x * x)(3, 4)

  product(x=>x)(1,5) // factorial

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
  }

  mapReduce(x=>x, (a,b) =>a*b, 1)(1,5)
}