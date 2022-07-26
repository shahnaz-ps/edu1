package controller;

import javafx.application.Platform;
import javafx.scene.input.MouseEvent;

public class WelcomePage {
    public void godMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("godMode");
    }

    public void studentMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("studentMode");
    }

    public void ProfessorMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("professorMode");
    }

    public void departmentMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("departmentMoode");
    }

    public void exit(MouseEvent mouseEvent) {
        Platform.exit();
    }
}
