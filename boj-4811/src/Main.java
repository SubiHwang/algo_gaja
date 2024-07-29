import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int MAX_P = 30;
	static long[][] dp = new long[MAX_P + 1][MAX_P + 1];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// dp[i][j] = i개의 완전한 알약, j개의 반절짜리 약이 남았을 때 조합의 수
		//제일 처음에는 완전한 알약을 꺼내야한다.
		for (int i = 1; i <= MAX_P; i++) {
			dp[0][i] = 1;
		}

		//메모이제이션 : 항상 동일한 결과를 도출한다면 한 번만 구하도록 한다.
		getDaughterText();

		for (int i = 0; i <= MAX_P; i++) {
			for (int j = 0; j <= MAX_P; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}


		while (true) {

			N = Integer.parseInt(br.readLine());

			if (N == 0) {
				return;
			}

			System.out.println(dp[N][0]);
		}


	}

	//각 배열에는 이전 경우의 수 + 현재 경우의 수가 담겨있다.
	static void getDaughterText() {

		for (int i = 1; i <= MAX_P; i++) {
			for (int j = 0; j < MAX_P; j++) {
				//점화식 : 현재 가능한 경우의 수 = 이전에 반쪽 자리 알역을 구하는 경우의 수 + 이전에 한 알 자리 알약을 구하는 경우의 수
				//i : 완전한 알약, j : 반쪽 자리 알약
				if (j == 0) dp[i][j] = dp[i - 1][j + 1];
				else dp[i][j] = dp[i][j - 1] + dp[i - 1][j + 1];
			}
		}
	}

}