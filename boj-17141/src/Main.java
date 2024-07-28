import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//바이러스 연구소
class Node {
	int x;
	int y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int N, M;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int MIN = Integer.MAX_VALUE;

	//Node 로 선언한 부분
	static List<Node> list = new ArrayList<>(); //바이러스가 들어갈 수 있는 좌표가 들어가있는 리스트
	static Node[] virus; //바이러스가 간 곳을 저장하는 배열


	//0:빈칸 1:벽 2:바이러스를 놓을 수 있는 칸
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		virus = new Node[M]; //바이러스는 M개가 들어갈 수 있다.

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					list.add(new Node(i, j)); //바이러스를 놓을 수 있는 모든 좌표를 list에 삽입한다.
				}
			}
		}

		getVirusCases(0, 0); //바이러스 좌표를 모두 찾는 코드

		if (MIN == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}

		System.out.println(MIN);

	}

	//바이러스가 놓여질 수 있는 모든 경우의 수를 구한다.
	static void getVirusCases(int start, int depth) {

		if (depth == M) {

			int time = getMinTime();
			MIN = Math.min(MIN, time);

			return;
		}

		//바이러스가 갈 수 있는 모든 곳을 저장한 리스트에서
		for (int i = start; i < list.size(); i++) {
			virus[depth] = list.get(i); //하나의 좌표를 불러와서 virus의 Node 배열 좌표에 넣는다.
			getVirusCases(i + 1, depth + 1);
		}

	}

	//바이러스가 다 놓아졌을 경우 bfs로 모든 바이러스가 퍼져나가는 시간을 구한다.
	static int getMinTime() {

		//각 경우마다 vistied 판별을 해야하므로 함수 안에 선언한다.
		int[][] visited = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					visited[i][j] = -2;
				} else {
					visited[i][j] = -1;
				}
			}
		}

		Queue<Node> queue = new LinkedList<>();

		//바이러스가 놓여진 각 좌표에 대해서 0으로 바꾸준다.
		for (int i = 0; i < M; i++) {
			int x = virus[i].x;
			int y = virus[i].y;
			visited[x][y] = 0;
			queue.offer(new Node(x, y));
		}

		while (!queue.isEmpty()) {

			Node node = queue.poll();

			for (int k = 0; k < 4; k++) {
				int nowX = dx[k] + node.x;
				int nowY = dy[k] + node.y;

				if (nowX >= 0 && nowY >= 0 && nowX < N && nowY < N && visited[nowX][nowY] != -2) {
					if (visited[nowX][nowY] == -1) { //아직 방문 안 한 경우라면 (즉, -1이라면)
						visited[nowX][nowY] = visited[node.x][node.y] + 1; //바이러스에서 +1 을 해준다.
						queue.offer(new Node(nowX, nowY));
					}
				}
			}
		}

		int result = 0;

		//바이러스가 끝까지 퍼져나갔을 때의 시간을 구한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == -1) { //바이러스가 한 번이라도 도달 못한 경우가 있을 경우라면
					result = Integer.MAX_VALUE;
				} else {
					result = Math.max(result, visited[i][j]);
				}
			}
		}


		return result;

	}
}
