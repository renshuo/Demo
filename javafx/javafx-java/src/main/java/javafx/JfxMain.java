package javafx;

import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class JfxMain extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {

    ObservableList<TestData> data = FXCollections.observableArrayList(
            new TestData("a", 0d),
            new TestData("b", 0.5d),
            new TestData("c", 1d)
    );
    TableView<TestData> tv = new TableView<TestData>(data);

    TableColumn<TestData, String> invitedCol = new TableColumn<>();
    invitedCol.setText("name");
    invitedCol.setCellValueFactory(new PropertyValueFactory("name"));
    tv.getColumns().add(invitedCol);

    TableColumn<TestData, Double> gc = new TableColumn<>();
    gc.setCellValueFactory(new PropertyValueFactory("progres"));
    gc.setCellFactory(ProgressBarTableCell.forTableColumn());
    tv.getColumns().add(gc);

    Scene scene = new Scene(tv, 420, 420);
    scene.getStylesheets().add("Main.css");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
