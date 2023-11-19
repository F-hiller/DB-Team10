import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Console {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    public static final String USER_ID = "GYM";
    public static final String USER_PASSWD = "COMP322";

    private static Connection connection;
    private static Statement statement;

    public static void start() throws Exception {
        /* DB연결, Connection, Statement 세팅 */
        dbInitSetting();

        ConsoleView consoleView = new ConsoleView();
        while (true) {
            String str = consoleView.getCommand();
            if (str.equals("EXIT")) {
                break;
            }
            //command별 세부정보 받고 데이터 처리
        }
        System.out.println("-------프로그램을 종료합니다-------");
    }

    private static void dbInitSetting() throws Exception {
        Connection conn = null; // Connection object
        Statement stmt = null;    // Statement object
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(URL, USER_ID, USER_PASSWD);
        conn.setAutoCommit(false);
        stmt = conn.createStatement();
    }
}
