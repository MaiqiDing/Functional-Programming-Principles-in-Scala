abstract class Base {
  def foo = 1
  def bar: Int
}


class Sub extends  Base {
  override def foo = 2
  def bar = 3
}

println("Scala worksheet")
val s = new Sub
println(s.bar)
println(s.foo)
