package de.liz3.nativejs.bridge.js;

import de.liz3.nativejs.NativeProcess;
import jdk.nashorn.api.scripting.AbstractJSObject;

public class ClearInterval extends AbstractJSObject {

    private NativeProcess process;

    public ClearInterval(NativeProcess process) {
        this.process = process;
    }

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {
        Integer id = (Integer) args[0];
        Thread x = process.getIntervals().get(id);
        x.interrupt();
        process.getIntervals().remove(id);

        return true;
    }

}
