//프라미스1.js

let promise = new Promise(  function(resolve, reject){
    //setTimeout( ()=> resolve(100), 1000);  //제작코드 
    //setTimeout 함수(호출될함수, 시간);  밀리초를 단위로 사용한다 1000 - 1초   
    setTimeout( ()=>reject("에러입니다"), 1000);
})
.then( (res)=>{
    console.log( res );
})//resolve가 호출될때 then 구문이 호출된다. 
  //resolve(값)  ==> then 구문에서 호출될 함수 => 함수의 매개변수값으로 전달된다. 
.catch( (error)=>{
    console.log( error );
})
console.log( promise ); 

