package de.liz3.nativejs.bridge.js;

import de.liz3.nativejs.NativeProcess;
import jdk.nashorn.api.scripting.AbstractJSObject;

public class ClearTimeout extends AbstractJSObject {

    private NativeProcess process;

    public ClearTimeout(NativeProcess process) {
        this.process = process;
    }

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {
        Integer id = (Integer) args[0];
        Thread x = process.getTimeOuts().get(id);
        x.interrupt();
        process.getTimeOuts().remove(id);
        return true;
    }

}
