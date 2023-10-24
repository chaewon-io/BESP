//문자열에 대해서 람다적용하기 

let words = ["school", "survey", "rain", "rainbow", "cloud", "desk", "hospital", "guesthouse", "guest"];

//단어를 for문을 사용안하고 출력하기, forEach 함수는 매개변수가 두개이고 반환값이 없는 함수를 매개변수로 받는다 
//주로 출력을 하고자 할때 사용한다  (인자, 인자의 인덱스)

words.forEach( (a)=>{
    console.log( a );   //매개변수의 이름은 마음대로 붙일 수 있다 
});

words.forEach( (a, index)=>{
    console.log( a, index );   //매개변수의 이름은 마음대로 붙일 수 있다 
});

//단어의 길이가 5개이상만 추출 
let w5 = words.filter( (x) =>x.length>=5 ); //함수의 수행결과가 true 인 데이터만 새로운 배열로 만들어서 전달한다 
console.log( w5 );
w5.forEach(w=> console.log( w ));

//map 함수는 매개변수를 받아와서 연산을 수행한다. 바뀐값의 배열을 출력한다
let result = words.map( w=> w.toUpperCase() ); //모든 문자를 대문자로 받아간다 
console.log( result ); 
console.log( words );

words.map( w=> w.toUpperCase() )
     .filter( w=> w[0]=="S" || w[0]=="H" )
     .forEach( w=> console.log( w ));  //forEach - 최종연산 

//정렬, sort - 자기 자신이 순서를 바꾼다 
//toSorted() - 순서를 정렬한 배열을 리턴한다. 자기 순서는 안바뀐다 

words.sort();
console.log( words );

//node 20이상
//let orderd = words.toSorted();
//console.log( orderd );



