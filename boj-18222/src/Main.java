import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());

		long len = 1;

		while (len < N) {
			len *= 2;
		}

		int ans = dfs(len, N);
		System.out.println(ans);

	}

	private static int dfs(long len, long idx) {

		long preLen = len / 2;

		if (idx == 1) {
			return 0;
		} else if (idx <= preLen) {
			return dfs(preLen, idx);
		} else {
			return dfs(preLen, idx - preLen) ^ 1;
		}

	}
}