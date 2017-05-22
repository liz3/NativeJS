/**
 * //var conn = httpPost("http://www.whoishostingthis.com/tools/user-agent/", "y=10", [{key: "User-agent", value: "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36"}]);
 //println(conn.body);
 */
nat.loadJNative("image4j", "/Users/liz3/Downloads/image4j-core-0.7.1/image4j.jar");
nat.loadJNative("jutils", "/Users/liz3/Downloads/JUtils.jar");
requireNative("/Users/liz3/Documents/IntelliJProjects/NativeJS/NativeJSLib/Std-NativeJSLib.js", false);
async(function () {
    fxInit(function (mainStage) {
        createApp(mainStage);
    });

});


function createApp(mainStage) {
    println("Created");

    mainStage.setTitle("Test App");
    var pane = createInstance("javafx.scene.layout.BorderPane", null);
    var area = createInstance("javafx.scene.control.TextArea", null);
    mainStage.setOnCloseRequest(function () {

    });
    pane.setCenter(area);
    mainStage.setScene(createInstance("javafx.scene.Scene", pane, 800, 500));

    mainStage.centerOnScreen();

    mainStage.show();

}

println(nat.startArgs()[0]);
/*var thread = createInstance("java.lang.Thread", invokable(function () {
 setInterval(function () {
 println("Cool!");
 }, 2000);
 }));

 thread.start();
 fxInvoke(function () {
 createApp(createInstance("javafx.stage.Stage", null));

 });
 */

/*setTimeout(function () {
   fxInvoke();
}, 3000); */

function simpleWindow() {
    var frame = nativeFrame("Test Frame");
    var label = nativeLabel("Was geht junge?");
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
        if (line == "stop") {
            clearInterval(id)

        }


    }, 20);
}
