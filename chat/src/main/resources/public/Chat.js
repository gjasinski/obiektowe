//Send message if "Send" is clicked
id("send").addEventListener("click", function () {
    sendMessage(id("message").value);
});

//Send message if enter is pressed in the input field
id("message").addEventListener("keypress", function (e) {
    if (e.keyCode === 13) { sendMessage(e.target.value); }
});


function sendMessage(message) {
    if (message == null){
        return;
    }
    message = message.replace(/[^a-zA-Z0-9 ]/g, "");
    if (message !== "") {
        webSocket.send(buildJson("chatMessage", message));
        id("message").value = "";
    }
}

//Update the chat-panel, and the list of connected users
function updateChat(msg) {
    var data = JSON.parse(msg.data);
    insert("chat", data.userMessage);
    id("userlist").innerHTML = "";
    data.userList.forEach(function (user) {
        insert("userlist", "<li>" + user + "</li>");
    });
    data.channelList.forEach(function (channel) {
        insert("channellist", "<button id=\"" + channel + "\">" + channel + "</button>");
    })
    insert("channellist", "<li>Channel List</li>");
}

//Helper function for inserting HTML as the first child of an element
function insert(targetId, message) {
    id(targetId).insertAdjacentHTML("afterbegin", message);
}

//Helper function for selecting element by id
function id(id) {
    return document.getElementById(id);
}

