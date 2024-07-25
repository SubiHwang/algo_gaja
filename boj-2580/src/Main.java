import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//스도쿠

	static int[][] map = new int[9][9];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		getFinalNumber(0, 0);

	}

	static void getFinalNumber(int x, int y) { //탐색 시작

		//다음 행 실행
		if (y == 9) {
			getFinalNumber(x + 1, 0);
			return;
		}

		if (x == 9) { //모든 map이 다 채워졌을 경우 map 출력
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.exit(0);
		}

		//탐색
		if (map[x][y] == 0) {
			for (int k = 1; k <= 9; k++) { //K는 1-9까지로 설정!
				if (isP(x, y, k)) { //행,열,대각선이 모두 만족할 경우
					map[x][y] = k; //자리를 값으로 채운다.
					getFinalNumber(x, y + 1);
				}
			}
			map[x][y] = 0; //백트래킹
			//isP하다면 다음 열로 넘어가지만 isP하지 않다면 0으로 바꾸고 이전 단계로 돌아간다.
			return;
		}

		getFinalNumber(x, y + 1);

	}

	static boolean isP(int row, int col, int num) {

		for (int k = 0; k < 9; k++) {
			if (map[row][k] == num || map[k][col] == num) { //1. 행과 열 판단
				return false;
			}
		}

		//2. 대각선 판단
		// num이 포환됨 3*3의 첫 시작 좌표
		int nowRow = (row / 3) * 3;
		int nowCol = (col / 3) * 3;

		for (int i = nowRow; i < nowRow + 3; i++) {
			for (int j = nowCol; j < nowCol + 3; j++) {
				if (map[i][j] == num) {
					return false;
				}
			}
		}

		return true;
	}


}