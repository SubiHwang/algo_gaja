import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int MAX_H = 256;
	static int[][] ground = new int[257][2];//[땅이 0-256 높이로 블록을 쌓는 경우의 수][각 경우마다 사용하는 블록 수]
	static int N, M;
	static int B;
	static int minT, nowH;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int k = 0; k <= MAX_H; k++) {
			int blockNum = B;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] > k) {
						ground[k][0] += (2 * (map[i][j] - k));
						blockNum += (map[i][j] - k);
					} else if (map[i][j] < k) {
						ground[k][0] += (k - map[i][j]);
						blockNum -= (k - map[i][j]);
					}
				}
			}
			ground[k][1] = blockNum;
		}

		minT = Integer.MAX_VALUE;
		for (int k = 0; k <= MAX_H; k++) {
			if (ground[k][1] >= 0) {
				if (ground[k][0] <= minT) {
					minT = ground[k][0];
					nowH = k;
				}
			}
		}

		System.out.println(minT + " " + nowH);

	}


}