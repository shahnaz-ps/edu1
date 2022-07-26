package controller;

import controller.ProgramController;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ProfessorModePage {
    @FXML
    private VBox infoVbox;
    
    public void initialize(){
        Text temp = new Text("salam");
        infoVbox.getChildren().add(temp);
    }

    public void submit(MouseEvent mouseEvent) {
    }

    public void welcomePage(MouseEvent mouseEvent) {
        ProgramController.changeMenu("welcome");
    }

    public void godMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("godMode");
    }

    public void studentMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("studentMode");
    }

    public void departmentMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("departmentMode");
    }
}
