package de.liz3.nativejs.bridge;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Importer {

    public boolean loadJNative(String name, String path) {

        try {
            Loader.loaders.put(name, new URLClassLoader(new URL[]{new File(path).toURI().toURL()}));

            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void println(String msg) {
        System.out.println(msg);
    }
}
