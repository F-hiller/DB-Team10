package p3;

public class Main {
	public static void main(String[] args) {
		ConfigLoader configLoader = new ConfigLoader();
		DatabaseManager dbManager = new DatabaseManager(configLoader);
		QueryExecutor queryExecutor = new QueryExecutor(dbManager);
		InputHandler inputHandler = new InputHandler(queryExecutor);

		inputHandler.start();

		return;
	}
}