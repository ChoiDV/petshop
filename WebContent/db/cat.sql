-- 고양이
-- DROP & CREATE
DROP TABLE CBREED;

CREATE TABLE CBREED(   -- 고양이 종류 테이블 
    CBREEDNO NUMBER(3) PRIMARY KEY,  -- 고양이 종류 번호 
    CBREEDNAME VARCHAR2(30) UNIQUE NOT NULL  -- 고양이 종류 이름 
);

-- cat 테이블 
DROP SEQUENCE CAT_SEQ;

CREATE SEQUENCE CAT_SEQ
    MAXVALUE 999999
    NOCACHE
    NOCYCLE;
    
DROP TABLE CAT CASCADE CONSTRAINTS;

CREATE TABLE CAT(
   CNUM NUMBER(6) PRIMARY KEY,   -- 고양이 고유번호
   CNAME VARCHAR2(30) NOT NULL,   -- 고양이 이름
   CGENDER VARCHAR2(2) NOT NULL,  -- 고양이 성별
   CBIRTH DATE NOT NULL,   -- 고양이 생년월일
   CPRICE NUMBER(8) NOT NULL,
   CBREEDNO REFERENCES CBREED(CBREEDNO) NOT NULL,  -- 고양이 종류 번호
   AID REFERENCES ADMIN(AID) NOT NULL,   -- 관리자 아이디(담당자)
   CCONTENT VARCHAR2(3000) NOT NULL,
   CIMAGE1 VARCHAR2(50) NOT NULL,
   CIMAGE2 VARCHAR2(50),
   CIMAGE3 VARCHAR2(50),
   CIMAGE4 VARCHAR2(50),
   CIMAGE5 VARCHAR2(50),
   CIP VARCHAR2(50),
   CHIT NUMBER(6) DEFAULT 0 NOT NULL,
   CR_CHECK NUMBER(1) DEFAULT 1 NOT NULL,  -- 고양이 분양 예약 여부 0은 예약중.
   CRDATE DATE DEFAULT SYSDATE NOT NULL  -- 고양이 글 등록 시점
);
 -- 1. 고양이 분양 글 등록
INSERT INTO CAT (CNUM, CNAME, CGENDER, CBIRTH, CPRICE, CBREEDNO, AID, CCONTENT, CIMAGE1, CIMAGE2,CIMAGE3,CIMAGE4,CIMAGE5, CIP)
    VALUES(CAT_SEQ.NEXTVAL, '비범', 'F', '2021-06-24', 1000000, 30 , 'happycat', '예쁘고 귀여운 비범에요', 'bibum.jpg', null, null, null, null, '192.168.10.30' );


-- 2. 고양이 목록 출력 (startRow ~ endRow )
SELECT * FROM(SELECT ROWNUM RN, A.* FROM
                (SELECT * FROM CAT ORDER BY CRDATE DESC) A) C, CBREED CB
    WHERE C.CBREEDNO = CB.CBREEDNO
        AND RN BETWEEN 1 AND 12
            ORDER BY RN ;

-- 3. 글 상세보기 (DNUM 으로 DTO 가져오기 )
SELECT * FROM CAT C, CBREED CB
    WHERE C.CBREEDNO = CB.CBREEDNO
        AND CNUM=1;

-- 4. 글 조회수 올리기
UPDATE CAT SET CHIT= CHIT +1
    WHERE CNUM=1;

-- 5. 전체 글 개수 가져오기
SELECT COUNT(*) FROM CAT;

-- 6. 글수정
UPDATE CAT SET CNAME='방비',
                CGENDER='M',
                 CBIRTH='2020-05-24',
                  CPRICE=1200000,
                   CBREEDNO=30,
                    AID='happycat',
                     CCONTENT='정말정말 예쁜 방비이에요',
                      CIMAGE1='bambi1.jpg',
                       CIMAGE2='bambi2.jpg',
                        CIMAGE3='bambi3.jpg',
                         CIMAGE4='bambi4.jpg',
                          CIMAGE5='bambi5.jpg',
                           CIP ='192.162.13.01'
                    WHERE CNUM=1;

-- 7. 글 삭제하기
DELETE CAT WHERE CNUM=2;

-- 8. 예약하기
UPDATE CAT SET CR_CHECK=0
    WHERE CNUM=1;
    
-- 9. 예약 취소
UPDATE CAT SET CR_CHECK=1
    WHERE CNUM=1;
    

    

COMMIT;   
