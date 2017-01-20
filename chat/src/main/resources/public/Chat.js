var currentlyVisibleChannel;

id("send").addEventListener("click", function () {
    sendMessage(id("message").value);
});

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
        id(channel).addEventListener("click", function(){
            showChannel(channel);
        });
    });
    insert("channellist", "<li>Channel List</li>");
    id("addchannel").addEventListener("click", function() {
        var channelName = addNewChannel();
        showChannel(channelName);
    });
    id("leavechannel").addEventListener("click", function(){leaveChannel();});
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
    return channelName;
}

function showChannel(channelName) {
    if (id("chat_" + channelName) == null) {
        insert("chat", "<div id=\"chat_" + channelName + "\"></div>");
        joinToChannel(channelName);
        changeVisibleChannel(channelName)
    }
    else{
        changeVisibleChannel(channelName);
    }
}
function changeVisibleChannel(channelName) {
    if(currentlyVisibleChannel != null && id("chat_" + currentlyVisibleChannel) != null){
        id("chat_" + currentlyVisibleChannel).style.visibility = "hidden"
    }
    id("chat_" + channelName).style.visibility = "visible";
    currentlyVisibleChannel = channelName;
}

function joinToChannel(channelName) {
        webSocket.send(buildSimpleJson("joinToChannelName", channelName));
}

function leaveChannel() {
    if(id("chat_" + currentlyVisibleChannel) != null){
        webSocket.send(buildSimpleJson("leaveChannel", currentlyVisibleChannel));
        id("chat_" + currentlyVisibleChannel).remove();
    }
}

//Helper function for inserting HTML as the first child of an element
function insert(targetId, message) {
    id(targetId).insertAdjacentHTML("afterbegin", message);
}

//Helper function for selecting element by id
function id(id) {
    return document.getElementById(id);
}

