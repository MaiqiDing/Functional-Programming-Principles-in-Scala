import scala.io.Source

/**
  * Assume you are given a dictionary words as a list of words.
  * Design a method translate such that
  *   translate(phoneNumber)
  * produces all phrases of words that can serve as mnemonic for the phone number
  *
  * example: The phone number "7225247386" should have the mnemonic "Scala is fun"
  * as one element of the set of solution phrase
  */
val in = Source.fromURL("https://www.epfl.ch/labs/lamp/wp-content/uploads/2019/01/linuxwords.txt")
//val in = Source.fromURL("https://www.mit.edu/~ecprice/wordlist.10000")
//val in = Source.fromURL("https://raw.githubusercontent.com/first20hours/google-10000-english/master/20k.txt")


val words = in.getLines.toList filter (word => word forall (chr => chr.isLetter)) //filter(word => word.length > 1)

val mnem = Map('2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
  '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")

/** Invert the mnem map to give a map from chars 'A' 'B' ... to digits '2' '3' */
val charCode: Map[Char, Char] =
  for ((digit, str) <- mnem; ltr <- str) yield ltr -> digit

/** Maps a word to the digit string it can represent, e.g. "Java" -> "5282" */
def wordCode(word: String): String =
  word.toUpperCase map charCode

//wordCode("Java")

/**
  * A map from digit strings to the words that represent them,
  * e.g. "5282" -> List("Java", "Kata", "Lava", ...)
  * Note: A missing number should map to the empty set, e.g. "1111" -> List()
  */
val wordsForNum: Map[String, Seq[String]] =
  words groupBy wordCode withDefaultValue Seq()

/** Return all ways to encode a number as a list of words */
def encode(number: String): Set[List[String]] =
  if (number.isEmpty) Set(List())
  else {
    for {
      split <- 1 to number.length
      word <- wordsForNum(number take split)
      rest <- encode(number drop split)
    } yield word :: rest
  }.toSet

 encode("2665489") // cool guy


def translate(number: String): Set[String] =
  encode(number) map (_ mkString " ")
println("trans="+translate("7363398687"))
println(translate("2665489"))
println(translate("4128976527"))
println(translate("7225247386"))
// Set(sack air fun,
// pack ah re to,
// pack bird to,
// Scala ire to,
// Scala is fun,
// rack ah re to,
// pack air fun,
// sack bird to,
// rack bird to,
// sack ah re to,
// rack air fun)