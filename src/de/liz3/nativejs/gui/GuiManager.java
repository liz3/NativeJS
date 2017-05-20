package de.liz3.nativejs.gui;

import de.liz3.nativejs.NativeProcess;
import de.liz3.nativejs.bridge.Native;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintStream;

/**
 * Created by liz3 on 20.05.17.
 */
public class GuiManager extends Application {

    private File chooserFile = null;

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

        });
        c.launchBtn.setOnAction(event -> {

            if (chooserFile == null) return;

            TextArea area = new TextArea();
            area.setEditable(false);
            Stage stage = new Stage();
            BorderPane pane = new BorderPane();
            pane.setCenter(area);
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.setTitle("NativeJS - " + chooserFile.getName());
            stage.show();
            System.setOut(new PrintStream(new AreaOutputStream(area)));
            new Thread(() -> new NativeProcess(chooserFile)).start();

        });
        primaryStage.setTitle("NativeJS Launcher");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
