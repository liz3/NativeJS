package de.liz3.nativejs.bridge.js;

import de.liz3.nativejs.bridge.jxfx.FXIniter;
import javafx.application.Platform;
import jdk.nashorn.api.scripting.AbstractJSObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * Created by liz3 on 21.05.17.
 */
public class FxRun extends AbstractJSObject {

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {
        ScriptObjectMirror mirror = (ScriptObjectMirror) args[0];
        FXIniter.start(mirror, thiz);

        return null;
    }
}
