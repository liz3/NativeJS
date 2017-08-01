package de.liz3.nativejs;

import de.liz3.nativejs.bridge.Creator;
import de.liz3.nativejs.bridge.Importer;
import de.liz3.nativejs.v8.BabelCompiler;
import org.binaryone.jutils.io.FileUtils;

import javax.script.*;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NativeProcess {

    private File file;
    private HashMap<Integer, Thread> intervals;
    private HashMap<Integer, Thread> timeOuts;
    private HashMap<Integer, Thread> asyncOps;

    public NativeProcess(File f, String[] startArgs) {
        System.out.println("initializing process");
        this.file = f;
        intervals = new HashMap<>();
        asyncOps = new HashMap<>();
        timeOuts = new HashMap<>();
        System.out.println("bootstrapping process");
        bootstrap(startArgs);
    }

    public void killProcess() {

        for (Thread x : intervals.values()) {
            x.interrupt();
        }
        for (Thread x : asyncOps.values()) {
            x.interrupt();
        }
        for (Thread x : timeOuts.values()) {
            x.interrupt();
        }
    }

    private void bootstrap(String[] startArgs) {
        if (!file.exists()) return;

        String content = FileUtils.readFile(file);

        for (String arg : startArgs) {
            if (arg.equalsIgnoreCase("--es6")) {
                System.out.println("Starting Babel Transpiler....");
                content = new BabelCompiler().parse(content).toString();
                System.out.println("ES6 has been transpiled to ES5");

            }
        }

        System.out.println("Starting engine....");
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        System.out.println("Creating bindings and loading Standard Library");
        Creator.createBinding(engine, this, startArgs);
        try {
            System.out.println("=========Start of Script:");
            engine.eval(content);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Thread> getIntervals() {
        return intervals;
    }

    public HashMap<Integer, Thread> getTimeOuts() {
        return timeOuts;
    }

    public HashMap<Integer, Thread> getAsyncOps() {
        return asyncOps;
    }
}
