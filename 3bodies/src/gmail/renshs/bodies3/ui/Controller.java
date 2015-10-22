package gmail.renshs.bodies3.ui;

import gmail.renshs.bodies3.TheWorld;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class Controller {

    public TextField Gtext;


    public TableView<BodyConfig> bodyTableView;




    public Pane pane;

    TheWorld world;

    public void initialize(){
        System.out.println("init");
        BodyConfigTable  bodyConfigTable = new BodyConfigTable(bodyTableView);
        world = new TheWorld(pane, bodyTableView.getItems() );
    }

    public void start(ActionEvent actionEvent) {
        world.start();
        System.out.println("timer start");
    }


    public void reset(ActionEvent actionEvent) {
        System.out.println(bodyTableView.getItems().size());
        world.reset(bodyTableView.getItems(), Double.valueOf(Gtext.getText()));
    }


    public void addBody(ActionEvent actionEvent) {
        BodyConfig bodyConfig = new BodyConfig();
        bodyConfig.setName("name1");
        bodyConfig.setMass(1);
        bodyConfig.setRadius(1);
        bodyTableView.getItems().add(bodyConfig);
    }

    public void removeBody(ActionEvent actionEvent) {
        bodyTableView.getItems().remove(bodyTableView.getSelectionModel().getSelectedItem());
    }


    public void stop(ActionEvent actionEvent) {
        world.pause();
    }
}
