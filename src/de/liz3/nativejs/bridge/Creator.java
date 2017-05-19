package de.liz3.nativejs.bridge;

import javax.script.ScriptEngine;

/**
 * Created by liz3 on 18.05.17.
 */
public class Creator {

    public static void createBinding(ScriptEngine engine) {
        engine.put("nat", new Native(engine));
    }


}
