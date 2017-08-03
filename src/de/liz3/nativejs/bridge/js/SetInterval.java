package de.liz3.nativejs.bridge.js;

import de.liz3.nativejs.NativeProcess;
import jdk.nashorn.api.scripting.AbstractJSObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class  SetInterval extends AbstractJSObject {

    private NativeProcess process;

    public SetInterval(NativeProcess process) {
        this.process = process;
    }

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {
        ScriptObjectMirror mirror = (ScriptObjectMirror) args[0];
        Integer sleep = (Integer) args[1];
        int id = process.getIntervals().size() + 1;
        Thread x = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                mirror.call(thiz, args);

                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException ignored) {
                    break;
                }
            }
        });
        process.getIntervals().put(id, x);
        x.start();
        return id;
    }
}
