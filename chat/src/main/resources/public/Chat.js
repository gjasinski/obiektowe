var currentlyVisibleChannel;

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
        webSocket.send("{" +
                buildJsonEntity("messageType", "chatMessage") + "," +
                buildJsonEntity("message", message) + "," +
                buildJsonEntity("channelName", currentlyVisibleChannel) +
                "}");
        id("message").value = "";
    }
}

function updateChat(msg) {
    var data = JSON.parse(msg.data);

    insert("chat_" + data.channelName, data.userMessage);

    id("userlist").innerHTML = "";
    data.userList.forEach(function (user) {
        insert("userlist", "<li>" + user + "</li>");
    });

    id("channellist").innerHTML = "";
    insert("channellist", "<button id=\"addchannel\">Add new channel</button>");
    insert("channellist", "<button id=\"leavechannel\">Leave channel</button>");
    data.channelList.forEach(function (channel) {
        insert("channellist", "<button id=\"" + channel + "\">" + channel + "</button>");
        id(channel).addEventListener("click", function(){changeVisibleChannel(channel)});
    });
    insert("channellist", "<li>Channel List</li>");
    id("addchannel").addEventListener("click", function() {addNewChannel();});
    id("leavechannel").addEventListener("click", function(){alert("leavechanel");});
}

function addNewChannel() {
    channelName = prompt("Type name of new channel: ", "New channel")
    if (channelName == null){
        return;
    }
    channelName = channelName.replace(/[^a-zA-Z0-9 ]/g, "");
    if (channelName !== "") {
        webSocket.send(buildSimpleJson("newChannelName", channelName));
    }
    joinToChannel(channelName);
    showNewChannel(channelName);
}

function showNewChannel(channelName) {
    insert("chat", "<div id=\"chat_" + channelName + "\"></div>");
    changeVisibleChannel(channelName);
}
function changeVisibleChannel(channelName) {
    id("chat_" + currentlyVisibleChannel).style.visibility = "hidden"
    id("chat_" + channelName).style.visibility = "visible";
    currentlyVisibleChannel = channelName;
}

function joinToChannel(channelName) {
        webSocket.send(buildSimpleJson("joinToChannelName", channelName));
}

//Helper function for inserting HTML as the first child of an element
function insert(targetId, message) {
    id(targetId).insertAdjacentHTML("afterbegin", message);
}

//Helper function for selecting element by id
function id(id) {
    return document.getElementById(id);
}

