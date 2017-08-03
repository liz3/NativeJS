package de.liz3.nativejs.bridge.js;

import jdk.nashorn.api.scripting.AbstractJSObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class Executable extends AbstractJSObject{

   private ScriptObjectMirror caller;
   private Object target;

    public Executable(ScriptObjectMirror caller, Object target) {
        this.caller = caller;
        this.target = target;
    }

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {

        return caller.call(target, null);
    }
}
