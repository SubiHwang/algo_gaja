import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test = Integer.parseInt(br.readLine());

		for (int i = 0; i < test; i++) {
			int max = 0;
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			int[][] map = new int[N][N];


			int num = Integer.parseInt(st.nextToken());
			int[] power = new int[num];

			for (int j = 0; j < num; j++) {
				power[j] = Integer.parseInt(st.nextToken());
			}

			int[] dx = {-1, 1, 0, 0, 1, -1, 1, -1};
			int[] dy = {0, 0, 1, -1, 1, -1, -1, 1};

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < num; j++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				for (int d = 0; d < 4; d++) { //한 방향에서 power 의 크기만큼 파장이 생겨야 한다.
					for (int p = 0; p < power[j]; p++) { //power 크기만큼 값 입력을 반복해준다.
						int nowX = x + dx[d];
						int nowY = y + dy[d];
						if (nowX < 0 || nowY < 0 || nowX >= N || nowY >= N) continue;
						map[nowX][nowY]++;
						x = nowX;
						y = nowY;
						max = Math.max(map[nowX][nowY], max);
					}
				}
			}

			System.out.println("#" + test + " " + max);
		}
	}
}

//3
//6
//3 3 2 2
//2 3 1 2 5 2
//6
//3 3 1 1
//1 1 2 2 6 6
//5
//4 3 2 2 1
//2 3 1 2 5 2 3 3