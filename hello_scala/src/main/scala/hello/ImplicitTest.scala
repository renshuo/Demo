package hello

object ImplicitTest {

  /**
   * 通过 implicit class给 String类添加了一个 stringPrxt 方法，
   * @param v
   */
  implicit class StringUts(val v: String) {
    def stringPrxt(): String = {
      println(v)
      v
    }
  }
}



object TestImp {
  import ImplicitTest._

  def main(args: Array[String]): Unit = {
    "abc".stringPrxt().split("_")
  }

}
