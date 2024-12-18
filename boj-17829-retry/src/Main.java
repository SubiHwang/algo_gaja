import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] maps;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		maps = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = dfs(0, 0, N);
		System.out.println(ans);

	}

	private static int dfs(int startRowIdx, int startColIdx, int size) {

		if (size == 1) {
			return maps[startRowIdx][startColIdx];
		}

		int nextSize = size / 2;

		int[] nums = new int[4];

		//나무를 타고 내려오면서 마지막 한 집합에 대해 -6, -8, -5, -5에 대해 2번쨰 값 계산
		nums[0] = dfs(startRowIdx, startColIdx, nextSize);
		nums[1] = dfs(startRowIdx, startColIdx + nextSize, nextSize);
		nums[2] = dfs(startRowIdx + nextSize, startColIdx, nextSize);
		nums[3] = dfs(startRowIdx + nextSize, startColIdx + nextSize, nextSize);

		Arrays.sort(nums);

		return nums[2];

	}
}