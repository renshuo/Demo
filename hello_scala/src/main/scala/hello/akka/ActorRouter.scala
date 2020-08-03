package hello.akka


import akka.actor.ActorSystem
import akka.routing.ActorRefRoutee
import akka.routing.Router
import akka.routing.RoundRobinRoutingLogic

object ActorRouter {

  def main(args: Array[String]): Unit = {
    val ac = ActorSystem.create("app")

  }
}
