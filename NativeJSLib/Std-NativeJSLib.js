function nativeFrame(name) {
    var type = nat.type("javax.swing.JFrame");
    var instance =  new type(name);
    return instance;
}
function nativeLabel(text) {
    var type = nat.type("javax.swing.JLabel");
    var instance = new type(text);

    return instance;

}
function serverSocket(port) {
    var serverSocketType = nat.type("java.net.ServerSocket");
    var serverInstance = new serverSocketType(port);
    return serverInstance;
}
function httpGet(target, headers) {
    var url = createInstance("java.net.URL", target);
    var conn;
    var urlStr = createInstance("java.lang.String", target);
    conn = url.openConnection();
    conn.setRequestMethod("GET");

    for(var i = 0; i != headers.length; i++) {
        var current = headers[i];
        conn.addRequestProperty(current.key, current.value);
    }
    var reader = createInstance("java.io.BufferedReader", createInstance("java.io.InputStreamReader", conn.getInputStream()));

    var text = "";
    do {
        var line = reader.readLine();
        println(line);
        if(line == null) break;
        text += line;
    }while (true);

    return {body: text, responseBody: conn.getResponseCode(), responseHeaders: conn.getHeaderFields()};
}
function httpPost(target, body, headers) {
    var url = createInstance("java.net.URL", target);
    var conn;
    var urlStr = createInstance("java.lang.String", target);
    conn = url.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    conn.setDoInput(true);
    for(var i = 0; i != headers.length; i++) {
        var current = headers[i];
        conn.addRequestProperty(current.key, current.value);
    }
    var bytes = body.getBytes();
    conn.getOutputStream().write(bytes);
    var reader = createInstance("java.io.BufferedReader", createInstance("java.io.InputStreamReader", conn.getInputStream()));

    var text = "";
    do {
        var line = reader.readLine();
        println(line);
        if(line == null) break;
        text += line;
    }while (true);

    return {body: text, responseBody: conn.getResponseCode(), responseHeaders: conn.getHeaderFields()};
}

function createInstance(address, args) {

    var type = nat.type(address);

    return new type(args);
}