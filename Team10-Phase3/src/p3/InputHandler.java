package p3;

import java.util.Scanner;

public class InputHandler {
	private Scanner scanner;
	private QueryExecutor queryExecutor;

	public InputHandler(QueryExecutor queryExecutor) {
		this.scanner = new Scanner(System.in);
		this.queryExecutor = queryExecutor;
	}

	public void start() {
		while (true) {
			System.out.print("[Main]: Enter SQL command or 'exit' to quit:\n>> ");
			String input = scanner.nextLine();

			if ("exit".equalsIgnoreCase(input)) {
				System.out.println("[System]: Program Terminated.");
				break;
			}

			// SQL 명령 실행
			try {
				queryExecutor.execute(input);
			} catch (Exception e) {
				System.out.println("[System]: Error executing command: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
