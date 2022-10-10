package hx


import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout._
import scalafx.scene.control._
import scalafx.scene.paint.Color._
import scalafx.scene.paint._
import scalafx.scene.text.Text
import javafx.scene.input.{KeyCode, KeyEvent}


extension[T] (s: Seq[T]) {
  def allDo(f: T => Unit): Seq[T] = {
    s.map(f)
    s
  }
}

/**
 *
 */
object FxTest extends JFXApp3 {

  override def start(): Unit = {

    val menu = new MenuBar {
      useSystemMenuBar = true
      menus = Seq(
        new Menu("File") {
          items = Seq(
            new MenuItem("Open") {},
            new SeparatorMenuItem(),
            new MenuItem("exit") {
              onAction = { e => System.exit(0) }
              // click =  e:Event => exit(0)
            })
        },
        new Menu("编辑") {
          items = List(
            new MenuItem("copy") {},
            new MenuItem("paste"),
            new MenuItem("cut") {},
            new MenuItem("delete") {}
          )
        }
      )
    }

    val toolbar = new ToolBar() {
      items = Seq(
        new Button("tool1") {},
        new Button("tool2") {},
      )
    }

    val leftNav = new VBox {
      children = Seq(
        new Button("abc") {
          style = "-fx-base: red"
        },
        new Button("abc") {
          style = "-fx-base: green"
        },
      ).allDo { b =>
        b.maxWidth = 300 //; btn.minWidth = 120; btn.maxHeight = 30
        b.minWidth = 120
      }
    }


    val b1 = new BorderPane {
      minWidth = 800
      minHeight = 600
      top = new VBox {
        children = Seq(menu, toolbar)
      }
      left = leftNav
      style = "-fx-font: normal bold 10pt 'Source Han Sans CN'"
      onKeyPressed = { e =>
        println(s"get key: ${e}")
        if e.getCode == KeyCode.ESCAPE then System.exit(0)
      }
      right = new HBox {
        children = Seq(
          new Text {
            text = "abc"
          }
        )
      }
      bottom = new HBox {
        children = Seq(
          new Label {
            text = "abc"
          }
        )
      }
    }

    val content1 = b1

    stage = new JFXApp3.PrimaryStage {
      title = "主界面"
      scene = new Scene {
        content = content1
      }
    }
  }
}
