package p3;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		ConfigLoader configLoader = new ConfigLoader();
		DatabaseManager dbManager = new DatabaseManager(configLoader);
		QueryExecutor queryExecutor = new QueryExecutor(dbManager);
		InputHandler inputHandler = new InputHandler(queryExecutor);

		inputHandler.start();

		return;
	}
}