function usernameAlreadyExists() {
    alert("Your username is already taken!");
    setUsername();
}

function setUsername() {
    var cookie = getCookie("username");
    var username = prompt("Type your username: ", "cookie");

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
        webSocket.send(buildJson("username", username));
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

function buildJson(jsonObject1, jsonObject2) {
    return "{\"messageType\":\"" + jsonObject1 + "\",\"message\":\"" + jsonObject2 + "\"}"
}