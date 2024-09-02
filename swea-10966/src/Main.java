import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int test;
	static Character[][] map;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	// static boolean[][] check;
	static Queue<int[]> queue;
	static boolean[][] check;
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
			//check = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);
				}
			}

			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					check = new boolean[N][M];
					queue = new LinkedList<>();
					if (map[i][j] == 'L') {
						//System.out.println(i + " " + j);
						ans += bfs(i, j, 0);
						//System.out.println("각 최단 경로: " + bfs(i, j));
					}
				}
			}

			System.out.println("#" + t + " " + ans);
		}

	}

	private static int bfs(int x, int y, int cnt) {

		queue.add(new int[] {x, y, cnt});
		check[x][y] = true;
		// ans++;
		// 상하좌우 다 탐색해서 있으면 1칸이다.
		// 처음 출발지는 값을 더해주지 않는다.

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			for (int k = 0; k < 4; k++) {

				int nowX = now[0] + dx[k];
				int nowY = now[1] + dy[k];

				//System.out.println("x좌표: " + nowX + " y좌표: " + nowY);

				if (nowX < 0 || nowY < 0 || nowX >= N || nowY >= M || check[nowX][nowY])
					continue;

				if (map[nowX][nowY] == 'W') {
					//System.out.println("근접한 물의 좌표: " + nowX + " " + nowY);
					//System.out.println(now[2] + 1);
					return now[2] + 1;
				}

				queue.add(new int[] {nowX, nowY, now[2] + 1});
				check[nowX][nowY] = true;

			}

		}

		return 0;

	}

}
