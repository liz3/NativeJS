package de.liz3.nativejs;

import de.liz3.nativejs.gui.GuiManager;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            GuiManager.start();
            return;
        }

        new NativeProcess(new File(args[0]), args);
    }
}
