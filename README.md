# DB-Team10
Database Team Project with JDBC.

```
Requirements
• 제출물에 대한 실행 및 사용 방법, 기능 설명, 유의 사항, Application 제작 환경 등
• 제작한 Application에 대해 사용자가 알아야 할 내용을 복합적으로 기술.
- 쿼리에 수정사항이 있으면 반드시 명시할 것
```

Java version은 11을 사용하여 진행하였습니다.  

프로그램 실행 후 사용 방식은 아래와 같습니다.

1. 1~20번 까지의 기능들 중 실행하고 싶은 기능 선택(또는 exit를 입력하여 프로그램 종료)
2. 프로그램에서 요구하는 데이터들을 입력 (ex. 사용자 id, gym 이름과 같은 정보들)
3. 프로그램 실행 결과 확인  
<br>

프로그램 시나리오 예시
1. 프로그램을 실행한다.
2. 화면에 1~20가지 기능들이 표시된다.
3. 4을 입력하고 엔터를 눌러 4번 기능을 실행한다.
4. 4에서 요청하는 정보(체육관 이름) Lazzy를 입력하고 엔터.
5. 동적 쿼리로 진행된 4번 기능의 실행 결과를 화면에서 확인.
6. 다음으로 실행할 기능 번호를 입력하거나 exit를 입력해서 프로그램 종료.  
<br>

기능들 리스트는 아래와 같습니다.



각 기능을 담당하는 쿼리는 QueryCreater.java에서 console 입력을 통해 동적으로 생성됩니다.