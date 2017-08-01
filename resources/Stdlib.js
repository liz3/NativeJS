var Symbol = {
    for: function (key) {
        return nat.checkForType(key);
    }
};
var WeakMap = nat.type("de.liz3.nativejs.bridge.js.collections.WeakMap");

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

/**
 * This function reads a stream until the read() method returns -1
 * @param  stream InputStream
 * @returns {string}
 */
function readUntilNull(stream) {
    var all = "";
    do {
        var line = stream.read();
        if(line == -1) break;
        all += nat.byteToChar(line);
    }while (true);
    return all;
}
/**
 * This function send a message to Socket
 * @param socket Socket
 * @param msg string
 */
function socketMessage(socket, msg) {
    socket.getOutputStream().write(msg.getBytes());
    socket.getOutputStream().flush();
}
/**
 * Creates a server Socket
 * @param port int
 */
function serverSocket(port) {
    var serverSocketType = nat.type("java.net.ServerSocket");
    var serverInstance = new serverSocketType(port);
    return serverInstance;
}
/**
 * Creates a Socket.
 * @param address string
 * @param port int
 */
function socket(address, port) {

    var con = nat.type("java.net.Socket");

    return new con(address, port);
}
/**
 * This function will make a http get request to the target url and return the response
 * @param target string
 * @param headers {{key: string, value: string}}
 * @returns {{body: string, responseBody: *, responseHeaders: *}}
 */
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
    var line;
    do {
         line = reader.readLine();
        println(line);
        if(line == null) break;
        text += line;
    }while (true);

    return {body: text, responseBody: conn.getResponseCode(), responseHeaders: conn.getHeaderFields()};
}
/**
 * This function will make a native http post request and return the result
 * @param target string
 * @param body string
 * @param headers {{key: string, value: string}}
 * @returns {{body: string, responseBody: *, responseHeaders: *}}
 */
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

    return {body: text, responseCode: conn.getResponseCode(), responseHeaders: conn.getHeaderFields()};
}
/**
 * This function is made to create a new instance of a java object,
 * the first argument should be the full classpath of the url, the second are the constructor arguments
 * @param address string
 * @param args varargs... any
 */
function createInstance(address, args) {
    var type = nat.type(address);
    return new type(args);
}

function classType(path) {
    var type = nat.type(path);

    return type.class;
}