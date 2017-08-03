package de.liz3.nativejs;

import com.eclipsesource.v8.V8;
import de.liz3.nativejs.bridge.Creator;
import de.liz3.nativejs.bridge.Importer;
import de.liz3.nativejs.v8.BabelCompiler;
import org.binaryone.jutils.io.FileUtils;

import javax.script.*;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NativeProcess {

    private boolean isEs6 = false;
    private boolean fromDir = false;
    private File file;
    private HashMap<Integer, Thread> intervals;
    private HashMap<Integer, Thread> timeOuts;
    private HashMap<Integer, Thread> asyncOps;
    private HashMap<String, String> transpiled;

    public NativeProcess(File f, String[] startArgs) {
        System.out.println("initializing process");
        this.file = f;
        intervals = new HashMap<>();
        asyncOps = new HashMap<>();
        timeOuts = new HashMap<>();
        transpiled = new HashMap<>();
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

        for (int i = 0; i != startArgs.length; i++) {
            String arg = startArgs[i];
            if (arg.equalsIgnoreCase("--es6")) {
                V8 engine = V8.createV8Runtime();
                BabelCompiler compiler = new BabelCompiler();
                isEs6 = true;
                if(i + 1 < startArgs.length && startArgs[i +1].equals(".")) {
                    fromDir = true;
                    boolean strict = i + 2 < startArgs.length && startArgs[i + 2].equals("-strict");
                    boolean print = i + 3 < startArgs.length && startArgs[i + 3].equals("-print");
                    System.out.println("Starting transpiling..");
                    Vector<File> files = new Vector<>();
                    UtilsKt.scanDir(file.getParentFile(), files);


                    for(File file : files) {


                        if(strict && !file.getName().endsWith(".js")) continue;

                        try {
                            System.out.println("Trying to Transpiling file: " + file.getAbsolutePath());
                            String out = (String) compiler.parse(FileUtils.readFile(file), false, engine);
                          // out = out.replace(".prototype", "");

                            transpiled.put(file.getAbsolutePath(), out);
                            if(print) System.out.println(out);

                            System.out.println("Succesfully transpiled " + file.getAbsolutePath());
                        }catch (Exception e) {
                            System.out.println("Transpile of " + file.getAbsolutePath() + " Failed caused by: " + e.getMessage());
                        }
                    }

                    continue;
                }


                System.out.println("Starting Babel Transpiler....");
                content = (String) compiler.parse(content, true, engine);

                System.out.println("ES6 has been transpiled to ES5");

            }
        }

        System.out.println("Starting engine....");
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        System.out.println("Creating bindings and loading Standard Library");
        Creator.createBinding(engine, this, startArgs);
        try {
            System.out.println("=========Start of Script:");

            if(fromDir) {
                engine.eval(transpiled.get(file.getAbsolutePath()));
            } else {
                engine.eval(content);
            }
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
    public HashMap<String, String> getTranspiled() {
        return transpiled;
    }

    public boolean isEs6() {
        return isEs6;
    }
}
