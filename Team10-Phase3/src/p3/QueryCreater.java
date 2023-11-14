package p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QueryCreater {
	public String create(String number) throws IOException {
        switch (number) {
            case "1":
                return selectGymWithCapacity();
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
}
