package de.liz3.nativejs.bridge.js;

import de.liz3.nativejs.NativeProcess;
import jdk.nashorn.api.scripting.AbstractJSObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class Async extends AbstractJSObject {

    private NativeProcess process;

    public Async(NativeProcess process) {
        this.process = process;
    }


    @Override
    public boolean isFunction() {


        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {
        if (args.length != 1) return null;
        ScriptObjectMirror execute = (ScriptObjectMirror) args[0];
        Thread t = new Thread(() -> execute.call(thiz, (Object) null));
        int id = process.getAsyncOps().size() + 1;
        process.getAsyncOps().put(id, t);
        t.start();

        return id;
    }
}
