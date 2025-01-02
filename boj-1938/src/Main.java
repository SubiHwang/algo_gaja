import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int N;
	static char[][] maps;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static boolean[][][] check;
	static Queue<int[]> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		maps = new char[N][N];
		check = new boolean[2][N][N];

		ArrayList<Node> end = new ArrayList<>();


		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				maps[i][j] = s.charAt(j);
				if (maps[i][j] == 'B') {
					queue.add(new int[] {i, j, 0}); //일단 시작점 저장
				} else if (maps[i][j] == 'E') {
					end.add(new Node(i, j));
				}
			}
		}

		int ans = 0;

		while (!queue.isEmpty()) {

			ArrayList<Node> A = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				int[] now = queue.poll();
				A.add(new Node(now[0], now[1])); //세 점 저장
				ans = now[2];
			}

			int finalCnt = 0;
			for (int i = 0; i < 3; i++) {
				if (A.get(i).x == end.get(i).x && A.get(i).y == end.get(i).y) {
					finalCnt++;
				}
			}

			if (finalCnt == 3) {
				System.out.println(ans);
				return;
			}

			//이동시켜서 갈 수 있는 범위인지 확인
			if (A.get(0).y == A.get(2).y) { //세로인 상태

				for (int k = 0; k < 5; k++) {

					ArrayList<Node> nextA = new ArrayList<>();
					boolean flag = true; //cnt==3 말고 flag로 가능하다.

					for (int num = 0; num < 3; num++) {

						int nowX = A.get(num).x;
						int nowY = A.get(num).y;

						if (k < 4) {

							nowX += dx[k];
							nowY += dy[k];

						} else {

							int midX = A.get(1).x;
							int midY = A.get(1).y;

							if (canRotate(midX, midY)) { //회전하는게 가능한지 판별
								nowX = midX;
								nowY = midY + num - 1; //222이라면 2+0-1, 2+1-1, 2+2-1
							} else {
								flag = false;
								break;
							}
						}

						//B,E,0일 때에 모두 이동가능하다.
						if (nowX < 0 || nowY < 0 || nowX >= N || nowY >= N || maps[nowX][nowY] == '1') {
							flag = false;
							break;
						}
						//여기까지 도달했다는 말은 내가 상하좌우회전으로 올 수 있는 좌표라는 것
						nextA.add(new Node(nowX, nowY));
					}

					//세 개 다 통과해야 flag == true가 된다.
					if (flag) {
						int dir = (k < 4 ? 0 : 1);//90도 회전하면 가로! 가로는 1
						if (check[dir][nextA.get(1).x][nextA.get(1).y]) continue;
						check[dir][nextA.get(1).x][nextA.get(1).y] = true;
						for (Node n : nextA) {
							queue.add(new int[] {n.x, n.y, ans + 1});
						}

					}
					nextA.clear();

				}


			} else { //가로인 상태

				for (int k = 0; k < 5; k++) {

					ArrayList<Node> nextA = new ArrayList<>();
					boolean flag = true; //cnt==3 말고 flag로 가능하다.

					for (int num = 0; num < 3; num++) {

						int nowX = A.get(num).x;
						int nowY = A.get(num).y;

						if (k < 4) {

							nowX += dx[k];
							nowY += dy[k];

						} else {

							int midX = A.get(1).x;
							int midY = A.get(1).y;

							if (canRotate(midX, midY)) { //회전하는게 가능한지 판별
								nowX = midX + num - 1; //123 -> 2+0-1, 2+1-1, 2+2-1
								nowY = midY;
							} else {
								flag = false;
								break;
							}
						}

						//B,E,0일 때에 모두 이동가능하다.
						if (nowX < 0 || nowY < 0 || nowX >= N || nowY >= N || maps[nowX][nowY] == '1') {
							flag = false;
							break;
						}
						//여기까지 도달했다는 말은 내가 상하좌우회전으로 올 수 있는 좌표라는 것
						nextA.add(new Node(nowX, nowY));
					}

					//세 개 다 통과해야 flag == true가 된다.
					if (flag) {
						int dir = (k < 4 ? 1 : 0);
						if (check[dir][nextA.get(1).x][nextA.get(1).y]) continue;
						check[dir][nextA.get(1).x][nextA.get(1).y] = true;
						for (Node n : nextA) {
							queue.add(new int[] {n.x, n.y, ans + 1});
						}

					}
					nextA.clear();

				}

			}


			A.clear();

		}

		System.out.println(0);


	}

	static boolean canRotate(int midX, int midY) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int nx = midX + i;
				int ny = midY + j;
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || maps[nx][ny] == '1')
					return false;
			}
		}
		return true;
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}