import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int OFF = 0, ON = 1;
	static int N, M;
	static Node[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int ans = 0;
	static Queue<int[]> queue = new LinkedList<>();

	static boolean[][] check;
	//약속! 항상 큐에 넣을 떄 체크해주자.

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Node[N][N];
		check = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Node();
				map[i][j].addStatus(OFF);
			}
		}
		map[0][0].addStatus(ON);
		//end of stats

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			//(x, y)방에서 (a, b)방의 불을 켜고 끌 수 있다.
			map[x][y].addToNode(a, b);
		}//end of input


		queue.add(new int[] {0, 0});
		check[0][0] = true;
		bfs();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].getStatus() == ON) ans++;
				//System.out.print(map[i][j].getStatus() + " ");
			}
			//System.out.println();
		}

		System.out.println(ans);
		br.close();
	}

	private static void bfs() {

		//현재 상황에서 켤 수 있는 모든 스위치를 키고 난 다음에
		//갈 수 있는 좌표내에서 ON인 상태면 간다.

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			//불을 켤 수 있는 노드는 다 킨다.

			ArrayList<int[]> toNodes = map[now[0]][now[1]].getToNode();

			for (int[] n : toNodes) {

				if (map[n[0]][n[1]].getStatus() == ON) continue;
				//불 켜져있으면 안 켜도 된다.

				map[n[0]][n[1]].addStatus(ON);

				//불이 켜진 방을 현재까지 왔던 경로를 이용해서 들어갈 수 있다면 그 방의 좌표를 큐에 넣는다.
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (check[i][j]) { //현재까지 왔던 경로에서 인접한 방을 탐색해서
							for (int k = 0; k < 4; k++) {
								int nowX = i + dx[k];
								int nowY = j + dy[k];

								if (nowX < 0 || nowY < 0 || nowX >= N || nowY >= N) continue;

								//불 켠 곳을 갈 수 있다면
								if (nowX == n[0] && nowY == n[1]) {
									queue.add(new int[] {nowX, nowY});
									check[nowX][nowY] = true;  // 방문 처리
								}

							}
						}
					}
				}

			}

			for (int k = 0; k < 4; k++) {
				int nowX = now[0] + dx[k];
				int nowY = now[1] + dy[k];

				if (nowX < 0 || nowY < 0 || nowX >= N || nowY >= N || check[nowX][nowY]) continue;

				if (map[nowX][nowY].getStatus() == ON) {
					queue.add(new int[] {nowX, nowY});
					check[nowX][nowY] = true;
				}
			}

		}

	}

	static class Node {
		int s;
		ArrayList<int[]> A = new ArrayList<>();

		public void addStatus(int s) {
			this.s = s;
		}

		public void addToNode(int a, int b) {
			this.A.add(new int[] {a, b});
		}

		public ArrayList<int[]> getToNode() {
			return A;
		}

		public int getStatus() {
			return s;
		}

	}


}