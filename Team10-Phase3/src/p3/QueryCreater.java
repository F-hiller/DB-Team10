package p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
		case "11":
			return selectGymsWithUser();
		case "12":
			return selectTrainerWithTwoSpecialization();
		case "13":
			return select13();
		case "14":
			return select14();
		case "15":
			return select15();
		case "16":
			return select16();
		case "17":
			return rentItemByUser();
		case "18":
			return addReview();
		case "19":
			return updateReview();
		case "20":
			return deleteReview();
		}
		return null;
	}

	//1. 특정 Capacity 이하의 수용량을 가진 Gym 찾기
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

	//2. 특정 ID를 가진 USER 조회
	private String selectUserWithId() throws IOException {
		StringBuffer sb = new StringBuffer();

		System.out.print("User의 Id를 입력하세요:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String userId = br.readLine();
		sb.append("SELECT * FROM USERS U WHERE U.User_id = " + userId);
		String sql = sb.toString();
		return sql;
	}

	//3. 전문분야와 경력을 설정하여 트레이너 조회 
	private String selectTrainerWithSpecAndYear() throws IOException {
		StringBuffer sb = new StringBuffer();

		System.out.print("Specialization을 입력하세요: ex) Bulk up, Conditioning");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String specialization = br.readLine();

		System.out.print("최소 경력을 입력하세요:");
		String minYear = br.readLine();
		System.out.print("최대 경력을 입력하세요:");
		String maxYear = br.readLine();

		sb.append("SELECT * FROM TRAINER T WHERE T.Specialization = '" + specialization + "' AND T.Work_year BETWEEN "
				+ minYear + " AND " + maxYear);
		String sql = sb.toString();
		return sql;
	}

	//4. 특정 GYM의 보유 장비 조회
	private String selectMachineWithGym() throws IOException {
		StringBuffer sb = new StringBuffer();

		System.out.print("체육관 이름을 입력하세요: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String name = br.readLine();

		sb.append("SELECT * FROM GYM G JOIN RENTAL_ITEM R ON G.Gym_id = R.Gym_id WHERE G.Name = '" + name + "'");
		String sql = sb.toString();
		return sql;
	}

	private String selectEight() throws IOException {
		StringBuffer sb = new StringBuffer();

		System.out.print("정렬 방법을 입력하세요: 'DESC(평점 높은 순)' 또는 'ASC(평점 낮은 순)'");
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

		System.out.print("특정 성별을 입력하세요: ex) M or F");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String sex = br.readLine();

		System.out.print("특정 나잇대(생일 시작 YEAR)을 입력하세요: ex) 2004");
		String startYear = br.readLine();

		System.out.print("특정 나잇대(생일 마지막 YEAR)을 입력하세요: ex) 2007");
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

		System.out.print("특정 몸무게를 입력하세요: ");
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

		System.out.print("특정 몸무게를 입력하세요: ");
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
	// System.out.print("5. 특정 나이 이상, 성별에 해당하는 유저의 GYM의 리뷰 수 조회\n ");
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
	// System.out.print("6. 특정 전문분야의 트레이너를 보유한 GYM 이름, 위치 조회\n ");
	private String selectGymWithSpecializedTrainer() throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Body Profile, Diet, Conditioning, Bulk Up, Body Building, Power Lifting");
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
		sb.append("WHERE Gym_id =" + gymId);

		return sb.toString();
	}

	private String select13() throws IOException {
		StringBuffer sb = new StringBuffer();

		System.out.print("USER ID를 입력하세요 (1~1000):");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String userId = br.readLine();

		System.out.print("수정할 USER 이름을 입력하세요:");
		String name = br.readLine();

		System.out.print("수정할 전화번호를 입력하세요 (ex 010-1111-1111):");
		String phoneNumber = br.readLine();

		sb.append("UPDATE USERS");
		sb.append(" SET NAME='" + name + "', PHONE_NUMBER='" + phoneNumber + "'");
		sb.append(" WHERE USER_ID=" + userId);

		String sql = sb.toString();
		return sql;
	}
	private String select14() throws IOException {
		StringBuffer sb = new StringBuffer();

		System.out.print("USER ID를 입력하세요 (1~1000):");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String userId = br.readLine();

		System.out.print("트레이너 ID를 입력하세요 (1~100):");
		String trainerId = br.readLine();

		sb.append("UPDATE USERS");
		sb.append(" SET TRAINER_ID=" + trainerId);
		sb.append(" WHERE USER_ID=" + userId);

		String sql = sb.toString();
		return sql;
	}
	//--------------------------15-----------------------------------
	private String updateUsersQuery(String userId, String machineId) {
	    StringBuffer sb = new StringBuffer();
	    sb.append("UPDATE USERS");
	    sb.append(" SET RESERVE_MACHINE_ID=" + machineId);
	    sb.append(" WHERE USER_ID=" + userId);
	    return sb.toString();
	}

	private String updateMachineQuery(String machineId) {
	    StringBuffer sb = new StringBuffer();
	    sb.append("UPDATE MACHINE");
	    sb.append(" SET STATE='non_reservable'");
	    sb.append(" WHERE MACHINE_ID=" + machineId);
	    return sb.toString();
	}

	private String select15() throws IOException {
	    System.out.print("USER ID를 입력하세요 (1~1000):");
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String userId = br.readLine();

	    System.out.print("예약할 Machine ID를 입력하세요 (1~1600):");
	    String machineId = br.readLine();

	    // 첫 번째 쿼리: 사용자의 reserve_machine_id 업데이트
	    String updateUsersSql = updateUsersQuery(userId, machineId);

	    // 두 번째 쿼리: 기계의 state 업데이트
	    String updateMachineSql = updateMachineQuery(machineId);

	    // 이제 두 개의 SQL 문을 각각 실행하거나 필요에 따라 조합하여 사용할 수 있음

	    return updateUsersSql + "\n" + updateMachineSql;
	}

	//---------------------------16---------------------------------
	private String updateUsingMachineIdQuery(String userId, String machineId) {
	    StringBuffer sb = new StringBuffer();
	    sb.append("UPDATE USERS");
	    sb.append(" SET USING_MACHINE_ID=" + machineId);
	    sb.append(" WHERE USER_ID=" + userId);
	    return sb.toString();
	}

	private String updateMachineStateQuery(String machineId, String newState) {
	    StringBuffer sb = new StringBuffer();
	    sb.append("UPDATE MACHINE");
	    sb.append(" SET STATE='" + newState + "'");
	    sb.append(" WHERE MACHINE_ID=" + machineId);
	    return sb.toString();
	}

	private String select16() throws IOException {
	    System.out.print("USER ID를 입력하세요 (1~1000):");
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String userId = br.readLine();

	    System.out.print("사용할 Machine ID를 입력하세요 (1~1600):");
	    String machineId = br.readLine();

	    // 첫 번째 쿼리: 사용자의 using_machine_id 업데이트
	    String updateUsingMachineIdSql = updateUsingMachineIdQuery(userId, machineId);

	    // 두 번째 쿼리: 기계의 state 업데이트
	    String updateMachineStateSql = updateMachineStateQuery(machineId, "reservable");

	    // 이제 두 개의 SQL 문을 각각 실행하거나 필요에 따라 조합하여 사용할 수 있음

	    return updateUsingMachineIdSql + "\n" + updateMachineStateSql;
	}

	private String selectGymsWithUser() throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("User id를 입력해주세요 (1~1000): ");
		String userId = br.readLine();

		sb.append(
				"SELECT G.NAME, G.LOCATION FROM GYM     G, USERS   U, ENROLLS E WHERE G.GYM_ID = E.GYM_ID AND E.USER_ID = U.USER_ID AND U.USER_ID = "
						+ userId);

		return sb.toString();
	}

	//12 
	private String selectTrainerWithTwoSpecialization() throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("첫번째 관심 Specialization을 입력해주세요: ex)Bulk Up, Diet");
		String spec1 = br.readLine();
		System.out.print("두번째 관심 Specialization을 입력해주세요: ex)Bulk Up, Diet");
		String spec2 = br.readLine();

		sb.append("SELECT Name, Contact FROM TRAINER WHERE Specialization IN ( '" + spec1 + "', '" + spec2 + "' )");

		return sb.toString();
	}
	
	//--17. 대여물품 대여 (1번유저가 3번 헬스장의 Strap을 빌리는 경우)
		private String rentItemByUser() throws IOException {
			StringBuffer sb = new StringBuffer();
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	
	    	System.out.print("User id를 입력해주세요 (1~1000): ");
	    	String userId = br.readLine();
	    	
	    	System.out.print("Gym id를 입력해주세요 (1~100): ");
	    	String gymId = br.readLine();
	    	
	    	System.out.print("rental Item을 입력해주세요(Fitness Band, Locker, Strap, Towel, Fitness wear): ");
	    	String itemName = br.readLine();
	    	
	    	String valueFormat = "VALUES (%s, %s, '%s')";
	    	
	    	sb.append("INSERT INTO RENTS(User_id, Gym_id, Item_name) ");
	    	sb.append(String.format(valueFormat, userId, gymId, itemName));
	    	return sb.toString();
		}
		
		
		//--18. 헬스장 리뷰 등록
		private String addReview() throws IOException {
			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.print("rating를 입력해주세요(1~5): ");
			String rating = br.readLine();

			System.out.print("comment를 입력해주세요: ");
			String comment = br.readLine();

			System.out.print("User id를 입력해주세요 (1~1000): ");
			String userId = br.readLine();

			System.out.print("Gym id를 입력해주세요 (1~100): ");
			String gymId = br.readLine();

			LocalDate currentDate = LocalDate.now();

			// 날짜를 원하는 형식으로 포맷팅
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedDate = currentDate.format(formatter);

			sb.append("INSERT INTO REVIEW(Review_id, Rating, User_Comment, Created_date, User_id, Gym_id) ");
			sb.append(
					"VALUES ((Select Review_id from (SELECT Review_id from review order by Review_id desc) where ROWNUM = 1)+1, "
							+ rating + ", '" + comment + "', " + "TO_DATE('" + formattedDate + "', 'YYYY-MM-DD'), " + userId
							+ ", " + gymId + ")");
			return sb.toString();
		}
		
		//--19. 헬스장 리뷰 수정
		private String updateReview() throws IOException {
			StringBuffer sb = new StringBuffer();
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	
	    	System.out.print("수정할 Review id를 입력해주세요: ");
	    	String reviewId = br.readLine();
	    	
	    	System.out.print("수정할 rating를 입력해주세요: ");
	    	String rating = br.readLine();
	    	
	    	System.out.print("수정할 comment를 입력해주세요: ");
	    	String comment = br.readLine();
	    	
	    	LocalDate currentDate = LocalDate.now();

	    	
	        // 날짜를 원하는 형식으로 포맷팅
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String formattedDate = currentDate.format(formatter);
	    	sb.append("UPDATE REVIEW ");
	    	sb.append("SET Rating = "+rating+", ");
	    	sb.append("    User_comment = '"+comment+"', ");
	    	sb.append("    Created_date = TO_DATE('"+formattedDate+"', 'YYYY-MM-DD') ");
	    	sb.append(" WHERE Review_id = "+reviewId);
	    	
	    	return sb.toString();
		}
		
		//--20. 헬스장 리뷰 삭제
		private String deleteReview() throws IOException {
			StringBuffer sb = new StringBuffer();
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	
	    	System.out.print("삭제할 Review id를 입력해주세요: ");
	    	String reviewId = br.readLine();
	    	
	    	sb.append("DELETE REVIEW WHERE REVIEW_ID = "+reviewId);
	    	return sb.toString();
		}
}
