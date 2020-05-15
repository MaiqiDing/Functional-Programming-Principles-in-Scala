import week03._

new Rational(1, 2)

//return type is `scala.Nothing`
def error(msg: String) = throw new Error(msg)

//error("error")

val x = null

val y:String = x

//val z: Int = null // error: type mismatch

val a = if(true) 1 else false //type: AnyVal
