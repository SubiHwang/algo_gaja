import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int test, ans;
	static Character[][] map;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	// static boolean[][] check;
	static Queue<int[]> queue;
	static int[][] check;
	static int N, M;

	public static void main(String[] args) throws IOException {

		// 땅으로 표현된 모든 칸에 대해서, 어떤 물인 칸으로 이동하기 위한 최소 이동 횟수
		// 모든 이동 횟수의 합

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		test = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= test; t++) {

			st = new StringTokenizer(br.readLine().trim());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new Character[N][M];
			check = new int[N][M];


			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == 'W') check[i][j] = 0;
					else check[i][j] = -1;
				}
			}

			ans = 0;
			queue = new LinkedList<>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 'W') {
						queue.add(new int[] {i, j}); //W 각 지점을 큐에 모두 넣고 한 번에 여러방면으로 파도타기!
					}
				}
			}

			bfs(); //bfs는 한 번만 실행
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (check[i][j] != 0) ans += check[i][j];
				}
			}


			System.out.println("#" + t + " " + ans);
		}

	}

	private static void bfs() {

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			for (int k = 0; k < 4; k++) {

				int nowX = now[0] + dx[k];
				int nowY = now[1] + dy[k];

				if (nowX < 0 || nowY < 0 || nowX >= N || nowY >= M || check[nowX][nowY] == 0)
					continue;

				if (check[nowX][nowY] == -1) {
					queue.add(new int[] {nowX, nowY});
					check[nowX][nowY] = check[now[0]][now[1]] + 1;
				}

			}

		}

	}

}
