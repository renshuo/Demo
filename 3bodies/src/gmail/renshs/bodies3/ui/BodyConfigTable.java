package gmail.renshs.bodies3.ui;

import com.sun.javafx.geom.Vec3d;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point3D;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.DoubleStringConverter;

/**
 * Created by sren on 15-10-22.
 */
public class BodyConfigTable {

    ObservableList<BodyConfig> bodyList = FXCollections.observableArrayList();


    TableView<BodyConfig> tableView;

    public BodyConfigTable(TableView<BodyConfig> tableView){
        this.tableView = tableView;
        tableView.setEditable(true);
        init();
        tableView.setItems(bodyList);
    }

    StringConverter<Point3D> point3DStringConverter = new StringConverter<Point3D>() {
        @Override
        public String toString(Point3D object) {
            return object==null?null:""+object.getX()+","+object.getY()+","+object.getZ();
        }

        @Override
        public Point3D fromString(String string) {
            String[] strs = string.split(",");
            if (strs.length<3){
                return new Point3D(0,0,0);
            }
            double x = Double.valueOf(strs[0]);
            double y = Double.valueOf(strs[1]);
            double z = Double.valueOf(strs[2]);
            return new Point3D(x, y, z);
        };
    };

    StringConverter<Vec3d> vec3dStringConverter = new StringConverter<Vec3d>() {
        @Override
        public String toString(Vec3d object) {
            return object==null?null:""+object.x+","+object.y+","+object.z;
        }

        @Override
        public Vec3d fromString(String string) {
            String[] strs = string.split(",");
            if (strs.length<3){
                return new Vec3d(0,0,0);
            }
            double x = Double.valueOf(strs[0]);
            double y = Double.valueOf(strs[1]);
            double z = Double.valueOf(strs[2]);
            return new Vec3d(x, y, z);
        };

    };

    void init(){
        {
            TableColumn tc = new TableColumn();
            tc.setText("名称");
            tc.setMinWidth(70);
            tc.setCellValueFactory(new PropertyValueFactory("name"));
            tc.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
            tableView.getColumns().add(tc);
        }
        {
            TableColumn tc = new TableColumn();
            tc.setText("质量");
            tc.setCellValueFactory(new PropertyValueFactory("mass"));
            tc.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            tableView.getColumns().add(tc);
        }
        {
            TableColumn tc = new TableColumn();
            tc.setText("半径");
            tc.setCellValueFactory(new PropertyValueFactory("radius"));
            tc.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            tableView.getColumns().add(tc);
        }
        {
            TableColumn tc = new TableColumn();
            tc.setText("位置");
            tc.setMinWidth(100);
            tc.setCellValueFactory(new PropertyValueFactory("position"));
            tc.setCellFactory(TextFieldTableCell.forTableColumn(point3DStringConverter));
            tableView.getColumns().add(tc);
        }
        {
            TableColumn tc = new TableColumn();
            tc.setText("初始速度");
            tc.setMinWidth(100);
            tc.setCellValueFactory(new PropertyValueFactory("velocity"));
            tc.setCellFactory(TextFieldTableCell.forTableColumn(vec3dStringConverter));
            tableView.getColumns().add(tc);
        }
    }

//    public static Body createBody(Map<String, Object> bodyData){
//        String name = (String) bodyData.get("name");
//        Double mass = (Double) bodyData.get("mass");
//        Double radius = (Double) bodyData.get("radius");
//        Point3D posi = (Point3D) bodyData.get("position");
//        Body bd = new Body(mass, posi);
//        return bd;
//    }
//
//
//    public static Map<String, Object> createBodyConfig(String name, Double mass, Double radius, Point3D position, Vec3d velocity){
//
//        Map<String, Object> body = FXCollections.observableMap(new HashMap<String, Object>());
//        body.put("name", name);
//        body.put("mass", mass);
//        body.put("radius", radius);
//        body.put("position", position);
//        body.put("velocity", velocity);
//        return body;
//    }


    public ObservableList<BodyConfig> getBodyList() {
        return bodyList;
    }
}
