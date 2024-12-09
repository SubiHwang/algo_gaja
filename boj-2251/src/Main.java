import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, k, p, x;
	static int[] origin;
	static int[][] map = {
			{1, 1, 1, 1, 1, 1, 0}, //0
			{0, 1, 1, 0, 0, 0, 0}, //1
			{1, 1, 0, 1, 1, 0, 1}, //2
			{1, 1, 1, 1, 0, 0, 1}, //3
			{0, 1, 1, 0, 0, 1, 1}, //4
			{1, 0, 1, 1, 0, 1, 1}, //5
			{1, 0, 1, 1, 1, 1, 1}, //6
			{1, 1, 1, 0, 0, 0, 0}, //7
			{1, 1, 1, 1, 1, 1, 1}, //8
			{1, 1, 1, 1, 0, 1, 1}  //9
	};

	static int[] temp;
	static int ans = 0;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); //1-n 이하 수
		k = Integer.parseInt(st.nextToken()); //k자리 수
		p = Integer.parseInt(st.nextToken()); //1-p까지 반전
		x = Integer.parseInt(st.nextToken()); //x층에 멈춰있음

		// 앞에 0을 채워서 K 자리로 만들겠다.
		String str = String.format("%0" + k + "d", x);

		origin = new int[k];
		for (int i = 0; i < k; i++) {
			origin[i] = str.charAt(i) - '0';
		} //end of make origin

		temp = new int[k];

		findSol(0, 0);

		System.out.println(ans);

	}

	private static void findSol(int idx, int total) {

		if (total > n) {
			return;
		}

		if (idx == k) {

			if (total <= 0) return;

			//System.out.println("완성된 수 : " + total);

			//System.out.println(Arrays.toString(origin));
			//System.out.println(Arrays.toString(temp));

			int cnt = 0;
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < 7; j++) {
					int result = map[temp[i]][j] ^ map[origin[i]][j];
					if (result == 1) {
						cnt++;
					}
				}
			} //바뀐 전체 led 수

			//System.out.println(cnt);

			if (cnt >= 1 && cnt <= p) { //구간 안에 있는 led 개수라면
				//ystem.out.println("반전 가능한 수 : " + cnt);
				ans++;
			}

			return;
		}

		for (int i = 0; i <= 9; i++) {
			temp[idx] = i;
			//k=5라면
			//0번째 숫자는 10000 * (숫자) -> (10, k-1 에서 - idx)
			//1번째 숫자는 1000 * (숫자)
			int num = i * (int)Math.pow(10, k - 1 - idx);
			findSol(idx + 1, total + num);
		}

	}


}