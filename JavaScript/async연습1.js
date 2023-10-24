
async function sigma(limit=10)
{
    s=0;
    for(i=1; i<=limit; i++)
    {
        s+=i;
    }
    return s; 
}

console.log( sigma(100) );  //이렇게 사용못한다, async 키워드가 함수를 => Promise 객체로 바꿔서 보냄

sigma(100)
.then( 
    (res)=>{ console.log( res )}
);


async function sigma2( limit=10)
{
    let promise = new Promise( function(resolve, reject){
        s=0;
        for(i=1; i<=limit; i++)
        {
            s+=i;
        }
        resolve(s); //성공함
    });

    let result = await promise;
    return result; 
}

console.log( sigma2(100) );
