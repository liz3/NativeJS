package de.liz3.nativejs.bridge.js;

import de.liz3.nativejs.NativeProcess;
import jdk.nashorn.api.scripting.AbstractJSObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptEngine;

/**
 * Created by liz3 on 19.05.17.
 */
public class SetTimeout extends AbstractJSObject {

    private NativeProcess process;
    public SetTimeout(NativeProcess process)  {
        this.process = process;
    }

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {
        ScriptObjectMirror mir = (ScriptObjectMirror)args[0];
        int id = process.getTimouts().size() + 1;
        int sleep = (Integer) args[1];
       Thread x = new Thread(() -> {
            try {
                Thread.sleep(sleep);
                mir.call(thiz, args);
            } catch (InterruptedException ignored) {

            }

        });
       process.getTimouts().put(id, x);
       x.start();

        return id;
    }
}
