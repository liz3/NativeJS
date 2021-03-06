# What is NativeJS?
NativeJS is a binding System between Javascript and Java.
Its primary made to allow easy java execution in javascript.

# Benefits
One big benefit is that simplify a lot of boilerplate code, no compilation required.
You can load Jar Files and even make simple Gui Apps with Swing/JavaFX(Limited to one Stage)

# Examples
1. Print to console: ``` println(message); ```

2. How to make a action async: ``` async(function(){
/* Code goes here */}); ```

3. Create instance of any java class ``` var instance = createInstance("full.class.path", args);```

4. Load java library: To include a java library you provide two arguments, the first is a name, which will be needed on a invoke, 
trough NativeJS is using Reflection, the second is the path to the file:
``` nat.loadJNative("LoaderName", "Path/To/jar"); ```

5. require another file to include,
Now this is infact a little more complex, you need 3 or 4 parameters, just as nodejs, NativeJS
will include the exports object from the file to the own file, the first argument is the path to the file, the second parameter is a boolean, if set,
NativeJS will load the File Async, the third argument, is also a boolean, if this is set to true,
you will need a fourth argument of type string, this will provide the own exports to the other file in a variable, which name is defined in this fourth argument.
Example: ``` var inc = require("/Users/liz3/IdeaProjects/NativeJS/tests/IncludeOne.js", false, true, "parent"); ```

6. setInterval and setTimeout, work exact the same way as in normal javascript

7. get class from a imported library: To get a intance of a class imported by library(.jar file),at first argument you provide the classpath,
the second is the loadername this is the first argument passed at example 4, the third is a boolean and defines, if the class should be initialized,
the fourth are the constructor parameter instances in form of an array, the fifth are the types of the arguments, but no worries, NativeJS has a method that makes that for you,
Example:``` var string =  nat.typeFromLib("java.lang.String", "loadername", false, ["ExampleString"], classType("java.lang.String")); ```

8. static invoke from a imported library: To invoke a imported static method by library(.jar file),at first argument you provide the classpath,
the second is the methodName, the third argument is the loader name, this is the first argument passed at example 4,
the fourth are the constructor parameter instances in form of an array, the fifth are the types of the arguments, but no worries, NativeJS has a method that makes that for you,
Example:``` var parsed =  nat.staticLibInvoke("java.lang.Integer", "parseInt", "loaderName", [25], classType("int")); ```

9. To sleep just do ``` nat.sleep(1000);```

10. To exit use:  ``` nat.exit(0);```

11. How to make a Invokable: ``` invokable(function(){
/* Code goes hier */})```

12. JavaFX, to use javaFX use simply call ``` fxInit(function(stage){ /* Code goes hier */}) ```, this will call the function with the stage as parameter.
                                          
13. Socket, for a Socket server use: ``` var server = serverSocket(8080); ```,for a Socket client use: ``` var client = socket("127.0.0.1", 8080); ```, to write to a socket use: ``` socketMessage(socket, "Message"); ```   
 
14. To read a Stream fully use ``` var content = readUntilNull(stream); ```
          
15. for http get use ``` var content = httpGet("google.de", [{key: "Content-type", value: "text/plain"}]) ```, the content looks like: ``` {body: text, responseCode: conn.getResponseCode(), responseHeaders: conn.getHeaderFields()}```

16. for http post use ``` var content = httpPost("google.de", body, [{key: "Content-type", value: "text/plain"}]) ```, the content looks like: ``` {body: text, responseCode: conn.getResponseCode(), responseHeaders: conn.getHeaderFields()}```

#Notice
Sadly NativeJS cannot use the Object.prototype function, because the engine does not support that, so if you use Typescript or similar, 
do not use get and set!

# Want to use ES6?
No problem, just use the --es6 argument on execution, this will transpile the script to es5

If you are using Multple files use the program in this way: ``` java -jar nativejs.jar --es6 .``` this will use all files in directory(recursive), if you have files, 
that should not be compiled, add ``` -strict ``` that will exclude every file that does not has the file extension .js
