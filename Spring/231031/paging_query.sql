-- procedure(프로슈저 사용해서 대용량 데이터 만들어 보기)
-- 반환값이 있으면 function이고, 없으면 procedure(ex.void)
-- PL/SQL 
create or replace procedure insert_data
is
begin 
    delete from tb_board;  -- 
    for i in 1..450 loop 
       insert into tb_board (seq, title, writer, contents, wdate)
       values(i, '제목'||i, '작성자'||i, '내용'||i, sysdate);
    end loop;
    commit;
end;
/
exec insert_data;

select count(*)from tb_board;


-- 서브쿼리(오라클 페이징 표준 쿼리)-> 속도가 가장 빠름
select A.rnum, A.seq, A.title, A.contents, A.writer, 
   to_char(A.wdate, 'yyyy-mm기-dd') wdate, A.hit
from(
    select rownum rnum, A.seq, A.title, A.contents, A.writer, A.wdate, A.hit
    from(   
        select A.seq, A.title, A.contents, A.writer, A.wdate, A.hit 
        from tb_board A 
        order by seq desc
    )A where rownum<=30
)A
where rnum>20;
