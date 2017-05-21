package de.liz3.nativejs;

import de.liz3.nativejs.bridge.Creator;
import de.liz3.nativejs.bridge.Importer;

import javax.script.*;
import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NativeProcess {

    private File file;
    private HashMap<Integer, Thread> intervals;
    private HashMap<Integer, Thread> timeOuts;
    private HashMap<Integer, Thread> asyncOps;

    public NativeProcess(File f, String[] startArgs) {
        this.file = f;
        intervals = new HashMap<>();
        asyncOps = new HashMap<>();
        timeOuts = new HashMap<>();
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
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder all = new StringBuilder();
        try {
            String line;
            while ((line = reader != null ? reader.readLine() : null) != null) {
                all.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScriptEngine importerEng = new ScriptEngineManager().getEngineByName("nashorn");
        importerEng.put("nat", new Importer());
        Pattern p = Pattern.compile("pre\\s*\\(\\s*function\\s*\\(\\s*\\)\\s*\\{.*}\\s*\\)\\s*;", Pattern.MULTILINE | Pattern.DOTALL);

        Matcher matcher = p.matcher(all.toString());
        String f = matcher.group();
        all = new StringBuilder(all.toString().replace(f, ""));
        while (!f.startsWith("{")) {
            f = f.substring(1);
        }
        f = f.substring(1);
        while (!f.endsWith("}")) {
            f = f.substring(0, f.length() - 1);
        }
        f = f.substring(0, f.length() - 1);
        try {
            importerEng.eval(f);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        Creator.createBinding(engine, this, startArgs);
        try {
            engine.eval(all.toString());
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
