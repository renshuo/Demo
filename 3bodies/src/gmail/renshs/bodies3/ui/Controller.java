package gmail.renshs.bodies3.ui;

import gmail.renshs.bodies3.WorldLine;
import gmail.renshs.bodies3.model.BodyModel;
import gmail.renshs.bodies3.model.WorldModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.*;


public class Controller {

    @FXML
    public TextField Gtext;


    public TableView<BodyConfig> bodyTableView;

    public Pane pane;
    public TextField timeSpeed;

    WorldLine world;

    public void initialize(){
        System.out.println("init");
        BodyConfigTable bct = new BodyConfigTable(bodyTableView);

    }

    public void initWorld(){
        WorldModel wm = new WorldModel();
        wm.setPhyParams("G", Double.parseDouble(Gtext.getText()));
        wm.setTimeSpeed(Integer.parseInt(timeSpeed.getText()));
        for (BodyConfig bodyConfig : bodyTableView.getItems()) {
            wm.getBodies().add(new BodyModel(bodyConfig));
        }
        world = new WorldLine(pane, wm);
    }

    public void start(ActionEvent actionEvent) {
        world.start();
        System.out.println("timer start");
    }


    public void reset(ActionEvent actionEvent) {
        initWorld();
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

    public void save(ActionEvent actionEvent) {
        WorldModel world = new WorldModel();
        for (BodyConfig bodyConfig : bodyTableView.getItems()) {
            BodyModel bm = new BodyModel(bodyConfig);
            world.getBodies().add(bm);
        }

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("/home/"));
        File f = fc.showSaveDialog(null);
        {
            ObservableList<BodyConfig> items = bodyTableView.getItems();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                oos.writeObject(world);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void load(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("/home/"));
        File f= fc.showOpenDialog(null);
        if (f!=null){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                WorldModel world = (WorldModel) ois.readObject();
                for (BodyModel bodyModel : world.getBodies()) {
                    BodyConfig bc = bodyModel.getBodyConfig();
                    bodyTableView.getItems().add(bc);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
