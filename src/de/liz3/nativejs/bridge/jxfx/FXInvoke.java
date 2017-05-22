package de.liz3.nativejs.bridge.jxfx;

import javafx.application.Platform;
import javafx.concurrent.Task;
import jdk.nashorn.api.scripting.AbstractJSObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * Created by liz3 on 21.05.17.
 */
public class FXInvoke extends AbstractJSObject{

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {
        ScriptObjectMirror mirror = (ScriptObjectMirror) args[0];

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

               Platform.runLater(() -> mirror.call(thiz, null));
                return null;

            }
        };
        Thread x = new Thread(task);
        x.setDaemon(true);
        x.start();

        return null;
    }
}
