package p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QueryCreater {
	public String create(String number) throws IOException {
        switch (number) {
            case "1":
                return selectGymWithCapacity();
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
        sb.append("WHERE Capacity < "+capacity);
        String sql = sb.toString();
        return sql;
    }
    
    private String selectEight() throws IOException {
        StringBuffer sb = new StringBuffer();

        System.out.println("정렬 방법을 입력하세요: 'DESC' 또는 'ASC'");
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

        System.out.println("특정 나잇대(시작 YEAR)을 입력하세요: ex) 2004");
        String startYear = br.readLine();
        
        System.out.println("특정 나잇대(마지막 YEAR)을 입력하세요: ex) 2007");
        String endYear = br.readLine();

        sb.append("SELECT COUNT (*) AS USERS_NUM, G.Name AS GYM_NAME, G.Gym_id, U.Sex");
        sb.append(" FROM (ENROLLS E JOIN GYM G ON E.Gym_id = G.Gym_id) JOIN USERS U ON U.User_id = E.User_id");
        sb.append(" WHERE U.Sex = '" + sex + "' AND U.Birth_date BETWEEN '" + startYear +"-01-01' AND '" + endYear +"-12-31'");
        sb.append(" GROUP BY G.Name, G.Gym_id, U.Sex");
        sb.append(" ORDER BY USERS_NUM DESC");
        sb.append(" FETCH FIRST 10 ROWS ONLY");


        String sql = sb.toString();
        return sql;
    }
    
    private String selectTen() throws IOException {
        StringBuffer sb = new StringBuffer();

        System.out.println("특정 몸무게를 입력하세요:");
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

        System.out.println("특정 몸무게를 입력하세요:");
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

}
