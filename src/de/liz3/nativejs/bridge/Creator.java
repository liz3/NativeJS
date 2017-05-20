package de.liz3.nativejs.bridge;

import de.liz3.nativejs.NativeProcess;
import de.liz3.nativejs.bridge.js.*;

import javax.script.ScriptEngine;

/**
 * Created by liz3 on 18.05.17.
 */
public class Creator {

    public static void createBinding(ScriptEngine engine, NativeProcess process) {

        engine.put("nat", new Native(engine));
        engine.put("setInterval", new SetInterval(process));
        engine.put("clearInterval", new ClearInterval(process));
        engine.put("setTimeout", new SetTimeout(process));
        engine.put("clearTimeout", new ClearTimeout(process));
        engine.put("println", new PrintLn());
        engine.put("requireNative", new Require(engine));
    }
}

