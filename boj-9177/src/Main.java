import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N, M;
	static char[][] maps;
	static Queue<Node> queue = new LinkedList<>();
	static boolean[][][] check;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maps = new char[N][M];
		check = new boolean[1 << 6][N][M]; //모든 열쇠를 하나만 가질 때부터 모든 열쇠를 모두 가질 때까지의 모든 경우를 탐색해주어야 한다.

		int sX = 0, sY = 0;

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				maps[i][j] = s.charAt(j);
				if (maps[i][j] == '0') {
					sX = i;
					sY = j;
				}
			}
		}

		int ans = bfs(sX, sY);
		System.out.println(ans);

	}

	private static int bfs(int x, int y) {

		//0이면 열쇠가 없는 상태에서 가는 상황

		queue.add(new Node(x, y, 0, 0));
		check[0][x][y] = true;


		while (!queue.isEmpty()) {

			Node node = queue.poll();
			int checkKey = node.key;

			if (maps[node.x][node.y] == '1') {
				return node.cnt;
			}

			for (int k = 0; k < 4; k++) {
				int nowX = node.x + dx[k];
				int nowY = node.y + dy[k];

				if (nowX < 0 || nowY < 0 || nowX >= N || nowY >= M || maps[nowX][nowY] == '#' || check[checkKey][nowX][nowY])
					continue;

				char num = maps[nowX][nowY];

				if (num == '.' || num == '0' || num == '1') { //도착지도 갈 수 있는 경우이다.
					queue.add(new Node(nowX, nowY, node.cnt + 1, checkKey));
					check[checkKey][nowX][nowY] = true;
				} else if (num == 'a' || num == 'b' || num == 'c' || num == 'd' || num == 'e' || num == 'f') {
					int newKey = checkKey | (1 << (num - 'a')); //OR 연산으로 기존 가지고 있는 열쇠에서 현재 열쇠를 더해준다.
					//오른쪽부터 abcdef (111111)
					//b만 있을 경우 (000010)
					//a도 획득했을 경우 (000011) 0을 1로 바꿔준다.
					queue.add(new Node(nowX, nowY, node.cnt + 1, newKey));
					check[newKey][nowX][nowY] = true;
				} else if (num == 'A' || num == 'B' || num == 'C' || num == 'D' || num == 'E' || num == 'F') {
					//가지고 있는게 ab 즉, 000011일 때 A를 열수 있는지 확인하려면 000011 & 000001(a)를 and 연산한다.
					//a를 가지고 있다면 000001이 출력된다.
					if ((checkKey & (1 << (num - 'A'))) == 1 << num - 'A') {
						queue.add(new Node(nowX, nowY, node.cnt + 1, checkKey));
						check[checkKey][nowX][nowY] = true;
					}
				}

			}
		}

		return -1;

	}

	static class Node {
		int x;
		int y;
		int cnt;
		int key;

		Node(int x, int y, int cnt, int key) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.key = key;
		}

	}

}