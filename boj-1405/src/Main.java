import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//미친 로봇
public class Main {

	static int N;
	static double[] dir = new double[4];
	static boolean[][] visited;
	static int[] dx = {0, 0, 1, -1}; // 동서남북
	static int[] dy = {1, -1, 0, 0};
	static double ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		visited = new boolean[N * 2 + 1][N * 2 + 1];

		for (int i = 0; i < 4; i++) {
			dir[i] = Integer.parseInt(st.nextToken()) * 0.01; // 각 방향의 확률
		}

		getTotalPercent(N, N, 0, 1.0); // 초기 확률은 1.0

		System.out.println(ans);
	}

	static void getTotalPercent(int x, int y, int depth, double probability) {
		if (depth == N) { // N만큼 이동했다면 기저 사례
			ans += probability; // 이동 확률을 ans에 추가
			return;
		}

		visited[x][y] = true;

		for (int k = 0; k < 4; k++) { // 동서남북 이동
			int nowX = x + dx[k];
			int nowY = y + dy[k];
			if (nowX >= 0 && nowY >= 0 && nowX < N * 2 + 1 && nowY < N * 2 + 1) {
				if (!visited[nowX][nowY]) {
					// 다음 확률 계산
					getTotalPercent(nowX, nowY, depth + 1, probability * dir[k]);
				}
			}
		}

		visited[x][y] = false; // 방문 상태 복구
	}
}
