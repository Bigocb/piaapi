var stompClient = null;

function stompFailureCallback (error) {
    console.log('STOMP: ' + error);
    setTimeout(connect, 10000);
    console.log('STOMP: Reconecting in 10 seconds');
};

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
            showNews(
                JSON.parse(news.body).title,
            JSON.parse(news.body).sentiment
            )});

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

function showNews(message, sentiment) {
    console.log(sentiment);
    console.log(message);
    if(sentiment < 0) {
        $("#news").append("<p >" + "<h1 class='bad'>" + message + "</h1>" + "</p>");
    } else if (sentiment > 0) {
        $("#news").append("<p >" + "<h1 class='good'>" + message + "</h1>" + "</p>");
    } else {
        $("#news").append("<p><h1>" + message + "</h1></p><hr>");
    }
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

