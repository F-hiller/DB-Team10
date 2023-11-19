package p3;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		ConfigLoader configLoader = new ConfigLoader();//db 연결 주소, USER명, PWD 등 담은 정보
		DatabaseManager dbManager = new DatabaseManager(configLoader);//위 정보를 이용해 DB연결
		QueryExecutor queryExecutor = new QueryExecutor(dbManager);//쿼리 실행시키는 메서드 담은 객체
		InputHandler inputHandler = new InputHandler(queryExecutor);//입력받아서 실행시킴

		inputHandler.start();

		return;
	}
}