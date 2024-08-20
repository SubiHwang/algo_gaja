import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int x;
	int y;
	int cnt;

	public Node(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Node o) {
		return this.cnt - o.cnt;
	}
}

public class Main {
	static int N, M;
	static int[][] map;

	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //4
		M = Integer.parseInt(st.nextToken()); //2
		//2행 4열

		map = new int[M][N];

		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(s.substring(j, j + 1));
			}
		}

		int ans = bfs(0, 0);
		System.out.println(ans);
		br.close();

	}

	private static int bfs(int x, int y) {
		//벽 부순 개수를 오름차순
		PriorityQueue<Node> queue = new PriorityQueue<>();
		visited = new boolean[M][N];

		visited[x][y] = true;
		queue.offer(new Node(x, y, 0));

		while (!queue.isEmpty()) {
			Node node = queue.poll();

			if (node.x == M - 1 && node.y == N - 1) {
				return node.cnt;
			}

			for (int k = 0; k < 4; k++) {
				int nowX = node.x + dx[k];
				int nowY = node.y + dy[k];

				if (nowX < 0 || nowY < 0 || nowX >= M || nowY >= N) continue;

				if (!visited[nowX][nowY] && map[nowX][nowY] == 0) {
					visited[nowX][nowY] = true;
					queue.offer(new Node(nowX, nowY, node.cnt));
				}

				if (!visited[nowX][nowY] && map[nowX][nowY] == 1) {
					visited[nowX][nowY] = true;
					queue.offer(new Node(nowX, nowY, node.cnt + 1));
				}

			}
		}

		return 0;

	}
}