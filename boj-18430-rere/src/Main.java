import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] maps;
	static int[][] fdir = {{0, -1}, {0, -1}, {-1, 0}, {0, 1}};
	static int[][] tdir = {{1, 0}, {-1, 0}, {0, 1}, {1, 0}};
	static boolean[][] check;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maps = new int[N][M];
		check = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0);
		System.out.println(max);

	}

	private static void dfs(int depth, int sum) {

		if (depth == N * M) {
			max = Math.max(max, sum);
			return;
		}

		//현재 중심에 도달했다.

		int mx = depth / M;
		int my = depth % M;

		//부메랑을 놓는 경우와 안 놓는 경우 2가지로 분리한다.
		//어떻게 분리?

		//부메랑을 안 놓는 경우
		dfs(depth + 1, sum); //예는 아무것도 안 놓는 거니까 check를 안한다.
		//부메랑이 놓는 자리만 true이다.

		//부메랑을 놓는 경우
		if (!check[mx][my]) {
			//이전의 오른쪽 날개가 요번의 중심이 될 수도 있는데, 이때 양날개는 true이고 중심은 false일 경우 못 놓으므로
			//들어가기전 중심을 체크해야 한다.
			for (int k = 0; k < 4; k++) {
				int fx = mx + fdir[k][0];
				int fy = my + fdir[k][1];
				int tx = mx + tdir[k][0];
				int ty = my + tdir[k][1];

				if (isPossible(fx, fy, tx, ty)) {
					check[fx][fy] = true;
					check[tx][ty] = true;
					check[mx][my] = true;
					dfs(depth + 1, sum + (maps[mx][my] * 2) + maps[fx][fy] + maps[tx][ty]);
					check[fx][fy] = false;
					check[tx][ty] = false;
					check[mx][my] = false;
				}
			}
		}

	}

	private static boolean isPossible(int fx, int fy, int tx, int ty) {

		if (fx < 0 || fy < 0 || fx >= N || fy >= M || check[fx][fy]) return false;
		if (tx < 0 || ty < 0 || tx >= N || ty >= M || check[tx][ty]) return false;

		return true;

	}

}