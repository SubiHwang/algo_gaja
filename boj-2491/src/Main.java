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

		int max = 0;
		for (int i = 2; i <= N; i++) {
			if (arr[i] >= arr[i - 1]) {
				dp[i] = dp[i - 1] + 1;
			}
			max = Math.max(max, dp[i]);
		}

		for (int i = 2; i <= N; i++) {
			if (arr[i] <= arr[i - 1]) {
				dpp[i] = dpp[i - 1] + 1;
			}
			max = Math.max(max, dpp[i]);
		}

		System.out.println(max);

	}
}