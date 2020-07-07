import breeze.signal.OptWindowFunction.User

object HelloWorld {
  def main(args: Array[String]): Unit = {
    val data = (1, 2, 3)
    data match {
      case (1, x, _) => println(x)
      case _ => println("get none")
    }
    val u =new User1("", 1)
    u match {
      case User1(name, age) => println(s"get user name: $name")
    }
  }
}

case class User1(name: String, age: Int)