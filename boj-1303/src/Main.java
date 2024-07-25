import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int x;
	int y;
	public Node(int x,int y){
		this.x = x;
		this.y= y;
	}

}

public class Main {

	static int col, row;
	static char[][] map;
	static boolean[][] visitied;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int ourPower=0, yourPower=0;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		col = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		map = new char[row][col];
		visitied = new boolean[row][col];

		for (int i = 0; i < row; i++) {
			String s = br.readLine();
			for (int j = 0; j < col; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (!visitied[i][j]) {
					int temp = getPower(i,j);
					if(map[i][j] == 'W'){
						ourPower += temp*temp;
					} else{
						yourPower += temp*temp;
					}
				}
			}
		}

		System.out.print(ourPower + " " + yourPower);

		br.close();

	}

	static int getPower(int x, int y) {

		Queue<Node> queue = new LinkedList<>();

		queue.offer(new Node(x,y));
		visitied[x][y] = true;

		int cnt = 1;

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			for (int k = 0; k < 4; k++) {

				int nowX = node.x + dx[k];
				int nowY = node.y + dy[k];

				if (nowX >= 0 && nowY >= 0 && nowX < row && nowY < col && !visitied[nowX][nowY]) {
					if(map[x][y] == map[nowX][nowY]){ //넣은 좌표의 값과 새로 넣을 좌표의 값이 다르다면 다른 진영이다.
						visitied[nowX][nowY] = true;
						cnt++;
						queue.offer(new Node(nowX,nowY));
					}
				}

			}
		}

		return cnt;
	}

}