package p3;

import java.io.IOException;
import java.util.Scanner;

public class InputHandler {
	private Scanner scanner;
	private QueryExecutor queryExecutor;

	public InputHandler(QueryExecutor queryExecutor) {
		this.scanner = new Scanner(System.in);
		this.queryExecutor = queryExecutor;
	}

	public void start() throws IOException {
		while (true) {
			System.out.print("[Main]: Enter SQL command or 'exit' to quit:\n>> ");
			System.out.print("1. Capacity 이하의 수용량을 가진 Gym 찾기\n ");
			
			String number = scanner.nextLine();
			if ("exit".equalsIgnoreCase(number)) {
				System.out.println("[System]: Program Terminated.");
				break;
			}
			QueryCreater queryCreater = new QueryCreater();
			String sql = queryCreater.create(number);
			

			// SQL 명령 실행
			try {
				queryExecutor.execute(sql);
			} catch (Exception e) {
				System.out.println("[System]: Error executing command: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
