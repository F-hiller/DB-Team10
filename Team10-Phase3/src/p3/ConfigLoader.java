package p3;

public class ConfigLoader {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER_NAME = "GYM";
	private static final String USER_PASSWD = "comp322";

	public ConfigLoader() {
	}

	public String getDatabaseUrl() {
		return ConfigLoader.URL;
	}

	public String getUsername() {
		return ConfigLoader.USER_NAME;
	}

	public String getPassword() {
		return ConfigLoader.USER_PASSWD;
	}
}
