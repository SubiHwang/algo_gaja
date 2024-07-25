import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//안전 영역
public class Main {

	static int N, MAX;
	static int[][] map;
	static boolean[][] isNotSafe;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int MAX_H;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		isNotSafe = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		MAX_H = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				MAX_H = Math.max(MAX_H, map[i][j]);
			}
		}

		for (int k = 0; k <= MAX_H; k++) {

			int cnt = 0;

			isNotSafe = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] <= k) {
						isNotSafe[i][j] = true;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!isNotSafe[i][j]) {
						cnt++;
						getAreaCount(i, j);
					}
				}
			}

			MAX = Math.max(MAX, cnt);

		}

		System.out.println(MAX);

	}

	static void getAreaCount(int x, int y) {

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		isNotSafe[x][y] = true;

		while (!queue.isEmpty()) {

			int[] now = queue.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nowX = now[0] + dx[dir];
				int nowY = now[1] + dy[dir];

				if (nowX >= 0 && nowY >= 0 && nowX < N && nowY < N) {
					if (!isNotSafe[nowX][nowY]) {
						isNotSafe[nowX][nowY] = true;
						queue.add(new int[] {nowX, nowY});
					}
				}
			}
		}
	}

}
