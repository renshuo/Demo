import akka.actor.typed.{ActorRef, ActorSystem, Behavior, PostStop, Signal}
import akka.actor.typed.scaladsl.AbstractBehavior
import akka.actor.typed.scaladsl.ActorContext
import akka.actor.typed.scaladsl.Behaviors

@main def main(): Unit = {
  println("in main")
  val sys = ActorSystem[String](MonitorActor(), "monitor")
  sys.tell("a")
}


object MonitorActor {
  def apply(): Behavior[String] = {
    println("in monitor apploy")
    Behaviors.setup[String](context => new MonitorActor(context))
  }
}

class MonitorActor(context: ActorContext[String]) extends AbstractBehavior[String](context) {

  val name = "monitor actor"
  context.log.info("IoT Application started")

  val dev1 = context.spawn(Device(), "dev1")

  override def onMessage(msg: String): Behavior[String] =
    println(s"monitor get a msg: $msg")
    dev1 ! DeviceCommand("test", context.self)
    Behaviors.unhandled[String]
  end onMessage

}

case class DeviceCommand(name: String, father: ActorRef[String])

object Device {
  def apply(): Behavior[DeviceCommand] = {
    Behaviors.setup(context => new Device(context).counter())
  }
}
class Device(context: ActorContext[DeviceCommand]) {

  def counter(): Behavior[DeviceCommand] =
    Behaviors.receive { (ctx, msg) =>
      println(s"get dev msg: ${ctx.self} $msg")
      Behaviors.same
    }

}
