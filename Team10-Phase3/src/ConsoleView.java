import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public String getCommand() throws IOException {
        System.out.println("접속할 User를 선택해주세요");

        System.out.println("원하시는 작업을 선택해주세요");
        System.out.println("개인 정보 수정(이름/전화번호)");
        System.out.println("개인 건강정보 조회");
        System.out.println("트레이너 매칭");
        System.out.println("운동기구 예약");
        System.out.println("운동기구 사용");
        System.out.println("헬스장 리뷰");
        System.out.println("대여물품 대여");
        System.out.println("헬스장 리뷰 수정");

//        select 10개
        System.out.println("~~한 헬스장 or 유저를 조회할래요");
        return br.readLine();
    }
}
