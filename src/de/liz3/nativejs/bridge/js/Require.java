package de.liz3.nativejs.bridge.js;

import de.liz3.nativejs.NativeProcess;
import de.liz3.nativejs.bridge.Creator;
import jdk.nashorn.api.scripting.AbstractJSObject;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Require extends AbstractJSObject {

    private ScriptEngine engine;
    private NativeProcess process;
    private String[] startArgs;

    public Require(ScriptEngine engine, NativeProcess process, String[] startArgs) {
        this.engine = engine;
        this.process = process;
        this.startArgs = startArgs;
    }

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {

        ScriptEngine newEngine = new ScriptEngineManager().getEngineByName("nashorn");
        Creator.createBinding(newEngine, process, startArgs);

        boolean nativeAsnc = (Boolean) args[1];
        boolean includeOwnExports = (Boolean) args[2];

        File file = new File((String) args[0]);
        if (nativeAsnc) {
            Thread worker = new Thread(() -> {
                if (includeOwnExports) newEngine.put((String) args[3], engine.get("exports"));
                try {
                    if (process.isEs6()) {
                        newEngine.eval(process.getTranspiled().get(file.getAbsolutePath()));
                    } else {
                        newEngine.eval(new FileReader(file));

                    }
                } catch (ScriptException | FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            worker.setName("Require Worker for: " + file.getAbsolutePath());
            worker.start();
            return null;
        }
        if (includeOwnExports) newEngine.put((String) args[3], engine.get("exports"));
        try {
            if (process.isEs6()) {
                newEngine.eval(process.getTranspiled().get(file.getAbsolutePath()));
            } else {
                newEngine.eval(new FileReader(file));

            }

            return newEngine.get("exports");
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
