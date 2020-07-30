package sample;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Random;

public class Controller {

    @FXML
    private TableColumn<Answer, String> ki;

    @FXML
    private TableColumn<Answer, String> yi;

    @FXML
    private TableView table;


    @FXML
    private TextField a;

    @FXML
    private TextField b;

    private ObservableList<Answer> answers = FXCollections.observableArrayList();

    public Controller() {


    }


    public void generateNums() {
        Random r = new Random();
        if (answers.size() > 0) {
            answers.clear();
        }
        for (int i = 0; i < 10; i++) {
            answers.add(new Answer(r.nextFloat(), 0f));
        }
        ki.setCellValueFactory(celldata -> celldata.getValue().kiProperty().asString());
        table.setItems(answers);


    }

    public void execute() {

        float a = Float.parseFloat(this.a.getText());
        float b = Float.parseFloat(this.b.getText());

        for (int i = 0; i < 10; i++) {
            Answer ans = answers.get(i);
            float result = (float) Math.sqrt(Math.pow(Math.cos(ans.getKi()), 2) / ((Math.pow(a, 2) + Math.pow(b, 2)) - Math.sin(ans.getKi())));
            ans.setYi(result);
        }
        yi.setCellValueFactory(celldata -> celldata.getValue().yiProperty().asString());
        table.setItems(answers);
        table.refresh();
    }

    public void clear() {
        a.setText("");
        b.setText("");
        answers.clear();
        table.refresh();
    }


    public void exit() {
        System.exit(0);
    }
}


class Answer {
    FloatProperty ki;
    FloatProperty yi;


    public Answer(Float ki, Float yi) {
        this.ki = new SimpleFloatProperty(ki);
        this.yi = new SimpleFloatProperty(yi);
    }

    public Answer(FloatProperty ki, FloatProperty yi) {
        this.ki = ki;
        this.yi = yi;
    }

    public float getKi() {
        return ki.get();
    }

    public FloatProperty kiProperty() {
        return ki;
    }

    public void setKi(float ki) {
        this.ki.set(ki);
    }

    public float getYi() {
        return yi.get();
    }

    public FloatProperty yiProperty() {
        return yi;
    }

    public void setYi(float yi) {
        this.yi.set(yi);
    }
}




