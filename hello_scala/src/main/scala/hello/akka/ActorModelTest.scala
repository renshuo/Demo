package hello.akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props, Terminated}

object ActorModelTest {

  def main(args: Array[String]): Unit = {
    println("hello world")

    val system = ActorSystem("test")

    val act1 = system.actorOf(Props[Liuwei], name = "liuwei")
    val act2 = system.actorOf(Props[YuanBao], name="yuanbao")

    act1.tell("test", act1)


  }
}

class Liuwei extends Actor {

  override def receive: Receive = {
    case "test" => {
      println(s" in liuwei act ${self} from $sender()")
      val yb = context.actorSelection("/user/yuanbao")
      val yb2: ActorRef = context.actorOf(Props[YuanBao], "yuanbao")
      yb.tell("milk", self)
      yb2.tell("milk", self)

      val v = context.watch(yb2).tell("test", self)

    }
    case "finished" => {
      println(s"${sender()} have drinked milk")
    }
    case e => {
      println(s"any mesg: $e")
    }
    case Terminated(yb) => {
      context
    }
    case Terminated(yb2) => {
      println(s"child $yb2 is term")
    }

  }
}


class YuanBao extends Actor {

  override def receive: Receive = {
    case "milk" => {
      println(s"${this} drink milk")
      Thread.sleep(1000)
      context.stop(self)
      sender().tell("finished", self)
    }
    case _ => {
      println("any ok")
    }


  }
}