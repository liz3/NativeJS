package de.liz3.nativejs

/**
 * Created by liz3 on 01.08.17.
 */
fun transpile(rawSource:String) : String {
    var target = ""
    var backtrace = ""
    var next = ' '
    var source = rawSource.trim()

    for(index in 0..source.length) {
        var current = source.toCharArray()[index]
        next = source.toCharArray()[index + 1]
    }



    return target
}