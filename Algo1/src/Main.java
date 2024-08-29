import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//냐옹이를 위한 사탕의 위치를 알려주는 프로그램
		//간식이 몇 번째 컵에 있는지 알려주는 프로그램
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int cup = Integer.parseInt(st.nextToken());
			int position = Integer.parseInt(st.nextToken()); //컵 번호
			int chance = Integer.parseInt(st.nextToken());
			//바꾼 두 컵의 위치 A, B가 공백으로 구분
			//간식이 위치한 종이컵이 왼쪽부터 몇 번째 종이컵인지 출력
			int[] num = new int[cup + 1]; //1,2,3
			for (int i = 1; i <= cup; i++) {
				num[i] = i; //대입
			}
			//2번째에 위치
			for (int i = 0; i < chance; i++) { //4번 swap
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); //1
				int b = Integer.parseInt(st.nextToken()); //3
				int temp = num[a];
				num[a] = num[b];
				num[b] = temp;
			}

			int ans = 0;
			for (int i = 1; i <= cup; i++) {
				if (num[i] == position) ans = i;
			}

			System.out.println("#" + t + " " + ans);

		}
	}
}