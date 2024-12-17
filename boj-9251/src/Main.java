import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s1 = br.readLine();
		String s2 = br.readLine();

		dp = new int[s2.length() + 1][s1.length() + 1];

		for (int i = 1; i < s2.length() + 1; i++) {
			for (int j = 1; j < s1.length() + 1; j++) {
				if (s2.charAt(i - 1) == s1.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

//		for (int i = 0; i < s2.length() + 1; i++) {
//			for (int j = 0; j < s1.length() + 1; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}

		System.out.println(dp[s2.length()][s1.length()]);

	}
}