pre(function () {
    /*
    This function will be executed before everything else and it will be started on a separated Native Engine
     */
});
nat.loadJNative("image4j", "/Users/liz3/Downloads/image4j-core-0.7.1/image4j.jar");
nat.loadJNative("jutils", "/Users/liz3/Downloads/JUtils.jar");
nat.require("/Users/liz3/Documents/IntelliJProjects/NativeJS/NativeJSLib/NativeJFrameLib.js", false);

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

    while (scanner.hasNextLine()) {
        println(scanner.nextLine());
    }
}
nat.println("Loaded!");
