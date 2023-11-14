package p3;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

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
		while (rs.next()) {
			// 각 컬럼의 데이터를 출력 (예제를 위한 단순화)
			System.out.println(rs.getString(1));
		}
	}
}
