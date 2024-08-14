import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());

		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N + 1];
		int[] dpp = new int[N + 1];

		Arrays.fill(dp, 1);
		Arrays.fill(dpp, 1);

		//dp[n] = n번 자리에서 가장 긴 증가하는 수열의 길이
		int max = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j < i - 1; j++) {
				if (arr[i] < arr[j]) {
					dp[i] = Math.max(dp[i], dp[j]);
				} else {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[i]);
			max = Math.max(max, dpp[i]);
		}

		System.out.println(max);

	}
}