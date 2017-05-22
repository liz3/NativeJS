package de.liz3.nativejs.gui;

import de.liz3.nativejs.NativeProcess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintStream;

public class GuiManager extends Application {

    private File chooserFile = null;
    private NativeProcess process = null;

    public static void start() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        FXMLLoader l = new FXMLLoader();
        Parent root = l.load(getClass().getResourceAsStream("/de/liz3/nativejs/gui/Gui.fxml"));
        Controller c = l.getController();
        c.chooseBtn.setOnAction(event -> {

            Stage fileChooserWindow = new Stage();
            FileChooser dirChooser = new FileChooser();
            dirChooser.setTitle("Choose path to File!");
            dirChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("NativeJS Files", "*.js")
            );
            chooserFile = dirChooser.showOpenDialog(fileChooserWindow);
            if (chooserFile != null) c.fileName.setText(chooserFile.getName());

        });
        c.launchBtn.setOnAction(event -> {
            if (chooserFile == null) return;
            TextArea area = new TextArea();
            area.setEditable(false);
            Stage stage = new Stage();
            BorderPane pane = new BorderPane();
            pane.setCenter(area);
            Scene scene = new Scene(pane);
            stage.setOnCloseRequest(event1 -> {
            });
            stage.setScene(scene);
            stage.setTitle("NativeJS - " + chooserFile.getName());
            stage.show();
            PrintStream stream = new PrintStream(new AreaOutputStream(area));
            System.setOut(stream);
            System.setErr(stream);
            new Thread(() -> process = new NativeProcess(chooserFile, c.startArgFields.getText().split(" "))).start();

        });
        primaryStage.setTitle("NativeJS Launcher");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
