import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] maps;
	static int N;

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

	private static int dfs(int row, int col, int size) {

		if (size == 1) {
			return maps[row][col];
		}

		int preSize = size / 2;
		int[] nums = new int[4];
		nums[0] = dfs(row, col, preSize);
		nums[1] = dfs(row + preSize, col, preSize);
		nums[2] = dfs(row, col + preSize, preSize);
		nums[3] = dfs(row + preSize, col + preSize, preSize);

		Arrays.sort(nums);

		return nums[1];

	}
}