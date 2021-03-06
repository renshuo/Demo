package hello.scalafx

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

object MyApp extends JFXApp {
    stage = new JFXApp.PrimaryStage {
        title.value = "hello world"
        width = 600
        height = 450
        scene = new Scene {
            fill = LightGreen
            content = new Rectangle {
                x = 25
                y = 30
                width = 100
                height = 100
                fill <== when(hover) choose Green otherwise Red
            }
        }
    }
}
