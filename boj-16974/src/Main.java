import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int level;
	static long[] patty; //각 레벨의 총 패티수
	static long[] len;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		level = Integer.parseInt(st.nextToken());
		long N = Long.parseLong(st.nextToken());

		patty = new long[level + 1];
		len = new long[level + 1];
		patty[0] = 1;
		len[0] = 1;

		int k = 0;

		while (k < level) {
			k++;
			len[k] = len[k - 1] * 2 + 3;
			patty[k] = patty[k - 1] + 1 + patty[k - 1];
		}

		long ans = dfs(k, N);
		System.out.println(ans);

	}

	static private long dfs(int k, long n) {

		if (k == 0) {
			return 1;
		}

		long preLen = len[k - 1];

		if (n == 1) {
			return 0;
		} else if (n <= preLen + 1) {
			return dfs(k - 1, n - 1);
		} else if (n == preLen + 2) {
			return patty[k - 1] + 1;
		} else if (n <= preLen + 2 + preLen) {
			return patty[k - 1] + 1 + dfs(k - 1, n - preLen - 2);
		} else {
			return patty[k - 1] + 1 + patty[k - 1];
		}

	}

}