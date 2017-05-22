package de.liz3.nativejs.bridge.js;

import jdk.nashorn.api.scripting.AbstractJSObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * Created by liz3 on 21.05.17.
 */
public class Invokable extends AbstractJSObject{

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {
        ScriptObjectMirror caller = (ScriptObjectMirror)args[0];

        return (Runnable) () -> caller.call(thiz, null);
    }
}
