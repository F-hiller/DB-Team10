package p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QueryCreater {
	public String create(String number) throws IOException {
        switch (number) {
            case "1":
                return selectGymWithCapacity();
            case "5":
                return selectReviewWithGenderAndAge();
            case "6":
                return selectGymWithSpecializedTrainer();
            case "7":
                return selectGymWithGymId();
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
        sb.append("WHERE Capacity < "+capacity);
        String sql = sb.toString();
        return sql;
    }
    
    
    
   
    //수정됨 -> 특정 나이 추가
    //System.out.print("5. 성별을 특정한 GYM의 리뷰 조회\n ");
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
    
    //수정됨 -> Gym Location 추가
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
    	sb.append("     WHERE T.Gym_id = G.Gym_id AND Specialization = '"+specialization+"'");
    	sb.append("    ) ");
    	
    	return sb.toString();
    }

    //수정됨 -> GYM에 등록된 인원의 이름, 생일 조회
    //System.out.print("7. 특정 GYM의 ID 입력시, 해당 GYM에 등록된 인원 수 조회\n ");
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
