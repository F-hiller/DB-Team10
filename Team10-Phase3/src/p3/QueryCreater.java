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
		case "5":
			return selectReviewWithGenderAndAge();
		case "6":
			return selectGymWithSpecializedTrainer();
		case "7":
			return selectGymWithGymId();
		case "8":
			return selectEight();
		case "9":
			return selectNine();
		case "10":
			return selectTen();
		case "10-1":
			return selectTen2();
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

		System.out.println("체육관 이름을 입력하세요: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String name = br.readLine();

		sb.append("SELECT * FROM GYM G JOIN RENTAL_ITEM R ON G.Gym_id = R.Gym_id WHERE G.Name = '" + name + "'");
		String sql = sb.toString();
		return sql;
	}

	private String selectEight() throws IOException {
		StringBuffer sb = new StringBuffer();

		System.out.println("정렬 방법을 입력하세요: 'DESC(평점 높은 순)' 또는 'ASC(평점 낮은 순)'");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String sortOrder = br.readLine();

		sb.append("SELECT ROUND(AVG(R.Rating), 2) AS AVG_RATE, G.GYM_ID, G.NAME, G.LOCATION");
		sb.append(" FROM GYM G");
		sb.append(" JOIN REVIEW R ON G.GYM_ID = R.GYM_ID");
		sb.append(" GROUP BY G.GYM_ID, G.NAME, G.LOCATION");
		sb.append(" ORDER BY AVG_RATE " + sortOrder);
		sb.append(" FETCH FIRST 10 ROWS ONLY");
        
        String sql = sb.toString();
        return sql;

    }

	private String selectNine() throws IOException {
		StringBuffer sb = new StringBuffer();

		System.out.println("특정 성별을 입력하세요: ex) M or F");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String sex = br.readLine();

		System.out.println("특정 나잇대(생일 시작 YEAR)을 입력하세요: ex) 2004");
		String startYear = br.readLine();

		System.out.println("특정 나잇대(생일 마지막 YEAR)을 입력하세요: ex) 2007");
		String endYear = br.readLine();

		sb.append("SELECT COUNT (*) AS USERS_NUM, G.Name AS GYM_NAME, G.Gym_id, U.Sex");
		sb.append(" FROM (ENROLLS E JOIN GYM G ON E.Gym_id = G.Gym_id) JOIN USERS U ON U.User_id = E.User_id");
		sb.append(" WHERE U.Sex = '" + sex + "' AND U.Birth_date BETWEEN '" + startYear + "-01-01' AND '" + endYear
				+ "-12-31'");
		sb.append(" GROUP BY G.Name, G.Gym_id, U.Sex");
		sb.append(" ORDER BY USERS_NUM DESC");
		sb.append(" FETCH FIRST 10 ROWS ONLY");

		String sql = sb.toString();
		return sql;
	}

	private String selectTen() throws IOException {
		StringBuffer sb = new StringBuffer();

		System.out.println("특정 몸무게를 입력하세요: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String weight = br.readLine();

		sb.append("SELECT U.NAME AS USER_NAME, G.NAME AS GYM_NAME");
		sb.append(" FROM USERS U");
		sb.append(" JOIN ENROLLS E ON U.USER_ID = E.USER_ID");
		sb.append(" JOIN GYM G ON E.GYM_ID = G.GYM_ID");
		sb.append(" JOIN HEALTH_INFO H ON U.USER_ID = H.USER_ID");
		sb.append(" WHERE H.WEIGHT >= " + weight);
		sb.append(" ORDER BY H.WEIGHT DESC");
		sb.append(" FETCH FIRST 10 ROWS ONLY");

		String sql = sb.toString();
		return sql;
	}

	private String selectTen2() throws IOException {
		StringBuffer sb = new StringBuffer();

		System.out.println("특정 몸무게를 입력하세요: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String weight = br.readLine();

		sb.append("SELECT COUNT(*) AS USERS_NUM, G.NAME AS GYM_NAME, G.LOCATION");
		sb.append(" FROM USERS U");
		sb.append(" JOIN ENROLLS E ON U.USER_ID = E.USER_ID");
		sb.append(" JOIN GYM G ON E.GYM_ID = G.GYM_ID");
		sb.append(" JOIN HEALTH_INFO H ON U.USER_ID = H.USER_ID");
		sb.append(" WHERE H.WEIGHT >= " + weight);
		sb.append(" GROUP BY G.NAME, G.LOCATION");

		String sql = sb.toString();
		return sql;
	}


	// 수정됨 -> 특정 나이 추가
	// System.out.print("5. 성별을 특정한 GYM의 리뷰 조회\n ");
	private String selectReviewWithGenderAndAge() throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String whereFormat = "WHERE U.Sex = '%s' AND U.Birth_date < TO_DATE('%s','YYYY-MM-DD')";
		System.out.print("Gender를 입력하세요(M/F):");
		String gender = br.readLine();

		System.out.print("Upper Birthday를 입력하세요(YYYY-MM-DD):");
		String upperBirthday = br.readLine();

		sb.append("SELECT U.Name, TO_CHAR(U.Birth_date,'YYYY-MM-DD') as BIRTHDAY,U.Sex, COUNT(*) as Review_COUNT ");
		sb.append("FROM REVIEW R JOIN USERS U ON R.User_id = U.User_id ");
		sb.append(String.format(whereFormat, gender, upperBirthday));
		sb.append("GROUP BY U.Name, TO_CHAR(U.Birth_date,'YYYY-MM-DD'),U.Sex ");
		sb.append("ORDER BY COUNT(*) ");
		String sql = sb.toString();
		return sql;
	}

	// 수정됨 -> Gym Location 추가
	// System.out.print("6. 특정 전문분야의 트레이너를 보유한 GYM 조회\n ");
	private String selectGymWithSpecializedTrainer() throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Body Profile, Diet, Conditioning, Bulk Up, Body Building, Power Lifting");
		System.out.print("Trainer의 전문분야를 입력하세요(위 예시 중 입력): ");
		String specialization = br.readLine();
		sb.append("SELECT G.Name as GYM_NAME, G.Location as GYM_LOCATION ");
		sb.append("FROM GYM G ");
		sb.append("WHERE EXISTS ( ");
		sb.append("    SELECT T.TRAINER_ID  ");
		sb.append("    FROM TRAINER T ");
		sb.append("     WHERE T.Gym_id = G.Gym_id AND Specialization = '" + specialization + "'");
		sb.append("    ) ");

		return sb.toString();
	}

	// 수정됨 -> GYM에 등록된 인원의 이름, 생일 조회
	// System.out.print("7. 특정 GYM의 ID 입력시, 해당 GYM에 등록된 인원 수 조회\n ");
	private String selectGymWithGymId() throws IOException {
    	StringBuffer sb = new StringBuffer();
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
    	System.out.print("Gym id를 입력해주세요 (1~100): ");
    	String gymId = br.readLine();
    	
    	sb.append("SELECT Name as USER_NAME,TO_CHAR(Birth_date,'YYYY-MM-DD') as USER_BIRTHDAY ");
    	sb.append("FROM ( ");
    	sb.append("    SELECT U.Name, U.Birth_date, E.Gym_id ");
    	sb.append("    FROM USERS U JOIN ENROLLS E ON U.User_id = E.User_id ) ");
    	sb.append("WHERE Gym_id =" +gymId);
    	
    	return sb.toString();
    }
}
