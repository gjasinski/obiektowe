function usernameAlreadyExists() {
    alert("Your username is already taken!");
    setUsername();
}

function setUsername() {
    id("userlist").style.visibility = "hidden";
    id("chat").style.visibility = "hidden";
    id("chatControls").style.visibility = "hidden";
    id("channellist").style.visibility = "hidden";
    id("channellist").style.marginLeft = "-100px";
    var cookie = getCookie("username");
    var username = prompt("Type your username: ", cookie);

    if (username == null){
        alert("You can't be without username");
        setUsername();
        return;
    }

    username = username.replace(/[^a-zA-Z0-9 ]/g, "");

    if (username == ""){
        alert("You can't be without username");
        setUsername();
    }else {
        setCookie("username", username, 1);
        webSocket.send(buildSimpleJson("username", username));
    }
}

function getCookie(cname) {
    var cookies = document.cookie;
    var indexOf = cookies.indexOf(cname);
    var username = "Username";
    if (indexOf >= 0){
        username = cookies.substr(indexOf + 9);
        indexOf = username.indexOf(";");
        if (indexOf >= 0){
            username = cookies.substr(0, cookies.indexOf(";"))
        }
    }
    return username;
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function buildSimpleJson(jsonObject1, jsonObject2) {
    return "{\"messageType\":\"" + jsonObject1 + "\",\"message\":\"" + jsonObject2 + "\"}";
}

function buildJsonEntity(key, value) {
    return "\"" + key + "\":\"" + value + "\"";
}



function refreshChannelList(msg) {
    id("channellist").style.visibility = "visible";
    var data = JSON.parse(msg.data);
    id("channellist").innerHTML = "";
    insert("channellist", "<button id=\"addchannel\">Add new channel</button>");
    insert("channellist", "<button id=\"leavechannel\">Leave channel</button>");
    data.channelList.forEach(function (channel) {
        insert("channellist", "<button id=\"" + channel + "\">" + channel + "</button>");
        id(channel).addEventListener("click", function(){alert(channel);});
    });
    insert("channellist", "<li>Channel List</li>");
    id("addchannel").addEventListener("click", function() {addNewChannelFirstVisit();});
}

function addNewChannelFirstVisit() {
    channelName = prompt("Type name of new channel: ", "New channel")
    if (channelName == null){
        return;
    }
    channelName = channelName.replace(/[^a-zA-Z0-9 ]/g, "");
    if (channelName !== "") {
        webSocket.send(buildSimpleJson("newChannelName", channelName));
        webSocket.send(buildSimpleJson("joinToChannelName", channelName))
        showChatFirstVisit(channelName);
    }
}

function showChatFirstVisit(channelname) {
    id("userlist").style.visibility = "visible";
    id("chat").style.visibility = "visible";
    id("chatControls").style.visibility = "visible";
    id("channellist").style.visibility = "visible";
    id("channellist").style.marginLeft = "-450px";
    currentlyVisibleChannel = channelname;
    showNewChannel(channelname);
}