package de.liz3.nativejs.bridge;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

public class Native {

    private ScriptEngine engine;
    private String[] startArgs;

    Native(ScriptEngine engine, String[] startArgs) {
        this.engine = engine;
        this.startArgs = startArgs;
    }

    public void exit(int code) {
        System.exit(code);
    }

    public String jproperty(String name) {
        return System.getProperty(name);
    }

    public Object file(String path) {

        return new File(path);
    }

    public String[] startArgs() {
        return startArgs;
    }

    public HashMap<Object, Object> jmap() {
        return new HashMap<>();
    }

    public Object jruntime() {

        return Runtime.getRuntime();
    }

    public String cdir() {
        try {
            return new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Object instream() {
        return System.in;
    }

    public Object outstream() {
        return System.out;
    }

    public Object typeFromLib(String fullName, String loader, boolean init, Object[] vals, Class<?>... params) {


        try {

            Constructor constructor = null;

            if (loader != null) {
                constructor = Class.forName(fullName, init, Loader.loaders.get(loader)).getConstructor(params);
            } else {
                constructor = Class.forName(fullName).getConstructor(params);
            }

            try {
                System.out.println("args: " + params);
                return constructor.newInstance(vals);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object staticLibInvoke(String fullName, String methodName, String loader, Object[] args, Class<?>... params) {

        Class cl = null;
        if (loader == null) {
            try {
                cl = Class.forName(fullName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                cl = Class.forName(fullName, false, Loader.loaders.get(loader));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        Method method = null;
        try {
            method = cl.getDeclaredMethod(methodName, params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            return method.invoke(null, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object type(String fullname) throws NoSuchMethodException {
        try {
            return engine.eval("Java.type(\"" + fullname + "\")");
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean loadJNative(String name, String path) {
        try {

            Loader.loaders.put(name, new URLClassLoader(new URL[]{new File(path).toURI().toURL()}));

            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
