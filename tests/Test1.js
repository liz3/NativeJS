/**
 * //var conn = httpPost("http://www.whoishostingthis.com/tools/user-agent/", "y=10", [{key: "User-agent", value: "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36"}]);
 //println(conn.body);
 */
nat.loadJNative("image4j", "/Users/liz3/Downloads/image4j-core-0.7.1/image4j.jar");
nat.loadJNative("jutils", "/Users/liz3/Downloads/JUtils.jar");
requireNative("/Users/liz3/Documents/IntelliJProjects/NativeJS/NativeJSLib/Std-NativeJSLib.js", false);

setInterval(function () {
    println("Cool!");
}, 500);
function simpleWindow() {
    var frame = nativeFrame("Test");
    var label = nativeLabel("Test text");
    frame.getContentPane().add(label);
    frame.setSize(200, 100);
    frame.pack();
    frame.setVisible(true);
}
function cmdListener() {
    var scannerType = nat.type("java.util.Scanner");
    var scanner = new scannerType(nat.instream());

    var id = setInterval(function () {

        var line = scanner.nextLine();
        if(line == "stop") {
            clearInterval(id)

        }


    }, 20);
}
