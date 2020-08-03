package hello.akka

import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorRef, ActorSystem, Props, Terminated}
import akka.event.Logging
import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder
import sun.security.krb5.Config

import scala.concurrent.duration.FiniteDuration

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


      (0 to 10).toList.map {i =>
        context.actorOf(Props[YuanBao].withDispatcher("writer-dispatcher"), s"yuanbao_$i")
      }.map { act =>
        (0 to 5).foreach { i => act.tell("milk", self)}
      }
      val yb1: ActorRef = context.actorOf(Props[YuanBao], "yuanbao1")
      val yb2: ActorRef = context.actorOf(Props[YuanBao], "yuanbao2")
      yb1.tell("jlgl", self)
      yb2.tell("jlgl", self)

      val v = context.watch(yb2).tell("test", self)

      val yb = context.actorSelection("/user/yuanbao")
      yb.tell("milk", self)
      val a1 = context.actorSelection("yuanbao")
      val a1i = a1.resolveOne(FiniteDuration(1000, TimeUnit.SECONDS))

    }
    case "finished" => {
      println(s"${sender()} ")
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

  val log = Logging(context.system, this)

  case class GoToSchool(val date: LocalDateTime, p1name: String, p2Name: String)

  override def receive: Receive = {
    case "milk" => {
      println(s"${self.path} drink milk")
      Thread.sleep(1000)
      sender().tell("finished", self)
    }
    case "jlgl" => {
      println("做叽喱呱啦")
      Thread.sleep(3000)
      println("叽喱呱啦做完了")
      sender().tell("finished", self)
    }
    case e:GoToSchool => {
      log.debug("weak up")
      Thread.sleep(2000)
      log.debug("take school bag")
      Thread.sleep(1000)
      log.debug("I'm go to school now")
    }
    case _ => {
      println("any ok")
    }


  }
}