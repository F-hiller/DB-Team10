package p3;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class QueryExecutor {
	private DatabaseManager dbManager;

	public QueryExecutor(DatabaseManager dbManager) {
		this.dbManager = dbManager;
	}

	public void execute(String sql) throws SQLException {
		try (Connection conn = dbManager.getConnection(); Statement stmt = conn.createStatement()) {

			if (sql.trim().toLowerCase().startsWith("select")) {
				ResultSet rs = stmt.executeQuery(sql);
				// 결과 처리 (예: 출력)
				printResultSet(rs);
			} else {
				int affectedRows = stmt.executeUpdate(sql);
				System.out.println(affectedRows + " row(s) affected.");
			}
			dbManager.closeConnection(conn);
		}
	}

	private void printResultSet(ResultSet rs) throws SQLException {
		if (!rs.next()) {
			System.out.println("No result.\n");
			return;
		}
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();

		for (int i = 1; i <= columnsNumber; i++) {
			if (i > 1)
				System.out.print(",  ");
			System.out.print(rsmd.getColumnName(i));
		}
		System.out.println("");
		do {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1)
					System.out.print(",  ");
				String columnValue = rs.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		} while (rs.next());
		System.out.println("");
	}
}
