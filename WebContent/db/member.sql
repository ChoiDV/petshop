-- member
-- DROP & CREATE
DROP TABLE MEMBER CASCADE CONSTRAINTS;

CREATE TABLE MEMBER(
    MID VARCHAR2(30) PRIMARY KEY,  -- 회원 아이디
    MPW VARCHAR2(30) NOT NULL,  -- 회원 비밀번호
    MNAME VARCHAR2(30) NOT NULL,  -- 회원 이름
    MTEL VARCHAR2(30) NOT NULL,  -- 회원 전화번호
    MBIRTH DATE NOT NULL,  -- 회원 생년월일
    MEMAIL VARCHAR2(50) NOT NULL,  -- 회원 이메일
    MADDRESS VARCHAR2(100) NOT NULL,  -- 회원 주소 
    MGENDER VARCHAR2(5) NOT NULL,  -- 회원 성별
    MRDATE DATE DEFAULT SYSDATE NOT NULL,  --- 가입일
    MWITHD NUMBER(1) DEFAULT 1 NOT NULL  -- 탈퇴여부 탈퇴시 0
);

-- 1.회원가입
INSERT INTO MEMBER (MID, MPW, MNAME, MTEL, MBIRTH, MEMAIL, MADDRESS, MGENDER )
    VALUES ('aaa', '123', '최진영', '010-4434-9878',
                TO_DATE('991203','RRMMDD'),'chlwlsdud69@naver.com', 
                                    '서울시 강남구', 'M' );   
INSERT INTO MEMBER (MID, MPW, MNAME, MTEL, MBIRTH, MEMAIL, MADDRESS, MGENDER )
    VALUES ('bbb', '123', '오동준', '010-1235-1458',TO_DATE('951010','RRMMDD'),'dhehdwns12@naver.com', '서울시 서초구', 'M' );    
    
-- SELECT MNAME, TO_CHAR(MBIRTH, 'YYYY-MM-DD') FROM MEMBER;  이렇게 TO_CHAR 써서 출력하면 1999-12-03 알아서 해줌
SELECT * FROM MEMBER;

-- 2.로그인
SELECT * FROM MEMBER
    WHERE MID='aaa' AND MPW ='123';
SELECT * FROM MEMBER
    WHERE MID='aaa' AND MWITHD=0;

-- 3. 로그인 성공시 DTO 가져오기
SELECT * FROM MEMBER
    WHERE MID='aaa';

-- 4. 정보수정
UPDATE MEMBER SET MPW ='123',
                    MTEL='010-1474-1123',
                     MEMAIL='dhehdwhk@naver.com',
                      MADDRESS='서울시 용산구'
            WHERE MID='bbb';
            
-- 5. 회원탈퇴
UPDATE MEMBER SET MWITHD = 0
            WHERE MID='bbb' AND MPW='123';
            
-- 6. 회원목록출력(정상회원)
SELECT * FROM(SELECT ROWNUM RN, A.* FROM
                    (SELECT * FROM MEMBER ORDER BY MRDATE DESC) A)
        WHERE MWITHD != 0 
            AND RN BETWEEN 1 AND 10;
-- 7. 회원목록출력(탈퇴회원)  
SELECT * FROM(SELECT ROWNUM RN, A.* FROM
                    (SELECT * FROM MEMBER ORDER BY MRDATE DESC) A)
        WHERE MWITHD != 1
            AND RN BETWEEN 1 AND 10;


SELECT * FROM(SELECT ROWNUM RN, A.* FROM
                (SELECT * FROM DOG_REPLY ORDER BY RDATE DESC) A) D, MEMBER M
    WHERE D.MID = M.MID
        AND RN BETWEEN 1 AND 10;
        
-- 8. ID 중복검사
SELECT * FROM MEMBER WHERE MID='aaa';
        
-- 9. 이름, 전화번호, 이메일, 생일, 주소 업데이트
UPDATE MEMBER SET MNAME = '홍길동',
                    MTEL = '010-1478-2145',
                     MEMAIL = 'wlsdud@naver.com',
                        MBIRTH = '1999-12-03',
                            MADDRESS = '서울시 강남구'
                    WHERE MID='aaa';
                    
-- 아이디 변경
UPDATE MEMBER SET MID = 'Aaa'
                WHERE MID='aaa';
                
-- 비밀번호 변경
UPDATE MEMBER SET MPW = '111'
                WHERE MID = 'aaa'
                    AND MPW ='123';
        
commit;

