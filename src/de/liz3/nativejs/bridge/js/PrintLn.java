package de.liz3.nativejs.bridge.js;

import jdk.nashorn.api.scripting.AbstractJSObject;

/**
 * Created by liz3 on 19.05.17.
 */
public class PrintLn extends AbstractJSObject{


    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {
        System.out.println((String)args[0]);
        return null;
    }
}
