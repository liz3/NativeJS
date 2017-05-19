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
