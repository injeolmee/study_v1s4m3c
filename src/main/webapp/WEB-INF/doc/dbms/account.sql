1. 등록된 사용자 목록
    SELECT username, user_id  
    FROM dba_users 
    ORDER BY username ASC;
 
       USERNAME                                USER_ID
       --------------------------------------- ----------
       OUTLN                                        9
       SOLDESK                                     48
       SYS                                             0
       SYSTEM                                       5
       XDB                                            34
       XS$NULL                                     2147483638
 
 
2. SOLDESK계정을 기본적으로 생성하나 존재하면 SOLDESK + IP를 결합하여
   계정을 만듭니다다. 1234는 패스워드입니다.
   예) 훈련교사: SOLDESK
        훈련생: SOLDESK1, SOLDESK2, SOLDESK3...
 
   CREATE USER soldesk IDENTIFIED BY 1234;
   CREATE USER study IDENTIFIED BY 1234;
 
 
3. 권한 부여
    GRANT connect, resource to soldesk;
    GRANT connect, resource to study;
 
 
4. 사용자 삭제 
   DROP USER userid CASCADE; 
 
 
 
[참고] 계정 동시 사용으로인한 잠김의 해제
        일반 계정이 잠겼을경우 푸는 방법: system으로 로그인하여 실행  
        ALTER USER soldesk ACCOUNT UNLOCK;  
 
 
 
[참고] 관리자인 system 계정이 Lock 잠김 경우
         - 'Run SQL Command Line' 실행
        SQL> connect
        Enter user-name: sys as SYSDBA
        Enter password: 1234 or oracle
        Connected.
 
        SQL> alter user system account unlock;
        User altered.