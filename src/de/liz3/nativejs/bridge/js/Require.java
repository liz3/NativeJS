package de.liz3.nativejs.bridge.js;

import jdk.nashorn.api.scripting.AbstractJSObject;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by liz3 on 19.05.17.
 */
public class Require extends AbstractJSObject{

    private ScriptEngine engine;
    public Require(ScriptEngine engine) {
        this.engine = engine;
    }
    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {

        boolean nativeAsnc = (Boolean) args[1];
        File file = new File((String)args[0]);
        if(nativeAsnc)  {
            Thread worker = new Thread(() -> {
                try {
                    engine.eval(new FileReader(file));
                } catch (ScriptException | FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            worker.setName("Require Worker for: "+ file.getAbsolutePath());
            worker.start();
            return null;
        }
        try {
            engine.eval(new FileReader(file));
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
