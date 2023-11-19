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

//	public void execute(String sql) throws SQLException {
//		try (Connection conn = dbManager.getConnection(); Statement stmt = conn.createStatement()) {
//
//			if (sql.trim().toLowerCase().startsWith("select")) {
//				ResultSet rs = stmt.executeQuery(sql);
//				// 결과 처리 (예: 출력)
//				printResultSet(rs);
//			} else {
//				int affectedRows = stmt.executeUpdate(sql);
//				System.out.println(affectedRows + " row(s) affected.");
//			}
//			dbManager.closeConnection(conn);
//		}
//	}
	
	public void execute(String sql) throws SQLException {
	    try (Connection conn = dbManager.getConnection(); Statement stmt = conn.createStatement()) {
	        String[] queries = sql.split(";");
	        for (String query : queries) {
	        	System.out.println(query);
//	            if (!query.trim().isEmpty()) { // 빈 문자열이 아닌 경우에만 실행
//	                if (query.trim().toLowerCase().startsWith("select")) {
//	                    ResultSet rs = stmt.executeQuery(query);
//	                    // 결과 처리 (예: 출력)
//	                    printResultSet(rs);
//	                } else {
//	                    int affectedRows = stmt.executeUpdate(query);
//	                    System.out.println(affectedRows + " row(s) affected.");
//	                }
//	            }
	        }
	        dbManager.closeConnection(conn);
	    }
	}


	private void printResultSet(ResultSet rs) throws SQLException {
		System.out.println("결과는 아래와 같습니다.");

		ResultSetMetaData metaData = rs.getMetaData();
	    int columnCount = metaData.getColumnCount();
	    for (int i = 1; i <= columnCount; i++) {
	    	System.out.print(String.format("%-50s", metaData.getColumnName(i)));
	    }
	    System.out.println();
	    for (int i = 1; i <= columnCount; i++) {
		    System.out.print("--------------------------------------------------");
	    }
	    System.out.println();
	    while (rs.next()) {
	        for (int i = 1; i <= columnCount; i++) {
	            // 각 컬럼의 데이터를 출력 (예제를 위한 단순화)
	            System.out.print(String.format("%-50s", rs.getString(i)));   
	        }
            System.out.println();
	    }
	    System.out.println(); 
	}
}
