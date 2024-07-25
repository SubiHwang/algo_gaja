import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int MAX_COUNT = 3; //심을 수 있는 꽃의 MAX값
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0, 0}; // 상, 우, 하, 좌, 현재
	static int[] dy = {0, 1, 0, -1, 0};
	static int answer, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];
		answer = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0);

		System.out.println(answer);

		br.close();
	}

	//두 번째 꽃까지 심은 후 마지막 꽃에 대해서 모든 경우의 수를 구한 후 최소 비용 도출
	//첫 번째 꽃까지 심은 후 두 번째, 세 번쨰 꽃에 대해서 모든 경우의 수를 구한 후 최소 비용 도출
	//시작점을 옮기고 이 과정을 반복
	static void dfs(int count, int cost) { //꽃의 개수, 비용

		if (count == MAX_COUNT) { //기저 사례
			answer = Math.min(answer, cost);
			return;
		}

		for (int x = 1; x < N - 1; x++) { //씨앗을 심을 수 있는 범위
			for (int y = 1; y < N - 1; y++) {

				boolean canPlant = true;

				// 현재 위치와 주변 4칸이 방문되지 않았는지 확인
				for (int dir = 0; dir < 5; dir++) {
					int nowX = x + dx[dir];
					int nowY = y + dy[dir];

					if (visited[nowX][nowY]) { //한 곳이라도 방문했다면
						canPlant = false; //심을 수 없음
						break; //dir for문 탈출
					}
				}

				if (canPlant) {
					// 방문 처리 및 비용 계산
					for (int dir = 0; dir < 5; dir++) {
						int nowX = x + dx[dir];
						int nowY = y + dy[dir];

						visited[nowX][nowY] = true; // 꽃 피운 구역 방문
						cost += map[nowX][nowY]; // 꽃 피운 비용
					}

					dfs(count + 1, cost); // 다음 꽃 심기

					// 방문 해제 및 비용 복구
					for (int dir = 0; dir < 5; dir++) {
						int nowX = x + dx[dir];
						int nowY = y + dy[dir];

						visited[nowX][nowY] = false;
						cost -= map[nowX][nowY]; // 비용 복구
					}
				}
			}
		}

	}

}