package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 2))
  println(contains(singletonSet(1), 1))
  printSet(singletonSet(2))
  val s1 = singletonSet(1)
  val s2 = singletonSet(2)
  val s3 = singletonSet(3)
  val u1 = union(s1, s2)
  val u2 = union(s3, u1)
  printSet(u2) //{1,2,3}
  printSet(map(u2, x => x * x)) //print {1,4,9}
  println(forall(u2, x => x == 4))
  println(forall(u2, x => x == 1))
  println(forall(u2, x => x == 1 || x==2 || x==3))
  println(exists(u2, x => x == 1))
  printSet(filter(u2, x => x % 2 != 0))

}
