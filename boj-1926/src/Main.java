import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int col, row;
	static int[][] map;
	static boolean[][] visitied;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int photoNumber = 0, photoMAX = 0;

	//N*N만큼
	static int[] area = new int[250000];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new int[row][col];
		visitied = new boolean[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (!visitied[i][j] && map[i][j] == 1) {
					photoNumber++;
					findPicture(i, j);
				}
			}
		}

		for (int n : area) {
			if (n != 0) {
				photoMAX = Math.max(n, photoMAX);
			}
		}


		System.out.println(photoNumber); //처음 좌표 포함
		System.out.println(photoMAX);

	}

	static void findPicture(int x, int y) {

		Queue<int[]> queue = new LinkedList<>();

		queue.add(new int[] {x, y});
		visitied[x][y] = true;
		area[photoNumber]++; //처음 시작점 더해주기

		while (!queue.isEmpty()) {
			int now[] = queue.poll();
			for (int k = 0; k < 4; k++) {
				int nowX = now[0] + dx[k];
				int nowY = now[1] + dy[k];

				if (nowX >= 0 && nowY >= 0 && nowX < row && nowY < col && map[nowX][nowY] == 1) {
					if (!visitied[nowX][nowY]) {
						visitied[nowX][nowY] = true;
						queue.add(new int[] {nowX, nowY});
						area[photoNumber]++; //넓이
					}
				}
			}
		}

	}

}