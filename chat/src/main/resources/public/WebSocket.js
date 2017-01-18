//Establish the WebSocket connection and set up event handlers
var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat/");
webSocket.onmessage = function (msg) {handleMessage(msg); };
webSocket.onclose = function () { alert("WebSocket connection closed") };
webSocket.onopen = setUsername();

function handleMessage(msg) {
    var data = JSON.parse(msg.data);
    alert(data.channelName);
    if (data.error != null) {
        switch (data.error){
            case "already_joined": alert("Already joined to channel!");
                break;
            case "channel_exists": alert("Channel already exists!");
                break;
            case "username_exists": usernameAlreadyExists();
                break;
            default: alert(data.error);
                break;
        }
    }
    else {
        if(data.refresh != null){
            refreshChannelList(msg);
        }else {
            updateChat(msg);
        }
    }
}