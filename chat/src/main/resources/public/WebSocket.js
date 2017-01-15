//Establish the WebSocket connection and set up event handlers
var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat/");
webSocket.onmessage = function (msg) {handleMessage(msg); };
webSocket.onclose = function () { alert("WebSocket connection closed") };
webSocket.onopen = setUsername();

function handleMessage(msg) {
    var data = JSON.parse(msg.data);
    if (data.error != null) {
        usernameAlreadyExists();
    }
    else {
        updateChat(msg);
    }
}