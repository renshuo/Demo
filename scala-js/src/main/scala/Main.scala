import org.scalajs.dom
import org.scalajs.dom.document
import scala.scalajs.js.annotation.JSExportTopLevel




object Main {

  def main(args: Array[String]): Unit = {
    println("hello world.")
    appendPar(document.body, "hello world")
    addBtn()
  }

  def addClickMsg(): Unit = {
    appendPar(document.body, "clicked")
  }
  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    parNode.textContent = text
    targetNode.appendChild(parNode)
  }

  def addBtn(): Unit = {
    val btn = document.createElement("button")
    btn.textContent="clickit"
    btn.addEventListener("click", { (e: dom.MouseEvent) =>
      addClickMsg()
    })
    document.body.appendChild(btn)
  }
}
