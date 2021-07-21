import akka.actor.typed.{ActorRef, ActorSystem, Behavior, PostStop, Signal}
import akka.actor.typed.scaladsl.AbstractBehavior
import akka.actor.typed.scaladsl.ActorContext
import akka.actor.typed.scaladsl.Behaviors

@main def main(): Unit = {
  println("in main")
  val sys = ActorSystem[String](Behaviors.setup[String](context => new MonitorActor(context)), "monitor")
  sys.tell("a")
}


class MonitorActor(context: ActorContext[String]) extends AbstractBehavior[String](context) {

  val name = "monitor actor"
  context.log.info("IoT Application started")

  val dev1 = context.spawn(Device().counter(), "dev1")

  override def onMessage(msg: String): Behavior[String] =
    context.log.info(s"monitor get a msg: $msg")
    dev1 ! DeviceCommand("test", context.self)
    Behaviors.unhandled[String]
  end onMessage

}

case class DeviceCommand(name: String, father: ActorRef[String])

class Device() {

  def counter(): Behavior[DeviceCommand] =
    Behaviors.receive { (ctx, msg) =>
      ctx.log.info(s"device get a msg: ${ctx.self} $msg")
      Behaviors.same
    }

}
