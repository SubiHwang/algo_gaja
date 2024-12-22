import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static char[][] maps;
	static boolean[][] check;
	static int ans;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static Queue<int[]> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		maps = new char[N][M];
		check = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				maps[i][j] = s.charAt(j);
			}
		}

		check[N - 1][0] = true;
		dfs(N - 1, 0, 1);

		System.out.println(ans);

	}

	private static void dfs(int x, int y, int depth) {


		if (x == 0 && y == M - 1) {
			if (depth == K) ans++;
			return;
		}

		for (int k = 0; k < 4; k++) {
			int nowX = x + dx[k];
			int nowY = y + dy[k];

			if (nowX < 0 || nowY < 0 || nowX >= N || nowY >= M || check[nowX][nowY] || maps[nowX][nowY] == 'T')
				continue;

			check[nowX][nowY] = true;
			dfs(nowX, nowY, depth + 1);
			check[nowX][nowY] = false;

		}


	}


}