package de.liz3.nativejs

import java.io.File
import java.util.*

fun scanDir(folder: File, list:Vector<File>) {

    if(folder.isDirectory) {
        for(file in folder.listFiles()) {
            if(file.isDirectory) {
               scanDir(file, list)
                continue
            }
            list.addElement(file)

        }
    } else {
        list.addElement(folder)
    }
}

fun localDir() = File(".").canonicalPath