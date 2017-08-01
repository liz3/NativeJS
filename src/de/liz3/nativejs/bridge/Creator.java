package de.liz3.nativejs.bridge;

import de.liz3.nativejs.NativeProcess;
import de.liz3.nativejs.bridge.js.*;
import de.liz3.nativejs.bridge.js.type.Object;
import org.binaryone.jutils.io.IOUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;

public class Creator {

    public static void createBinding(ScriptEngine engine, NativeProcess process, String[] startArgs) {

        engine.put("Object", new Object());
        engine.put("nat", new Native(engine, startArgs));
        engine.put("setInterval", new SetInterval(process));
        engine.put("clearInterval", new ClearInterval(process));
        engine.put("setTimeout", new SetTimeout(process));
        engine.put("clearTimeout", new ClearTimeout(process));
        engine.put("println", new PrintLn());
        engine.put("require", new Require(engine, process, startArgs));
        engine.put("async", new Async(process));
        engine.put("clearAsync", new CancelAsync(process));
        engine.put("invokable", new Invokable());
        engine.put("fxInit", new FxRun());
        try {
            engine.eval(IOUtils.convertStreamToString(Creator.class.getResourceAsStream("/Stdlib.js")));
            engine.eval("var exports = {};");
        } catch (ScriptException | IOException e) {
            e.printStackTrace();
        }

        //   engine.put("fxInvoke", new FXInvoke());
    }
}

