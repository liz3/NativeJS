package de.liz3.nativejs.bridge.js;

import jdk.nashorn.api.scripting.AbstractJSObject;

public class PrintLn extends AbstractJSObject {

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {
        System.out.println( args[0].toString());
        return null;
    }
}
