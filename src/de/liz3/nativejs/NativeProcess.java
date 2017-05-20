package de.liz3.nativejs;

import de.liz3.nativejs.bridge.Creator;
import de.liz3.nativejs.bridge.Importer;

import javax.script.*;
import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NativeProcess {

    private ScriptEngine engine;
    private File file;
    public HashMap<Integer, Thread> intervals;
    public HashMap<Integer, Thread> timeOuts;

    public NativeProcess(File f) {
            this.file = f;
            intervals = new HashMap<>();
            timeOuts = new HashMap<>();
            bootstrap();
    }
    private void bootstrap() {
        if(!file.exists()) return;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String all = "";
        try {
            String line;
            while ((line = reader.readLine()) != null) { all += line; }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScriptEngine importerEng = new ScriptEngineManager().getEngineByName("nashorn");
        importerEng.put("nat", new Importer());
        Pattern p = Pattern.compile("pre\\s*\\(\\s*function\\s*\\(\\s*\\)\\s*\\{.*}\\s*\\)\\s*;", Pattern.MULTILINE | Pattern.DOTALL);

        Matcher matcher = p.matcher(all);

        while (matcher.find()) {
            String f = matcher.group();
            all = all.replace(f, "");
            while (!f.startsWith("{")) {f = f.substring(1);}
            f = f.substring(1);
            while (!f.endsWith("}")) {f = f.substring(0, f.length() - 1);}
            f = f.substring(0, f.length() - 1);
            try {
                importerEng.eval(f);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            break;
        }
        engine = new ScriptEngineManager().getEngineByName("nashorn");
        Creator.createBinding(engine, this);
        try {
            engine.eval(all);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Thread> getIntervals() {
        return intervals;
    }

    public HashMap<Integer, Thread> getTimouts() {
        return timeOuts;
    }
}
