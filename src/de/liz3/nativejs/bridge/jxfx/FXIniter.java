package de.liz3.nativejs.bridge.jxfx;

import javafx.application.Application;
import javafx.stage.Stage;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * Created by liz3 on 21.05.17.
 */
public class FXIniter extends Application{

    private static ScriptObjectMirror mirror;
    public static Object thiz;

    public static void start(ScriptObjectMirror mirror, Object thiz) {
        FXIniter.mirror = mirror;
        FXIniter.thiz = thiz;
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        mirror.call(thiz, primaryStage);
    }
}
