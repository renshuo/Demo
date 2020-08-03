package hello.akka

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import akka.actor.{Actor, ActorRef, ActorSystem, Props, Terminated}


object AkkaScheduler {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem.create()
    val app = actorSystem.actorOf(Props[SchedulerTe].withDispatcher("writer-dispatcher"), "app")
    app.tell("test",  ActorRef.noSender)

    (0 to 30).map({ i =>
      actorSystem.actorOf(Props[SchedulerTe].withDispatcher("writer-dispatcher"), s"app$i")
    }).map { act =>
      act ! ("a", ActorRef.noSender)
    }


  }
}


class SchedulerTe extends Actor{

  override def receive: Receive = {
    case _ => {
      println(s"in akka instance ${self.path}")
      Thread.sleep(1000)

    }
  }
}
