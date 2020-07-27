package hello.akka

import akka.actor._
import akka.actor.typed.{ActorRef, ActorSystem, Behavior, scaladsl}
import akka.actor.typed.scaladsl.Behaviors
import hello.akka.Yuanbao.SayHello


object ActorTest {

  def main(args: Array[String]): Unit = {
    println("start actor: ")
    val yuanbao :ActorSystem[Yuanbao.SayHello] = ActorSystem(Yuanbao(), "AkkaTest")

    yuanbao ! SayHello("main")

  }
}

object Yuanbao {
  final case class SayHello(name: String)

  def apply(): Behavior[SayHello] = {
    Behaviors.setup { context =>
      val liuwei = context.spawn(Greeter(), "liuwei")
      Behaviors.receiveMessage{ message: SayHello =>
        val renshuo = context.spawn(GreeterBot(max = 3), message.name)
        println("begin send msg 1")
        liuwei ! Greeter.Milk(message.name, 1, renshuo)
        println("begin send msg 2")
        liuwei ! Greeter.Milk(message.name, 1, renshuo)
        println("begin send msg 3")

        Behaviors.same
      }
    }
  }
}


object Greeter {
  case class Milk(name: String, age: Int, replyTo: ActorRef[Cook])
  case class Cook(name: String, age: Int, from : ActorRef[Milk])

  def apply(): Behavior[Milk] = {
    Behaviors.receive { (context: scaladsl.ActorContext[Milk], message: Milk) =>
      context.log.info(s"liuwei get milk age=${message.age}", message.name)
      message.replyTo ! Cook(message.name, message.age+1, context.self)
      Behaviors.same
    }
  }
}

object GreeterBot {
  def apply(max: Int): Behavior[Greeter.Cook] = {
    bot(0, max)
  }


  def bot(greetingCounter: Int, max: Int): Behavior[Greeter.Cook] = {
    Behaviors.receive { (context, message: Greeter.Cook) =>

      if (message.age > max) {
        Behaviors.same
      } else {
        context.log.info(s"renshuo get message $message")
        message.from ! Greeter.Milk(message.name, message.age, context.self)
        Behaviors.same
      }
    }
  }
}