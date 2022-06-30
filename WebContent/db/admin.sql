-- Admin 테이블
DROP TABLE ADMIN;

CREATE TABLE ADMIN(
    AID VARCHAR2(30) PRIMARY KEY,
    APW VARCHAR2(30) NOT NULL,
    ANAME VARCHAR2(30) UNIQUE NOT NULL
);

-- 관리자 등록
INSERT INTO ADMIN (AID, APW, ANAME )
    VALUES ('admin' , '123', '총관리자' );


-- 1. 관리자 로그인
SELECT * FROM ADMIN
    WHERE AID='happydog' AND APW='123';
    
-- 2. 로그인 후 DTO 가져오기
SELECT * FROM ADMIN
    WHERE AID='happydog';
-- 2-1
SELECT ANAME FROM ADMIN WHERE AID='admin';
    
-- 3. 관리자(브리더 등록) 등록
INSERT INTO ADMIN (AID, APW, ANAME )
    VALUES ('happydog', '123', '강아지브리더1');

INSERT INTO ADMIN (AID, APW, ANAME)
    VALUES ('happycat', '123', '고양이브리더1');

-- 4. 관리자 삭제
DELETE ADMIN WHERE AID='test';

-- 5. 관리자 목록 출력
SELECT * FROM ADMIN;

commit;