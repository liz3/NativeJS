var inc = require("/Users/liz3/IdeaProjects/NativeJS/tests/IncludeOne.js", false, true, "parent");
var server = serverSocket(25565);

async(function () {
    while (true) {
        var sock = server.accept();

        async(function () {
            println(readUntilNull(sock.getInputStream()))

        });

    }
});
nat.sleep(5000);

setInterval(function () {

    var connector = socket("127.0.0.1", 25565);
    socketMessage(connector, "Test\nDas geht, richtig, vom socket\n");
    connector.close();
}, 2500);