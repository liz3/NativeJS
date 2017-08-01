package de.liz3.nativejs.bridge.js.type;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * Created by liz3 on 01.08.17.
 */
public class Object {

    public java.lang.Object assign(ScriptObjectMirror target, ScriptObjectMirror...sources) {

        for (ScriptObjectMirror x : sources) {
            target.setSlot(target.size() + 1, x);
        }

        return target;
    }

    public java.lang.Object defineProperty(ScriptObjectMirror obj, String x, ScriptObjectMirror props) {

        return true;
    }
}
