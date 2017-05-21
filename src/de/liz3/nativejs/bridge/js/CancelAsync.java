package de.liz3.nativejs.bridge.js;

import de.liz3.nativejs.NativeProcess;
import jdk.nashorn.api.scripting.AbstractJSObject;

public class CancelAsync extends AbstractJSObject {

    private NativeProcess process;

    public CancelAsync(NativeProcess process) {
        this.process = process;
    }

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {
        int id = (Integer) args[0];
        if (!process.getAsyncOps().containsKey(id)) return null;
        process.getAsyncOps().get(id).interrupt();
        process.getAsyncOps().remove(id);
        return null;
    }
}
