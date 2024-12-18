import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static final String IN = "0", OUT = "1";
	static String input;
	static int T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {

			input = br.readLine();
			System.out.println(dfs(0, input.length() - 1) ? "YES" : "NO");

		}

	}

	private static boolean dfs(int startIdx, int endIdx) {

		if (startIdx == endIdx) {
			return true;
		}

		int mid = (startIdx + endIdx) / 2;

		for (int i = startIdx; i < mid; i++) { //왼쪽과 오른쪽만 살핀다.
			if (input.charAt(i) == input.charAt(endIdx - i)) {
				return false;
			}
		}

		boolean left = dfs(startIdx, mid - 1);
		boolean right = dfs(mid + 1, endIdx);
		//return left && right를 아래처럼 축약해서 썼다.

		return left && right;

		//return dfs(startIdx, mid - 1) && dfs(mid + 1, endIdx); //좌우로 나눠서 재귀를 탔을 때 둘다 true여야 true를 반환한다.
	}
}