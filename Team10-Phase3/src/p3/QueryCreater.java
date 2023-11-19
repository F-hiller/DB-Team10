package p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QueryCreater {
	public String create(String number) throws IOException {
		switch (number) {
		case "1":
			return selectGymWithCapacity();
		case "2":
			return selectUserWithId();
		case "3":
			return selectTrainerWithSpecAndYear();
		case "4":
			return selectMachineWithGym();
		}
		return null;
	}

	private String selectGymWithCapacity() throws IOException {
		StringBuffer sb = new StringBuffer();

		System.out.print("Capacity를 입력하세요:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String capacity = br.readLine();

		sb.append("SELECT Name, Location, Contact ");
		sb.append("FROM GYM ");
		sb.append("WHERE Capacity < " + capacity);
		String sql = sb.toString();
		return sql;
	}

	private String selectUserWithId() throws IOException {
		StringBuffer sb = new StringBuffer();

		System.out.println("User의 Id를 입력하세요:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String userId = br.readLine();
		sb.append("SELECT * FROM USERS U WHERE U.User_id = " + userId);
		String sql = sb.toString();
		return sql;
	}

	private String selectTrainerWithSpecAndYear() throws IOException {
		StringBuffer sb = new StringBuffer();

		System.out.println("Specialization을 입력하세요: ex) Bulk up, Conditioning");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String specialization = br.readLine();

		System.out.println("최소 경력을 입력하세요:");
		String minYear = br.readLine();
		System.out.println("최대 경력을 입력하세요:");
		String maxYear = br.readLine();

		sb.append("SELECT * FROM TRAINER T WHERE T.Specialization = '" + specialization + "' AND T.Work_year BETWEEN "
				+ minYear + " AND " + maxYear);
		String sql = sb.toString();
		return sql;
	}

	private String selectMachineWithGym() throws IOException {
		StringBuffer sb = new StringBuffer();

		System.out.println("체육관 이름을 입력하세요:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String name = br.readLine();

		sb.append("SELECT * FROM GYM G JOIN RENTAL_ITEM R ON G.Gym_id = R.Gym_id WHERE G.Name = '" + name + "'");
		String sql = sb.toString();
		return sql;
	}
}
