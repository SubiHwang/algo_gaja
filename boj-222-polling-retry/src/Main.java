import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = dfs(0, 0, N); //한 변의 길이가 size 이다.
		System.out.println(ans);

	}

	private static int dfs(int row, int col, int size) {

		if (size == 1) {
			//presize == 1로 하면 분할된 블록의 크기가 1인 경우 나누지 않겠다는 뜻이된다.
			return map[row][col];
		}
		int preSize = size / 2;


		int[] nums = new int[4];
		nums[0] = dfs(row, col, preSize);
		nums[1] = dfs(row + preSize, col, preSize);
		nums[2] = dfs(row, col + preSize, preSize);
		nums[3] = dfs(row + preSize, col + preSize, preSize);

		Arrays.sort(nums);

		return nums[2];
	}
}