package de.liz3.nativejs;

import java.io.File;

/**
 * Created by liz3 on 18.05.17.
 */
public class Main {

    public static void main(String[] args) {

        if(args.length < 1) System.exit(1);

        new NativeProcess(new File(args[0]));
    }
}
