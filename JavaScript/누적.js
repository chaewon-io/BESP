let nums = [2,9,6,7,8];

let result = nums.reduce( (prev, current)=>{ 
    //prev => 앞의 값, 처음에 아무값도 부여하지 않으면 0이 부여된다. 
    //앞서의 값이 prev로 전달되고 current 는 현재 요소의 값이 전달된다. 
    console.log( prev, current );
    return prev+current;
});

console.log("결과 : " + result );