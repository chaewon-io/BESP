//StompJS 라이브러리를 이용해 데이터를 주고 받는다. 
//JSON 형태로 데이터를 전달한다 
//브로커주소 전달자 프로토콜은 ws 또는 wss이다. 
//설정파일에 사용한 값으로 시작을 한다. 
//포트번호는 웹서버와 같은것을 사용한다 

//stompClient -- 데이터를 주고 받을 객체가 된다. 
//StompJs - 이 라이브러리는 스프링이 제공하고 있는 것이다. 
// const 상수 stomClient 상수에 객체를 할당한다. 그럼 상수(final) 라서 새로운 객체로 바꾸진 못한다.
// 객체 안의 정보를 바꾸는 건 가능하다.
const stompClient = new StompJs.Client({
	
	// 설정파일(웹소켓콘피그파일)의 이 부분 registry.addEndpoint("/testwebsocket");
    brokerURL: 'ws://localhost:9000/testwebsocket'
});

// 접속 요청이 왔을 때 처리함수전달 (화살표함수) 
//접속요청이 오면 웹소켓의 경우에 별도로 서버와 클라이언트 개념이 없다. 서로 주고 받을 수 있디 
stompClient.onConnect = (frame) => {
    setConnected(true);//연결을한다. 
    console.log('Connected: ' + frame); //연결요청받은 로그를 남긴다 
    // registry.addEndpoint("/topic");
    // 정보를 기다린다, /topic/greetins 로 오면 chatcontroller에서 sendto 함수가 보낸 정보인 메시지 객체가 온다.
    // greeting => message 객체가 온다. 
    stompClient.subscribe('/topic/greeting', (greeting) => {
		console.log(greeting); // 이 데이터가 문자열로 온다.
		// JSON.parse --- json으로 파싱 작업을 해줌  -> ex 제이슨 - {name:"홍길동"}, 스트링 - '{name:"홍길동"}'
        showGreeting(JSON.parse(greeting.body).content);
    });
};

//소켓 에러처리 
stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

// 잘못올때 에러처리 -> ex. 브로커가 제대로 동작안할때 
stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
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

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

//서버로 정보를 보낸다. 
//@MessageMapping("/hello") -> 컨트롤러에서 
//registry.setApplicationDestinationPrefixes("/app"); -> 컨피그에서 
// 이 두개가 합쳐지면 destination: "/app/hello" 이다.
// JSON.stringify : JSON 객체를 문자열로 바꿔서 보낸다 
function sendName() {
    stompClient.publish({
        destination: "/app/hello",
        body: JSON.stringify(
			{'username': $("#username").val(), 'message':$("#message").val()}
		)
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

// 함수를 여기서 붙인다. 앞부분 html 파일에는 submit 버튼의 기능 차단이 가능하다 
// 원래 submit가 생기면 자바스크립트의 기본매개변수(event) - 내장객체, 발생했던 모든 이벤트에 대한 정보를 갖고 있는 객체이다.
// => jquery 나 다른라이브러리 매개변수로 전달된다. e
// e -> 이벤트 객체 
// preventDefault : submit가 무조건 서버로 정보를 전송하는데 이를 차단하는 기능을 한다. 

$(function () {
    $("form").on('submit', (e) => e.preventDefault()); //submit 버튼의 원래 기능 차단 
    $( "#connect" ).click(() => connect()); //connet 클릭하면 connet 함수 호출 
    $( "#disconnect" ).click(() => disconnect()); // disconnet 클릭하면 disconnect 함수 호출 
    $( "#send" ).click(() => sendName()); // sendName 클릭하면 sendName 함수 호출 
});
