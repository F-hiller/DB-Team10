package p3;

import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputHandler {
	private Scanner scanner;
	private QueryExecutor queryExecutor;

	public InputHandler(QueryExecutor queryExecutor) {
		this.scanner = new Scanner(System.in);
		this.queryExecutor = queryExecutor;
	}

	public void start() throws IOException {
		while (true) {
			System.out.print("[Main]: Enter SQL command or 'exit' to quit:\n>> ");
			//----------------SELECT 사용----------------//
			System.out.print("1. Capacity 이하의 수용량을 가진 Gym 찾기\n ");
			System.out.print("2. 특정 ID를 가진 USER 조회\n ");
			System.out.print("3. 전문분야와 경력을 설정하여 트레이너 조회\n ");
			System.out.print("4. 특정 GYM의 보유 장비 조회\n ");
			System.out.print("5. 성별을 특정한 GYM의 리뷰 조회\n ");
			System.out.print("6. 특정 전문분야의 트레이너를 보유한 GYM 조회\n ");
			System.out.print("7. 특정 GYM의 ID 입력시, 해당 GYM에 등록된 인원 수 조회\n ");
			System.out.print("8. GYM의 리뷰 평점순으로 정렬하여 조회\n ");
			System.out.print("9. 특정 성별,나잇대에 해당하는 GYM별 인원 수\n ");
			System.out.print("10. 특정 몸무게 이상인 USER 이름 및 등록된 GYM 조회\n ");
			//----------------DELETE, UPDATE 사용----------------//
			System.out.print("11. 접속할 User 선택\n ");
			System.out.print("12. 원하는 작업을 선택\n ");
			System.out.print("13. 개인 정보 수정(이름 및 전화번호)\n ");
			System.out.print("14. 트레이너 매칭\n ");
			System.out.print("15. 운동기구 예약\n ");
			System.out.print("16. 운동기구 사용\n ");
			System.out.print("17. 대여물품 대여\n ");
			System.out.print("18. 헬스장 리뷰 등록\n ");
			System.out.print("19. 헬스장 리뷰 수정\n ");
			System.out.print("20. 헬스장 리뷰 삭제\n ");

			String number = scanner.nextLine();
			if ("exit".equalsIgnoreCase(number)) {
				System.out.println("[System]: Program Terminated.");
				break;
			}
			QueryCreater queryCreater = new QueryCreater();
			String sql = queryCreater.create(number);
			

			// SQL 명령 실행
	         try {
	            if (sql.contains("\n")) {
	               StringTokenizer st = new StringTokenizer(sql, "\n");
	               String sql1 =st.nextToken();
	               queryExecutor.execute(sql1);
	               queryExecutor.execute(st.nextToken());
	            }
	            else queryExecutor.execute(sql);
	         } catch (Exception e) {
	            System.out.println("[System]: Error executing command: " + e.getMessage());
	            e.printStackTrace();
	         }

		}
	}
}
