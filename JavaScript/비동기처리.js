//비동기식처리.js
let fs = require("fs"); 

//비동기모드의 경우에 함수는 값을 반환하지 못한다
// result = 함수();   비동기는 못한다 
//함수의 매개변수로 함수를 전달해야 한다. 호출자는 시스템이다. 
//callback 함수라고 부른다.  콜백함수의 매개변수는 정해져있다. 
//함수의 정의, 함수의 표현식, 화살표함수

let result = fs.readFile("./test.txt", encode="utf-8", 
  function (error, data){
    if(error)
    {
        console.log( error );
        return; 
    }
    console.log( data );
  });
console.log( result );
console.log( "완료-----------") 



