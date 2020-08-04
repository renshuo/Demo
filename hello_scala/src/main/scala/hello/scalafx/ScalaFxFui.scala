package hello.scalafx

import scalafx.application.JFXApp
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Menu, MenuBar, MenuItem, ToolBar}
import scalafx.scene.layout.{AnchorPane, BorderPane}
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

object ScalaFxFui extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title = "hello world"
    width = 800
    height = 600
    scene = new Scene {

      content = new BorderPane {
        top = new MenuBar {
          menus = Seq(
            new Menu("file(f)") {
              items = Seq(
                new Menu("save"){
                  items = Seq(
                    new MenuItem("def1"){},
                    new MenuItem("def2"){}
                  )
                },
                new MenuItem("abc1") {},
                new MenuItem("abc2") {}
              )
            },
          )


        }
        center = new AnchorPane {
          children = Seq(
            new Button("press1"){

            },
            new Button("press2")
          )
        }
//        x = 25
//        y = 30
//        width = 100
//        height = 100
//        fill <== when(hover) choose Green otherwise Red
      }
    }
  }

}
