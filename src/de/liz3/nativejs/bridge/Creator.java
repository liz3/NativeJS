package de.liz3.nativejs.bridge;

import de.liz3.nativejs.NativeProcess;
import de.liz3.nativejs.bridge.js.*;
import de.liz3.nativejs.bridge.jxfx.FXInvoke;

import javax.script.ScriptEngine;

public class Creator {

    public static void createBinding(ScriptEngine engine, NativeProcess process, String[] startArgs) {


        engine.put("nat", new Native(engine, startArgs));
        engine.put("setInterval", new SetInterval(process));
        engine.put("clearInterval", new ClearInterval(process));
        engine.put("setTimeout", new SetTimeout(process));
        engine.put("clearTimeout", new ClearTimeout(process));
        engine.put("println", new PrintLn());
        engine.put("requireNative", new Require(engine));
        engine.put("async", new Async(process));
        engine.put("clearAsync", new CancelAsync(process));
        engine.put("invokable", new Invokable());
        engine.put("fxInit", new FxRun());
     //   engine.put("fxInvoke", new FXInvoke());
    }
}

