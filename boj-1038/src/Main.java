import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int col, row;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int ourNum = 0, yourNum = 0;
	static int ourMax = 0, yourMax = 0;
	static int[] ourArea = new int[10000];
	static int[] yourArea = new int[10000];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new char[row][col];
		visited = new boolean[row][col];

		for (int i = 0; i < row; i++) {
			String s = br.readLine();
			for (int j = 0; j < col; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (!visited[i][j])
					if (map[i][j] == 'W') {
						ourNum++;
						findPicture(i, j);
					}
				if (map[i][j] == 'B') {
					yourNum++;
					findPicture(i, j);
				}
			}
		}

		for (int n : ourArea) {
			if (n != 0) {
				ourMax += (n * n);
			}
		}

		for (int n : yourArea) {
			if (n != 0) {
				yourMax += (n * n);
			}
		}

		System.out.println(ourMax);
		System.out.println(yourMax);
	}

	static void findPicture(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});

		visited[x][y] = true;

		if (map[x][y] == 'W') {
			ourArea[ourNum]++;
		} else {
			yourArea[yourNum]++;
		}

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int k = 0; k < 4; k++) {
				int nowX = now[0] + dx[k];
				int nowY = now[1] + dy[k];

				if (nowX >= 0 && nowY >= 0 && nowX < row && nowY < col) {
					if (!visited[nowX][nowY]) {
						if (map[nowX][nowY] == 'W') {
							visited[nowX][nowY] = true;
							queue.add(new int[] {nowX, nowY});
							ourArea[ourNum]++;
						} else {
							visited[nowX][nowY] = true;
							queue.add(new int[] {nowX, nowY});
							yourArea[yourNum]++;
						}
					}
				}
			}
		}

	}
}
