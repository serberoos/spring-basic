drop table if exist member CASCADE;
create table key_member(
    id bigint generate by default as identity,
    name varchar(255),
    primary key (id)
);
-- 김영한의 경우 git을 사용하면서 같이 sql 코드도 관리하기 위해 이런식으로 따로 sql 폴더를 만들고 관리해준다.