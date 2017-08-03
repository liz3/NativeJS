package de.liz3.nativejs.v8

import com.eclipsesource.v8.V8
import com.eclipsesource.v8.V8Object
import org.binaryone.jutils.io.IOUtils

/**
 * Created by liz3 on 01.08.17.
 */
class BabelCompiler {



    @JvmOverloads
    fun parse(content:String, release:Boolean = false,  v8:V8 = V8.createV8Runtime()) : Any {

    try {
        val babel = IOUtils.convertStreamToString(BabelCompiler::class.java.getResourceAsStream("/babel.min.js"))

        v8.add("input", content)
        v8.executeScript(babel)

        v8.executeScript("var output = Babel.transform(input, { presets: ['es2015'] }).code;")
    }catch (e:Exception) {
        e.printStackTrace()
    }

        val output = v8.get("output")

       if(release)  {
           v8.release()
       } else{
          v8.executeVoidScript("input = null; output = null;")
       }
        return output
    }
}