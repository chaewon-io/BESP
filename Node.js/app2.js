// Express 애플리케이션을 생성합니다.
let express = require('express');
let app = express();

// 필요한 모듈 가져오기 -> 파일 경로 관련 모듈 가져옴
// 파일 경로를 상대경로 -> 실제경로(물리적경로를 알아오거나) 혹은 경로를 만들기도 함
let path = requite('path'); 

// view엔진을 설정해야 한다.
// 환경변수 - 스프링 설정(application.properties)에 따라 뷰 엔진을 설정해야 함
// 변수나 함수중에__(언더바 두개)로 시작하는 요소들은 시스템이 제공하는 내장변수나 함수이다
// __dirname: 현재 프로젝트가 가동주인 폴더 정보를 가져온다
//현재 폴더 아래에 /views 폴더를 만들어 html 문서를 넣겠다고 설정함
app.set('views',path.join(__dirname, "views"))
app.set("view engine", "ejs"); //Express에서 EJS(view 엔진)를 사용하도록 설정 -> 스프링 - jsp, 머스티치, 타임리프

// POST처리를 하기위한 코드이다
app.use(express.urlencoded({ extended: false }));

// json 객체배열로 데이터를 만든다
// board_list - 스네이크 표기법 
// boardList - 카멜 표기법 
boardList = [
    {"id":1, "title":"쌍갑포차", "author":"배혜수"},
    {"id":2, "title":"아지갑놓고나왔다", "author":"미역"},
    {"id":3, "title":"무빙", "author":"강풀"},
    {"id":4, "title":"마음의소리", "author":"조석"},
    {"id":5, "title":"고수", "author":"문정후"},
];

app.get("/board/list", (req, res)=>{
    res.render("board/board_list", {"boardList":boardList});
});

// /board/view/1  => 
app.get("/board/view/:id", (req, res)=>{
    let id = parseInt(req.params.id);  //id를 1,2,3  배열인덱스 : 0 1 2 
    res.render("board/board_view", {"board":boardList[id-1]});
});

// 페이지 이동
app.get("/board/write", (req, res)=>{
    res.render("board/board_write", {});
});

// 페이지 이동
app.post("/board/save", (req, res)=>{
    let id = boardList.length+1;
    let title = req.body.title;
    let author = req.body.author;
    boardList.push({"id":id, "title":title, "author":author});
    //리스트페이지로 이동한다 == 직접 함수를 호출하면 안된다. 내부적으로 처리할 일들이다.
    // 그래서 redirect를 통해서 url을 변경해야 한다.
    res.redirect("/board/list"); 
});

//ajax - Asychronous java script ~xml;
/*
    클라이언트 --------> 서버로 비동기로 정보 전송
               <-------- 실행한 결과를 보낸다. xml => json
*/


app.post("/board/save2", (req, res)=>{
    let id = boardList.length+1;
    let title = req.body.title;
    let author = req.body.author;
    boardList.push( {"id":id, "title":title, "author":author});
    // 결과값을 json으로 보내줘야 한다.
    res.send( {result:"success"})
});


// 라우팅 설정
// get: get 방식처리, post: post방식만 처리, use: get도 post도 모두 처리한다
app.get("/", (req, res)=>{
	// res.render: HTML 템플릿을 사용하여 데이터와 함께 HTML을 렌더링하여 클라이언트에게 반환함. 
    // render("html문서정보", "json형태의 데이터")
    // 자바스크립트 => 한 번에 여러 개의 값을 전달하면 -> 자바는 객체(DTO) 사용함
	res.render("index", {name:"홍길동", age:12});

	// res.send: json 형태로 데이터를 화면에 출력한다 (클라이언트에게 반환함) -> 결과를 json 형태로 보내고자 할 떄 많이 사용한다.
	// res.end: text 또는 html 형식의 데이터를 클라이언트에게 반환 -> 테스트용, 실제사용안함
	// res.render: html 문서와 데이터를 합쳐서 새로운 html을 만들어서 클라이언트에게 보낸다. -> 렌더링한다고 표현함
});

app.use("/a", function(req, res){
    res.writeHead(200, {"Content-Type":"text/html"});
    res.end("<h1>This is A </h1>");
});


// http://127.0.0.1:4000/add?x=5&y=7 
app.use("/add", function(req, res){
    let x = parseInt( req.query.x );
    let y = parseInt( req.query.y );
    let result = x+y;    
    res.writeHead(200, {"Content-Type":"text/html"});
    res.end(`<h1>${x} + ${y} = ${result} </h1>`);
});

// http://127.0.0.1:4000/add2/5/7 
app.use("/add2/:x/:y", function(req, res){
    let x = parseInt( req.params.x );
    let y = parseInt( req.params.y );
    let result = x+y;    
    res.writeHead(200, {"Content-Type":"text/html"});
    res.end(`<h1>*****  ${x} + ${y} = ${result} *****</h1>`);
});

app.use("/add_post", function(req, res){
    let x = parseInt( req.body.x );
    let y = parseInt( req.body.y );
    let result = x+y;    
    res.writeHead(200, {"Content-Type":"text/html"});
    res.end(`<h1>====  ${x} + ${y} = ${result}  ====</h1>`);
});

//적당하지 않은 url처리를 이 함수내에서 해야 한다.
//모든 url에 대해서 이 함수가 처리를 한다. 
app.use( function(req, res){
    res.writeHead(200, {"Content-Type":"text/html"});
    res.end("<h1>Hello express</h1>");
});

app.listen(4000, ()=>{
    console.log("server start at http://127.0.0.1:4000");
});
