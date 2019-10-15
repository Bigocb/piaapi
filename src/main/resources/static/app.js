var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function start() {
    connect();
}

function sendForNews() {
    stompClient.send("/news", {}, JSON.stringify({'email': 'test'}));
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
             showGreeting(JSON.parse(greeting.body).temperature);
            showWeatherSummary(JSON.parse(greeting.body).summary);
        });
        stompClient.subscribe('/topic/news', function (news) {
            showNews(JSON.parse(news.body).title)});
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/hello", {}, JSON.stringify({'email': 'test'}));
}

function showGreeting(message) {
    $("#temp").empty();
    $("#temp").append(message);
}

function showWeatherSummary(message) {
    $("#summary").empty();
    $("#summary").append('The weather is ' + message + ' right now.');
}

function showNews(message) {
    $("#news").append("<h1>" + message + "</h1><hr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

