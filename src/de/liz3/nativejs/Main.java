package de.liz3.nativejs;

import de.liz3.nativejs.gui.GuiManager;

import java.io.BufferedReader;
import java.io.File;

/**
 * Created by liz3 on 18.05.17.
 */
public class Main {

    public static void main(String[] args) {
        if(args.length < 1) {
            GuiManager.start();
            return;
        }

        new NativeProcess(new File(args[0]));
    }
}
